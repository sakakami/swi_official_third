package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VExchangeFinish extends Fragment {
    private Unbinder unbinder;
    @BindViews({R.id.textMyExchangeInExchangeFinish, R.id.textContinueInExchangeFinish})
    List<TextView> textViews;
    @BindView(R.id.imageBackInExchangeFinish)
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_exchange_finish, container, false);
        MyApplication.getAppData().setExchangeFinish(true);
        unbinder = ButterKnife.bind(this, view);
        imageView.setOnClickListener(view1 -> {
            int count = getParentFragmentManager().getBackStackEntryCount();
            for (int i = 1; i < count; i++) {
                FragmentManager.BackStackEntry backStackEntry = getParentFragmentManager().getBackStackEntryAt(i);
                getParentFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            getParentFragmentManager().popBackStack();
        });
        textViews.get(0).setOnClickListener(view12 -> {
            MyApplication.getAppData().setExchangeFinish(false);
            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMyExchange()).addToBackStack("exchangeFinish").commit();
        });
        textViews.get(1).setOnClickListener(view13 -> getParentFragmentManager().popBackStack("StoreList", FragmentManager.POP_BACK_STACK_INCLUSIVE));
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
