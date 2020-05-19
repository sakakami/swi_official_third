package com.switube.www.landmark2018test.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.view.callback.IMainActivity;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SignInUtil {
    @BindView(R.id.imageFacebookInSignIn)
    LoginButton loginButton;
    @BindView(R.id.imageGoogleInSignIn)
    SignInButton signInButton;
    @BindView(R.id.textLaterInSignIn)
    TextView textView;
    private Unbinder unbinder;
    private IMainActivity iMainActivity;
    public SignInUtil(Context context, IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        init(context);
    }

    private AlertDialog alertDialog;
    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_sign_in, new RelativeLayout(context));
        unbinder = ButterKnife.bind(this, view);
        RxView.clicks(loginButton)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        iMainActivity.handleSignIn("facebook");
                        close();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(signInButton)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        iMainActivity.handleSignIn("google");
                        close();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) { }

                    @Override
                    public void onNext(Object o) {
                        close();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setCancelable(false);
        alertDialog = builder.create();
        alertDialog.show();
        int width = context.getResources().getDisplayMetrics().widthPixels / 100 * 85;
        Objects.requireNonNull(alertDialog.getWindow()).setLayout(width, -2);
    }

    private void close() {
        if (alertDialog != null) {
            unbinder.unbind();
            alertDialog.dismiss();
        }
        if (MyApplication.getAppData().isPlayerPage()) {
            MyApplication.getAppData().getiFloatPlayerService().showBigMode();
        }
    }
}
