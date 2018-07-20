package com.example.p006_recycleview_alluses.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.librecycleview.Items;
import com.example.librecycleview.MultiTypeAdapter;
import com.example.librecycleview.MultiTypeAsserts;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo1.ItemDemo1;
import com.example.p006_recycleview_alluses.viewholders.demo1.ItemDemo1Viewholder;

public class Demo1Activity extends AppCompatActivity {

    private MultiTypeAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_demo1);
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1, OrientationHelper.VERTICAL,false));
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);

        Items items = new Items();//Items 等同于 ArrayList<Object>
        mAdapter = new MultiTypeAdapter();
        mAdapter.register(ItemDemo1.class,new ItemDemo1Viewholder("我是从Activity传进去的值"));
        mRecyclerView.setAdapter(mAdapter);
        MultiTypeAsserts.assertHasTheSameAdapter(mRecyclerView,mAdapter);

        for (int i = 0; i < 20; i++) {
            items.add(new ItemDemo1(i+"","url:"+i));
        }
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
        MultiTypeAsserts.assertAllRegistered(mAdapter,items);
    }
}
