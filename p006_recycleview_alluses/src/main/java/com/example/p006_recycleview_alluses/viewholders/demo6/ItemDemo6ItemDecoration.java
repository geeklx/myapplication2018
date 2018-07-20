package com.example.p006_recycleview_alluses.viewholders.demo6;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemDemo6ItemDecoration extends RecyclerView.ItemDecoration {

  private int space;
  private @NonNull
  SpanSizeLookup spanSizeLookup;


  public ItemDemo6ItemDecoration(int space, @NonNull SpanSizeLookup spanSizeLookup) {
    this.space = space;
    this.spanSizeLookup = spanSizeLookup;
  }


  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    int position = parent.getChildLayoutPosition(view);
    if (spanSizeLookup.getSpanSize(position) == 1) {
      outRect.left = space;
      if (position % 2 == 0) {
        outRect.right = space;
      }
    }
  }
}
