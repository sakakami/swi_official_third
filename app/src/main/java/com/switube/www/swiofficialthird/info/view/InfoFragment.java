package com.switube.www.swiofficialthird.info.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerViewAdapter;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.info.InterfaceInfo;
import com.switube.www.swiofficialthird.info.adapter.InfoAdapter;
import com.switube.www.swiofficialthird.map.view.AttractionSelectFragment;

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
public class InfoFragment extends Fragment implements InterfaceInfo {


    public InfoFragment() {
        // Required empty public constructor
    }

    private Unbinder mUnbinder;
    @BindView(R.id.textBackInInfo) TextView mTextBack;
    @BindView(R.id.recyclerInInfo) RecyclerView mRecyclerView;
    @BindView(R.id.textTitleInInfo)
    TextView mTextTitle;
    @BindView(R.id.viewBarInInfo)
    View mView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        InfoAdapter infoAdapter = new InfoAdapter(container.getContext(), this);
        mRecyclerView.setAdapter(infoAdapter);
        RxRecyclerView.scrollEvents(mRecyclerView)
                .subscribe(new Observer<RecyclerViewScrollEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(RecyclerViewScrollEvent recyclerViewScrollEvent) {
                        if (linearLayoutManager.findFirstVisibleItemPosition() > 1) {
                            mView.setVisibility(View.VISIBLE);
                            mTextTitle.setVisibility(View.VISIBLE);
                        } else {
                            mView.setVisibility(View.INVISIBLE);
                            mTextTitle.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextBack)
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
        return view;
    }

    @Override
    public void handleSwitchPage(int i) {
        switch (i) {
            case 0:
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new AttractionSelectFragment()).addToBackStack("info").commit();
                break;
            case 1:
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new PhotoViewFragment()).addToBackStack("info").commit();
                break;
            case 2:
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new MessageListFragment()).addToBackStack("info").commit();
                break;
            case 3:
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new MessageRepliesFragment()).addToBackStack("info").commit();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
