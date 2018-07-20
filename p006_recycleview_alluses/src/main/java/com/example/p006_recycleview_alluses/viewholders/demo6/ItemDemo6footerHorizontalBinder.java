package com.example.p006_recycleview_alluses.viewholders.demo6;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.librecycleview.ItemViewBinder;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo6.ItemDemo62;
import com.example.p006_recycleview_alluses.models.demo6.ItemDemo621;

import java.util.List;

/**
 * Created by shining on 2018/3/26.
 */

public class ItemDemo6footerHorizontalBinder extends ItemViewBinder<ItemDemo62, ItemDemo6footerHorizontalBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.rec_demo6_item_horizontal_list, parent, false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemDemo62 item) {
        holder.setdata(item.getContent1());
        assertGetAdapterNonNull();
    }

    private void assertGetAdapterNonNull() {
        // noinspection ConstantConditions
        if (getAdapter() == null) {
            throw new NullPointerException("getAdapter() == null");
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;
        private ItemDemo6footerAdapter adapter;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.list1);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
            linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            new LinearSnapHelper().attachToRecyclerView(recyclerView);
            adapter = new ItemDemo6footerAdapter();
            recyclerView.setAdapter(adapter);
        }

        private void setdata(List<ItemDemo621> item) {
            adapter.setData(item);
            adapter.notifyDataSetChanged();
        }

    }
}
