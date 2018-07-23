package com.example.p006_recycleview_alluses.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.librecycleview.Items;
import com.example.librecycleview.MultiTypeAdapter;
import com.example.librecycleview.MultiTypeAsserts;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo6.ItemDemo6;
import com.example.p006_recycleview_alluses.models.demo6.ItemDemo61;
import com.example.p006_recycleview_alluses.models.demo6.ItemDemo62;
import com.example.p006_recycleview_alluses.models.demo6.ItemDemo621;
import com.example.p006_recycleview_alluses.viewholders.demo6.ItemDemo6ContentBinder;
import com.example.p006_recycleview_alluses.viewholders.demo6.ItemDemo6HeadBinder;
import com.example.p006_recycleview_alluses.viewholders.demo6.ItemDemo6ItemDecoration;
import com.example.p006_recycleview_alluses.viewholders.demo6.ItemDemo6footerHorizontalBinder;

import java.util.ArrayList;
import java.util.List;

public class Demo6Activity extends AppCompatActivity {

    private static final int SPAN_COUNT = 2;
    private Items items = new Items();//Items 等同于 ArrayList<Object>
    private MultiTypeAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview_demo6);
        mRecyclerView = findViewById(R.id.list);

        mAdapter = new MultiTypeAdapter();
        mAdapter.register(ItemDemo6.class, new ItemDemo6HeadBinder());
        mAdapter.register(ItemDemo61.class, new ItemDemo6ContentBinder());
        mAdapter.register(ItemDemo62.class, new ItemDemo6footerHorizontalBinder());

        GridLayoutManager layoutManager = new GridLayoutManager(this, SPAN_COUNT, OrientationHelper.VERTICAL, false);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Object item = items.get(position);
                if (item instanceof ItemDemo62 || item instanceof ItemDemo6) {
                    return SPAN_COUNT;
                } else {
                    return 1;
                }
            }
        };
        layoutManager.setSpanSizeLookup(spanSizeLookup);
        mRecyclerView.setLayoutManager(layoutManager);
//        new LinearSnapHelper().attachToRecyclerView(mRecyclerView);
        int space = getResources().getDimensionPixelSize(R.dimen.normal_space);
        mRecyclerView.addItemDecoration(new ItemDemo6ItemDecoration(space, spanSizeLookup));

        mRecyclerView.setAdapter(mAdapter);
        MultiTypeAsserts.assertHasTheSameAdapter(mRecyclerView, mAdapter);

        //databufen
        JsonData data = new JsonData();
        for (int i = 0; i < 10; i++) {
            /* You also could use Category as your CategoryItemContent directly */
            items.add(data.list_head);//ItemDemo6.class
            items.add(data.post00);// ItemDemo61.class
            items.add(data.post01);// ItemDemo61.class
            items.add(data.post10);// ItemDemo61.class
            items.add(data.post11);// ItemDemo61.class
            items.add(data.post00);// ItemDemo61.class
            items.add(new ItemDemo62(data.list_footer));// ItemDemo62.class
        }

        mAdapter.setItems(items);
        mAdapter.notifyDataSetChanged();
        MultiTypeAsserts.assertAllRegistered(mAdapter, items);
    }

    private static class JsonData {

        private static final String PREFIX = "这是一条长长的达到两行的标题文字";

        ItemDemo6 list_head = new ItemDemo6("title0", "");

        private ItemDemo61 post00 = new ItemDemo61(R.drawable.img00, PREFIX + "post00");
        private ItemDemo61 post01 = new ItemDemo61(R.drawable.img01, PREFIX + "post01");
        private ItemDemo61 post10 = new ItemDemo61(R.drawable.img02, PREFIX + "post10");
        private ItemDemo61 post11 = new ItemDemo61(R.drawable.img03, PREFIX + "post11");

        ItemDemo61[] list_content = {post00, post01, post10, post11};

        private ItemDemo621 post1 = new ItemDemo621(R.drawable.img00, PREFIX + "post00");
        private ItemDemo621 post2 = new ItemDemo621(R.drawable.img01, PREFIX + "post01");
        private ItemDemo621 post3 = new ItemDemo621(R.drawable.img02, PREFIX + "post10");
        private ItemDemo621 post4 = new ItemDemo621(R.drawable.img03, PREFIX + "post11");

        List<ItemDemo621> list_footer = new ArrayList<>();

        {
            list_footer.add(post1);
            list_footer.add(post2);
            list_footer.add(post3);
            list_footer.add(post4);
            list_footer.add(post1);
            list_footer.add(post2);
        }
    }
}
