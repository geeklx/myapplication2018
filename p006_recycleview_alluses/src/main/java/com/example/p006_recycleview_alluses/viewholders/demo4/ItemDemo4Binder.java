package com.example.p006_recycleview_alluses.viewholders.demo4;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.librecycleview.ItemViewBinder;
import com.example.p006_recycleview_alluses.R;
import com.example.p006_recycleview_alluses.models.demo4.ItemDemo4;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by shining on 2018/3/22.
 */

public class ItemDemo4Binder extends ItemViewBinder<ItemDemo4, ItemDemo4Binder.ViewHolder> {

    private List<ItemDemo4> selectedList;
    private Set<HashMap<String, ItemDemo4>> selectedSet;

    public Set<HashMap<String, ItemDemo4>> getSelectedSet() {
        return selectedSet;
    }

    public List<ItemDemo4> getSelectedList() {
        return selectedList;
    }

    public ItemDemo4Binder(Set<HashMap<String, ItemDemo4>> selectedSet) {
        this.selectedSet = selectedSet;
    }

    public ItemDemo4Binder(List<ItemDemo4> selectedList) {
        this.selectedList = selectedList;
    }

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.rec_demo4_item_text, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull ItemDemo4 item) {
        holder.itemDemo4 = item;
        holder.tv1.setText(item.getContent1() + "");
        holder.tv1.setSelected(item.isEnselected());
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv1;
        private ItemDemo4 itemDemo4;

        public ViewHolder(final View itemView) {
            super(itemView);
            tv1 = itemView.findViewById(R.id.tv1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv1.setSelected(itemDemo4.enselected = !itemDemo4.enselected);
                    //取值bufen
                    if (itemDemo4.enselected) {
                        selectedList.add(itemDemo4);
                    } else {
                        selectedList.remove(itemDemo4);
                    }
                }
            });
        }
    }
}
