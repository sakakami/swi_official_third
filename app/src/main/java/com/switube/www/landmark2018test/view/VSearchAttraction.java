package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ASaveList;
import com.switube.www.landmark2018test.adapter.ASearchAttraction;
import com.switube.www.landmark2018test.adapter.callback.IAPlacePhotoList;
import com.switube.www.landmark2018test.adapter.callback.IASearchAttraction;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.presenter.PSearchAttraction;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.util.SignInUtil;
import com.switube.www.landmark2018test.view.callback.IFragmentBackHandler;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVSearchAttraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
public class VSearchAttraction extends Fragment implements IVSearchAttraction, IASearchAttraction, IAPlacePhotoList, IFragmentBackHandler {
    @BindView(R.id.recyclerInPlaceList)
    RecyclerView mRecyclerView;
    @BindViews({R.id.textBackInPlaceList, R.id.textTag1InPlaceList, R.id.textTag2InPlaceList,
            R.id.textTag3InPlaceList, R.id.textSearchInPlaceList})
    List<TextView> mTextList;
    @BindViews({R.id.imageMapInPlaceList, R.id.imageSettingInPlaceList})
    List<ImageView> mImageList;
    private static int listMode = -1;
    private PSearchAttraction pSearchAttraction;
    private Unbinder mUnbinder;
    private boolean canClick = false;
    private List<String> mSpid = new ArrayList<>();
    private GSearchAttractionDetail gSearchAttractionDetail;
    private List<String> mOpen = new ArrayList<>();
    private List<String> mTime = new ArrayList<>();
    private List<String> mStyle = new ArrayList<>();
    private ASearchAttraction aSearchAttraction;
    private IMainActivity mIMainActivity;

