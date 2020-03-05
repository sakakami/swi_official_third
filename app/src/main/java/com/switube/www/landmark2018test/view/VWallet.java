package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.ACashFlow;
import com.switube.www.landmark2018test.entity.ECashFlow;
import com.switube.www.landmark2018test.presenter.PWallet;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.AppData;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVWallet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class VWallet extends Fragment implements IVWallet {
    private Unbinder unbinder;
    private PWallet pWallet;
    @BindViews({R.id.textBackWallet,
            R.id.textBalanceWallet,
            R.id.textMultipleOneWallet,
            R.id.textScannerWallet,
            R.id.textShowWallet,
            R.id.textPayDetailWallet})
    List<TextView> textViewList;
    @BindView(R.id.imageHeadWallet)
    CircleImageView circleImageView;
    @BindView(R.id.imageQRCodeWallet)
    ImageView imageView;
    @BindView(R.id.editCashWallet)
    EditText editText;

    public VWallet() { pWallet = new PWallet(this); }


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

    private String cash = "0";
    private String code = "";
    private void init() {
        final String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
        if (maid.equals("gpErQr46Rr")) {
            Glide.with(MyApplication.getInstance()).load(R.drawable.user_normal).into(circleImageView);
            if (VMap.isCent) {
                textViewList.get(2).setText("附近還車點");
            } else {
                textViewList.get(2).setText("附近租車點");
            }
            textViewList.get(5).setText("消費明細");
            textViewList.get(3).setText("掃碼付款");
            textViewList.get(4).setVisibility(View.GONE);
            editText.setVisibility(View.GONE);
        } else if (maid.equals("gplZ37q2qo")) {
            Glide.with(MyApplication.getInstance()).load(R.drawable.user_shop).into(circleImageView);
            textViewList.get(2).setText("碳幣結算");
            textViewList.get(5).setVisibility(View.GONE);
            textViewList.get(3).setText("入帳確認");
            textViewList.get(4).setText("收款\nQR Code");
            editText.setHint("輸入商品碳幣價格");
        } else {
            StringBuilder builder = new StringBuilder(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
            builder.delete(0, 2);
            Glide.with(MyApplication.getInstance()).load(R.drawable.user_admin).into(circleImageView);
            textViewList.get(2).setText("交易紀錄");
            textViewList.get(3).setVisibility(View.GONE);
            textViewList.get(4).setVisibility(View.GONE);
            textViewList.get(5).setVisibility(View.GONE);
            editText.setVisibility(View.GONE);
        }
        imageView.setVisibility(View.GONE);
        /*if (builder.toString().equals("null")) {
            circleImageView.setImageResource(R.drawable.person_unlogin);
        } else {
            builder.delete(0, 2);
            Glide.with(MyApplication.getInstance()).load(Uri.parse(AppConstant.BASE_URL2 + builder.toString())).into(circleImageView);
        }*/
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        //readyToCent = false;
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewList.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (maid.equals("gpErQr46Rr")) {
                            MyApplication.getAppData().setFromWallet(true);
                            getFragmentManager().popBackStack();
                        } else {
                            pWallet.showDetailList();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewList.get(3))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        //decoratedBarcodeView.setVisibility(View.VISIBLE);
                        if (maid.equals("gpErQr46Rr")) {
                            IntentIntegrator.forSupportFragment(VWallet.this)
                                    .setBeepEnabled(false)
                                    .setOrientationLocked(false)
                                    .setBarcodeImageEnabled(true)
                                    .initiateScan();
                        } else {
                            if (code.length() > 0) {
                                pWallet.handleCheckPay(code);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewList.get(4))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (cash.length() > 0 && !cash.equals("0")) {
                            BarcodeEncoder encoder = new BarcodeEncoder();
                            try {
                                String date = new SimpleDateFormat("yyyyMMddHHmmssS", Locale.getDefault()).format(new Date());
                                StringBuilder builder1 = new StringBuilder();
                                for (int i = 0; i < 13; i++) { builder1.append((int)(Math.random() * 10)); }
                                code = date + builder1.toString();
                                String qrcode = maid + "/" + cash + "/" + date + builder1.toString();
                                imageView.setVisibility(View.VISIBLE);
                                Bitmap bitmap = encoder.encodeBitmap(qrcode, BarcodeFormat.QR_CODE, 250, 250);
                                Glide.with(getContext())
                                        .load(bitmap)
                                        .into(imageView);
                            } catch (WriterException e) {
                                e.printStackTrace();
                            }
                            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            if (inputMethodManager != null) {
                                inputMethodManager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
                            }
                            //pWallet.saveFromQR(cash);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewList.get(5))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) { pWallet.showDetailList(); }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxTextView.textChanges(editText)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(CharSequence charSequence) { if (charSequence.length() > 0) { cash = charSequence.toString(); } }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                Log.e("scanner", "result: " + result.getContents());
                pWallet.handlePay(totalCash, result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private int totalCash = 0;
    private boolean isClear = false;
    private int clearCash = 0;
    @Override
    public void showDetail(final ArrayList<ECashFlow> eCashFlows, boolean showDetail, final int cash) {
        //imageView.setVisibility(View.GONE);
        //editText.setText("");
        if (showDetail) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_cash_flow, new LinearLayout(getContext()), false);
            RecyclerView recyclerView = view.findViewById(R.id.recyclerCashFlow);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(new ACashFlow(eCashFlows));
            TextView textView = view.findViewById(R.id.textTitleCashFlow);
            textView.setText("交易明細列表");
            TextView textRight = view.findViewById(R.id.textRightCashFlow);
            TextView textLeft = view.findViewById(R.id.textLeftCashFlow);
            View point = view.findViewById(R.id.viewPointCashFlow);
            String message;
            final String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
            if (maid.equals("gpErQr46Rr") || maid.equals("sbUhP7d4qS")) {
                message = getString(R.string.global_ok);
                AlertDialogUtil.getInstance()
                        .initDialogBuilder(getContext(), view, message, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialogUtil.getInstance().clearAlertDialog();
                            }
                        }, true);
                AlertDialogUtil.getInstance().showAlertDialog();
            } else {
                point.setVisibility(View.VISIBLE);
                textRight.setVisibility(View.VISIBLE);
                textLeft.setVisibility(View.VISIBLE);
                textRight.setText("結算");
                textLeft.setText(R.string.global_cancel);
                textRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialogUtil.getInstance().clearAlertDialog();
                        clearCash = cash;
                        isClear = true;
                        pWallet.handleClear(clearCash, eCashFlows);
                    }
                });
                textLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialogUtil.getInstance().clearAlertDialog();
                    }
                });
                AlertDialogUtil.getInstance().initDialogBuilder(getContext(), view);
                AlertDialogUtil.getInstance().showAlertDialog();
            }
        } else {
            String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
            if (!maid.equals("gpErQr46Rr") && isClear) {
                isClear = false;
                String message = "總結算碳幣" + clearCash;
                String ok = getString(R.string.global_ok);
                AlertDialogUtil.getInstance()
                        .initDialogBuilder(getContext(), message, ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                AlertDialogUtil.getInstance().clearAlertDialog();
                            }
                        });
                AlertDialogUtil.getInstance().showAlertDialog();
            }
        }
        Log.e("cash", String.valueOf(cash));
        totalCash = cash;
        String finalCash = "碳幣金額： " + cash;
        textViewList.get(1).setText(finalCash);
    }

    @Override
    public void checkPay(final String cash, final String maid, final String code) {
        String message = "付款金額為" + cash + "，點下確認後直接在帳戶中扣除相應金額。";
        String ok = getString(R.string.global_ok);
        String cancel = getString(R.string.global_cancel);
        AlertDialogUtil.getInstance()
                .initDialogBuilder(getContext(), message, ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pWallet.savePay(cash, maid, code);
                        AlertDialogUtil.getInstance().clearAlertDialog();
                    }
                }, cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialogUtil.getInstance().clearAlertDialog();
                    }
                });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void payError() {
        String message = "碳幣餘額不足，付款失敗。";
        String ok = getString(R.string.global_ok);
        AlertDialogUtil.getInstance()
                .initDialogBuilder(getContext(), message, ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialogUtil.getInstance().clearAlertDialog();
                    }
                });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void clearError() {
        String message = "無可結算碳幣";
        String ok = getString(R.string.global_ok);
        AlertDialogUtil.getInstance()
                .initDialogBuilder(getContext(), message, ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        AlertDialogUtil.getInstance().clearAlertDialog();
                    }
                });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void showFinishCheck(String pay, int cash) {
        editText.setText("");
        imageView.setVisibility(View.GONE);
        String ok = getString(R.string.global_ok);
        if (pay.isEmpty()) {
            AlertDialogUtil.getInstance()
                    .initDialogBuilder(getContext(), "款項尚未入帳", ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            AlertDialogUtil.getInstance().clearAlertDialog();
                        }
                    });
            AlertDialogUtil.getInstance().showAlertDialog();
        } else {
            String message = "已入帳" + pay + "元";
            AlertDialogUtil.getInstance()
                    .initDialogBuilder(getContext(), message, ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            code = "";
                            AlertDialogUtil.getInstance().clearAlertDialog();
                        }
                    });
            AlertDialogUtil.getInstance().showAlertDialog();
            String finalCash = "碳幣金額： " + cash;
            textViewList.get(1).setText(finalCash);
        }
    }
}
