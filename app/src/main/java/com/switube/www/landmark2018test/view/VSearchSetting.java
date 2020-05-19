package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxSeekBar;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AFeatures;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;
import com.switube.www.landmark2018test.presenter.PSearchSetting;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVSearchSetting;

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
public class VSearchSetting extends Fragment implements IVSearchSetting {
    private PSearchSetting pSearchSetting;
    private int distance = 0;

    @BindView(R.id.recyclerInFindSetting)
    RecyclerView mRecyclerView;
    @BindView(R.id.seekBarInFindSetting)
    SeekBar mSeekBar;
    @BindViews({R.id.textBackInFindSetting, R.id.textOkInFindSetting,
            R.id.textLeftInFindSetting, R.id.textCenterLeftInFindSetting, R.id.textCenterInFindSetting,
            R.id.textCenterRightInFindSetting, R.id.textRightInFindSetting})
    List<TextView> mTextList;

    private Unbinder mUnbinder;
    private AFeatures aFeatures;

    public VSearchSetting() {
        pSearchSetting = new PSearchSetting(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
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
        View view = inflater.inflate(R.layout.fragment_v_search_setting, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        pSearchSetting.getDataOne(mIMainActivity.getSelectedAttractionType().getMsid());
        if (MyApplication.getAppData().isUsingSettingData()) {
            handleDistance(MyApplication.getAppData().getDistanceForSetting());
        } else {
            mSeekBar.setProgress(100);
        }
        RxView.clicks(mTextList.get(0))
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
        RxView.clicks(mTextList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setUsingSettingData(true);
                        MyApplication.getAppData().seteFeaturesListForSetting(aFeatures.getServiceItemEntities());
                        MyApplication.getAppData().setDistanceForSetting(distance);
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxSeekBar.userChanges(mSeekBar)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Integer integer) {
                        handleDistance(integer);
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
        aFeatures = new AFeatures(classEntities, termEntities, itemEntities, mIMainActivity.getSelectedAttractionType().getMstitle_tw(), false);
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

    private void handleDistance(int integer) {
        if (integer > 0 && integer <= 13) {
            distance = 5;
            mSeekBar.setProgress(1);
            mTextList.get(2).setTextColor(Color.parseColor("#6398d9"));
            mTextList.get(3).setTextColor(Color.parseColor("#808080"));
            mTextList.get(4).setTextColor(Color.parseColor("#808080"));
            mTextList.get(5).setTextColor(Color.parseColor("#808080"));
            mTextList.get(6).setTextColor(Color.parseColor("#808080"));
        } else if (integer > 13 && integer <= 38) {
            distance = 20;
            mSeekBar.setProgress(25);
            mTextList.get(2).setTextColor(Color.parseColor("#808080"));
            mTextList.get(3).setTextColor(Color.parseColor("#6398d9"));
            mTextList.get(4).setTextColor(Color.parseColor("#808080"));
            mTextList.get(5).setTextColor(Color.parseColor("#808080"));
            mTextList.get(6).setTextColor(Color.parseColor("#808080"));
        } else if (integer > 38 && integer <= 63) {
            distance = 40;
            mSeekBar.setProgress(50);
            mTextList.get(2).setTextColor(Color.parseColor("#808080"));
            mTextList.get(3).setTextColor(Color.parseColor("#808080"));
            mTextList.get(4).setTextColor(Color.parseColor("#6398d9"));
            mTextList.get(5).setTextColor(Color.parseColor("#808080"));
            mTextList.get(6).setTextColor(Color.parseColor("#808080"));
        } else if (integer > 63 && integer <= 88) {
            distance = 80;
            mSeekBar.setProgress(75);
            mTextList.get(2).setTextColor(Color.parseColor("#808080"));
            mTextList.get(3).setTextColor(Color.parseColor("#808080"));
            mTextList.get(4).setTextColor(Color.parseColor("#808080"));
            mTextList.get(5).setTextColor(Color.parseColor("#6398d9"));
            mTextList.get(6).setTextColor(Color.parseColor("#808080"));
        } else if (integer > 88) {
            distance = 160;
            mSeekBar.setProgress(100);
            mTextList.get(2).setTextColor(Color.parseColor("#808080"));
            mTextList.get(3).setTextColor(Color.parseColor("#808080"));
            mTextList.get(4).setTextColor(Color.parseColor("#808080"));
            mTextList.get(5).setTextColor(Color.parseColor("#808080"));
            mTextList.get(6).setTextColor(Color.parseColor("#6398d9"));
        }
    }
}
