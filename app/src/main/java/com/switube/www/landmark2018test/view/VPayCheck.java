package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.presenter.PPayCheck;
import com.switube.www.landmark2018test.view.callback.IVPayCheck;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VPayCheck extends Fragment implements IVPayCheck {
    private Unbinder unbinder;
    private PPayCheck pPayCheck;
    @BindViews({R.id.textNameInPayCheck, R.id.textCashInPayCheck, R.id.textPayInPayCheck})
    List<TextView> textViews;
    public VPayCheck() {
        pPayCheck = new PPayCheck(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_pay_check, container, false);
        unbinder = ButterKnife.bind(this, view);
        pPayCheck.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void init(CodeContent codeContent, String cash) {
        textViews.get(0).setText(codeContent.getName());
        textViews.get(1).setText(cash);
        String pay = "支付碳幣" + cash;
        textViews.get(2).setText(pay);
        textViews.get(2).setOnClickListener(view -> pPayCheck.handlePay(codeContent, cash));
    }

    @Override
    public void finishSend() {
        int count = getParentFragmentManager().getBackStackEntryCount();
        for (int i = 1; i < count; i++) {
            FragmentManager.BackStackEntry backStackEntry = getParentFragmentManager().getBackStackEntryAt(i);
            getParentFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VWallet()).commit();
    }
}
