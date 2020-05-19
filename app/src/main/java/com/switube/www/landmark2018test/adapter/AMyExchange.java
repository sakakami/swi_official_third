package com.switube.www.landmark2018test.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.switube.www.landmark2018test.view.VTicketList;
import com.switube.www.landmark2018test.view.callback.IVMyExchange;

public class AMyExchange extends PagerAdapter {
    private Context context;
    private IVMyExchange ivMyExchange;
    public AMyExchange(Context context, IVMyExchange ivMyExchange) {
        this.context = context;
        this.ivMyExchange = ivMyExchange;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        VTicketList vTicketList = new VTicketList(context, ivMyExchange);
        vTicketList.setPageOne(position == 0);
        container.addView(vTicketList);
        return vTicketList;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
