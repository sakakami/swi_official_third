package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.presenter.PExchangeCheck;
import com.switube.www.landmark2018test.view.callback.IVExchangeCheck;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VExchangeCheck extends Fragment implements IVExchangeCheck {
    private Unbinder unbinder;
    private PExchangeCheck pExchangeCheck;
    private int cost;
    private int count = 0;
    @BindViews({R.id.textBackInExchangeCheck,
            R.id.textMessageInExchangeCheck, R.id.textConfirmInExchangeCheck,
            R.id.textCostTitleInExchangeCheck, R.id.textCostContentInExchangeCheck,
            R.id.textCountTitleInExchangeCheck, R.id.textCountContentInExchangeCheck,
            R.id.textTotalTitleInExchangeCheck, R.id.textTotalContentInExchangeCheck,
            R.id.textExchangeInExchangeCheck, R.id.textNameInExchangeCheck,
            R.id.textCostInExchangeCheck})
    List<TextView> textViews;
    @BindView(R.id.spinnerInExchangeCheck)
    Spinner spinner;
    @BindView(R.id.imageHomeInExchangeCheck)
    ImageView imageHome;
    public VExchangeCheck() {
        pExchangeCheck = new PExchangeCheck(this);
        cost = Integer.parseInt(MyApplication.getAppData().geteStoreList().getMessage());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_exchange_check, container, false);
        unbinder = ButterKnife.bind(this, view);
        textViews.get(4).setText(MyApplication.getAppData().geteStoreList().getMessage());
        textViews.get(11).setText(MyApplication.getAppData().geteStoreList().getMessage());
        textViews.get(10).setText(MyApplication.getAppData().geteStoreList().getName());
        textViews.get(9).setOnClickListener(view1 -> pExchangeCheck.saveTicket(count));
        textViews.get(0).setOnClickListener(view12 -> getParentFragmentManager().popBackStack());
        textViews.get(2).setOnClickListener(view13 -> {
            textViews.get(1).setVisibility(View.INVISIBLE);
            textViews.get(2).setVisibility(View.INVISIBLE);
            textViews.get(3).setVisibility(View.VISIBLE);
            textViews.get(4).setVisibility(View.VISIBLE);
            textViews.get(5).setVisibility(View.VISIBLE);
            textViews.get(6).setVisibility(View.VISIBLE);
            textViews.get(7).setVisibility(View.VISIBLE);
            textViews.get(8).setVisibility(View.VISIBLE);
        });
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.count_list, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                textViews.get(1).setVisibility(View.VISIBLE);
                textViews.get(2).setVisibility(View.VISIBLE);
                textViews.get(3).setVisibility(View.INVISIBLE);
                textViews.get(4).setVisibility(View.INVISIBLE);
                textViews.get(5).setVisibility(View.INVISIBLE);
                textViews.get(6).setVisibility(View.INVISIBLE);
                textViews.get(7).setVisibility(View.INVISIBLE);
                textViews.get(8).setVisibility(View.INVISIBLE);
                count = i + 1;
                textViews.get(6).setText(String.valueOf(count));
                int total = count * cost;
                textViews.get(8).setText(String.valueOf(total));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });
        imageHome.setOnClickListener(view14 -> {
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

    @Override
    public void finishInsert() {
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VExchangeFinish()).commit();
    }

    @Override
    public void showHint(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
