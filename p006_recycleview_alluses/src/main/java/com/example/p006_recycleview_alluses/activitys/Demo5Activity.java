package com.example.p006_recycleview_alluses.activitys;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.librecycleview.Items;
import com.example.librecycleview.Linker;
import com.example.librecycleview.MultiTypeAdapter;
import com.example.librecycleview.MultiTypeAsserts;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo5.ItemDemo5;
import com.example.p006_recycleview_alluses.viewholders.demo5.ItemDemo5DataType1ViewBinder;
import com.example.p006_recycleview_alluses.viewholders.demo5.ItemDemo5DataType2ViewBinder;

import java.util.ArrayList;
import java.util.List;

public class Demo5Activity extends AppCompatActivity {

    private MultiTypeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    Items items = new Items();//Items 等同于 ArrayList<Object>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_demo5);
        findview();
        onclick();
        donetwork();
    }

    private void donetwork() {
        List<ItemDemo5> dataList = getDataFromService();
        mAdapter.setItems(dataList);
        mAdapter.notifyDataSetChanged();
        MultiTypeAsserts.assertAllRegistered(mAdapter, dataList);
    }

    private void onclick() {

    }

    private void findview() {
        mRecyclerView = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, OrientationHelper.VERTICAL, false));
        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);

        mAdapter = new MultiTypeAdapter();
        mAdapter.register(ItemDemo5.class).to(
                new ItemDemo5DataType1ViewBinder(),
                new ItemDemo5DataType2ViewBinder()
        ).withLinker(new Linker<ItemDemo5>() {
            @Override
            public int index(int position, @NonNull ItemDemo5 itemDemo5) {
                if (itemDemo5.getContent2() == ItemDemo5.TYPE_1) {
                    return 0;
                } else if (itemDemo5.getContent2() == ItemDemo5.TYPE_2) {
                    return 1;
                }
                return 0;
            }
        });

//        mAdapter.register(ItemDemo5.class).to(
//                new ItemDemo5DataType1ViewBinder(),
//                new ItemDemo5DataType2ViewBinder())
//                .withClassLinker(new ClassLinker<ItemDemo5>() {
//                    @NonNull
//                    @Override
//                    public Class<? extends ItemViewBinder<ItemDemo5, ?>> index(int position, @NonNull ItemDemo5 itemDemo5) {
//                        if (itemDemo5.getContent2() == ItemDemo5.TYPE_1) {
//                            return ItemDemo5DataType1ViewBinder.class;
//                        } else if (itemDemo5.getContent2() == ItemDemo5.TYPE_2) {
//                            return ItemDemo5DataType2ViewBinder.class;
//                        }
//                        return ItemDemo5DataType1ViewBinder.class;
//                    }
//                });

        mRecyclerView.setAdapter(mAdapter);
        MultiTypeAsserts.assertHasTheSameAdapter(mRecyclerView, mAdapter);
    }

    private List<ItemDemo5> getDataFromService() {
        List<ItemDemo5> list = new ArrayList<>();
        for (int i = 0; i < 30; i = i + 2) {
            list.add(new ItemDemo5("title: " + i, ItemDemo5.TYPE_1));
            list.add(new ItemDemo5("title: " + i + 1, ItemDemo5.TYPE_2));
        }
        return list;
    }
}
