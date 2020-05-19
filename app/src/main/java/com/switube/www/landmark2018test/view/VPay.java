package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.presenter.PPay;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVPay;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VPay extends Fragment implements IVPay {
    private Unbinder unbinder;
    private PPay pPay;
    private String maid;
    @BindView(R.id.editCashInPay)
    EditText editText;
    @BindViews({R.id.textNameInPay, R.id.textCarbonInPay, R.id.textPayInPay, R.id.textCashInPay, R.id.textTitleInPay})
    List<TextView> textViews;
    public VPay() {
        pPay = new PPay(this);
        maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        Gson gson = new Gson();
        CodeContent codeContent = gson.fromJson(MyApplication.getAppData().getScannerResult(), CodeContent.class);
        textViews.get(0).setText(codeContent.getName());
        textViews.get(1).setText(MyApplication.getAppData().getCarbonCash());
        textViews.get(2).setOnClickListener(view1 -> {
            if (maid.equals(MyApplication.getAppData().getStoreMaid())) {
                if (editText.getText().length() > 0) {
                    String pay = editText.getText().toString();
                    pPay.handlePay(codeContent, pay);
                } else {
                    Toast.makeText(getContext(), "請輸入付款金額", Toast.LENGTH_SHORT).show();
                }
            } else {
                handlePayClick();
            }
        });
        if (maid.equals(MyApplication.getAppData().getStoreMaid())) {
            textViews.get(4).setText("請求款項");
            textViews.get(3).setText("請求金額");
            textViews.get(2).setText("請款");
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void finishSend() {
        int count = getParentFragmentManager().getBackStackEntryCount();
        for (int i = 1; i < count; i++) {
            FragmentManager.BackStackEntry backStackEntry = getParentFragmentManager().getBackStackEntryAt(i);
            getParentFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VWallet()).commit();
        //getParentFragmentManager().popBackStack();
    }

    private void handlePayClick() {
        if (editText.getText().length() > 0) {
            int carbon = Integer.parseInt(MyApplication.getAppData().getCarbonCash());
            int pay = Integer.parseInt(editText.getText().toString());
            if (carbon >= pay) {
                MyApplication.getAppData().setPayCash(String.valueOf(pay));
                getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPayCheck()).addToBackStack("VPay").commit();
            } else {
                Toast.makeText(getContext(), "餘額不足", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "請輸入付款金額", Toast.LENGTH_SHORT).show();
        }
    }
}
