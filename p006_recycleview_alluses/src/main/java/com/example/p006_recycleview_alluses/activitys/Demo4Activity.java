package com.example.p006_recycleview_alluses.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.libanimation.toasts.Toasty;
import com.example.librecycleview.Items;
import com.example.librecycleview.MultiTypeAdapter;
import com.example.librecycleview.MultiTypeAsserts;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo4.ItemDemo4;
import com.example.p006_recycleview_alluses.models.demo4.ItemHeadDemo4;
import com.example.p006_recycleview_alluses.viewholders.demo4.ItemDemo4Binder;
import com.example.p006_recycleview_alluses.viewholders.demo4.ItemDemo4HeadBinder;

import java.util.ArrayList;
import java.util.List;

public class Demo4Activity extends AppCompatActivity {

    private static final int SPAN_COUNT = 5;
    private MultiTypeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Button btn1;
    Items items = new Items();
//    private TreeSet<ItemDemo4> selectedSet = new TreeSet<>();
    private List<ItemDemo4> selectedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_demo4);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        loadData();
        MultiTypeAsserts.assertHasTheSameAdapter(mRecyclerView, mAdapter);
        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
        MultiTypeAsserts.assertAllRegistered(mAdapter, items);
    }

    private void loadData() {
        ItemHeadDemo4 spacialCategory = new ItemHeadDemo4("特别篇", "1");
        items.add(spacialCategory);
        for (int i = 0; i < 7; i++) {
            items.add(new ItemDemo4(i + 1, false));
        }
        ItemHeadDemo4 currentCategory = new ItemHeadDemo4("本篇", "2");
        items.add(currentCategory);
        for (int i = 7; i < 100; i++) {
            items.add(new ItemDemo4(i + 1, false));
        }
    }

    private void onclick() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder content = new StringBuilder();
                for (ItemDemo4 number : selectedList) {
                    content.append(number.getContent1()).append(" ");
                }
//                ToastUtil.showToastCenter("Selected items: " + content);
                Toasty.normal(Demo4Activity.this,"Selected items: " + content).show();
            }
        });

    }

    private void findview() {
        mRecyclerView = findViewById(R.id.list);
        btn1 = findViewById(R.id.btn1);

        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT, OrientationHelper.VERTICAL, false);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {

            @Override
            public int getSpanSize(int position) {
                if (items.get(position) instanceof ItemHeadDemo4) {
                    return SPAN_COUNT;
                } else {
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MultiTypeAdapter();
        mAdapter.register(ItemHeadDemo4.class, new ItemDemo4HeadBinder());
//        mAdapter.register(ItemDemo4.class, new ItemDemo4Binder(selectedSet));
        mAdapter.register(ItemDemo4.class, new ItemDemo4Binder(selectedList));

        mRecyclerView.setAdapter(mAdapter);

    }
}
