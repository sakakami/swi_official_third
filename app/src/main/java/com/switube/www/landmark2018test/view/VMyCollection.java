package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AMyCollection;
import com.switube.www.landmark2018test.adapter.ASaveList;
import com.switube.www.landmark2018test.adapter.callback.IAMyCollection;
import com.switube.www.landmark2018test.gson.GMyCollection;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.presenter.PMyCollection;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVMyCollection;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class VMyCollection extends Fragment implements IVMyCollection, IAMyCollection {
    private PMyCollection pMyCollection;
    public VMyCollection() {
        pMyCollection = new PMyCollection(this);
    }

    @BindView(R.id.textBackInMyCollection)
    TextView textBack;
    @BindView(R.id.imageEditInMyCollection)
    ImageView imageEdit;
    @BindView(R.id.recyclerInMyCollection)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private AMyCollection aMyCollection;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_my_collection, container, false);
        unbinder = ButterKnife.bind(this, view);
        setClick();
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        aMyCollection = new AMyCollection(this);
        recyclerView.setAdapter(aMyCollection);
        pMyCollection.getCollectData();
        return view;
    }

    @Override
    public void init(GMyCollection gMyCollection) {
        pMyCollection.handleInit(gMyCollection, iMainActivity.getNowGps());
    }

    @Override
    public void init(GMyCollection gMyCollection, List<String> distance) {
        aMyCollection.init(gMyCollection, distance);
    }

    @Override
    public void handleRemove(int index, String spid) {
        pMyCollection.removeAttraction(index, spid);
    }

    @Override
    public void handleSave(String spid) {
        pMyCollection.getSaveList(spid);
    }

    @Override
    public void handleFinishRemove(int index) {
        aMyCollection.finishRemove(index);
        Toast.makeText(getContext(), R.string.stroke_collect_remove, Toast.LENGTH_SHORT).show();
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
                                    pMyCollection.sendSaveData(gSaveList, aSaveList.getIsChecked(), spid);
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
    public void showFinishAdd() {
        AlertDialogUtil.getInstance().clearAlertDialog();
        Toast.makeText(getContext(), R.string.stroke_title_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFinishCreate() {
        Toast.makeText(getContext(), R.string.stroke_title_trip_created, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleToInfoPage(String spid) {
        iMainActivity.saveAttractionId(spid);
        MyApplication.getAppData().setFromMyCollection(true);
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VInfo()).addToBackStack("MyCollection").commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private IMainActivity iMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity)context;
    }

    private void setClick() {
        RxView.clicks(textBack)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (getFragmentManager() != null) {
                            getFragmentManager().popBackStack();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageEdit)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setCollectionPage(true);
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VSwapStroke()).addToBackStack("myCollection").commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }

    private void createNewStroke() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item, null);
        final EditText editText = view.findViewById(R.id.editInDialogItem);
        AlertDialogUtil.getInstance()
                .initDialogBuilder(
                        getContext(),
                        view,
                        getString(R.string.stroke_create_title),
                        getString(R.string.global_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                pMyCollection.handleNewStroke(editText.getText().toString());
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
