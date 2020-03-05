package com.switube.www.landmark2018test.view;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ALeaveCommnets;
import com.switube.www.landmark2018test.adapter.ASpinner;
import com.switube.www.landmark2018test.adapter.ATagSelected;
import com.switube.www.landmark2018test.adapter.callback.IALeaveComments;
import com.switube.www.landmark2018test.presenter.PLeaveComments;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IFragmentBackHandler;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVLeaveComments;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
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
public class VLeaveComments extends Fragment implements IVLeaveComments, IALeaveComments, IFragmentBackHandler {
    public static boolean IS_IN_MAP = false;
    @BindViews({R.id.textPostInMessage, R.id.textBackInMessage})
    List<TextView> mTextViews;
    @BindView(R.id.layoutInMessage)
    RelativeLayout relativeLayout;
    private PLeaveComments pLeaveComments;
    private boolean canPost = true;
    private Unbinder mUnbinder;
    private IMainActivity mIMainActivity;
    @BindView(R.id.recyclerInMessage)
    RecyclerView recyclerView;
    private ALeaveCommnets aLeaveCommnets;
    public VLeaveComments() {
        pLeaveComments = new PLeaveComments(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_v_leave_comments, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        aLeaveCommnets = new ALeaveCommnets(this);
        recyclerView.setAdapter(aLeaveCommnets);
        RxView.clicks(mTextViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().isEditMode()) {
                            MyApplication.getAppData().geteEditComment().setMessage(aLeaveCommnets.getMessage());
                            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VTag()).commit();
                        } else {
                            getFragmentManager().popBackStackImmediate();
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
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().isEditMode()) {
                            if (MyApplication.getAppData().getSelectedPhotos().size() > 0) {
                                MyApplication.getAppData().setEditCommentMode(true);
                            }
                            if (aLeaveCommnets.getDeleteImage().size() > 0) {
                                MyApplication.getAppData().setEditCommentMode(true);
                                MyApplication.getAppData().geteEditComment().setImageDel(aLeaveCommnets.getDeleteImage());
                            }
                            MyApplication.getAppData().geteEditComment().setMessage(aLeaveCommnets.getMessage());
                            pLeaveComments.sendEditData();
                        } else {
                            if (canPost) {
                                canPost = false;
                                relativeLayout.setVisibility(View.VISIBLE);
                                pLeaveComments.handleDataToSend(getContext(), mIMainActivity.getAttractionId(),
                                        MyApplication.getAppData().getSelectedPhotos(), aLeaveCommnets.getPrivacy(), aLeaveCommnets.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return view;
    }

    @Override
    public void handleFinishSend() {
        MyApplication.getAppData().getSelectedPhotos().clear();
        SharePreferencesUtil.getInstance().getEditor().putString("selectedTagA", "finish");
        SharePreferencesUtil.getInstance().getEditor().putString("selectedTagB", "finish");
        ATagSelected.tagQBAEntities.clear();
        ATagSelected.tagQBNEntities.clear();
        MyApplication.getAppData().setMessage("");
        relativeLayout.setVisibility(View.GONE);
        if (MyApplication.getAppData().isEditMode()) {
            MyApplication.getAppData().setEditMode(false);
            MyApplication.getAppData().setEditCommentMode(false);
            MyApplication.getAppData().setEditTagMode(false);
            MyApplication.getAppData().seteEditComment(null);
        }
        if (IS_IN_MAP) {
            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VInfo()).commit();
        } else {
            if (MyApplication.getAppData().isFromAttractionSteaming()) {
                MyApplication.getAppData().setFromAttractionSteaming(false);
                getFragmentManager().popBackStack();
            } else if (MyApplication.getAppData().isFromPersonalSteaming()) {
                MyApplication.getAppData().setFromPersonalSteaming(false);
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPersonalSteaming()).commit();
            } else {
                getFragmentManager().popBackStack("Info", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) context;
    }

    @Override
    public void handleSelectPhoto() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            new RxPermissions(getActivity())
                    .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Observer<Permission>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Permission permission) {
                            if (permission.granted) {
                                if (MyApplication.getAppData().isEditMode()) {
                                    if (aLeaveCommnets.getPhotoSize() < 3) {
                                        if (aLeaveCommnets.getDeleteImage().size() > 0) {
                                            MyApplication.getAppData().geteEditComment().setImageDel(aLeaveCommnets.getDeleteImage());
                                        }
                                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("LeaveComments").commit();
                                    }
                                } else {
                                    getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("LeaveComments").commit();
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        } else {
            if (MyApplication.getAppData().isEditMode()) {
                if (aLeaveCommnets.getPhotoSize() < 3) {
                    if (aLeaveCommnets.getDeleteImage().size() > 0) {
                        MyApplication.getAppData().geteEditComment().setImageDel(aLeaveCommnets.getDeleteImage());
                    }
                    getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoList(), "photoFragment").addToBackStack("LeaveComments").commit();
                }
            } else {
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoList(), "photoFragment").addToBackStack("LeaveComments").commit();
            }
        }
    }

    @Override
    public boolean onBackPressed() {
        if (MyApplication.getAppData().isEditMode()) {
            MyApplication.getAppData().geteEditComment().setMessage(aLeaveCommnets.getMessage());
            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VTag()).commit();
        } else {
            getFragmentManager().popBackStack();
        }
        return true;
    }
}
