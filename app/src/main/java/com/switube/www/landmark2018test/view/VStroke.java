package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ASaveList;
import com.switube.www.landmark2018test.adapter.AStroke;
import com.switube.www.landmark2018test.adapter.AStrokeAttractionList;
import com.switube.www.landmark2018test.adapter.callback.IAPlacePhotoList;
import com.switube.www.landmark2018test.adapter.callback.IAStroke;
import com.switube.www.landmark2018test.adapter.callback.IAStrokeAttractionList;
import com.switube.www.landmark2018test.gson.GPushStroke;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GStrokeList;
import com.switube.www.landmark2018test.presenter.PStroke;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVStroke;

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
 * 0 ==> default stroke
 * 1 ==> push stroke
 * 2 ==> my stroke
 */
public class VStroke extends Fragment implements IVStroke, IAStroke, IAStrokeAttractionList, IAPlacePhotoList {
    private PStroke pStroke;
    public VStroke() {
        pStroke = new PStroke(this);
    }

    @BindViews({R.id.imageAddInStroke, R.id.imageCancelInStroke})
    List<ImageView> imageViewList;
    @BindViews({R.id.textBackInStroke, R.id.textTitleInStroke})
    List<TextView> textViewList;
    @BindView(R.id.recyclerInStroke)
    RecyclerView recyclerView;
    @BindView(R.id.editSearchInStroke)
    EditText editText;
    private Unbinder unbinder;
    private AStroke aStroke;
    private AStrokeAttractionList aStrokeAttractionList;
    private boolean isSearchMode = false;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_stroke, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        switch (MyApplication.getAppData().getStrokeMode()) {
            case 1:
                imageViewList.get(0).setVisibility(View.GONE);
                imageViewList.get(1).setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                textViewList.get(1).setVisibility(View.VISIBLE);
                aStrokeAttractionList = new AStrokeAttractionList(this, false, this, iMainActivity);
                recyclerView.setAdapter(aStrokeAttractionList);
                pStroke.getPushStrokeData();
                break;
            case 2:
                imageViewList.get(0).setVisibility(View.VISIBLE);
                imageViewList.get(1).setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                textViewList.get(1).setVisibility(View.VISIBLE);
                textViewList.get(1).setText(R.string.stroke_title_trip);
                imageViewList.get(0).setImageResource(R.drawable.add_v1_2);
                aStroke = new AStroke(this, this, iMainActivity);
                recyclerView.setAdapter(aStroke);
                pStroke.getStrokeData();
                break;
            default:
                imageViewList.get(0).setVisibility(View.VISIBLE);
                imageViewList.get(1).setVisibility(View.GONE);
                editText.setVisibility(View.GONE);
                textViewList.get(1).setVisibility(View.VISIBLE);
                textViewList.get(1).setText(R.string.stroke_title_browse);
                imageViewList.get(0).setImageResource(R.drawable.search_2_2x);
                aStroke = new AStroke(this, this, iMainActivity);
                recyclerView.setAdapter(aStroke);
                pStroke.getPushStrokeData();
                break;
        }
        RxView.clicks(imageViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().getStrokeMode() == 2) {
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("authority", "null").equals("Y")) {
                                handleAddNewStroke();
                            } else {
                                if (aStroke.getItemCount() < 10) {
                                    handleAddNewStroke();
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
                        } else if (MyApplication.getAppData().getStrokeMode() == 0) {
                            isSearchMode = true;
                            imageViewList.get(0).setVisibility(View.GONE);
                            imageViewList.get(1).setVisibility(View.VISIBLE);
                            textViewList.get(1).setVisibility(View.GONE);
                            editText.setVisibility(View.VISIBLE);
                            editText.requestFocus();
                            InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (MyApplication.getAppData().getStrokeMode() == 0) {
                            if (isSearchMode) {
                                isSearchMode = false;
                                editText.setText("");
                                imageViewList.get(0).setVisibility(View.VISIBLE);
                                imageViewList.get(1).setVisibility(View.GONE);
                                editText.setVisibility(View.GONE);
                                pStroke.getPushStrokeData();
                            } else {
                                getFragmentManager().popBackStack();
                            }
                        } else {
                            if (MyApplication.getAppData().getStrokeMode() == 1) {
                                MyApplication.getAppData().setCanEditInPersonalMap(false);
                                MyApplication.getAppData().setNormalMap(false);
                            } else {
                                MyApplication.getAppData().setNormalMap(true);
                            }
                            getFragmentManager().popBackStack();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxTextView.textChanges(editText)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(CharSequence charSequence) {
                        if (charSequence.length() > 1) {
                            pStroke.getSearchData(charSequence.toString());
                        }
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
                        editText.setText("");
                        isSearchMode = false;
                        imageViewList.get(0).setVisibility(View.VISIBLE);
                        imageViewList.get(1).setVisibility(View.GONE);
                        textViewList.get(1).setVisibility(View.VISIBLE);
                        editText.setVisibility(View.GONE);
                        editText.clearFocus();
                        InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    private GSaveList gSaveList;
    @Override
    public void init(GSaveList gSaveList) {
        this.gSaveList = gSaveList;
        aStroke.init(gSaveList);
    }

    private GPushStroke gPushStroke;
    @Override
    public void init(GPushStroke gPushStroke) {
        this.gPushStroke = gPushStroke;
        aStroke.init(gPushStroke);
    }

    @Override
    public void init(GStrokeList gStrokeList, List<String> style, List<String> time, List<String> isOpen) {
        if (MyApplication.getAppData().getStrokeMode() == 1) {
            textViewList.get(1).setText(MyApplication.getAppData().getTitleForUrid());
        }
        aStrokeAttractionList.init(gStrokeList, style, time, isOpen);
    }

    @Override
    public void handleDeleteStroke(final int index) {
        AlertDialogUtil.getInstance()
                .initDialogBuilder(
                        getContext(),
                        R.string.stroke_title_delete_message,
                        R.string.global_confirm,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                pStroke.handleNewStroke("", "del", gSaveList.getData().get(index).getUrid(), 2);
                            }
                        },
                        R.string.global_cancel,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void handleEditTitle(final int index) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item, null);
        final EditText editText = view.findViewById(R.id.editInDialogItem);
        editText.setText(gSaveList.getData().get(index).getTitle());
        AlertDialogUtil.getInstance()
                .initDialogBuilder(
                        getContext(),
                        view,
                        getString(R.string.stroke_create_title),
                        getString(R.string.global_ok),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                pStroke.handleNewStroke(editText.getText().toString(), "edit", gSaveList.getData().get(index).getUrid(), 3);
                            }
                        },
                        getString(R.string.global_cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void handleShowAttraction(int index) {
        switch (MyApplication.getAppData().getStrokeMode()) {
            case 0:
                MyApplication.getAppData().setUrid(gPushStroke.getData().get(index).getUrid());
                MyApplication.getAppData().setTitleForUrid(gPushStroke.getData().get(index).getTitle());
                MyApplication.getAppData().setCanEditInPersonalMap(false);
                break;
            case 2:
                MyApplication.getAppData().setUrid(gSaveList.getData().get(index).getUrid());
                MyApplication.getAppData().setTitleForUrid(gSaveList.getData().get(index).getTitle());
                MyApplication.getAppData().setCanEditInPersonalMap(true);
                break;
        }
        MyApplication.getAppData().setNormalMap(false);
        getFragmentManager().popBackStack();
    }

    @Override
    public void showFinishCreate() {
        Toast.makeText(getContext(), R.string.stroke_title_trip_created, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFinishDel() {
        Toast.makeText(getContext(), R.string.stroke_title_trip_deleted, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFinishEdit() {
        Toast.makeText(getContext(), R.string.stroke_title_trip_edited, Toast.LENGTH_SHORT).show();
    }

    private IMainActivity iMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity)context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void handleCollectPlace(int index, boolean isAdd) {
        if (isAdd) {
            pStroke.sendAddToCollect(aStrokeAttractionList.getgStrokeList().getData().get(index).getSpid(), index, isAdd);
        } else {
            pStroke.sendAddToCollect(aStrokeAttractionList.getgStrokeList().getData().get(index).getColl(), index, isAdd);
        }
    }

    @Override
    public void handleRemoveTrip(int index) {
        pStroke.sendSaveData(aStrokeAttractionList.getgStrokeList().getData().get(index).getUrspid(), MyApplication.getAppData().getUrid(), index);
    }

    private int saveTripIndex = -1;
    @Override
    public void handleSaveTrip(int index) {
        saveTripIndex = index;
        pStroke.getSaveList();
    }

    @Override
    public void showFinishAddToCollect(int index, boolean isAdd, String sucid) {
        aStrokeAttractionList.refreshAdapter(index, sucid);
        if (isAdd) {
            Toast.makeText(getContext(), R.string.stroke_finish_add, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), R.string.stroke_finish_remove, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finishDelFormStroke(int index) {
        Toast.makeText(getContext(), R.string.stroke_title_remove, Toast.LENGTH_SHORT).show();
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
                                    pStroke.sendSaveData(gSaveList, aSaveList.getIsChecked(), aStrokeAttractionList.getgStrokeList().getData().get(saveTripIndex).getSpid());
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
                                                public void onClick(DialogInterface dialogInterface, int i) { }
                                            });
                            AlertDialogUtil.getInstance().showAlertDialog();
                        } else {
                            if (gSaveList.getData().size() < 10) {
                                createNewStroke(true);
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
                        } else {
                            if (gSaveList.getData().size() < 10) {
                                createNewStroke(true);
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
    public void finishAddToStroke() {
        AlertDialogUtil.getInstance().clearAlertDialog();
        Toast.makeText(getContext(), R.string.stroke_title_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleAddStroke(int index) {
        pStroke.sendAddToMyStroke(gPushStroke.getData().get(index).getUrid());
    }

    @Override
    public void showFinishAddToMyStroke() {
        Toast.makeText(getContext(), R.string.stroke_message_add_trip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handlePhotoClicked(int index) {
        if (MyApplication.getAppData().getStrokeMode() != 1) {
            if (MyApplication.getAppData().getStrokeMode() == 2) {
                if (Integer.parseInt(gSaveList.getData().get(index).getCut()) > 0) {
                    handleShowAttraction(index);
                } else {
                    Toast.makeText(getContext(), R.string.float_message_add_one, Toast.LENGTH_SHORT).show();
                }
            } else {
                if (Integer.parseInt(gPushStroke.getData().get(index).getCut()) > 0) {
                    handleShowAttraction(index);
                } else {
                    Toast.makeText(getContext(), R.string.float_message_add_one, Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            MyApplication.getAppData().setFromStroke(true);
            iMainActivity.saveAttractionId(aStrokeAttractionList.getgStrokeList().getData().get(index).getSpid());
            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VInfo()).addToBackStack("VStroke").commit();
        }
    }

    @Override
    public void showCaution() {
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

    private void handleAddNewStroke() {
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
                                pStroke.handleNewStroke(editText.getText().toString(), "add", "", 1);
                            }
                        },
                        getString(R.string.global_cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    private void createNewStroke(final boolean isCreate) {
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
                                pStroke.handleNewStroke(editText.getText().toString(), isCreate);
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
