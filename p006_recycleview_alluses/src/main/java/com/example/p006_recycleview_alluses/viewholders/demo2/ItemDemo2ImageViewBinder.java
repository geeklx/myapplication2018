package com.example.p006_recycleview_alluses.viewholders.demo2;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.librecycleview.ItemViewBinder;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo2.ItemDemo21;

public class ItemDemo2ImageViewBinder extends ItemViewBinder<ItemDemo21, ItemDemo2ImageViewBinder.ImageHolder> {

  class ImageHolder extends RecyclerView.ViewHolder {
    private @NonNull
    final ImageView image;
    ImageHolder(View itemView) {
      super(itemView);
      image = itemView.findViewById(R.id.image);
    }
  }

  @Override
  protected @NonNull
  ImageHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View root = inflater.inflate(R.layout.rec_demo2_item_image, parent, false);
    return new ImageHolder(root);
  }


  @Override
  protected void onBindViewHolder(@NonNull ImageHolder holder, @NonNull ItemDemo21 imageContent) {
    holder.image.setImageResource(imageContent.getContent2());
    setAnimation(holder.image,holder.getAdapterPosition());
  }

  private int lastShownAnimationPosition;

  private void setAnimation(@NonNull View viewToAnimate, int position) {
    if (position > lastShownAnimationPosition) {
      Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), android.R.anim.slide_in_left);
      viewToAnimate.startAnimation(animation);
      lastShownAnimationPosition = position;
    }
  }

  @Override
  public void onViewDetachedFromWindow(@NonNull ImageHolder holder) {
    holder.itemView.clearAnimation();
  }
}
