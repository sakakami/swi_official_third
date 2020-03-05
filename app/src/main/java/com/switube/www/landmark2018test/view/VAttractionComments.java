package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AAttractionComments;
import com.switube.www.landmark2018test.adapter.callback.IAAttractionComments;
import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.presenter.PAttractionComments;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.util.MyEditText;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.util.SignInUtil;
import com.switube.www.landmark2018test.view.callback.IFragmentBackHandler;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVAttractionComments;

import java.util.List;
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
public class VAttractionComments extends Fragment implements IVAttractionComments, IAAttractionComments {
    @BindViews({R.id.textBackInAllMessage, R.id.textSendInAllMessage})
    List<TextView> textViews;
    @BindView(R.id.imagePhotoInAllMessage)
    CircleImageView circleImageView;
    @BindView(R.id.editMessageInAllMessage)
    MyEditText editText;
    @BindView(R.id.recyclerInAllMessage)
    RecyclerView recyclerView;
    @BindViews({R.id.viewBarBInAllMessage, R.id.viewInAllMessage})
    List<View> viewList;
    private PAttractionComments pAttractionComments;
    private Unbinder unbinder;
    private String message = "";
    private AAttractionComments aAttractionComments;
    private IMainActivity iMainActivity;

    public VAttractionComments() {
        pAttractionComments = new PAttractionComments(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_attraction_comments, container, false);
        unbinder = ButterKnife.bind(this, view);
        editText = view.findViewById(R.id.editMessageInAllMessage);
        init();
        RxTextView.textChanges(editText)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(CharSequence charSequence) {
                        message = charSequence.toString();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
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
        RxView.clicks(textViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (message.length() > 0) {
                            if (isEdit) {
                                pAttractionComments.sendEditData(MyApplication.getAppData().getMessageList().get(index).getAcid(), message, index);
                            } else {
                                pAttractionComments.sendMessageData(MyApplication.getAppData().getArtid(), message);
                                message = "";
                                editText.setText("");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(viewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        new SignInUtil(getContext(), iMainActivity);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        editText.setOnBackPressedListener(new MyEditText.OnBackPressedListener() {
            @Override
            public void onKeyBack() {
                message = "";
                editText.setText("");
                isEdit = false;
                viewList.get(1).setVisibility(View.GONE);
            }
        });
        return view;
    }

    private void init() {
        StringBuilder stringBuilder = new StringBuilder(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
        if (stringBuilder.toString().equals("null")) {
            viewList.get(0).setVisibility(View.VISIBLE);
            circleImageView.setImageResource(R.drawable.person_unlogin);
        } else {
            viewList.get(0).setVisibility(View.GONE);
            stringBuilder.delete(0, 2);
            Glide.with(MyApplication.getInstance())
                    .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                    .into(circleImageView);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 8, 0, 8, 0));
        aAttractionComments = new AAttractionComments(MyApplication.getAppData().getMessageList(), this);
        recyclerView.setAdapter(aAttractionComments);
    }

    @Override
    public void handleClickMessage(int index) {
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            new SignInUtil(getContext(), iMainActivity);
        } else {
            iMainActivity.setMessageIndex(index);
            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VReplies()).addToBackStack("AttractionComments").commit();
        }
    }

    @Override
    public void handleSignIn() {
        new SignInUtil(getContext(), iMainActivity);
    }

    @Override
    public void handleRefreshData(GCommentsData gCommentsData) {
        gCommentsData.getMsgData().get(0).setMaid(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"));
        MyApplication.getAppData().getMessageList().add(0, gCommentsData.getMsgData().get(0));
        int size = MyApplication.getAppData().getArticleList().size();
        String artid = MyApplication.getAppData().getArtid();
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (MyApplication.getAppData().getArticleList().get(i).getArtid().equals(artid)) {
                index = i;
            }
        }
        MyApplication.getAppData().getArticleList().get(index).setMsg(MyApplication.getAppData().getMessageList());
        aAttractionComments.refreshAdapter(MyApplication.getAppData().getMessageList());
    }

    @Override
    public void handleClickLike(int index, String like) {
        pAttractionComments.sendLikeData(MyApplication.getAppData().getMessageList().get(index).getAcid(), like, index);
    }

    @Override
    public void handleRefreshCount(String count, int index, String like) {
        MyApplication.getAppData().getMessageList().get(index).setLike(like);
        MyApplication.getAppData().getMessageList().get(index).setCount(count);
        aAttractionComments.refreshAdapter(MyApplication.getAppData().getMessageList());
    }

    private boolean isEdit = false;
    private int index = -1;
    @Override
    public void handleEditComments(int index) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        viewList.get(1).setVisibility(View.VISIBLE);
        editText.setText(MyApplication.getAppData().getMessageList().get(index).getTxt());
        isEdit = true;
        this.index = index;
    }

    @Override
    public void handleDelComments(int index) {
        pAttractionComments.sendDelData(MyApplication.getAppData().getMessageList().get(index).getAcid(), index);
    }

    @Override
    public void handleFinishEdit() {
        isEdit = false;
        aAttractionComments.refreshAdapter(MyApplication.getAppData().getMessageList());
        message = "";
        editText.setText("");
        editText.clearFocus();
        InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        viewList.get(1).setVisibility(View.GONE);
    }

    @Override
    public void handleFinishDel() {
        aAttractionComments.refreshAdapter(MyApplication.getAppData().getMessageList());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
