package com.switube.www.landmark2018test.view;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AInfo;
import com.switube.www.landmark2018test.adapter.ASaveList;
import com.switube.www.landmark2018test.adapter.callback.IAInfo;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.presenter.PInfo;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.util.SignInUtil;
import com.switube.www.landmark2018test.view.callback.IFragmentBackHandler;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVInfo;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class VInfo extends Fragment implements IAInfo, IVInfo, IFragmentBackHandler {
    @BindView(R.id.textBackInInfo)
    TextView mTextBack;
    @BindView(R.id.recyclerInInfo)
    RecyclerView mRecyclerView;
    @BindView(R.id.textTitleInInfo)
    TextView mTextTitle;
    @BindViews({R.id.viewBarInInfo, R.id.viewBarLockInInfo, R.id.viewLineInInfo})
    List<View> viewList;
    @BindView(R.id.layoutInInfo)
    RelativeLayout relativeLayout;
    String url;
    private PInfo pInfo;
    private Unbinder mUnbinder;
    private LinearLayoutManager linearLayoutManager;
    private AInfo aInfo;
    private String location;
    private List<GInfoData.Article> articleList;
    private IMainActivity iMainActivity;
    public VInfo() {
        pInfo = new PInfo(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_info, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        viewList.get(1).setVisibility(View.VISIBLE);
        relativeLayout.setVisibility(View.VISIBLE);
        pInfo.getDetailData(iMainActivity.getAttractionId());
        linearLayoutManager = new LinearLayoutManager(container.getContext());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        RxRecyclerView.scrollEvents(mRecyclerView)
                .subscribe(new Observer<RecyclerViewScrollEvent>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RecyclerViewScrollEvent recyclerViewScrollEvent) {
                        if (linearLayoutManager.findFirstVisibleItemPosition() > 1) {
                            viewList.get(0).setVisibility(View.VISIBLE);
                            viewList.get(2).setVisibility(View.VISIBLE);
                            mTextTitle.setVisibility(View.VISIBLE);
                        } else {
                            viewList.get(0).setVisibility(View.INVISIBLE);
                            viewList.get(2).setVisibility(View.INVISIBLE);
                            mTextTitle.setVisibility(View.INVISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        RxView.clicks(mTextBack)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().isFromSearchAttraction()) {
                            MyApplication.getAppData().setFromSearchAttraction(false);
                            getFragmentManager().popBackStack();
                        } else if (MyApplication.getAppData().isFromMyCollection()) {
                            MyApplication.getAppData().setFromMyCollection(false);
                            getFragmentManager().popBackStack();
                        } else if (MyApplication.getAppData().isFromStrokeAttractionList()) {
                            MyApplication.getAppData().setFromStrokeAttractionList(false);
                            getFragmentManager().popBackStack();
                        } else if (MyApplication.getAppData().isFromStroke()) {
                            MyApplication.getAppData().setFromStroke(false);
                            getFragmentManager().popBackStack();
                        } else {
                            getFragmentManager().popBackStackImmediate("Map", FragmentManager.POP_BACK_STACK_INCLUSIVE);
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
    public void handleSwitchPage(int i) {
        Intent intent;
        switch (i) {
            case 0:
                if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                    new SignInUtil(getContext(), iMainActivity);
                } else {
                    VLeaveComments.IS_IN_MAP = false;
                    SharePreferencesUtil.getInstance().getEditor().putString("selectedTag", "finish").apply();
                    getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VTag()).addToBackStack("Info").commit();
                }
                break;
            case 1:
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoView()).addToBackStack("Info").commit();
                break;
            case 2:
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VAttractionSteaming()).addToBackStack("Info").commit();
                break;
            case 3:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
                break;
            case 5:
                VPhotoList.isFromInfo = true;
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    new RxPermissions(getActivity())
                            .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Observer<Permission>() {
                                @Override
                                public void onSubscribe(Disposable d) {}

                                @Override
                                public void onNext(Permission permission) {
                                    if (permission.granted) {
                                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                            new SignInUtil(getContext(), iMainActivity);
                                        } else {
                                            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("Info").commit();
                                        }
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {}

                                @Override
                                public void onComplete() {}
                            });
                } else {
                    if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                        new SignInUtil(getContext(), iMainActivity);
                    } else {
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("Info").commit();
                    }
                }
                break;
            case 6:
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VShowFeatures()).addToBackStack("Info").commit();
                break;
            case 7:
                getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VEditAttraction()).addToBackStack("Info").commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void handleLikeOrUnlike(String artid, String isLike, int num) {
        pInfo.sendLikeOrUnlike(artid, isLike, num);
    }

    @Override
    public void handleRating(String artid, String rate) {
        pInfo.sendRatingData(artid, rate);
    }

    @Override
    public void refreshAdapter(String count, int num) {
        aInfo.refreshLikeCount(count, num);
    }

    @Override
    public void refreshAdapter(String count) {
        aInfo.refreshRateData(count);
    }

    @Override
    public void handViewCustomerPhoto(List<String> photo) {
        MyApplication.getAppData().setFromPersonalSteaming(true);
        MyApplication.getAppData().setPhotoList(photo);
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPhotoView()).addToBackStack("Info").commit();
    }

    @Override
    public void handleClickHeadPhoto(String maid, String wsid, String type, String privacy) {
        MyApplication.getAppData().setPersonalData(maid, wsid, type, privacy);
        MyApplication.getAppData().setFromMapToPersonal(true);
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPersonalSteaming()).addToBackStack("Info").commit();
    }

    @Override
    public void saveSwiTubeUrl(String url) {
        this.url = url;
    }

    @Override
    public void handleSelectArtid(int index) {
        MyApplication.getAppData().setArtid(articleList.get(index).getArtid());
        MyApplication.getAppData().setMessageList(articleList.get(index).getMsg());
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VAttractionComments()).addToBackStack("Info").commit();
    }

    @Override
    public void handSignIn() {
        new SignInUtil(getContext(), iMainActivity);
    }

    @Override
    public void init(String style, String item, GInfoData gInfoData) {
        StringBuilder stringBuilder = new StringBuilder("geo:");
        stringBuilder.append("0,0");
        stringBuilder.append("?q=");
        stringBuilder.append(gInfoData.getData().get(0).getLat());
        stringBuilder.append(",");
        stringBuilder.append(gInfoData.getData().get(0).getLng());
        stringBuilder.append("(");
        stringBuilder.append(gInfoData.getData().get(0).getPlace());
        stringBuilder.append(")");

        location = stringBuilder.toString();
        aInfo = new AInfo(style, item, this, gInfoData);
        mRecyclerView.setAdapter(aInfo);
        mTextTitle.setText(gInfoData.getData().get(0).getPlace());
        iMainActivity.setPlaceName(gInfoData.getData().get(0).getPlace());
        iMainActivity.setPlaceId(gInfoData.getData().get(0).getMsid());
        iMainActivity.saveAttractionId(gInfoData.getData().get(0).getSpid());
        articleList = gInfoData.getData().get(0).getArticle();
        MyApplication.getAppData().setArticleList(gInfoData.getData().get(0).getArticle());
        MyApplication.getAppData().setPhotoList(gInfoData.getData().get(0).getPhoto());
        MyApplication.getAppData().setAttractionName(gInfoData.getData().get(0).getPlace());
        relativeLayout.setVisibility(View.GONE);
        viewList.get(1).setVisibility(View.GONE);
        if (MyApplication.getAppData().isNeedScrollToTop()) {
            MyApplication.getAppData().setNeedScrollToTop(false);
            mRecyclerView.scrollToPosition(0);
        }
    }

    @Override
    public void handleLinkClick(String attractionId) {
        if (iMainActivity.getAttractionId().equals(attractionId)) {
            mRecyclerView.scrollToPosition(0);
        } else {
            iMainActivity.saveAttractionId(attractionId);
            pInfo.getDetailData(iMainActivity.getAttractionId());
        }
    }

    @Override
    public void handleClickSave() {
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            new SignInUtil(getContext(), iMainActivity);
        } else {
            pInfo.getSaveList();
        }
    }

    @Override
    public void handleClickCollect(boolean isAdd, String sucid) {
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            new SignInUtil(getContext(), iMainActivity);
        } else {
            if (isAdd) {
                pInfo.sendAddToCollect(iMainActivity.getAttractionId(), true);
            } else {
                pInfo.sendAddToCollect(sucid, false);
            }
        }
    }

    @Override
    public void showSaveList(final GSaveList gSaveList) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item_stroke, null);
        final View view1 = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item_adding, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerInItemStroke);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final ASaveList aSaveList = new ASaveList(gSaveList);
        recyclerView.setAdapter(aSaveList);
        ImageView imageView = view.findViewById(R.id.imageNewInItemStroke);
        TextView textView = view.findViewById(R.id.textNewInItemStroke);
        AlertDialogUtil.getInstance()
                .initDialogBuilder(
                        getContext(),
                        view,
                        getString(R.string.global_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                int size = aSaveList.getIsChecked().size();
                                boolean canNext = false;
                                for (int j = 0; j < size; j++) {
                                    if (aSaveList.getIsChecked().get(j)) {
                                        canNext = true;
                                    }
                                }
                                if (canNext) {
                                    pInfo.sendSaveData(gSaveList, aSaveList.getIsChecked(), iMainActivity.getAttractionId());
                                    AlertDialogUtil.getInstance().initDialogBuilder(getContext(), view1);
                                    AlertDialogUtil.getInstance().showAlertDialog();
                                }
                            }
                        }, true);
        AlertDialogUtil.getInstance().showAlertDialog();
        RxView.clicks(imageView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("authority", "null").equals("Y")) {
                            AlertDialogUtil.getInstance()
                                    .initDialogBuilder(
                                            getContext(),
                                            R.string.stroke_title_create_message,
                                            R.string.global_ok,
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {}
                                            });
                            AlertDialogUtil.getInstance().showAlertDialog();
                        } else {
                            if (gSaveList.getData().size() < 10) {
                                createNewStroke();
                            } else {
                                AlertDialogUtil.getInstance()
                                        .initDialogBuilder(
                                                getContext(),
                                                R.string.stroke_title_create_message,
                                                R.string.global_ok,
                                                new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {}
                                                });
                                AlertDialogUtil.getInstance().showAlertDialog();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (gSaveList.getData().size() < 10) {
                            createNewStroke();
                        } else {
                            AlertDialogUtil.getInstance()
                                    .initDialogBuilder(
                                            getContext(),
                                            R.string.stroke_title_create_message,
                                            R.string.global_ok,
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) { }
                                            });
                            AlertDialogUtil.getInstance().showAlertDialog();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void finishAddToStroke() {
        AlertDialogUtil.getInstance().clearAlertDialog();
        Toast.makeText(getContext(), R.string.stroke_title_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishAddToCollect(boolean isAdd, String sucid) {
        aInfo.handleSave(sucid);
        if (isAdd) {
            Toast.makeText(getContext(), R.string.stroke_collect_add, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), R.string.stroke_collect_remove, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showFinishCreateStroke() {
        Toast.makeText(getContext(), R.string.stroke_title_trip_created, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleEdit(int index, GInfoData gInfoData, boolean isComment) {
        pInfo.handleEditData(index, gInfoData, isComment);
    }

    @Override
    public void handleDeleteComment(int index, GInfoData gInfoData) {
        pInfo.handleDeleteData(index, gInfoData);
    }

    @Override
    public void showEditComment() {
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VLeaveComments()).addToBackStack("Info").commit();
    }

    @Override
    public void showEditTag() {
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VTag()).addToBackStack("Info").commit();
    }

    @Override
    public void finishDelete() {
        aInfo.refreshData();
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
    public boolean onBackPressed() {
        if (MyApplication.getAppData().isFromSearchAttraction()) {
            MyApplication.getAppData().setFromSearchAttraction(false);
            getFragmentManager().popBackStack();
        } else if (MyApplication.getAppData().isFromMyCollection()) {
            MyApplication.getAppData().setFromMyCollection(false);
            getFragmentManager().popBackStack();
        } else if (MyApplication.getAppData().isFromStrokeAttractionList()) {
            MyApplication.getAppData().setFromStrokeAttractionList(false);
            getFragmentManager().popBackStack();
        } else if (MyApplication.getAppData().isFromStroke()) {
            MyApplication.getAppData().setFromStroke(false);
            getFragmentManager().popBackStack();
        } else {
            getFragmentManager().popBackStack("Map", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        return true;
    }

    private void createNewStroke() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item, null);
        final EditText editText = view.findViewById(R.id.editInDialogItem);
        AlertDialogUtil.getInstance()
                .initDialogBuilder(
                        getContext(),
                        view,
                        "行程標題",
                        getString(R.string.global_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                pInfo.handleNewStroke(editText.getText().toString());
                            }
                        },
                        getString(R.string.global_cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
        AlertDialogUtil.getInstance().showAlertDialog();
    }
}
