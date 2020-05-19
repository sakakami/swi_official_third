package com.switube.www.landmark2018test.view;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AEditAttraction;
import com.switube.www.landmark2018test.adapter.callback.IAEditAttraction;
import com.switube.www.landmark2018test.presenter.PEditAttraction;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVEditAttraction;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class VEditAttraction extends Fragment implements IVEditAttraction, IAEditAttraction {
    private PEditAttraction pEditAttraction;
    public VEditAttraction() {
        pEditAttraction = new PEditAttraction(this);
    }

    @BindViews({R.id.textBackInEditAttraction, R.id.textConfirmInEditAttraction})
    List<TextView> textViewList;
    @BindView(R.id.recyclerInEditAttraction)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private AEditAttraction aEditAttraction;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_edit_attraction, container, false);
        unbinder = ButterKnife.bind(this, view);
        initClick();
        aEditAttraction = new AEditAttraction(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(aEditAttraction);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private IMainActivity iMainActivity;
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity) context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3000) {
            if (resultCode == RESULT_OK) {
                MyApplication.getAppData().getSelectedPhotos().clear();
                Place place = PlacePicker.getPlace(getContext(), data);
                if (place.getName().toString().contains("°")) {
                    pEditAttraction.getPlaceId(place.getLatLng());
                } else {
                    iMainActivity.setPlaceId(place.getId());
                    pEditAttraction.getPlaceDetail(place.getId());
                }
            }
        }
    }

    @Override
    public void handleClick(int mode) {
        switch (mode) {
            case 0:
                PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(intentBuilder.build(getActivity()), 3000);
                } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                break;
            case 2:
                MyApplication.getAppData().setFromEditAttraction(true);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.layoutContainer, new VEditTime()).addToBackStack("EditAttraction")
                        .commit();
                break;
            case 3:
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
                                        MyApplication.getAppData().getIsEdit().set(8, true);
                                        getParentFragmentManager().beginTransaction()
                                                .replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("EditAttraction")
                                                .commit();
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
                    MyApplication.getAppData().getIsEdit().set(8, true);
                    getParentFragmentManager().beginTransaction()
                            .replace(R.id.layoutContainer, new VPhotoList()).addToBackStack("CreateAttraction")
                            .commit();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void finishSend() {
        MyApplication.getAppData().setFormEditAttractionForType(false);
        MyApplication.getAppData().setFromEditAttraction(false);
        MyApplication.getAppData().setIsEdit(new ArrayList<>());
        MyApplication.getAppData().getSelectedPhotos().clear();
        AlertDialogUtil.getInstance().clearAlertDialog();
        getParentFragmentManager().popBackStack();
    }

    @Override
    public void handleNewPlace() {
        MyApplication.getAppData().getIsEdit().set(0, true);
        MyApplication.getAppData().getIsEdit().set(1, true);
        MyApplication.getAppData().getIsEdit().set(3, true);
        MyApplication.getAppData().getIsEdit().set(4, true);
        MyApplication.getAppData().getIsEdit().set(5, true);
        MyApplication.getAppData().getIsEdit().set(6, true);
        MyApplication.getAppData().getIsEdit().set(7, true);
        aEditAttraction.refreshAdapter();
    }

    @Override
    public void handleNewPlaceId(String placeId) {
        iMainActivity.setPlaceId(placeId);
    }

    @Override
    public void handleRemove() {
        AlertDialogUtil.getInstance()
                .initDialogBuilder(getContext(), R.string.edit_attraction_remove_message,
                        R.string.edit_attraction_remove_confirm,
                        (dialogInterface, i) -> pEditAttraction.sendDelPlaceData(iMainActivity.getAttractionId()),
                        R.string.edit_attraction_remove_cancel,
                        (dialogInterface, i) -> {});
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void finishDelPlace() {
        if (MyApplication.getAppData().isFromSearchAttraction()) {
            MyApplication.getAppData().setFromSearchAttraction(false);
            getParentFragmentManager().popBackStackImmediate("SearchAttraction", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            getParentFragmentManager().popBackStackImmediate("Map", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    private void initClick() {
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setFromEditAttraction(false);
                        MyApplication.getAppData().setFormEditAttractionForType(false);
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().isFormEditAttractionForType()) {
                            MyApplication.getAppData().getIsEdit().set(2, true);
                            getParentFragmentManager().beginTransaction()
                                    .replace(R.id.layoutContainer, new VFeatures()).addToBackStack("EditAttraction")
                                    .commit();
                        } else {
                            AlertDialogUtil.getInstance()
                                    .initDialogBuilder(getContext(), LayoutInflater.from(getContext()).inflate(R.layout.dialog_item_saving, null));
                            AlertDialogUtil.getInstance().showAlertDialog();
                            pEditAttraction.sendEditData(iMainActivity.getAttractionId());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
