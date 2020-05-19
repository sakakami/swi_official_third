package com.switube.www.landmark2018test.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.ScannerActivity;
import com.switube.www.landmark2018test.gson.CodeContent;
import com.switube.www.landmark2018test.presenter.PMyCode;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVMyCode;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VMyCode extends Fragment implements View.OnClickListener, IVMyCode {
    private Unbinder unbinder;
    private PMyCode pMyCode;
    @BindViews({R.id.textScannerInMyCode, R.id.textCashInMyCode})
    List<TextView> textViews;
    @BindView(R.id.imgCodeInMyCode)
    ImageView imageView;
    @BindView(R.id.imageFlashInMyCode)
    ImageView flash;
    public VMyCode() {
        pMyCode = new PMyCode(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_my_code, container, false);
        unbinder = ButterKnife.bind(this, view);
        textViews.get(0).setOnClickListener(this);
        String cash = "碳幣金額： " + MyApplication.getAppData().getCarbonCash();
        textViews.get(1).setText(cash);
        flash.setOnClickListener(view1 -> pMyCode.getCashFlowData(checkId));
        initMyCode();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void finishSend() {
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void finishFlash() {
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPayCheck()).addToBackStack("VMyCode").commit();
    }

    @Override
    public void onClick(View view) {
        MyApplication.getAppData().setShowMyCode(false);
        IntentIntegrator
                .forSupportFragment(this)
                .setCaptureActivity(ScannerActivity.class)
                .initiateScan();
    }

    private String checkId = "";
    private void initMyCode() {
        BarcodeEncoder encoder = new BarcodeEncoder();
        try {
            String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
            String name = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userName", "null");
            StringBuilder builder1 = new StringBuilder();
            for (int i = 0; i < 13; i++) { builder1.append((int)(Math.random() * 10)); }
            checkId = builder1.toString();
            MyApplication.getAppData().setCheckId(builder1.toString());
            CodeContent codeContent = new CodeContent();
            codeContent.setId(builder1.toString());
            codeContent.setMaid(maid);
            codeContent.setName(name);
            codeContent.setMode("0");
            Gson gson = new Gson();
            String qrcode = gson.toJson(codeContent);
            Bitmap bitmap = encoder.encodeBitmap(qrcode, BarcodeFormat.QR_CODE, 250, 250);
            Glide.with(getContext())
                    .load(bitmap)
                    .into(imageView);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                MyApplication.getAppData().setScannerResult(result.getContents());
                Gson gson = new Gson();
                CodeContent codeContent = gson.fromJson(result.getContents(), CodeContent.class);
                if (codeContent.getMode().equals("0")) {
                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPay()).addToBackStack("myCode").commit();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
