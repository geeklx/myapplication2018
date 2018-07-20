package com.example.p016_glide47_quan.liebiao.demo1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.p016_glide47_quan.R;
import com.example.p016_glide47_quan.liebiao.Biaoge_listBean;
import com.geek.libglide47.base.GlideImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:geek
 * modification:2016年4月26日14:21:48
 */

public class RecycleAdapter2 extends RecyclerView.Adapter<RecycleAdapter2.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<Biaoge_listBean> mratings;
    private int selectedPosition = -1;// 选中的位置

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public RecycleAdapter2(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        mratings = new ArrayList<Biaoge_listBean>();
    }

    public void setContacts(List<Biaoge_listBean> ratings) {
        this.mratings = ratings;
    }

    public void addConstacts(List<Biaoge_listBean> ratings) {
        this.mratings.addAll(ratings);
    }

    public List<Biaoge_listBean> getMratings() {
        return mratings;
    }

    @Override
    public int getItemCount() {
        if (mratings == null)
            return 0;
        return mratings.size();
    }

    public Object getItem(int position) {
        return mratings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_main_glide_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.iv_icon = view.findViewById(R.id.iv_icon);
        viewHolder.iv1 = view.findViewById(R.id.iv1);
        viewHolder.tv_content_tag1 = view.findViewById(R.id.tv_content_tag1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        final Biaoge_listBean ratings = mratings.get(position);
//        viewHolder.tv_content_tag1.setText(ratings.getText_content());
        viewHolder.iv1.loadImage(ratings.getText_content2(), R.drawable.ic_def_loading);
        if (selectedPosition != position) {
            // 未选中
            viewHolder.iv_icon.setVisibility(View.GONE);
        } else {
            // 选中
            viewHolder.iv_icon.setVisibility(View.VISIBLE);
        }

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemViewClick != null) {
                    onItemViewClick.onclick(viewHolder.iv1, position, ratings.getText_content1());
                }
                return true;
            }
        });
        viewHolder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemViewClick2 != null) {
                    onItemViewClick2.onclick2(viewHolder.iv_icon, position);
                }
            }
        });
    }

    public OnItemViewClick onItemViewClick;

    public void setOnItemViewClick(OnItemViewClick onItemViewClick) {
        this.onItemViewClick = onItemViewClick;
    }

    public interface OnItemViewClick {
        void onclick(View view, int pos, String id);
    }

    public OnItemViewClick2 onItemViewClick2;

    public void setOnItemViewClick2(OnItemViewClick2 onItemViewClick2) {
        this.onItemViewClick2 = onItemViewClick2;
    }

    public interface OnItemViewClick2 {
        void onclick2(View view, int pos);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_content_tag1;//
        private GlideImageView iv1;//
        private ImageView iv_icon;//

        ViewHolder(View view) {
            super(view);
        }
    }
}