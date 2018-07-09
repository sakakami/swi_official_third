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
import com.switube.www.swiofficialthird.info.adapter.MessageListAdapter;

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
public class MessageListFragment extends Fragment implements IMessageListFragment {

    public MessageListFragment() {}

    @BindViews({R.id.textBackInMessageList, R.id.textTitleInMessageList})
    List<TextView> textViews;
    @BindViews({R.id.imageHeadInMessageList, R.id.imageSendInMessageList})
    List<ImageView> mImageViews;
    @BindView(R.id.editMessageInMessageList)
    EditText editMessage;
    @BindView(R.id.recyclerInMessageList)
    RecyclerView recyclerView;
    private Unbinder mUnbinder;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        final MessageListAdapter messageListAdapter = new MessageListAdapter(container.getContext(), this);
        recyclerView.setAdapter(messageListAdapter);
        RxView.clicks(textViews.get(0))
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
                        messageListAdapter.handlePlusCount();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void handleSwitchPage() {
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new MessageRepliesFragment()).addToBackStack("list").commit();
    }

    @Override
    public void handleFocusEditView() {
        editMessage.requestFocusFromTouch();
        InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editMessage, InputMethodManager.SHOW_FORCED);
    }

    @Override
    public void handleSwitchPageWithFocus() {
        mIMainActivity.setNeedFocusEditView(true);
        handleSwitchPage();
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
