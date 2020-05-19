package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ASaveList;
import com.switube.www.landmark2018test.adapter.AStrokeAttractionList;
import com.switube.www.landmark2018test.adapter.callback.IAPlacePhotoList;
import com.switube.www.landmark2018test.adapter.callback.IAStrokeAttractionList;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GStrokeList;
import com.switube.www.landmark2018test.presenter.PStrokeAttractionList;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVStrokeAttractionList;

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
public class VStrokeAttractionList extends Fragment implements IVStrokeAttractionList, IAStrokeAttractionList, IAPlacePhotoList {
    private PStrokeAttractionList pStrokeAttractionList;
    public VStrokeAttractionList() {
        pStrokeAttractionList = new PStrokeAttractionList(this);
    }

    @BindViews({R.id.imageOrderedInStrokeAttractionList, R.id.imageEditInStrokeAttractionList})
    List<ImageView> imageViewList;
    @BindViews({R.id.textBackInStrokeAttractionList, R.id.textTitleInStrokeAttractionList})
    List<TextView> textViewList;
    @BindView(R.id.recyclerInStrokeAttractionList)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private AStrokeAttractionList aStrokeAttractionList;

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_stroke_attraction_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        aStrokeAttractionList = new AStrokeAttractionList(this, true, this, iMainActivity);
        recyclerView.setAdapter(aStrokeAttractionList);
        textViewList.get(1).setText(MyApplication.getAppData().getTitleForUrid());
        if (MyApplication.getAppData().getStrokeMode() != 2 || !MyApplication.getAppData().isCanEditInPersonalMap()) {
            imageViewList.get(0).setVisibility(View.GONE);
            imageViewList.get(1).setVisibility(View.GONE);
        }
        pStrokeAttractionList.init();
        RxView.clicks(textViewList.get(0))
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
        RxView.clicks(imageViewList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        createNewStroke(false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VSwapStroke()).addToBackStack("strokeList").commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void init(GStrokeList gStrokeList, List<String> style, List<String> time, List<String> isOpen) {
        aStrokeAttractionList.init(gStrokeList, style, time, isOpen);
    }

    @Override
    public void handleCollectPlace(int index, boolean isAdd) {
        if (isAdd) {
            pStrokeAttractionList.sendAddToCollect(aStrokeAttractionList.getgStrokeList().getData().get(index).getSpid(), index);
        } else {
            pStrokeAttractionList.sendDelToCollect(aStrokeAttractionList.getgStrokeList().getData().get(index).getColl(), index);
        }
    }

    @Override
    public void handleRemoveTrip(int index) {
        pStrokeAttractionList.sendSaveData(aStrokeAttractionList.getgStrokeList().getData().get(index).getUrspid(), MyApplication.getAppData().getUrid(), index);
    }

    private int saveTripIndex = -1;
    @Override
    public void handleSaveTrip(int index) {
        saveTripIndex = index;
        pStrokeAttractionList.getSaveList();
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
                        (dialogInterface, i) -> {
                            int size = aSaveList.getIsChecked().size();
                            boolean canNext = false;
                            for (int j = 0; j < size; j++) {
                                if (aSaveList.getIsChecked().get(j)) {
                                    canNext = true;
                                }
                            }
                            if (canNext) {
                                pStrokeAttractionList.sendSaveData(gSaveList, aSaveList.getIsChecked(), aStrokeAttractionList.getgStrokeList().getData().get(saveTripIndex).getSpid());
                                AlertDialogUtil.getInstance().initDialogBuilder(getContext(), view1);
                                AlertDialogUtil.getInstance().showAlertDialog();
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
                            createNewStroke(true);
                        } else {
                            if (gSaveList.getData().size() < 10) {
                                createNewStroke(true);
                            } else {
                                AlertDialogUtil.getInstance()
                                        .initDialogBuilder(
                                                getContext(),
                                                R.string.stroke_title_create_message,
                                                R.string.global_ok,
                                                (dialogInterface, i) -> { });
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
                            createNewStroke(true);
                        } else {
                            if (gSaveList.getData().size() < 10) {
                                createNewStroke(true);
                            } else {
                                AlertDialogUtil.getInstance()
                                        .initDialogBuilder(
                                                getContext(),
                                                R.string.stroke_title_create_message,
                                                R.string.global_ok,
                                                (dialogInterface, i) -> { });
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
    public void showFinishAddToCollect(int index, String sucid) {
        aStrokeAttractionList.refreshAdapter(index, sucid);
        Toast.makeText(getContext(), R.string.stroke_finish_add, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFinishDelToCollect(int index) {
        aStrokeAttractionList.refreshAdapter(index, "");
        Toast.makeText(getContext(), R.string.stroke_finish_remove, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishAddToStroke() {
        AlertDialogUtil.getInstance().clearAlertDialog();
        Toast.makeText(getContext(), R.string.stroke_title_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishDelFormStroke(int index) {
        AlertDialogUtil.getInstance().clearAlertDialog();
        Toast.makeText(getContext(), R.string.stroke_title_remove, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFinishCreate() {
        Toast.makeText(getContext(), R.string.stroke_title_trip_created, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFinishEdit() {
        textViewList.get(1).setText(MyApplication.getAppData().getTitleForUrid());
        Toast.makeText(getContext(), R.string.stroke_title_trip_edited, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handlePhotoClicked(int index) {
        MyApplication.getAppData().setFromStrokeAttractionList(true);
        iMainActivity.saveAttractionId(aStrokeAttractionList.getgStrokeList().getData().get(index).getSpid());
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VInfo()).addToBackStack("VStrokeAttractionList").commit();
    }

    private IMainActivity iMainActivity;
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity)context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void createNewStroke(final boolean isCreate) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item, null);
        final EditText editText = view.findViewById(R.id.editInDialogItem);
        if (!isCreate) {
            editText.setText(MyApplication.getAppData().getTitleForUrid());
        }
        AlertDialogUtil.getInstance()
                .initDialogBuilder(
                        getContext(),
                        view,
                        getString(R.string.stroke_create_title),
                        getString(R.string.global_ok),
                        (dialogInterface, i) -> pStrokeAttractionList.handleNewStroke(editText.getText().toString(), isCreate),
                        getString(R.string.global_cancel),
                        (dialogInterface, i) -> {});
        AlertDialogUtil.getInstance().showAlertDialog();
    }
}
