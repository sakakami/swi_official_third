package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ATag;
import com.switube.www.landmark2018test.adapter.ATagSelected;
import com.switube.www.landmark2018test.adapter.ATagType;
import com.switube.www.landmark2018test.adapter.callback.IATag;
import com.switube.www.landmark2018test.database.entity.TagQBAEntity;
import com.switube.www.landmark2018test.database.entity.TagQBNEntity;
import com.switube.www.landmark2018test.entity.ETagQB;
import com.switube.www.landmark2018test.presenter.PTag;
import com.switube.www.landmark2018test.view.callback.IFragmentBackHandler;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVTag;

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
public class VTag extends Fragment implements IATag, IVTag, IFragmentBackHandler {
    public static List<ETagQB> eTagQB = new ArrayList<>();
    @BindViews({R.id.textBackInTag, R.id.textTitleInTag, R.id.textNextInTag, R.id.textNameInTag})
    List<TextView> mTextViews;
    @BindView(R.id.editNewTagInTag)
    EditText mEditCreateTag;
    private PTag pTag;
    private ATagSelected aTagSelected;
    private Unbinder mUnbinder;
    private boolean inSearch = false;
    private ATag aTag;
    private boolean canInit = true;
    @BindViews({R.id.recyclerSelectedInTag, R.id.recyclerTypeInTag, R.id.recyclerTagInTag})
    List<RecyclerView> mRecyclerViews;
    @BindView(R.id.searchInTag)
    SearchView mSearchView;
    private List<TagQBNEntity> tagQBNEntities;
    private List<TagQBAEntity> tagQBAEntities;
    private IMainActivity iMainActivity;

    public VTag() {
        pTag = new PTag(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_tag, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mSearchView.setIconifiedByDefault(false);
        mTextViews.get(3).setText(iMainActivity.getPlaceName());
        RxView.clicks(mTextViews.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().isEditMode()) {
                            if (MyApplication.getAppData().isEditTagMode()) {
                                pTag.handleEditData(aTagSelected.getTitleA(), aTagSelected.getTitleB(), eTagQBList);
                            } else {
                                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VLeaveComments()).commit();
                            }
                        } else {
                            if (pTag.handleTag(aTagSelected.getTitleA(), aTagSelected.getTitleB())) {
                                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VLeaveComments()).addToBackStack("Tag").commit();
                            }
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
                        canInit = false;
                        MyApplication.getAppData().getSelectedPhotos().clear();
                        MyApplication.getAppData().setMessage("");
                        ATagSelected.tagQBAEntities.clear();
                        ATagSelected.tagQBNEntities.clear();
                        MyApplication.getAppData().setEditTagMode(false);
                        MyApplication.getAppData().setEditCommentMode(false);
                        MyApplication.getAppData().setEditMode(false);
                        if (MyApplication.getAppData().isFromPersonalSteaming()) {
                            Log.e("VTag => ", "is personal steaming");
                            MyApplication.getAppData().setFromPersonalSteaming(false);
                            getFragmentManager().popBackStack("PersonalSteaming", FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        } else {
                            Log.e("VTag => ", "is not personal steaming");
                            getFragmentManager().popBackStackImmediate();
                        }
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
                        if (charSequence.length() > 0) {
                            inSearch = true;
                            handleShowCreateTag(charSequence.toString());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        if (MyApplication.getAppData().isEditMode()) {
            pTag.getTagData(MyApplication.getAppData().geteEditComment().getMsid());
        } else {
            pTag.getTagData(iMainActivity.getPlaceId());
        }
        mRecyclerViews.get(0).setLayoutManager(new LinearLayoutManager(container.getContext()));
        aTagSelected = new ATagSelected(this);
        mRecyclerViews.get(0).setAdapter(aTagSelected);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(container.getContext());
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);
        mRecyclerViews.get(2).setLayoutManager(flexboxLayoutManager);
        aTag = new ATag(this);
        mRecyclerViews.get(2).setAdapter(aTag);
        return view;
    }

    @Override
    public void handleAddTagA(TagQBNEntity tagQBNEntity) {
        aTagSelected.handleAddTagA(tagQBNEntity);
        pTag.getQBAData(tagQBNEntity.getQbnid());
        MyApplication.getAppData().setEditTagMode(true);
    }

    @Override
    public void handleAddTagB(TagQBAEntity tagQBAEntity) {
        aTagSelected.handleAddTagB(tagQBAEntity);
        MyApplication.getAppData().setEditTagMode(true);
    }

    @Override
    public void handleDefault() {
        aTag.handleDefault();
    }

    @Override
    public void handleGetData(String qbid) {
        pTag.getQBNData(qbid);
    }

    private List<ETagQB> eTagQBList;
    @Override
    public void init(List<ETagQB> eTagQB) {
        eTagQBList = eTagQB;
        if (canInit) {
            VTag.eTagQB.clear();
            VTag.eTagQB.addAll(eTagQB);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerViews.get(1).setLayoutManager(linearLayoutManager);
            mRecyclerViews.get(1).setAdapter(new ATagType(eTagQB, this));
            handleGetData(eTagQB.get(0).getQbid());
        }
    }

    @Override
    public void initN(List<TagQBNEntity> tagQBNEntities) {
        if (!inSearch) {
            this.tagQBNEntities = tagQBNEntities;
        }
        aTag.setTagA(tagQBNEntities);
    }

    @Override
    public void initA(List<TagQBAEntity> tagQBAEntities) {
        if (!inSearch) {
            this.tagQBAEntities = tagQBAEntities;
        }
        aTag.setTagB(tagQBAEntities);
    }

    @Override
    public void refreshAdapter(List<TagQBNEntity> tagQBNEntities, List<TagQBAEntity> tagQBAEntities) {
        aTagSelected.refreshAdapter(tagQBNEntities, tagQBAEntities);
    }

    @Override
    public void finishSave() {
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VLeaveComments()).commit();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void handleTagSelected() {
        inSearch = false;
        mSearchView.setQuery("", false);
        mEditCreateTag.setVisibility(View.GONE);
        mRecyclerViews.get(0).setVisibility(View.VISIBLE);
        mRecyclerViews.get(1).setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onBackPressed() {
        canInit = false;
        MyApplication.getAppData().getSelectedPhotos().clear();
        MyApplication.getAppData().setMessage("");
        ATagSelected.tagQBAEntities.clear();
        ATagSelected.tagQBNEntities.clear();
        MyApplication.getAppData().setEditTagMode(false);
        MyApplication.getAppData().setEditCommentMode(false);
        MyApplication.getAppData().setEditMode(false);
        if (MyApplication.getAppData().isFromPersonalSteaming()) {
            MyApplication.getAppData().setFromPersonalSteaming(false);
            getFragmentManager().popBackStack("PersonalSteaming", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getFragmentManager().popBackStack();
        }
        return true;
    }

    private void handleShowCreateTag(String keyWord) {
        if (inSearch) {
            mRecyclerViews.get(0).setVisibility(View.GONE);
            mRecyclerViews.get(1).setVisibility(View.GONE);
            if (aTag.isTagA()) {
                pTag.searchQBN(tagQBNEntities, keyWord);
            } else {
                pTag.searchQBA(tagQBAEntities, keyWord);
            }
        }
    }
}
