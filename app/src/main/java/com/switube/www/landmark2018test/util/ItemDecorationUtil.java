package com.switube.www.landmark2018test.util;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jetbrains.annotations.NotNull;

public class ItemDecorationUtil extends RecyclerView.ItemDecoration {
    private int mTop;
    private int mBottom;
    private int mRight;
    private int mLeft;
    public ItemDecorationUtil(@NotNull Context context, int top, int bottom, int left, int right) {
        float density = context.getResources().getDisplayMetrics().density;
        mTop = (int)(top * density);
        mBottom = (int)(bottom * density);
        mRight = (int)(right * density);
        mLeft = (int)(left * density);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(mLeft, mTop, mRight, mBottom);
    }
}
