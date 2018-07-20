package com.example.shining.libutils.utilslib.etc;

import android.view.View;
import android.view.ViewGroup;


public class IMEUtil {
    public static View wrap(View contentView) {
        if (contentView.getParent() != null) {
            throw new UnsupportedOperationException("use HideIMEUtil.wrap instead");
        }

        ViewGroup.LayoutParams p = contentView.getLayoutParams();
        AutoHideInputMethodFrameLayout layout = new AutoHideInputMethodFrameLayout(contentView.getContext());
        layout.setLayoutParams(p);
        layout.addView(contentView);
        return layout;
    }
}
