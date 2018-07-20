package com.example.p016_glide47_quan.liebiao.demo4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class RecycleAdapter24 extends RecyclerView.Adapter<RecycleAdapter24.ViewHolder> {
    private LayoutInflater inflater;
    private Context context;
    private List<Biaoge_listBean> mratings;
    private boolean enallchoose = false;// 是否全选
    private List<Biaoge_listBean> getchooselist = new ArrayList<>();

    //获取所有foodid List
    public List<Biaoge_listBean> list_getall() {
        return getchooselist;
    }

    //获取所有id String 方法 bufen
    public String list_getallstring(List<Biaoge_listBean> ids) {
        if (getchooselist == null || getchooselist.size() == 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (Biaoge_listBean id : ids) {
            if (i == 0) {
                result.append(id.getText_content1());
            } else {
                result.append("," + id.getText_content1());
            }
            i++;
        }
        return result.toString();
    }

    //清除所有foodid String
    public void list_clearall() {
        if (getchooselist != null || getchooselist.size() > 0) {
            getchooselist.clear();
        }
    }

    //单选 add
    public void list_addone(Biaoge_listBean id) {
        getchooselist.add(id);
    }

    //单选 remove
    public void list_removeone(Biaoge_listBean id) {
        getchooselist.remove(id);
    }

    public boolean isEnallchoose() {
        return enallchoose;
    }

    public void setEnallchoose(boolean enallchoose) {
        this.enallchoose = enallchoose;
    }

    public RecycleAdapter24(Context context) {
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
        View view = inflater.inflate(R.layout.activity_main_glide_list_item24, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.ll_vis = view.findViewById(R.id.ll_vis);
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
        if (onItemViewClick3!=null){
            onItemViewClick3.onclick3(list_getall()!=null&&list_getall().size()>0);
        }
        if (!isEnallchoose()) {
            // 未全选
            viewHolder.ll_vis.setVisibility(View.GONE);
        } else {
            // 全选
            viewHolder.ll_vis.setVisibility(View.VISIBLE);
        }
        if (!ratings.isEnchoose()) {
            viewHolder.iv_icon.setBackgroundResource(R.drawable.iv_sku_unchecked);
        } else {
            viewHolder.iv_icon.setBackgroundResource(R.drawable.iv_sku_checked);
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
                    onItemViewClick2.onclick2(viewHolder.iv_icon, position,ratings.isEnchoose());
                }
            }
        });
    }

    public OnItemViewClick onItemViewClick;// 长按
    public OnItemViewClick2 onItemViewClick2;// 单个选择
    public OnItemViewClick3 onItemViewClick3;// 是否全选回调

    public void setOnItemViewClick(OnItemViewClick onItemViewClick) {
        this.onItemViewClick = onItemViewClick;
    }

    public interface OnItemViewClick {
        void onclick(View view, int pos, String id);
    }

    public void setOnItemViewClick2(OnItemViewClick2 onItemViewClick2) {
        this.onItemViewClick2 = onItemViewClick2;
    }

    public interface OnItemViewClick2 {
        void onclick2(View view, int pos,boolean enchoose);
    }

    public void setOnItemViewClick3(OnItemViewClick3 onItemViewClick3) {
        this.onItemViewClick3 = onItemViewClick3;
    }

    public interface OnItemViewClick3 {
        void onclick3(boolean engougou);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_content_tag1;//
        private GlideImageView iv1;//
        private LinearLayout ll_vis;//
        private ImageView iv_icon;//

        ViewHolder(View view) {
            super(view);
        }
    }
}