package com.example.p006_recycleview_alluses.viewholders.demo7;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo7.ItemDemo7ContentImg;

public  class ItemDemo7SimpleImageViewBinder extends ItemDemo7FrameBinder<ItemDemo7ContentImg, ItemDemo7SimpleImageViewBinder.ViewHolder> {

    @Override
    protected ItemDemo7ContentBinder onCreateNewViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.rec_demo7_item_simple_image, parent, false));
    }

    @Override
    protected void onBindNewViewHolder(@NonNull ViewHolder viewHolder, @NonNull ItemDemo7ContentImg itemDemo7ContentImg) {
        Log.d("weibo", "getItemId: " + viewHolder.getItemId());
        Log.d("weibo", "getItemViewType: " + viewHolder.getItemViewType());
        Log.d("weibo", "getAdapterPosition: " + viewHolder.getAdapterPosition());
        Log.d("weibo", "getLayoutPosition: " + viewHolder.getLayoutPosition());
        Log.d("weibo", "getOldPosition: " + viewHolder.getOldPosition());
        Log.d("weibo", "isRecyclable: " + viewHolder.isRecyclable());
        viewHolder.simpleImage.setImageResource(itemDemo7ContentImg.resId);
    }

//    @Override
//    protected void onBindContentViewHolder(@NonNull ViewHolder viewHolder, @NonNull ItemDemo7ContentImg itemDemo7ContentImg) {
//        Log.d("weibo", "getAdapterPosition: " + viewHolder.getAdapterPosition());
//        Log.d("weibo", "getLayoutPosition: " + viewHolder.getLayoutPosition());
//        Log.d("weibo", "getOldPosition: " + viewHolder.getOldPosition());
//        Log.d("weibo", "isRecyclable: " + viewHolder.isRecyclable());
//        viewHolder.simpleImage.setImageResource(itemDemo7ContentImg.getResId());
//    }

    static class ViewHolder extends ItemDemo7ContentBinder {

        private ImageView simpleImage;

        ViewHolder(View itemView) {
            super(itemView);
            simpleImage = itemView.findViewById(R.id.simple_image);
        }
    }
}
