package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.presenter.PCreditCard;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVCreditCard;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class VCreditCard extends Fragment implements IVCreditCard {
    private PCreditCard pCreditCard;
    public VCreditCard() {
        pCreditCard = new PCreditCard(this);
    }

    @BindViews({R.id.textTitleInCreditCard, R.id.textBackInCreditCard, R.id.textAddInCreditCard,
            R.id.textCardInfoInCreditCard, R.id.textCardNumberInCreditCard, R.id.textNameInCreditCard,
            R.id.textDateInCreditCard, R.id.textCodeInCreditCard})
    List<TextView> mTextViews;
    @BindViews({R.id.editCardNumberInCreditCard, R.id.editNameInCreditCard,
            R.id.editDateInCreditCard, R.id.editCodeInCreditCard})
    List<EditText> mEditTexts;
    private Unbinder mUnbinder;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_credit_card, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        //click add
        RxView.clicks(mTextViews.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        pCreditCard.sendCashFlow();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click back
        RxView.clicks(mEditTexts.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private IMainActivity iMainActivity;
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity)context;
    }

    @Override
    public void finishSend() {
        VMap.isCent = true;
        iMainActivity.handleStartTimer(false);
        getParentFragmentManager().popBackStack();
    }
}