    public VSearchAttraction() {
        pSearchAttraction = new PSearchAttraction(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_search_attraction, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        VMap.isAttractionList = false;
        canClick = false;
        View loading = LayoutInflater.from(container.getContext()).inflate(R.layout.dialog_item_loading, null);
        AlertDialogUtil.getInstance().initDialogBuilder(container.getContext(), loading);
        AlertDialogUtil.getInstance().showAlertDialog();
        if (MyApplication.getAppData().isUsingSettingData()) {
            pSearchAttraction.getSearchData(mIMainActivity.getSelectedAttractionType().getMsid(),
                    MyApplication.getAppData().geteFeaturesListForSetting(),
                    mIMainActivity.getNowGps(),
                    MyApplication.getAppData().getDistanceForSetting());
        } else {
            pSearchAttraction.getAllAttractionData(mIMainActivity.getSelectedAttractionType().getMsid(), mIMainActivity.getNowGps());
        }
        switch (Locale.getDefault().getLanguage()) {
            case "zh":
                switch (Locale.getDefault().getCountry()) {
                    case "TW":
                        mTextList.get(4).setText(mIMainActivity.getSelectedAttractionType().getMstitle_tw());
                        break;
                    case "CN":
                        mTextList.get(4).setText(mIMainActivity.getSelectedAttractionType().getMstitle_ch());
                        break;
                    default:
                        mTextList.get(4).setText(mIMainActivity.getSelectedAttractionType().getMstitle_en());
                        break;
                }
                break;
            case "jp":
                mTextList.get(4).setText(mIMainActivity.getSelectedAttractionType().getMstitle_jp());
                break;
            default:
                mTextList.get(4).setText(mIMainActivity.getSelectedAttractionType().getMstitle_en());
                break;
        }
        RxView.clicks(mTextList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            VAttractionType.isClickHotKey = true;
                            listMode = -1;
                            getFragmentManager().popBackStack();
                        }
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
                        if (canClick) {
                            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VSearchSetting()).addToBackStack("SearchAttraction").commit();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextList.get(4))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            VAttractionType.isClickHotKey = true;
                            getFragmentManager().popBackStack();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            VMap.isAttractionList = true;
                            mIMainActivity.saveSelectedAttractionId(mSpid);
                            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMap()).addToBackStack("SearchAttraction").commit();
                        }
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
                        switchList(0);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextList.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        switchList(1);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextList.get(3))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        switchList(2);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void init(GSearchAttractionDetail gSearchAttractionDetail, List<String> style, List<String> time, List<String> open) {
        this.gSearchAttractionDetail = gSearchAttractionDetail;
        mOpen = open;
        mTime = time;
        mStyle = style;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 8, 0, 0, 0));
        aSearchAttraction = new ASearchAttraction(this, gSearchAttractionDetail, style, time, open);
        mRecyclerView.setAdapter(aSearchAttraction);
        mSpid.clear();
        int size = gSearchAttractionDetail.getData().size();
        if (size > 900) {
            size = 500;
        }
        for (int i = 0; i < size; i++) {
            mSpid.add(gSearchAttractionDetail.getData().get(i).getSpid());
        }
        canClick = true;
        AlertDialogUtil.getInstance().clearAlertDialog();
        if (listMode >= 0) {
            switchList(listMode);
        }
    }

    @Override
    public void SwitchPage(String spid) {
        mIMainActivity.saveAttractionId(spid);
        MyApplication.getAppData().setFromSearchAttraction(true);
        VMap.isAttractionList = true;
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VInfo()).addToBackStack("SearchAttraction").commit();
    }

    @Override
    public void handleSaveAttractions(String spid, boolean saved, int index) {
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            new SignInUtil(getContext(), mIMainActivity);
        } else {
            if (MyApplication.getAppData().getRaid().length() == 0) {
                MyApplication.getAppData().setRaid("WebSite");
            }
            pSearchAttraction.sendAddToCollect(spid, saved, index);
            /*if (MyApplication.getAppData().getRaid().length() > 0) {
                pSearchAttraction.sendAddToCollect(spid, saved, index);
            }*/
        }
    }

    @Override
    public void handleCollectAttractions(String spid) {
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            new SignInUtil(getContext(), mIMainActivity);
        } else {
            if (MyApplication.getAppData().getRaid().length() == 0) {
                MyApplication.getAppData().setRaid("WebSite");
            }
            pSearchAttraction.getSaveList(spid);
            /*if (MyApplication.getAppData().getRaid().length() > 0) {
                pSearchAttraction.getSaveList(spid);
            }*/
        }
    }

    @Override
    public void refreshAdapter(GSearchAttractionDetail gSearchAttractionDetail, List<String> style, List<String> time, List<String> open) {
        aSearchAttraction.refreshAdapter(gSearchAttractionDetail, style, time, open);
    }

    @Override
    public void showSaveList(final GSaveList gSaveList, final String spid) {
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
                                    pSearchAttraction.sendSaveData(gSaveList, aSaveList.getIsChecked(), spid);
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
                            createNewStroke();
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
                                                    public void onClick(DialogInterface dialogInterface, int i) { }
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
                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("authority", "null").equals("Y")) {
                            createNewStroke();
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
                                                    public void onClick(DialogInterface dialogInterface, int i) { }
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
    }

    @Override
    public void showFinishCreateStroke() {
        Toast.makeText(getContext(), R.string.stroke_title_trip_created, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishAddToStroke() {
        AlertDialogUtil.getInstance().clearAlertDialog();
        Toast.makeText(getContext(), R.string.stroke_title_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishAddToCollect(String sucid, int index, boolean isAdd) {
        aSearchAttraction.refreshAdapter(index, sucid);
        if (isAdd) {
            Toast.makeText(getContext(), R.string.stroke_collect_add, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), R.string.stroke_collect_remove, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void handlePhotoClicked(int index) {
        SwitchPage(aSearchAttraction.getSpid(index));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public boolean onBackPressed() {
        VAttractionType.isClickHotKey = true;
        listMode = -1;
        getFragmentManager().popBackStack();
        return true;
    }

    private void switchList(int index) {
        switch (index) {
            case 0:
                listMode = 0;
                mTextList.get(1).setBackgroundResource(R.drawable.bg_gray_full_7b7b7b);
                mTextList.get(1).setTextColor(Color.parseColor("#ffffff"));
                mTextList.get(2).setBackgroundResource(R.drawable.bg_gray_2_surround);
                mTextList.get(2).setTextColor(Color.parseColor("#808080"));
                mTextList.get(3).setBackgroundResource(R.drawable.bg_gray_2_surround);
                mTextList.get(3).setTextColor(Color.parseColor("#808080"));
                pSearchAttraction.switchByDistance(gSearchAttractionDetail, mStyle, mTime, mOpen, mIMainActivity.getNowGps());
                break;
            case 1:
                listMode = 1;
                mTextList.get(1).setBackgroundResource(R.drawable.bg_gray_2_surround);
                mTextList.get(1).setTextColor(Color.parseColor("#808080"));
                mTextList.get(2).setBackgroundResource(R.drawable.bg_gray_full_7b7b7b);
                mTextList.get(2).setTextColor(Color.parseColor("#ffffff"));
                mTextList.get(3).setBackgroundResource(R.drawable.bg_gray_2_surround);
                mTextList.get(3).setTextColor(Color.parseColor("#808080"));
                pSearchAttraction.switchByTime(gSearchAttractionDetail, mStyle, mTime, mOpen);
                break;
            case 2:
                listMode = 2;
                mTextList.get(1).setBackgroundResource(R.drawable.bg_gray_2_surround);
                mTextList.get(1).setTextColor(Color.parseColor("#808080"));
                mTextList.get(2).setBackgroundResource(R.drawable.bg_gray_2_surround);
                mTextList.get(2).setTextColor(Color.parseColor("#808080"));
                mTextList.get(3).setBackgroundResource(R.drawable.bg_gray_full_7b7b7b);
                mTextList.get(3).setTextColor(Color.parseColor("#ffffff"));
                pSearchAttraction.switchByRate(gSearchAttractionDetail, mStyle, mTime, mOpen);
                break;
            default:
                break;
        }
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
                                pSearchAttraction.handleNewStroke(editText.getText().toString());
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
