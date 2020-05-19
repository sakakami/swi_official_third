package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AAttractionSteaming;
import com.switube.www.landmark2018test.adapter.callback.IAAttractionSteaming;
import com.switube.www.landmark2018test.presenter.PAttractionSteaming;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVAttractionSteaming;

import org.jetbrains.annotations.NotNull;

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
public class VAttractionSteaming extends Fragment implements IVAttractionSteaming, IAAttractionSteaming {
    @BindViews({R.id.textBackInAttractionSteaming, R.id.textTitleInAttractionSteaming})
    List<TextView> textViewList;
    @BindView(R.id.recyclerInAttractionSteaming)
    RecyclerView recyclerView;
    private PAttractionSteaming pAttractionSteaming;
    private Unbinder unbinder;
    private AAttractionSteaming adapter;

    public VAttractionSteaming() {
        pAttractionSteaming = new PAttractionSteaming(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_attraction_steaming, container, false);
        unbinder = ButterKnife.bind(this, view);
        textViewList.get(1).setText(MyApplication.getAppData().getAttractionName());
        recyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        adapter = new AAttractionSteaming(this, MyApplication.getAppData().getArticleList());
        recyclerView.setAdapter(adapter);
        RxView.clicks(textViewList.get(0))
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
    public void handleSwitchPage(int index) {
        MyApplication.getAppData().setArtid(adapter.getArticleList().get(index).getArtid());
        MyApplication.getAppData().setMessageList(adapter.getArticleList().get(index).getMsg());
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VAttractionComments()).addToBackStack("AttractionSteaming").commit();
    }

    private IMainActivity iMainActivity;
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity)context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void handleLikeOrUnlike(String artid, String isLike, int num) {
        pAttractionSteaming.sendLikeOrUnlike(artid, isLike, num);
    }

    @Override
    public void refreshAdapter(String count, int num) {
        adapter.refreshAdapter(count, num);
    }

    @Override
    public void handleClickPhoto(List<String> photo) {
        MyApplication.getAppData().setPhotoList(photo);
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoView()).addToBackStack("AttractionSteaming").commit();
    }

    @Override
    public void handleClickPhoto(int index) {}

    @Override
    public void handleLinkClick(String attractionId) {
        iMainActivity.saveAttractionId(attractionId);
        MyApplication.getAppData().setNeedScrollToTop(true);
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void handleDeleteComment(int index) {
        pAttractionSteaming.handleDelete(index);
    }

    @Override
    public void handleEdit(int index, boolean isComment) {
        pAttractionSteaming.handleEdit(index, isComment);
    }

    @Override
    public void toEditCommentPage() {
        MyApplication.getAppData().setFromAttractionSteaming(true);
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VLeaveComments()).addToBackStack("AttractionSteaming").commit();
    }

    @Override
    public void toEditTagPage() {
        MyApplication.getAppData().setFromAttractionSteaming(true);
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VTag()).addToBackStack("AttractionSteaming").commit();
    }

    @Override
    public void finishDeleteComment() {
        adapter.init(MyApplication.getAppData().getArticleList());
    }
}
