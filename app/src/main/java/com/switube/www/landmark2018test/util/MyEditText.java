package com.switube.www.landmark2018test.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {
    public MyEditText(Context context) {
        super(context);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (onBackPressedListener != null) {
                onBackPressedListener.onKeyBack();
            }
        }
        return super.onKeyPreIme(keyCode, event);
    }

    private OnBackPressedListener onBackPressedListener;
    public void setOnBackPressedListener(OnBackPressedListener onBackPressedListener) {
        this.onBackPressedListener = onBackPressedListener;
    }

    public interface OnBackPressedListener {
        void onKeyBack();
    }
}
