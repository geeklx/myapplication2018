package com.geeklx.libglide38.base;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

public class SquareImageView extends AppCompatImageView {

    public SquareImageView(Context context) {
        this(context, null);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthSize, widthSize);
    }
}
