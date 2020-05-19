package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VTransactionDetails extends Fragment {
    private Unbinder unbinder;
    @BindViews({R.id.textDateInTransactionDetails, R.id.textIdInTransactionDetails,
            R.id.textCashInTransactionDetails, R.id.textNameInTransactionDetails})
    List<TextView> textViewList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_transaction_details, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void init() {
        StringBuilder stringBuilder = new StringBuilder(MyApplication.getAppData().geteTransaction().getCash());
        if (stringBuilder.indexOf("-") >= 0) {
            stringBuilder.delete(0, 1);
        }
        textViewList.get(0).setText(MyApplication.getAppData().geteTransaction().getDate());
        textViewList.get(1).setText(MyApplication.getAppData().geteTransaction().getId());
        textViewList.get(2).setText(stringBuilder.toString());
        textViewList.get(3).setText(MyApplication.getAppData().geteTransaction().getName());
    }
}
