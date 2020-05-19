package com.switube.www.landmark2018test.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AMyExchange;
import com.switube.www.landmark2018test.presenter.PMyExchange;
import com.switube.www.landmark2018test.view.callback.IVMyExchange;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VMyExchange extends Fragment implements IVMyExchange {
    private Unbinder unbinder;
    private PMyExchange pMyExchange;
    @BindView(R.id.viewPagerInMyExchange)
    ViewPager viewPager;
    @BindViews({R.id.viewLeftInMyExchange, R.id.viewRightInMyExchange})
    List<View> viewList;
    @BindViews({R.id.textLeftInMyExchange, R.id.textRightInMyExchange, R.id.textBackInMyExchange})
    List<TextView> textViews;
    public VMyExchange() {
        pMyExchange = new PMyExchange(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_my_exchange, container, false);
        unbinder = ButterKnife.bind(this, view);
        AMyExchange aMyExchange = new AMyExchange(container.getContext(), this);
        pMyExchange.getTicketsList();
        viewPager.setAdapter(aMyExchange);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    viewList.get(0).setBackgroundColor(Color.RED);
                    viewList.get(1).setBackgroundColor(Color.WHITE);
                } else {
                    viewList.get(0).setBackgroundColor(Color.WHITE);
                    viewList.get(1).setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
        textViews.get(2).setOnClickListener(view1 -> getParentFragmentManager().popBackStack());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showCount(int left, int right) {
        String textLeft = "可使用(" + left + ")";
        String textRight = "未使用或已過期(" + right + ")";
        textViews.get(0).setText(textLeft);
        textViews.get(1).setText(textRight);
    }

    @Override
    public void toNextPage() {
        if (MyApplication.getAppData().getTicketData().getGoodUse().equals("0")) {
            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VShowTicket()).addToBackStack("MyExchange").commit();
        } else {
            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VShowTicketB()).addToBackStack("MyExchange").commit();
        }
    }
}
