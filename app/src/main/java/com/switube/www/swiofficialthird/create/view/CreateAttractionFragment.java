package com.switube.www.swiofficialthird.create.view;


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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.CreateAttractionEntity;
import com.switube.www.swiofficialthird.create.adapter.CreateAttractionAdapter;
import com.switube.www.swiofficialthird.create.gson.AttractionDetailEntity;
import com.switube.www.swiofficialthird.create.presenter.CreateAttractionPresenter;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.map.view.PhotoFragment;

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
public class CreateAttractionFragment extends Fragment implements ICreateAttraction {
    public CreateAttractionFragment() {}

    @BindViews({R.id.textBackInCreateAttraction, R.id.textNextInCreateAttraction, R.id.textTitleInCreateAttraction})
    List<TextView> mTextViews;
    @BindView(R.id.recyclerInCreateAttraction)
    RecyclerView mRecyclerView;
    @BindView(R.id.progressBarInCreateAttraction)
    ProgressBar mProgressBar;
    @BindView(R.id.viewBarLockInCreateAttraction)
    View mView;
    private Unbinder mUnbinder;
    private CreateAttractionPresenter mCreateAttractionPresenter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_attraction, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mCreateAttractionPresenter = new CreateAttractionPresenter(this);
        if (mIMainActivity.getCreateData() == null) {
            Log.e("placeID", mIMainActivity.getPlaceId());
            mCreateAttractionPresenter.getAttractionDetail(mIMainActivity.getPlaceId(), mIMainActivity.getKey());
        } else {
            Log.e("placeID", "false");
            init(mIMainActivity.getCreateData());
        }
        RxView.clicks(mTextViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.setCreateData(null);
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
                        getFragmentManager().beginTransaction()
                                .replace(R.id.layoutContainer, new ServiceItemFragment()).addToBackStack("create")
                                .commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public AttractionStyleEntity getStyleEntity() {
        return mIMainActivity.getSelectedAttractionType();
    }

    @Override
    public void switchPage(int type) {
        saveCreateData();
        switch (type) {
            case 0:
                getFragmentManager().beginTransaction()
                        .replace(R.id.layoutContainer, new AttractionTypeFragment()).addToBackStack("create")
                        .commit();
                break;
            case 1:
                getFragmentManager().beginTransaction()
                        .replace(R.id.layoutContainer, new EditTimeFragment()).addToBackStack("create")
                        .commit();
                break;
            case 2:
                getFragmentManager().beginTransaction()
                        .replace(R.id.layoutContainer, new PhotoFragment()).addToBackStack("create")
                        .commit();
                break;
            default:
                break;
        }
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

    private CreateAttractionAdapter mCreateAttractionAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    @Override
    public void init(List<AttractionDetailEntity> detailEntities) {
        mCreateAttractionPresenter.init(detailEntities, mIMainActivity);
    }

    @Override
    public void init(CreateAttractionEntity entity) {
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mCreateAttractionAdapter = new CreateAttractionAdapter(getContext(), entity,this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mCreateAttractionAdapter);
    }

    public void saveCreateData() {
        mIMainActivity.setCreateData(mCreateAttractionAdapter.getmCreateData());
    }
}
