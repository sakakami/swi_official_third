package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ASteamingPage;
import com.switube.www.landmark2018test.adapter.callback.IAAttractionSteaming;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GPersonalSteaming;
import com.switube.www.landmark2018test.presenter.PPersonalSteaming;
import com.switube.www.landmark2018test.view.callback.IFragmentBackHandler;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVPersonalSteaming;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class VPersonalSteaming extends Fragment implements IVPersonalSteaming, IAAttractionSteaming {
    @BindView(R.id.recyclerInPersonalSteaming)
    RecyclerView recyclerView;
    @BindView(R.id.viewBarInPersonalSteaming)
    View viewBar;
    @BindViews({R.id.textBackInPersonalSteaming, R.id.textTitleInPersonalSteaming})
    List<TextView> textViewList;
    private PPersonalSteaming pPersonalSteaming;
    private Unbinder unbinder;
    private ASteamingPage aSteamingPage;
    private List<GInfoData.Article> articleList;
    private List<String> photoList = new ArrayList<>();
    private DisposableObserver<GPersonalSteaming> disposableObserver;
    public VPersonalSteaming() {
        pPersonalSteaming = new PPersonalSteaming(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_personal_steaming, container, false);
        unbinder = ButterKnife.bind(this, view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        disposableObserver = new DisposableObserver<GPersonalSteaming>() {
            @Override
            public void onNext(GPersonalSteaming gPersonalSteaming) {
                pPersonalSteaming.handPersonalData(gPersonalSteaming);
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };
        pPersonalSteaming.getPersonalData(MyApplication.getAppData().getMaid(),
                MyApplication.getAppData().getWsid(), MyApplication.getAppData().getType(),
                MyApplication.getAppData().getPrivacy(), disposableObserver);
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setFromMapToPersonal(false);
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        RxRecyclerView.scrollEvents(recyclerView)
                .subscribe(new Observer<RecyclerViewScrollEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RecyclerViewScrollEvent recyclerViewScrollEvent) {
                        if (linearLayoutManager.findFirstVisibleItemPosition() > 1) {
                            textViewList.get(1).setVisibility(View.VISIBLE);
                            viewBar.setVisibility(View.VISIBLE);
                        } else {
                            textViewList.get(1).setVisibility(View.INVISIBLE);
                            viewBar.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return view;
    }

    @Override
    public void init(GPersonalSteaming gPersonalSteaming) {
        articleList = gPersonalSteaming.getData();
        photoList = gPersonalSteaming.getPicData();
        photoList.add(0, gPersonalSteaming.getImage());
        textViewList.get(1).setText(gPersonalSteaming.getName());
        aSteamingPage = new ASteamingPage(gPersonalSteaming.getName(), gPersonalSteaming.getImage(), gPersonalSteaming.getData(), this);
        recyclerView.setAdapter(aSteamingPage);
    }

    @Override
    public void handleSwitchPage(int index) {
        MyApplication.getAppData().setArtid(articleList.get(index).getArtid());
        MyApplication.getAppData().setMessageList(articleList.get(index).getMsg());
        MyApplication.getAppData().setArticleList(articleList);
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VAttractionComments()).addToBackStack("PersonalSteaming").commit();
    }

    @Override
    public void handleLikeOrUnlike(String artid, String isLike, int num) {
        pPersonalSteaming.sendLikeOrUnlike(artid, isLike, num);
    }

    @Override
    public void handleLinkClick(String attractionId) {
        iMainActivity.saveAttractionId(attractionId);
        if (MyApplication.getAppData().isFromMapToPersonal()) {
            MyApplication.getAppData().setNeedScrollToTop(true);
            getFragmentManager().popBackStack();
        } else {
            MyApplication.getAppData().setFromMapToPersonal(false);
            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VInfo()).commit();
        }
    }

    @Override
    public void handleClickPhoto(List<String> photo) {}

    @Override
    public void refreshAdapter(String count, int num) {
        aSteamingPage.refreshAdapter(count, num);
    }

    @Override
    public void handleClickPhoto(int index) {
        if (index == -1) {
            MyApplication.getAppData().setPhotoList(photoList);
        } else {
            MyApplication.getAppData().setPhotoList(articleList.get(index).getImg());
        }
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoView()).addToBackStack("PersonalSteaming").commit();
    }

    private IMainActivity iMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity)context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (disposableObserver != null) {
            disposableObserver.dispose();
        }
    }

    @Override
    public void handleDeleteComment(int index) {
        pPersonalSteaming.handleDelComment(articleList, index);
    }

    @Override
    public void handleEdit(int index, boolean isComment) {
        pPersonalSteaming.handleEditData(articleList, index, isComment);
    }

    @Override
    public void toEditCommentPage() {
        MyApplication.getAppData().setFromPersonalSteaming(true);
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VLeaveComments()).addToBackStack("PersonalSteaming").commit();
    }

    @Override
    public void toEditTagPage() {
        MyApplication.getAppData().setFromPersonalSteaming(true);
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VTag()).commit();
    }

    @Override
    public void finishDel(int index) {
        articleList.remove(index);
        aSteamingPage.refreshAdapter(index);
    }
}
