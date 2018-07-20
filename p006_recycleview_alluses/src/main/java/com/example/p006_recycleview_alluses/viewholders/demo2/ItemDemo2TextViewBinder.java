package com.example.p006_recycleview_alluses.viewholders.demo2;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.librecycleview.ItemViewBinder;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo2.ItemDemo2;

public class ItemDemo2TextViewBinder extends ItemViewBinder<ItemDemo2, ItemDemo2TextViewBinder.TextHolder> {

  private int lastShownAnimationPosition;


  static class TextHolder extends RecyclerView.ViewHolder {

    private @NonNull
    final TextView text;


    TextHolder(@NonNull View itemView) {
      super(itemView);
      this.text = itemView.findViewById(R.id.text);
    }
  }


  @Override
  protected @NonNull
  TextHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.rec_demo2_item_text, parent, false);
    return new TextHolder(root);
  }


  @Override
  @SuppressLint("SetTextI18n")
  protected void onBindViewHolder(@NonNull TextHolder holder, @NonNull ItemDemo2 textItem) {
    holder.text.setText("hello: " + textItem.getContent1());
    // should show animation, ref: https://github.com/drakeet/MultiType/issues/149
    setAnimation(holder.itemView, holder.getAdapterPosition());
  }

  private void setAnimation(@NonNull View viewToAnimate, int position) {
    if (position > lastShownAnimationPosition) {
      Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
      viewToAnimate.startAnimation(animation);
      lastShownAnimationPosition = position;
    }
  }

  @Override
  public void onViewDetachedFromWindow(@NonNull TextHolder holder) {
    holder.itemView.clearAnimation();
  }
}
