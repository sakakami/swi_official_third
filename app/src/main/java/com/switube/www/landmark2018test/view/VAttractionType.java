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
import com.switube.www.landmark2018test.adapter.AAttractionType;
import com.switube.www.landmark2018test.adapter.callback.IAAttractionType;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.presenter.PAttractionType;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVAttractionType;

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
public class VAttractionType extends Fragment implements IVAttractionType, IAAttractionType {
    static boolean isClickHotKey = false;
    private PAttractionType pAttractionType;

    public VAttractionType() {
        pAttractionType = new PAttractionType(this);
    }

    @BindViews({R.id.textBackInAttractionType, R.id.textTitleInAttractionType})
    List<TextView> mTextViews;
    @BindView(R.id.recyclerInAttractionType)
    RecyclerView mRecyclerView;
    private Unbinder mUnbinder;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_attraction_type, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        pAttractionType.getListData();
        return view;
    }

    @Override
    public void init(List<AttractionModeEntity> attractionModeEntities, List<AttractionStyleEntity> attractionStyleEntities) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new AAttractionType(attractionModeEntities,
                attractionStyleEntities, this));
        RxView.clicks(mTextViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        VMap.isAttractionList = false;
                        isClickHotKey = false;
                        MyApplication.getAppData().setUsingSettingData(false);
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void handleSelectType(AttractionStyleEntity attractionStyleEntity) {
        mIMainActivity.setSelectedAttractionType(attractionStyleEntity);
        if (MyApplication.getAppData().isFormEditAttractionForType()) {
            MyApplication.getAppData().getgInfoData().getData().get(0).setMsid(attractionStyleEntity.getMsid());
            MyApplication.getAppData().getgInfoData().getData().get(0).setMmid(attractionStyleEntity.getMmid());
            MyApplication.getAppData().getgInfoData().getData().get(0).setMmspid(attractionStyleEntity.getMmspid());
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    MyApplication.getAppData().setStyle(attractionStyleEntity.getMstitle_tw());
                    break;
                case 2:
                    MyApplication.getAppData().setStyle(attractionStyleEntity.getMstitle_ch());
                    break;
                case 3:
                    MyApplication.getAppData().setStyle(attractionStyleEntity.getMstitle_jp());
                    break;
                default:
                    MyApplication.getAppData().setStyle(attractionStyleEntity.getMstitle_en());
                    break;
            }
        }
        if (isClickHotKey) {
            isClickHotKey = false;
            MyApplication.getAppData().setUsingSettingData(false);
            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VSearchAttraction()).addToBackStack("AttractionType").commit();
        } else {
            getParentFragmentManager().popBackStack();
        }
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
