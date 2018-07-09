package com.switube.www.swiofficialthird.info.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.info.adapter.MessageRepliesAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageRepliesFragment extends Fragment implements IMessageRepliesFragment {

    public MessageRepliesFragment() {}


    @BindViews({R.id.textBackInMessageReplies, R.id.textTitleInMessageReplies})
    List<TextView> mTextViews;
    @BindViews({R.id.imageHeadInMessageReplies, R.id.imageSendInMessageReplies})
    List<ImageView> mImageViews;
    @BindView(R.id.editMessageInMessageReplies)
    EditText mEditMessage;
    @BindView(R.id.recyclerInMessageReplies)
    RecyclerView mRecyclerView;
    private Unbinder mUnbinder;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_replies, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        final MessageRepliesAdapter messageRepliesAdapter = new MessageRepliesAdapter(container.getContext(), this);
        mRecyclerView.setAdapter(messageRepliesAdapter);
        RxView.clicks(mTextViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        messageRepliesAdapter.handlePlusCount();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        if (mIMainActivity.getNeedFocusEditView()) {
            handleFocusEditView();
            mIMainActivity.setNeedFocusEditView(false);
        }
        return view;
    }

    @Override
    public void handleFocusEditView() {
        mEditMessage.requestFocusFromTouch();
        InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(mEditMessage, InputMethodManager.SHOW_FORCED);
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
