package com.switube.www.swiofficialthird.create.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.adapter.AttractionTypeAdapter;
import com.switube.www.swiofficialthird.create.presenter.AttractionTypePresenter;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.map.adapter.AttractionAdapter;
import com.switube.www.swiofficialthird.map.view.PlaceListFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionTypeFragment extends Fragment implements IAttractionType {
    public static boolean isClickHotKey = false;
    private AttractionTypePresenter mAttractionTypePresenter;
    public AttractionTypeFragment() {
        mAttractionTypePresenter = new AttractionTypePresenter(this);
    }

    @BindViews({R.id.textBackInAttractionType, R.id.textTitleInAttractionType})
    List<TextView> mTextViews;
    @BindView(R.id.recyclerInAttractionType)
    RecyclerView mRecyclerView;
    private Unbinder mUnbinder;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attraction_type, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mAttractionTypePresenter.getListData(container.getContext());
        return view;
    }

    @Override
    public void init(List<AttractionModeEntity> attractionModeEntities, List<AttractionStyleEntity> attractionStyleEntities) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new AttractionTypeAdapter(getContext(), attractionModeEntities,
                attractionStyleEntities, this));
    }

    @Override
    public void handleSelectType(AttractionStyleEntity attractionStyleEntity) {
        mIMainActivity.setSelectedAttractionType(attractionStyleEntity);
        if (isClickHotKey) {
            isClickHotKey = false;
            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new PlaceListFragment()).addToBackStack("type").commit();
        } else {
            isClickHotKey = false;
            getFragmentManager().popBackStack();
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
}
