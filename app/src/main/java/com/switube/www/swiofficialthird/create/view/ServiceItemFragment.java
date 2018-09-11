package com.switube.www.swiofficialthird.create.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.CreateAttractionEntity;
import com.switube.www.swiofficialthird.create.ServiceItemEntity;
import com.switube.www.swiofficialthird.create.adapter.ServiceItemAdapter;
import com.switube.www.swiofficialthird.create.presenter.ServiceItemPresenter;
import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.info.view.InfoFragment;
import com.switube.www.swiofficialthird.util.ItemDecorationUtil;

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
public class ServiceItemFragment extends Fragment implements IServiceItem {
    private ServiceItemPresenter mServiceItemPresenter;
    public ServiceItemFragment() {
        mServiceItemPresenter = new ServiceItemPresenter(this);
    }

    private Unbinder mUnbinder;
    @BindView(R.id.recyclerInServiceItem)
    RecyclerView mRecyclerView;
    @BindViews({R.id.textBackInServiceItem, R.id.textNextInServiceItem, R.id.textTitleInServiceItem, R.id.textMessageInServiceItem})
    List<TextView> mTextViews;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_item, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        /*if (mIMainActivity.getSelectedAttractionType().getMmid().equals("C")) {
            sendCreateData();
        } else {
            mServiceItemPresenter.getDataOne(container.getContext(), mIMainActivity.getSelectedAttractionType().getMsid());
        }*/
        mServiceItemPresenter.getDataOne(container.getContext(), mIMainActivity.getSelectedAttractionType().getMsid());
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
        RxView.clicks(mTextViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        sendCreateData();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }

    private ServiceItemAdapter mServiceItemAdapter;
    @Override
    public void init(List<AttractionClassEntity> classEntities, List<AttractionTermEntity> termEntities, List<AttractionItemEntity> itemEntities) {
        mServiceItemAdapter = new ServiceItemAdapter(getContext(), classEntities, termEntities, itemEntities, mIMainActivity.getSelectedAttractionType().getMstitle_tw());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 10, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                String title = mServiceItemAdapter.getServiceItemEntities().get(position).getTitle();
                String from = mServiceItemAdapter.getServiceItemEntities().get(position).getFrom();
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
        mRecyclerView.setAdapter(mServiceItemAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void handleToFinish() {
        int count = getFragmentManager().getBackStackEntryCount();
        for (int i = 1; i < count; i++) {
            FragmentManager.BackStackEntry backStackEntry = getFragmentManager().getBackStackEntryAt(i);
            getFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new InfoFragment()).commit();
    }

    private void sendCreateData() {
        /*if (mIMainActivity.getSelectedAttractionType().getMmid().equals("C")) {
            List<ServiceItemEntity> serviceItemEntities = new ArrayList<>();
            mServiceItemPresenter.sendCreateData(getContext(), mIMainActivity.getCreateData(),
                    serviceItemEntities, mIMainActivity.getSelectedAttractionType(), mIMainActivity.getSelectedPhoto());
        } else {
            mServiceItemPresenter.sendCreateData(getContext(), mIMainActivity.getCreateData(),
                    mServiceItemAdapter.getServiceItemEntities(), mIMainActivity.getSelectedAttractionType(), mIMainActivity.getSelectedPhoto());
        }*/
        mServiceItemPresenter.sendCreateData(getContext(), mIMainActivity.getCreateData(),
                mServiceItemAdapter.getServiceItemEntities(), mIMainActivity.getSelectedAttractionType(), mIMainActivity.getSelectedPhoto());
    }
}
