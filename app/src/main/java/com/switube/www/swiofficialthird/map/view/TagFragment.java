package com.switube.www.swiofficialthird.map.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.map.InterfaceTag;
import com.switube.www.swiofficialthird.map.adapter.TagAdapter;
import com.switube.www.swiofficialthird.map.adapter.TagSelectedAdapter;
import com.switube.www.swiofficialthird.map.adapter.TagTypeAdapter;
import com.switube.www.swiofficialthird.map.presenter.TagPresenter;

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
public class TagFragment extends Fragment implements InterfaceTag {

    private TagPresenter mTagFragmentPresenter;
    public TagFragment() {
        mTagFragmentPresenter = new TagPresenter();
    }

    private TagSelectedAdapter mTagSelectedAdapter;
    private TagAdapter mTagAdapter;
    private Unbinder mUnbinder;
    private boolean inSearch = false;
    private boolean isShow = false;
    @BindViews({R.id.textBackInTag, R.id.textTitleInTag, R.id.textNextInTag})
    List<TextView> mTextViews;
    @BindViews({R.id.recyclerSelectedInTag, R.id.recyclerTypeInTag, R.id.recyclerTagInTag})
    List<RecyclerView> mRecyclerViews;
    @BindView(R.id.searchInTag)
    SearchView mSearchView;
    @BindView(R.id.editNewTagInTag)
    EditText mEditCreatTag;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tag, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mSearchView.setIconifiedByDefault(false);
        RxView.clicks(mTextViews.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (mTagFragmentPresenter.handleTag(container.getContext(), mTagSelectedAdapter.getTitleA(), mTagSelectedAdapter.getTitleB())) {
                            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new MessageFragment()).addToBackStack("tag").commit();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
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
        RxSearchView.queryTextChanges(mSearchView)
                .skipInitialValue()
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(CharSequence charSequence) {
                        Log.e("searchView", charSequence.toString());
                        inSearch = true;
                        handleShowCreateTag();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxTextView.textChanges(mEditCreatTag).skipInitialValue().subscribe(new Observer<CharSequence>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(CharSequence charSequence) {
                Log.e("editText", charSequence.toString());
            }

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        });
        mRecyclerViews.get(0).setLayoutManager(new LinearLayoutManager(container.getContext()));
        mTagSelectedAdapter = new TagSelectedAdapter(container.getContext(), this);
        mRecyclerViews.get(0).setAdapter(mTagSelectedAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerViews.get(1).setLayoutManager(linearLayoutManager);
        mRecyclerViews.get(1).setAdapter(new TagTypeAdapter(container.getContext()));
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(container.getContext());
        flexboxLayoutManager.setJustifyContent(JustifyContent.CENTER);
        mRecyclerViews.get(2).setLayoutManager(flexboxLayoutManager);
        mTagAdapter = new TagAdapter(container.getContext(), this);
        mRecyclerViews.get(2).setAdapter(mTagAdapter);
        return view;
    }

    @Override
    public void handleAddTagA(String tag) {
        mTagSelectedAdapter.handleAddTagA(tag);
    }

    @Override
    public void handleAddTagB(String tag) {
        mTagSelectedAdapter.handleAddTagB(tag);
    }

    @Override
    public void handleDefault() {
        mTagAdapter.handleDefault();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void handleTagSelected() {
        isShow = false;
        inSearch = false;
        mEditCreatTag.setVisibility(View.GONE);
        mRecyclerViews.get(0).setVisibility(View.VISIBLE);
        mRecyclerViews.get(1).setVisibility(View.VISIBLE);
    }

    private void handleShowCreateTag() {
        if (!isShow && inSearch) {
            isShow = true;
            mEditCreatTag.setVisibility(View.VISIBLE);
            mRecyclerViews.get(0).setVisibility(View.GONE);
            mRecyclerViews.get(1).setVisibility(View.GONE);
        }
    }
}
