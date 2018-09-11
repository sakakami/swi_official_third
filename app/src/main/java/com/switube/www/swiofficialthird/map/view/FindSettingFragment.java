package com.switube.www.swiofficialthird.map.view;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxSeekBar;
import com.jakewharton.rxbinding2.widget.SeekBarChangeEvent;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.adapter.ServiceItemAdapter;
import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.map.presenter.FindSettingPresenter;
import com.switube.www.swiofficialthird.util.ItemDecorationUtil;

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
public class FindSettingFragment extends Fragment implements IFindSettingFragment {
    private FindSettingPresenter mFindSettingPresenter;
    public FindSettingFragment() {
        mFindSettingPresenter = new FindSettingPresenter(this);
    }

    @BindView(R.id.recyclerInFindSetting)
    RecyclerView mRecyclerView;
    @BindView(R.id.seekBarInFindSetting)
    SeekBar mSeekBar;
    @BindViews({R.id.textBackInFindSetting, R.id.textOkInFindSetting,
            R.id.textLeftInFindSetting, R.id.textCenterLeftInFindSetting, R.id.textCenterInFindSetting,
            R.id.textCenterRightInFindSetting, R.id.textRightInFindSetting})
    List<TextView> mTextList;

    private Unbinder mUnbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_setting, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mFindSettingPresenter.getDataOne(container.getContext(), mIMainActivity.getSelectedAttractionType().getMsid());
        mSeekBar.setProgress(1);
        RxView.clicks(mTextList.get(0))
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
        RxView.clicks(mTextList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new MapFragment()).addToBackStack("placeList").commit();
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
                        Log.e("seekBar", String.valueOf(integer));
                        if (integer > 0 && integer <= 13) {
                            mSeekBar.setProgress(1);
                            mTextList.get(2).setTextColor(Color.parseColor("#6398d9"));
                            mTextList.get(3).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(4).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(5).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(6).setTextColor(Color.parseColor("#808080"));
                        } else if (integer > 13 && integer <= 38) {
                            mSeekBar.setProgress(25);
                            mTextList.get(2).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(3).setTextColor(Color.parseColor("#6398d9"));
                            mTextList.get(4).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(5).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(6).setTextColor(Color.parseColor("#808080"));
                        } else if (integer > 38 && integer <= 63) {
                            mSeekBar.setProgress(50);
                            mTextList.get(2).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(3).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(4).setTextColor(Color.parseColor("#6398d9"));
                            mTextList.get(5).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(6).setTextColor(Color.parseColor("#808080"));
                        } else if (integer > 63 && integer <= 88) {
                            mSeekBar.setProgress(75);
                            mTextList.get(2).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(3).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(4).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(5).setTextColor(Color.parseColor("#6398d9"));
                            mTextList.get(6).setTextColor(Color.parseColor("#808080"));
                        } else if (integer > 88) {
                            mSeekBar.setProgress(100);
                            mTextList.get(2).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(3).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(4).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(5).setTextColor(Color.parseColor("#808080"));
                            mTextList.get(6).setTextColor(Color.parseColor("#6398d9"));
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
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
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
}
