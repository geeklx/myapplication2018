package com.example.p006_recycleview_alluses.viewholders.demo3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.librecycleview.ItemViewBinder;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo3.ItemDemo3;
import com.example.shining.libutils.utilslib.etc.ToastUtil;

import java.util.List;

/**
 * Created by shining on 2018/3/21.
 */

public class ItemDemo3Binder extends ItemViewBinder<ItemDemo3,ItemDemo3Binder.ViewHolder> {


    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.rec_demo3_item_text,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemDemo3 item) {
        //赋值部分
        holder.firstText.setText(item.getContent1());
        holder.endText.setText("currentTimeMillis: " + System.currentTimeMillis());
        holder.item = item;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemDemo3 item, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()){
            super.onBindViewHolder(holder, item, payloads);
        }else{
            holder.firstText.setText("Just update the first text: " + payloads.get(0));
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        TextView firstText;
        TextView endText;
        ItemDemo3 item;

        public ViewHolder(View itemView) {
            super(itemView);
            firstText = itemView.findViewById(R.id.first_text);
            endText = itemView.findViewById(R.id.end_text);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ToastUtil.showToastCenter("Update with a payload");
            getAdapter().notifyItemChanged(getAdapterPosition(), "la la la (payload)");
        }

        @Override
        public boolean onLongClick(View v) {
            ToastUtil.showToastCenter("Full update");
            item.setContent1("full full full");
            getAdapter().notifyItemChanged(getAdapterPosition());
            return true;
        }
    }
}
