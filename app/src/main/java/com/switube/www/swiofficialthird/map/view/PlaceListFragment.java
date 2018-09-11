package com.switube.www.swiofficialthird.map.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.view.AttractionTypeFragment;
import com.switube.www.swiofficialthird.map.adapter.PlaceListAdapter;
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
public class PlaceListFragment extends Fragment {


    public PlaceListFragment() {}

    @BindView(R.id.recyclerInPlaceList)
    RecyclerView mRecyclerView;
    @BindViews({R.id.textBackInPlaceList, R.id.textTag1InPlaceList, R.id.textTag2InPlaceList, R.id.textTag3InPlaceList})
    List<TextView> mTextList;
    @BindView(R.id.editSearchInPlaceList)
    EditText mEditText;
    @BindViews({R.id.imageMapInPlaceList, R.id.imageSettingInPlaceList})
    List<ImageView> mImageList;
    private Unbinder mUnbinder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        init();
        RxView.clicks(mTextList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        AttractionTypeFragment.isClickHotKey = true;
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new FindSettingFragment()).addToBackStack("placeList").commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 8, 0, 0, 0));
        mRecyclerView.setAdapter(new PlaceListAdapter());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
