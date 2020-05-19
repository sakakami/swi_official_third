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
public class VExchangeItem extends Fragment {
    private Unbinder unbinder;
    @BindViews({R.id.textBackInExchangeItem, R.id.textExchangeInExchangeItem,
            R.id.textNameInExchangeItem, R.id.textCostInExchangeItem})
    List<TextView> textViewList;
    @BindView(R.id.imageHomeInExchangeItem)
    ImageView imageHome;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_exchange_item, container, false);
        unbinder = ButterKnife.bind(this, view);
        textViewList.get(0).setOnClickListener(view1 -> getParentFragmentManager().popBackStack());
        textViewList.get(1).setOnClickListener(view12 -> getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VExchangeCheck()).addToBackStack("ExchangeItem").commit());
        textViewList.get(2).setText(MyApplication.getAppData().geteStoreList().getName());
        textViewList.get(3).setText(MyApplication.getAppData().geteStoreList().getMessage());
        imageHome.setOnClickListener(view13 -> {
            int count = getParentFragmentManager().getBackStackEntryCount();
            for (int i = 1; i < count; i++) {
                FragmentManager.BackStackEntry backStackEntry = getParentFragmentManager().getBackStackEntryAt(i);
                getParentFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            getParentFragmentManager().popBackStack();
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
