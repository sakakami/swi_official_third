package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.ECreateAttraction;
import com.switube.www.landmark2018test.entity.EEditTime;
import com.switube.www.landmark2018test.presenter.PEditTime;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVEditTime;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class VEditTime extends Fragment implements IVEditTime {
    @BindViews({R.id.textBackInEditTime, R.id.textSaveInEditTime, R.id.textAddTimeInEditTime,
            R.id.textTimeSet1InEditTime, R.id.textStartTime1InEditTime, R.id.textMiddle1InEditTime,
            R.id.textCloseTime1InEditTime, R.id.textTimeSet2InEditTime, R.id.textStartTime2InEditTime,
            R.id.textMiddle2InEditTime, R.id.textCloseTime2InEditTime, R.id.textTimeSet3InEditTime,
            R.id.textStartTime3InEditTime, R.id.textMiddle3InEditTime, R.id.textCloseTime3InEditTime,
            R.id.textTimeSet4InEditTime, R.id.textStartTime4InEditTime, R.id.textMiddle4InEditTime,
            R.id.textCloseTime4InEditTime})
    List<TextView> mTextTime;
    @BindViews({R.id.imageCancel1InEditTime, R.id.imageCancel2InEditTime, R.id.imageCancel3InEditTime,
            R.id.imageCancel4InEditTime})
    List<ImageView> mImageTime;
    @BindViews({R.id.viewBar1InEditTime, R.id.viewBarStart1InEditTime, R.id.viewBarEnd1InEditTime,
            R.id.viewBar2InEditTime, R.id.viewBarStart2InEditTime, R.id.viewBarEnd2InEditTime,
            R.id.viewBar3InEditTime, R.id.viewBarStart3InEditTime, R.id.viewBarEnd3InEditTime,
            R.id.viewBar4InEditTime, R.id.viewBarStart4InEditTime, R.id.viewBarEnd4InEditTime})
    List<View> mViewBars;
    @BindViews({R.id.layoutSelectWeekInEditTime, R.id.layoutTimerInEditTime})
    List<RelativeLayout> mLayouts;
    @BindViews({R.id.textMonInEditTime, R.id.textTueInEditTime, R.id.textWedInEditTime,
            R.id.textThuInEditTime, R.id.textFriInEditTime, R.id.textSatInEditTime,
            R.id.textSunInEditTime, R.id.textNextInEditTime, R.id.textCancel2InEditTime, R.id.textAllInEditTime})
    List<TextView> mTextWeek;
    @BindViews({R.id.checkbox1InEditTime, R.id.checkbox2InEditTime, R.id.checkbox3InEditTime,
            R.id.checkbox4InEditTime, R.id.checkbox5InEditTime, R.id.checkbox6InEditTime,
            R.id.checkbox7InEditTime, R.id.checkbox8InEditTime})
    List<CheckBox> mCheckBox;
    @BindViews({R.id.textConfirmInEditTime, R.id.textCancelInEditTime, R.id.textTimerTitleInEditTimer})
    List<TextView> mTextTimer;
    @BindView(R.id.timePickerInEditTimer)
    TimePicker mTimePicker;
    private List<Boolean> editWeek = new ArrayList<>();
    private String editStart = "";
    private String editEnd = "";
    private PEditTime pEditTime;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.TAIWAN);
    private Unbinder mUnbinder;
    private boolean isSetStart = true;
    private int nowSetTime = 0;
    private boolean isEditTime = false;
    private boolean isEdit = false;
    private List<EEditTime> eEditTimeList = new ArrayList<>();
    private IMainActivity mIMainActivity;

    public VEditTime() {
        pEditTime = new PEditTime(this);
        for (int i = 0; i < 4; i++) {
            eEditTimeList.add(new EEditTime());
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_edit_time, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        if (MyApplication.getAppData().isFromEditAttraction()) {
            pEditTime.handleTimeForEditMode(eEditTimeList);
        } else {
            pEditTime.handleTimeForCreateMode(mIMainActivity.getCreateData(), eEditTimeList);
        }
        RxView.clicks(mTextTime.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (eEditTimeList.get(0).isEdit()) {
                            int index = -1;
                            for (int i = 0; i < 4; i++) {
                                if (!eEditTimeList.get(i).isEdit()) {
                                    index = i;
                                    break;
                                }
                            }
                            if (index == -1) {
                                mTextTime.get(2).setVisibility(View.GONE);
                            } else {
                                handleSetTimeState(index, true);
                            }
                        } else {
                            handleSetTimeState(0, true);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextTime.get(3))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        nowSetTime = 0;
                        mTextTimer.get(0).setText(R.string.global_next);
                        mTextTimer.get(1).setText(R.string.global_cancel);
                        mTextTimer.get(2).setText(R.string.edit_time_open);
                        mLayouts.get(0).setVisibility(View.VISIBLE);
                        editWeek.clear();
                        for (int i = 0; i < 8; i++) {
                            editWeek.add(false);
                            mCheckBox.get(i).setChecked(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextTime.get(7))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        nowSetTime = 1;
                        mTextTimer.get(0).setText(R.string.global_next);
                        mTextTimer.get(1).setText(R.string.global_cancel);
                        mTextTimer.get(2).setText(R.string.edit_time_open);
                        mLayouts.get(0).setVisibility(View.VISIBLE);
                        editWeek.clear();
                        for (int i = 0; i < 8; i++) {
                            editWeek.add(false);
                            mCheckBox.get(i).setChecked(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextTime.get(11))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        nowSetTime = 2;
                        mTextTimer.get(0).setText(R.string.global_next);
                        mTextTimer.get(1).setText(R.string.global_cancel);
                        mTextTimer.get(2).setText(R.string.edit_time_open);
                        mLayouts.get(0).setVisibility(View.VISIBLE);
                        editWeek.clear();
                        for (int i = 0; i < 8; i++) {
                            editWeek.add(false);
                            mCheckBox.get(i).setChecked(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextTime.get(15))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        nowSetTime = 3;
                        mTextTimer.get(0).setText(R.string.global_next);
                        mTextTimer.get(1).setText(R.string.global_cancel);
                        mTextTimer.get(2).setText(R.string.edit_time_open);
                        mLayouts.get(0).setVisibility(View.VISIBLE);
                        editWeek.clear();
                        for (int i = 0; i < 8; i++) {
                            editWeek.add(false);
                            mCheckBox.get(i).setChecked(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        mCheckBox.get(0).setOnCheckedChangeListener((compoundButton, b) -> editWeek.set(0, b));
        mCheckBox.get(1).setOnCheckedChangeListener((compoundButton, b) -> editWeek.set(1, b));
        mCheckBox.get(2).setOnCheckedChangeListener((compoundButton, b) -> editWeek.set(2, b));
        mCheckBox.get(3).setOnCheckedChangeListener((compoundButton, b) -> editWeek.set(3, b));
        mCheckBox.get(4).setOnCheckedChangeListener((compoundButton, b) -> editWeek.set(4, b));
        mCheckBox.get(5).setOnCheckedChangeListener((compoundButton, b) -> editWeek.set(5, b));
        mCheckBox.get(6).setOnCheckedChangeListener((compoundButton, b) -> editWeek.set(6, b));
        mCheckBox.get(7).setOnCheckedChangeListener((compoundButton, b) -> editWeek.set(7, b));
        mTimePicker.setOnTimeChangedListener((timePicker, i, i1) -> {
            isEditTime = true;
            String time;
            String min;
            if (i < 10) {
                time = "0" + i;
            } else {
                time = String.valueOf(i);
            }
            if (i1 < 10) {
                min = "0" + i1;
            } else {
                min = String.valueOf(i1);
            }
            if (isSetStart) {
                editStart = time + ":" + min;
            } else {
                editEnd = time + ":" + min;
            }
        });
        RxView.clicks(mTextWeek.get(7))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        boolean isChecked = false;
                        for (int i = 0; i < 7; i++) {
                            if (editWeek.get(i)) {
                                isChecked = true;
                                break;
                            }
                        }
                        if (editWeek.get(7)) {
                            if (isChecked) {
                                mLayouts.get(0).setVisibility(View.GONE);
                                switch (nowSetTime) {
                                    case 0:
                                        eEditTimeList.get(0).setEdit(true);
                                        eEditTimeList.get(0).setAllDay(true);
                                        handleCheckWeek(0);
                                        mTextTime.get(3).setText(handleWeek(0));
                                        eEditTimeList.get(0).setStart("00:00");
                                        eEditTimeList.get(0).setEnd("23:59");
                                        mTextTime.get(4).setText(eEditTimeList.get(0).getStart());
                                        mTextTime.get(6).setText(eEditTimeList.get(0).getEnd());
                                        break;
                                    case 1:
                                        eEditTimeList.get(1).setEdit(true);
                                        eEditTimeList.get(1).setAllDay(true);
                                        handleCheckWeek(1);
                                        mTextTime.get(7).setText(handleWeek(1));
                                        eEditTimeList.get(1).setStart("00:00");
                                        eEditTimeList.get(1).setEnd("23:59");
                                        mTextTime.get(8).setText(eEditTimeList.get(1).getStart());
                                        mTextTime.get(10).setText(eEditTimeList.get(1).getEnd());
                                        break;
                                    case 2:
                                        eEditTimeList.get(2).setEdit(true);
                                        eEditTimeList.get(2).setAllDay(true);
                                        handleCheckWeek(2);
                                        mTextTime.get(11).setText(handleWeek(2));
                                        eEditTimeList.get(2).setStart("00:00");
                                        eEditTimeList.get(2).setEnd("23:59");
                                        mTextTime.get(12).setText(eEditTimeList.get(2).getStart());
                                        mTextTime.get(14).setText(eEditTimeList.get(2).getEnd());
                                        break;
                                    default:
                                        eEditTimeList.get(3).setEdit(true);
                                        eEditTimeList.get(3).setAllDay(true);
                                        handleCheckWeek(3);
                                        mTextTime.get(15).setText(handleWeek(3));
                                        eEditTimeList.get(3).setStart("00:00");
                                        eEditTimeList.get(3).setEnd("23:59");
                                        mTextTime.get(16).setText(eEditTimeList.get(3).getStart());
                                        mTextTime.get(18).setText(eEditTimeList.get(3).getEnd());
                                        break;
                                }
                                isEdit = true;
                            }
                        } else {
                            if (isChecked) {
                                isSetStart = true;
                                mTextTime.get(2).setVisibility(View.VISIBLE);
                                mLayouts.get(0).setVisibility(View.GONE);
                                mLayouts.get(1).setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextTimer.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (isSetStart) {
                            if (!isEditTime) {
                                editStart = simpleDateFormat.format(new Date());
                            }
                            isSetStart = false;
                            isEditTime = false;
                            mTextTimer.get(2).setText(R.string.edit_time_close);
                            mTextTimer.get(0).setText(R.string.global_confirm);
                        } else {
                            isSetStart = true;
                            if (!isEditTime) {
                                editEnd = simpleDateFormat.format(new Date());
                            }
                            mLayouts.get(1).setVisibility(View.GONE);
                            switch (nowSetTime) {
                                case 0:
                                    eEditTimeList.get(0).setEdit(true);
                                    handleCheckWeek(0);
                                    mTextTime.get(3).setText(handleWeek(0));
                                    eEditTimeList.get(0).setStart(editStart);
                                    eEditTimeList.get(0).setEnd(editEnd);
                                    mTextTime.get(4).setText(eEditTimeList.get(0).getStart());
                                    mTextTime.get(6).setText(eEditTimeList.get(0).getEnd());
                                    break;
                                case 1:
                                    eEditTimeList.get(1).setEdit(true);
                                    handleCheckWeek(1);
                                    mTextTime.get(7).setText(handleWeek(1));
                                    eEditTimeList.get(1).setStart(editStart);
                                    eEditTimeList.get(1).setEnd(editEnd);
                                    mTextTime.get(8).setText(eEditTimeList.get(1).getStart());
                                    mTextTime.get(10).setText(eEditTimeList.get(1).getEnd());
                                    break;
                                case 2:
                                    eEditTimeList.get(2).setEdit(true);
                                    handleCheckWeek(2);
                                    mTextTime.get(11).setText(handleWeek(2));
                                    eEditTimeList.get(2).setStart(editStart);
                                    eEditTimeList.get(2).setEnd(editEnd);
                                    mTextTime.get(12).setText(eEditTimeList.get(2).getStart());
                                    mTextTime.get(14).setText(eEditTimeList.get(2).getEnd());
                                    break;
                                default:
                                    eEditTimeList.get(3).setEdit(true);
                                    handleCheckWeek(3);
                                    mTextTime.get(15).setText(handleWeek(3));
                                    eEditTimeList.get(3).setStart(editStart);
                                    eEditTimeList.get(3).setEnd(editEnd);
                                    mTextTime.get(16).setText(eEditTimeList.get(3).getStart());
                                    mTextTime.get(18).setText(eEditTimeList.get(3).getEnd());
                                    break;
                            }
                        }
                        isEdit = true;
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextTime.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (isEdit) {
                            List<String> time1 = new ArrayList<>();
                            List<String> time2 = new ArrayList<>();
                            List<String> time3 = new ArrayList<>();
                            List<String> time4 = new ArrayList<>();
                            List<String> time5 = new ArrayList<>();
                            List<String> time6 = new ArrayList<>();
                            List<String> time7 = new ArrayList<>();
                            for (int i = 0; i < 4; i++) {
                                if (eEditTimeList.get(i).isEdit()) {
                                    if (eEditTimeList.get(i).isAllDay()) {
                                        if (eEditTimeList.get(i).isSun()) {
                                            time1.add("24");
                                        } else {
                                            time1.add("");
                                        }
                                        if (eEditTimeList.get(i).isMon()) {
                                            time2.add("24");
                                        } else {
                                            time2.add("");
                                        }
                                        if (eEditTimeList.get(i).isTue()) {
                                            time3.add("24");
                                        } else {
                                            time3.add("");
                                        }
                                        if (eEditTimeList.get(i).isWed()) {
                                            time4.add("24");
                                        } else {
                                            time4.add("");
                                        }
                                        if (eEditTimeList.get(i).isThu()) {
                                            time5.add("24");
                                        } else {
                                            time5.add("");
                                        }
                                        if (eEditTimeList.get(i).isFri()) {
                                            time6.add("24");
                                        } else {
                                            time6.add("");
                                        }
                                        if (eEditTimeList.get(i).isSat()) {
                                            time7.add("24");
                                        } else {
                                            time7.add("");
                                        }
                                    } else {
                                        if (eEditTimeList.get(i).isSun()) {
                                            time1.add(eEditTimeList.get(i).getStart() + "-" + eEditTimeList.get(i).getEnd());
                                        } else {
                                            time1.add("");
                                        }
                                        if (eEditTimeList.get(i).isMon()) {
                                            time2.add(eEditTimeList.get(i).getStart() + "-" + eEditTimeList.get(i).getEnd());
                                        } else {
                                            time2.add("");
                                        }
                                        if (eEditTimeList.get(i).isTue()) {
                                            time3.add(eEditTimeList.get(i).getStart() + "-" + eEditTimeList.get(i).getEnd());
                                        } else {
                                            time3.add("");
                                        }
                                        if (eEditTimeList.get(i).isWed()) {
                                            time4.add(eEditTimeList.get(i).getStart() + "-" + eEditTimeList.get(i).getEnd());
                                        } else {
                                            time4.add("");
                                        }
                                        if (eEditTimeList.get(i).isThu()) {
                                            time5.add(eEditTimeList.get(i).getStart() + "-" + eEditTimeList.get(i).getEnd());
                                        } else {
                                            time5.add("");
                                        }
                                        if (eEditTimeList.get(i).isFri()) {
                                            time6.add(eEditTimeList.get(i).getStart() + "-" + eEditTimeList.get(i).getEnd());
                                        } else {
                                            time6.add("");
                                        }
                                        if (eEditTimeList.get(i).isSat()) {
                                            time7.add(eEditTimeList.get(i).getStart() + "-" + eEditTimeList.get(i).getEnd());
                                        } else {
                                            time7.add("");
                                        }
                                    }
                                } else {
                                    time1.add("");
                                    time2.add("");
                                    time3.add("");
                                    time4.add("");
                                    time5.add("");
                                    time6.add("");
                                    time7.add("");
                                }
                            }
                            if (MyApplication.getAppData().isFromEditAttraction()) {
                                MyApplication.getAppData().getgInfoData().getData().get(0).setMon(time2);
                                MyApplication.getAppData().getgInfoData().getData().get(0).setTue(time3);
                                MyApplication.getAppData().getgInfoData().getData().get(0).setWed(time4);
                                MyApplication.getAppData().getgInfoData().getData().get(0).setThu(time5);
                                MyApplication.getAppData().getgInfoData().getData().get(0).setFri(time6);
                                MyApplication.getAppData().getgInfoData().getData().get(0).setSat(time7);
                                MyApplication.getAppData().getgInfoData().getData().get(0).setSun(time1);
                            } else {
                                mIMainActivity.getCreateData().setTime(new ECreateAttraction.Time("0", time1, time2, time3, time4, time5, time6, time7));
                            }
                            if (MyApplication.getAppData().isFromEditAttraction()) {
                                MyApplication.getAppData().getIsEdit().set(3, true);
                            }
                        } else {
                            MyApplication.getAppData().setFromEditAttraction(false);
                        }
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextTime.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setFromEditAttraction(false);
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextTimer.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mLayouts.get(1).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextWeek.get(8))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mLayouts.get(0).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageTime.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        handleCancelTimeOne();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageTime.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        handleCancelTimeTwo();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageTime.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        handleCancelTimeThree();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageTime.get(3))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        handleCancelTimeFour();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void showDefaultTime(List<String> week, List<EEditTime> eEditTimeList) {
        this.eEditTimeList = eEditTimeList;
        for (int i = 0; i < 4; i++) {
            if (eEditTimeList.get(i).isEdit()) {
                switch (i) {
                    case 0:
                        handleSetTimeState(0, true);
                        mTextTime.get(3).setText(week.get(0));
                        mTextTime.get(4).setText(eEditTimeList.get(i).getStart());
                        mTextTime.get(6).setText(eEditTimeList.get(i).getEnd());
                        break;
                    case 1:
                        handleSetTimeState(1, true);
                        mTextTime.get(7).setText(week.get(1));
                        mTextTime.get(8).setText(eEditTimeList.get(i).getStart());
                        mTextTime.get(10).setText(eEditTimeList.get(i).getEnd());
                        break;
                    case 2:
                        handleSetTimeState(2, true);
                        mTextTime.get(11).setText(week.get(2));
                        mTextTime.get(12).setText(eEditTimeList.get(i).getStart());
                        mTextTime.get(14).setText(eEditTimeList.get(i).getEnd());
                        break;
                    case 3:
                        handleSetTimeState(3, true);
                        mTextTime.get(15).setText(week.get(3));
                        mTextTime.get(16).setText(eEditTimeList.get(i).getStart());
                        mTextTime.get(18).setText(eEditTimeList.get(i).getEnd());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity) context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private void handleSetTimeState(int index, boolean needOpen) {
        int state;
        if (needOpen) {
            state = View.VISIBLE;
        } else {
            state = View.GONE;
        }
        switch (index) {
            case 0:
                mTextTime.get(3).setVisibility(state);
                mTextTime.get(4).setVisibility(state);
                mTextTime.get(5).setVisibility(state);
                mTextTime.get(6).setVisibility(state);
                mViewBars.get(0).setVisibility(state);
                mViewBars.get(1).setVisibility(state);
                mViewBars.get(2).setVisibility(state);
                mImageTime.get(0).setVisibility(state);
                break;
            case 1:
                mTextTime.get(7).setVisibility(state);
                mTextTime.get(8).setVisibility(state);
                mTextTime.get(9).setVisibility(state);
                mTextTime.get(10).setVisibility(state);
                mViewBars.get(3).setVisibility(state);
                mViewBars.get(4).setVisibility(state);
                mViewBars.get(5).setVisibility(state);
                mImageTime.get(1).setVisibility(state);
                break;
            case 2:
                mTextTime.get(11).setVisibility(state);
                mTextTime.get(12).setVisibility(state);
                mTextTime.get(13).setVisibility(state);
                mTextTime.get(14).setVisibility(state);
                mViewBars.get(6).setVisibility(state);
                mViewBars.get(7).setVisibility(state);
                mViewBars.get(8).setVisibility(state);
                mImageTime.get(2).setVisibility(state);
                break;
            case 3:
                mTextTime.get(15).setVisibility(state);
                mTextTime.get(16).setVisibility(state);
                mTextTime.get(17).setVisibility(state);
                mTextTime.get(18).setVisibility(state);
                mViewBars.get(9).setVisibility(state);
                mViewBars.get(10).setVisibility(state);
                mViewBars.get(11).setVisibility(state);
                mImageTime.get(3).setVisibility(state);
                break;
            default:
                break;
        }
    }

    private String handleWeek(int type) {
        StringBuilder stringBuilder = new StringBuilder();
        String mon = getString(R.string.weekly_mon);
        String tue = getString(R.string.weekly_tue);
        String wed = getString(R.string.weekly_wed);
        String thu = getString(R.string.weekly_thu);
        String fri = getString(R.string.weekly_fri);
        String sat = getString(R.string.weekly_sat);
        String sun = getString(R.string.weekly_sun);
        String point = ",";
        boolean isFirst = true;
        if (eEditTimeList.get(type).isMon()) {
            isFirst = false;
            stringBuilder.append(mon);
        }
        if (eEditTimeList.get(type).isTue()) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(point);
            }
            stringBuilder.append(tue);
        }
        if (eEditTimeList.get(type).isWed()) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(point);
            }
            stringBuilder.append(wed);
        }
        if (eEditTimeList.get(type).isThu()) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(point);
            }
            stringBuilder.append(thu);
        }
        if (eEditTimeList.get(type).isFri()) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(point);
            }
            stringBuilder.append(fri);
        }
        if (eEditTimeList.get(type).isSat()) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append(point);
            }
            stringBuilder.append(sat);
        }
        if (eEditTimeList.get(type).isSun()) {
            if (!isFirst) {
                stringBuilder.append(point);
            }
            stringBuilder.append(sun);
        }
        return stringBuilder.toString();
    }

    private void handleCheckWeek(int index) {
        eEditTimeList.get(index).setMon(editWeek.get(0));
        eEditTimeList.get(index).setTue(editWeek.get(1));
        eEditTimeList.get(index).setWed(editWeek.get(2));
        eEditTimeList.get(index).setThu(editWeek.get(3));
        eEditTimeList.get(index).setFri(editWeek.get(4));
        eEditTimeList.get(index).setSat(editWeek.get(5));
        eEditTimeList.get(index).setSun(editWeek.get(6));
        eEditTimeList.get(index).setAllDay(editWeek.get(7));
    }

    private void handleCancelTimeOne() {
        isEdit = true;
        eEditTimeList.remove(0);
        eEditTimeList.add(new EEditTime());
        int index = -1;
        for (int i = 0; i < 4; i++) {
            if (!eEditTimeList.get(i).isEdit()) {
                index = i;
                break;
            }
        }
        switch (index) {
            case 0:
                handleSetTimeState(0, false);
                mTextTime.get(3).setText("");
                mTextTime.get(4).setText("");
                mTextTime.get(6).setText("");
                break;
            case 1:
                handleSetTimeState(1, false);
                mTextTime.get(7).setText("");
                mTextTime.get(8).setText("");
                mTextTime.get(10).setText("");
                break;
            case 2:
                handleSetTimeState(2, false);
                mTextTime.get(11).setText("");
                mTextTime.get(12).setText("");
                mTextTime.get(14).setText("");
                break;
            case 3:
                handleSetTimeState(3, false);
                mTextTime.get(15).setText("");
                mTextTime.get(16).setText("");
                mTextTime.get(18).setText("");
                break;
            default:
                break;
        }
        pEditTime.handleRefreshTimeData(eEditTimeList);
    }

    private void handleCancelTimeTwo() {
        isEdit = true;
        eEditTimeList.remove(1);
        eEditTimeList.add(new EEditTime());
        int index = -1;
        for (int i = 0; i < 4; i++) {
            if (!eEditTimeList.get(i).isEdit()) {
                index = i;
                break;
            }
        }
        switch (index) {
            case 0:
                handleSetTimeState(0, false);
                mTextTime.get(3).setText("");
                mTextTime.get(4).setText("");
                mTextTime.get(6).setText("");
                break;
            case 1:
                handleSetTimeState(1, false);
                mTextTime.get(7).setText("");
                mTextTime.get(8).setText("");
                mTextTime.get(10).setText("");
                break;
            case 2:
                handleSetTimeState(2, false);
                mTextTime.get(11).setText("");
                mTextTime.get(12).setText("");
                mTextTime.get(14).setText("");
                break;
            case 3:
                handleSetTimeState(3, false);
                mTextTime.get(15).setText("");
                mTextTime.get(16).setText("");
                mTextTime.get(18).setText("");
                break;
            default:
                break;
        }
        pEditTime.handleRefreshTimeData(eEditTimeList);
    }

    private void handleCancelTimeThree() {
        isEdit = true;
        eEditTimeList.remove(2);
        eEditTimeList.add(new EEditTime());
        int index = -1;
        for (int i = 0; i < 4; i++) {
            if (!eEditTimeList.get(i).isEdit()) {
                index = i;
                break;
            }
        }
        switch (index) {
            case 0:
                handleSetTimeState(0, false);
                mTextTime.get(3).setText("");
                mTextTime.get(4).setText("");
                mTextTime.get(6).setText("");
                break;
            case 1:
                handleSetTimeState(1, false);
                mTextTime.get(7).setText("");
                mTextTime.get(8).setText("");
                mTextTime.get(10).setText("");
                break;
            case 2:
                handleSetTimeState(2, false);
                mTextTime.get(11).setText("");
                mTextTime.get(12).setText("");
                mTextTime.get(14).setText("");
                break;
            case 3:
                handleSetTimeState(3, false);
                mTextTime.get(15).setText("");
                mTextTime.get(16).setText("");
                mTextTime.get(18).setText("");
                break;
            default:
                break;
        }
        pEditTime.handleRefreshTimeData(eEditTimeList);
    }

    private void handleCancelTimeFour() {
        isEdit = true;
        eEditTimeList.remove(3);
        eEditTimeList.add(new EEditTime());
        int index = -1;
        for (int i = 0; i < 4; i++) {
            if (!eEditTimeList.get(i).isEdit()) {
                index = i;
                break;
            }
        }
        switch (index) {
            case 0:
                handleSetTimeState(0, false);
                mTextTime.get(3).setText("");
                mTextTime.get(4).setText("");
                mTextTime.get(6).setText("");
                break;
            case 1:
                handleSetTimeState(1, false);
                mTextTime.get(7).setText("");
                mTextTime.get(8).setText("");
                mTextTime.get(10).setText("");
                break;
            case 2:
                handleSetTimeState(2, false);
                mTextTime.get(11).setText("");
                mTextTime.get(12).setText("");
                mTextTime.get(14).setText("");
                break;
            case 3:
                handleSetTimeState(3, false);
                mTextTime.get(15).setText("");
                mTextTime.get(16).setText("");
                mTextTime.get(18).setText("");
                break;
            default:
                break;
        }
        pEditTime.handleRefreshTimeData(eEditTimeList);
    }
}
