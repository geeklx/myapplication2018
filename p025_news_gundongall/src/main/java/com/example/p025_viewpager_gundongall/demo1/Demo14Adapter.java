package com.example.p025_viewpager_gundongall.demo1;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.p025_viewpager_gundongall.R;
import com.gongwen.marqueen.MarqueeFactory;

public class Demo14Adapter extends MarqueeFactory<RelativeLayout, Demo14bean> {
    private LayoutInflater inflater;

    public Demo14Adapter(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RelativeLayout generateMarqueeItemView(Demo14bean data) {
        RelativeLayout mView = (RelativeLayout) inflater.inflate(R.layout.activity_main1_item, null);
        ((TextView) mView.findViewById(R.id.title)).setText(data.getTitle());
        ((TextView) mView.findViewById(R.id.secondTitle)).setText(data.getSecondTitle());
        ((TextView) mView.findViewById(R.id.time)).setText(data.getTime());
        return mView;
    }
}