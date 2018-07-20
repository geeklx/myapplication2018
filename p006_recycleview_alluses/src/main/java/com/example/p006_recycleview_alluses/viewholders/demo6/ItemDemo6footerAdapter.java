package com.example.p006_recycleview_alluses.viewholders.demo6;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.libanimation.toasts.Toasty;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo6.ItemDemo621;

import java.util.Collections;
import java.util.List;

/**
 * Created by shining on 2018/3/26.
 */

public class ItemDemo6footerAdapter extends RecyclerView.Adapter<ItemDemo6footerAdapter.ViewHolder>{

    private List<ItemDemo621> itemDemo621s = Collections.emptyList();

    public void setData(@NonNull List<ItemDemo621> itemDemo621s) {
        this.itemDemo621s = itemDemo621s;
    }
    
    @Override
    public int getItemCount() {
        return itemDemo621s.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_demo6_item_horizontal_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ItemDemo621 ItemDemo621 = itemDemo621s.get(position);
        holder.iv1.setImageResource(ItemDemo621.getContent1());
        holder.tv1.setText(ItemDemo621.getContent2());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.normal(v.getContext(),String.valueOf(position)).show();
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        
        private ImageView iv1;
        private TextView tv1;

        public ViewHolder(View itemView) {
            super(itemView);
            iv1 = itemView.findViewById(R.id.iv1);
            tv1 = itemView.findViewById(R.id.tv1);
//            Toasty.normal(v.getContext(),String.valueOf(getAdapterPosition())).show();
        }
    }
}
