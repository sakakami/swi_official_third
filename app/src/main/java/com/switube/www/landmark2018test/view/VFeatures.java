package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AFeatures;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.presenter.PFeatures;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVFeatures;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
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
public class VFeatures extends Fragment implements IVFeatures {
    @BindView(R.id.layoutInServiceItem)
    RelativeLayout layoutForCreating;
    private PFeatures pFeatures;
    private Unbinder mUnbinder;
    private boolean canClick = true;
    @BindView(R.id.recyclerInServiceItem)
    RecyclerView mRecyclerView;
    @BindViews({R.id.textBackInServiceItem, R.id.textNextInServiceItem, R.id.textTitleInServiceItem, R.id.textMessageInServiceItem})
    List<TextView> mTextViews;
    private AFeatures aFeatures;

    public VFeatures() {
        pFeatures = new PFeatures(this);
    }

    private IMainActivity mIMainActivity;

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_features, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        pFeatures.getDataOne(mIMainActivity.getSelectedAttractionType().getMsid());
        RxView.clicks(mTextViews.get(0))
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
        RxView.clicks(mTextViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            if (MyApplication.getAppData().isFormEditAttractionForType()) {
                                pFeatures.sendEditData(MyApplication.getAppData().getIsEdit(), mIMainActivity.getAttractionId(), aFeatures.getServiceItemEntities());
                            } else {
                                AlertDialogUtil.getInstance()
                                        .initDialogBuilder(getContext(), LayoutInflater.from(getContext()).inflate(R.layout.dialog_item_saving, null));
                                AlertDialogUtil.getInstance().showAlertDialog();
                                sendCreateData();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void init(List<AttractionClassEntity> classEntities, List<AttractionTermEntity> termEntities, List<AttractionItemEntity> itemEntities) {
        aFeatures = new AFeatures(classEntities, termEntities, itemEntities, mIMainActivity.getSelectedAttractionType().getMstitle_tw(), true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 10, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                String title = aFeatures.getServiceItemEntities().get(position).getTitle();
                String from = aFeatures.getServiceItemEntities().get(position).getFrom();
                if (from.equals("t") || from.equals("term") || from.equals("n")) {
                    return 10;
                } else {
                    if (title.length() <= 2) {
                        return 3;
                    } else if (title.length() <= 4) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
        });
        mRecyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 8, 0, 4, 4));
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(aFeatures);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void handleToFinish(String spid, boolean canToInfo) {
        mIMainActivity.setSelectedAttractionType(null);
        mIMainActivity.saveAttractionId(spid);
        MyApplication.getAppData().setTubeUrl("");
        MyApplication.getAppData().getSelectedPhotos().clear();
        MyApplication.getAppData().setCreateNewAttractionFailure(!canToInfo);
        AlertDialogUtil.getInstance().clearAlertDialog();
        if (canToInfo) {
            layoutForCreating.setVisibility(View.GONE);
            int count = getParentFragmentManager().getBackStackEntryCount();
            for (int i = 1; i < count; i++) {
                FragmentManager.BackStackEntry backStackEntry = getParentFragmentManager().getBackStackEntryAt(i);
                getParentFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VInfo()).commit();
        } else {
            getParentFragmentManager().popBackStack("Map", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void handleTimeOutError() {
        mIMainActivity.setSelectedAttractionType(null);
        mIMainActivity.saveAttractionId("");
        layoutForCreating.setVisibility(View.GONE);
        int count = getParentFragmentManager().getBackStackEntryCount();
        for (int i = 1; i < count; i++) {
            FragmentManager.BackStackEntry backStackEntry = getParentFragmentManager().getBackStackEntryAt(i);
            getParentFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMap()).commit();
    }

    @Override
    public void finishSend() {
        MyApplication.getAppData().setFormEditAttractionForType(false);
        MyApplication.getAppData().setFromEditAttraction(false);
        MyApplication.getAppData().setIsEdit(new ArrayList<>());
        MyApplication.getAppData().getSelectedPhotos().clear();
        AlertDialogUtil.getInstance().clearAlertDialog();
        getParentFragmentManager().popBackStack("Info", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void sendCreateData() {
        boolean type = mIMainActivity.getSelectedAttractionType() != null;
        boolean createData = mIMainActivity.getCreateData() != null;
        boolean item = aFeatures.getServiceItemEntities().size() > 0;
        if (type && createData && item) {
            canClick = false;
            layoutForCreating.setVisibility(View.VISIBLE);
            pFeatures.sendCreateData(mIMainActivity.getCreateData(),
                    aFeatures.getServiceItemEntities(), mIMainActivity.getSelectedAttractionType(),
                    MyApplication.getAppData().getSelectedPhotos());
        } else {
            AlertDialogUtil.getInstance().initDialogBuilder(getContext(),
                    R.string.create_action_Data_null,
                    R.string.global_ok,
                    (dialogInterface, i) -> {
                    });
            AlertDialogUtil.getInstance().showAlertDialog();
        }
    }
}
