package com.switube.www.landmark2018test.youtube;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.switube.www.landmark2018test.R;

public class FloatPlayerKiller extends RelativeLayout {
    public FloatPlayerKiller(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.layout_player_killer, this);
    }
}
