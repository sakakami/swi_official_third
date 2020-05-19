package com.switube.www.landmark2018test.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.ScannerActivity;
import com.switube.www.landmark2018test.adapter.AWallet;
import com.switube.www.landmark2018test.adapter.callback.IAWallet;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.presenter.PWallet;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IVWallet;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VWallet extends Fragment implements IVWallet, IAWallet {
    private Unbinder unbinder;
    private PWallet pWallet;
    private AWallet aWallet;
    private boolean canClick;
    @BindView(R.id.textCashInWallet)
    TextView textCash;
    @BindView(R.id.imageHeadInWallet)
    CircleImageView circleImageView;
    @BindView(R.id.recyclerInWallet)
    RecyclerView recyclerView;

    public VWallet() {
        pWallet = new PWallet(this);
        canClick = false;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vwallet, container, false);
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
        aWallet = new AWallet(this);
        recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 20, 20, 20, 10));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(aWallet);
        pWallet.init();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                MyApplication.getAppData().setScannerResult(result.getContents());
                Gson gson = new Gson();
                CodeContent codeContent = gson.fromJson(result.getContents(), CodeContent.class);
                if (codeContent.getMode().equals("0")) {
                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPay()).addToBackStack("wallet").commit();
                } else {
                    pWallet.handleScanTicket(codeContent);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void initAdapter(List<String> list) {
        aWallet.setList(list);
    }

    @Override
    public void onItemClick(int position) {
        if (canClick) {
            switch (position) {
                case 0:
                    MyApplication.getAppData().setFromWallet(true);
                    getParentFragmentManager().popBackStack();
                    break;
                case 1:
                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMyCode()).addToBackStack("wallet").commit();
                    break;
                case 2:
                    MyApplication.getAppData().setShowMyCode(true);
                    IntentIntegrator
                            .forSupportFragment(this)
                            .setCaptureActivity(ScannerActivity.class)
                            .initiateScan();
                    break;
                case 3:
                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VStoreList()).addToBackStack("wallet").commit();
                    break;
                case 4:
                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VTransaction()).addToBackStack("wallet").commit();
                    break;
                case 5:
                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMyExchange()).addToBackStack("wallet").commit();
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void showDetail(int cash) {
        if (textCash != null) {
            textCash.setText(String.valueOf(cash));
        }
        MyApplication.getAppData().setCarbonCash(String.valueOf(cash));
        canClick = true;
    }
}
