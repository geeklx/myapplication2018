package com.example.p006_recycleview_alluses.viewholders.demo7;

import android.view.View;

/**
 * Created by shining on 2018/3/26.
 */

public class ItemDemo7ContentBinder {
    public ItemDemo7FrameBinder.FrameHolder frameHolder;
    public final View itemView;

    public ItemDemo7ContentBinder(View itemView) {
        this.itemView = itemView;
    }

    public ItemDemo7FrameBinder.FrameHolder getParent() {
        return frameHolder;
    }

    public final long getItemId() {
        return getParent().getItemId();
    }

    public final int getItemViewType() {
        return getParent().getItemViewType();
    }

    public final int getAdapterPosition() {
        return getParent().getAdapterPosition();
    }

    public final int getLayoutPosition() {
        return getParent().getLayoutPosition();
    }

    public final int getOldPosition() {
        return getParent().getOldPosition();
    }

    public final boolean isRecyclable() {
        return getParent().isRecyclable();
    }

    public final void setIsRecyclable(boolean recyclable) {
        getParent().setIsRecyclable(recyclable);
    }
}
