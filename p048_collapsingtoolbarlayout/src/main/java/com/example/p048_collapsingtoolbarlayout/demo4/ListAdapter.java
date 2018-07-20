package com.example.p048_collapsingtoolbarlayout.demo4;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.p048_collapsingtoolbarlayout.R;

import java.util.*;

/**
 * Created by @vitovalov on 30/9/15.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    List<String> mListData;

    public ListAdapter(List<String> mListData) {
        this.mListData = mListData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main4_list_item,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(mListData.get(i));
    }

    @Override
    public int getItemCount() {
        return mListData == null ? 0 : mListData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.listitem_name);
        }
    }

}

