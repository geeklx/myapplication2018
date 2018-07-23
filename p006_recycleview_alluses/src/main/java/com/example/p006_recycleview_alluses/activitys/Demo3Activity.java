package com.example.p006_recycleview_alluses.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.librecycleview.Items;
import com.example.librecycleview.MultiTypeAdapter;
import com.example.librecycleview.MultiTypeAsserts;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo3.ItemDemo3;
import com.example.p006_recycleview_alluses.viewholders.demo3.ItemDemo3Binder;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class Demo3Activity extends AppCompatActivity {

    private MultiTypeAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_demo3);
        mRecyclerView = findViewById(R.id.list);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
//        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);

        mAdapter = new MultiTypeAdapter();
        mAdapter.register(ItemDemo3.class,new ItemDemo3Binder());
        mRecyclerView.setAdapter(mAdapter);
        MultiTypeAsserts.assertHasTheSameAdapter(mRecyclerView,mAdapter);

        Items items = new Items();//Items 等同于 ArrayList<Object>
        for (int i = 0; i < 30; i++) {
            items.add(new ItemDemo3("1000" + i,i+""));
        }
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
        MultiTypeAsserts.assertAllRegistered(mAdapter,items);
    }
}
