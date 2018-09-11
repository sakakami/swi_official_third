package com.switube.www.swiofficialthird.create.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.CreateAttractionEntity;
import com.switube.www.swiofficialthird.home.view.IMainActivity;

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
public class EditTimeFragment extends Fragment {
    private List<Boolean> isAddNewTime = new ArrayList<>();
    private List<Boolean> checkWeek = new ArrayList<>();
    private List<String> saveTime = new ArrayList<>();
    public EditTimeFragment() {
        for (int i = 0; i < 32; i++) {
            checkWeek.add(false);
        }
        for (int i = 0; i < 4; i++) {
            isAddNewTime.add(false);
        }
        for (int i = 0; i < 8; i++) {
            saveTime.add("0");
        }
    }

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
    private Unbinder mUnbinder;
    private boolean isSetStart = true;
    private int nowSetTime = 0;
    private boolean isRestart = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_time, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        RxView.clicks(mTextTime.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (isAddNewTime.get(0)) {
                            int index = isAddNewTime.lastIndexOf(true);
                            if (index != 3) {
                                isAddNewTime.set(index + 1, true);
                                handleSetTimeState(index + 1, true);
                            } else {
                                mTextTime.get(2).setVisibility(View.INVISIBLE);
                            }
                        } else {
                            isAddNewTime.set(0, true);
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
                        mLayouts.get(0).setVisibility(View.VISIBLE);
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
                        mLayouts.get(0).setVisibility(View.VISIBLE);
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
                        mLayouts.get(0).setVisibility(View.VISIBLE);
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
                        mLayouts.get(0).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        mCheckBox.get(0).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isRestart) {
                    switch (nowSetTime) {
                        case 0:
                            checkWeek.set(0, b);
                            break;
                        case 1:
                            checkWeek.set(8, b);
                            break;
                        case 2:
                            checkWeek.set(16, b);
                            break;
                        default:
                            checkWeek.set(24, b);
                            break;
                    }
                }
            }
        });
        mCheckBox.get(1).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isRestart) {
                    switch (nowSetTime) {
                        case 0:
                            checkWeek.set(1, b);
                            break;
                        case 1:
                            checkWeek.set(9, b);
                            break;
                        case 2:
                            checkWeek.set(17, b);
                            break;
                        default:
                            checkWeek.set(25, b);
                            break;
                    }
                }
            }
        });
        mCheckBox.get(2).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isRestart) {
                    switch (nowSetTime) {
                        case 0:
                            checkWeek.set(2, b);
                            break;
                        case 1:
                            checkWeek.set(10, b);
                            break;
                        case 2:
                            checkWeek.set(18, b);
                            break;
                        default:
                            checkWeek.set(26, b);
                            break;
                    }
                }
            }
        });
        mCheckBox.get(3).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isRestart) {
                    switch (nowSetTime) {
                        case 0:
                            checkWeek.set(3, b);
                            break;
                        case 1:
                            checkWeek.set(11, b);
                            break;
                        case 2:
                            checkWeek.set(19, b);
                            break;
                        default:
                            checkWeek.set(27, b);
                            break;
                    }
                }
            }
        });
        mCheckBox.get(4).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isRestart) {
                    switch (nowSetTime) {
                        case 0:
                            checkWeek.set(4, b);
                            break;
                        case 1:
                            checkWeek.set(12, b);
                            break;
                        case 2:
                            checkWeek.set(20, b);
                            break;
                        default:
                            checkWeek.set(28, b);
                            break;
                    }
                }
            }
        });
        mCheckBox.get(5).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isRestart) {
                    switch (nowSetTime) {
                        case 0:
                            checkWeek.set(5, b);
                            break;
                        case 1:
                            checkWeek.set(13, b);
                            break;
                        case 2:
                            checkWeek.set(21, b);
                            break;
                        default:
                            checkWeek.set(29, b);
                            break;
                    }
                }
            }
        });
        mCheckBox.get(6).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isRestart) {
                    switch (nowSetTime) {
                        case 0:
                            checkWeek.set(6, b);
                            break;
                        case 1:
                            checkWeek.set(14, b);
                            break;
                        case 2:
                            checkWeek.set(22, b);
                            break;
                        default:
                            checkWeek.set(30, b);
                            break;
                    }
                }
            }
        });
        mCheckBox.get(7).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!isRestart) {
                    switch (nowSetTime) {
                        case 0:
                            checkWeek.set(7, b);
                            if (b) {
                                for (int i = 0; i < 7; i++) {
                                    checkWeek.set(i, false);
                                    mCheckBox.get(i).setChecked(false);
                                }
                            }
                            break;
                        case 1:
                            checkWeek.set(15, b);
                            if (b) {
                                for (int i = 8; i < 15; i++) {
                                    checkWeek.set(i, false);
                                    mCheckBox.get(i).setChecked(false);
                                }
                            }
                            break;
                        case 2:
                            checkWeek.set(23, b);
                            if (b) {
                                for (int i = 16; i < 23; i++) {
                                    checkWeek.set(i, false);
                                    mCheckBox.get(i).setChecked(false);
                                }
                            }
                            break;
                        default:
                            checkWeek.set(31, b);
                            if (b) {
                                for (int i = 24; i < 31; i++) {
                                    checkWeek.set(i, false);
                                    mCheckBox.get(i).setChecked(false);
                                }
                            }
                            break;
                    }
                }
            }
        });
        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                String time;
                String min;
                if (i < 10) {
                    time = "0" + String.valueOf(i);
                } else {
                    time = String.valueOf(i);
                }
                if (i1 < 10) {
                    min = "0" + String.valueOf(i1);
                } else {
                    min = String.valueOf(i1);
                }
                if (isSetStart) {
                    saveTime.set(nowSetTime * 2, time + " : " + min);
                } else {
                    saveTime.set(nowSetTime * 2 + 1, time + " : " + min);
                }
            }
        });
        RxView.clicks(mTextWeek.get(7))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        isSetStart = true;
                        mLayouts.get(0).setVisibility(View.GONE);
                        mLayouts.get(1).setVisibility(View.VISIBLE);
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
                            isSetStart = false;
                            mTextTimer.get(2).setText("結束營業間");
                            mTextTimer.get(0).setText("確認");
                        } else {
                            isSetStart = true;
                            mTextTimer.get(2).setText("開始營業時間");
                            mTextTimer.get(0).setText("下一步");
                            mLayouts.get(1).setVisibility(View.GONE);
                            switch (nowSetTime) {
                                case 0:
                                    mTextTime.get(4).setText(saveTime.get(0));
                                    mTextTime.get(6).setText(saveTime.get(1));
                                    break;
                                case 1:
                                    mTextTime.get(8).setText(saveTime.get(2));
                                    mTextTime.get(10).setText(saveTime.get(3));
                                    break;
                                case 2:
                                    mTextTime.get(12).setText(saveTime.get(4));
                                    mTextTime.get(14).setText(saveTime.get(5));
                                    break;
                                default:
                                    mTextTime.get(16).setText(saveTime.get(6));
                                    mTextTime.get(18).setText(saveTime.get(7));
                                    break;
                            }
                            isRestart = true;
                            for (int i = 0; i < 8; i++) {
                                mCheckBox.get(i).setChecked(false);
                            }
                            isRestart = false;
                        }
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
                        List<String> time1 = new ArrayList<>();
                        List<String> time2 = new ArrayList<>();
                        List<String> time3 = new ArrayList<>();
                        List<String> time4 = new ArrayList<>();
                        List<String> time5 = new ArrayList<>();
                        List<String> time6 = new ArrayList<>();
                        List<String> time7 = new ArrayList<>();
                        if (checkWeek.get(7)) {
                            time1.add("24");
                            time2.add("24");
                            time3.add("24");
                            time4.add("24");
                            time5.add("24");
                            time6.add("24");
                            time7.add("24");
                        } else {
                            if (checkWeek.get(0)) {
                                time1.add(saveTime.get(0) + " - " + saveTime.get(1));
                            } else {
                                time1.add("");
                            }
                            if (checkWeek.get(1)) {
                                time2.add(saveTime.get(0) + " - " + saveTime.get(1));
                            } else {
                                time2.add("");
                            }
                            if (checkWeek.get(2)) {
                                time3.add(saveTime.get(0) + " - " + saveTime.get(1));
                            } else {
                                time3.add("");
                            }
                            if (checkWeek.get(3)) {
                                time4.add(saveTime.get(0) + " - " + saveTime.get(1));
                            } else {
                                time4.add("");
                            }
                            if (checkWeek.get(4)) {
                                time5.add(saveTime.get(0) + " - " + saveTime.get(1));
                            } else {
                                time5.add("");
                            }
                            if (checkWeek.get(5)) {
                                time6.add(saveTime.get(0) + " - " + saveTime.get(1));
                            } else {
                                time6.add("");
                            }
                            if (checkWeek.get(6)) {
                                time7.add(saveTime.get(0) + " - " + saveTime.get(1));
                            } else {
                                time7.add("");
                            }
                        }
                        if (checkWeek.get(15)) {
                            time1.add("24");
                            time2.add("24");
                            time3.add("24");
                            time4.add("24");
                            time5.add("24");
                            time6.add("24");
                            time7.add("24");
                        } else {
                            if (checkWeek.get(8)) {
                                time1.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time1.add("");
                            }
                            if (checkWeek.get(9)) {
                                time2.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time2.add("");
                            }
                            if (checkWeek.get(10)) {
                                time3.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time3.add("");
                            }
                            if (checkWeek.get(11)) {
                                time4.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time4.add("");
                            }
                            if (checkWeek.get(12)) {
                                time5.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time5.add("");
                            }
                            if (checkWeek.get(13)) {
                                time6.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time6.add("");
                            }
                            if (checkWeek.get(14)) {
                                time7.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time7.add("");
                            }
                        }
                        if (checkWeek.get(23)) {
                            time1.add("24");
                            time2.add("24");
                            time3.add("24");
                            time4.add("24");
                            time5.add("24");
                            time6.add("24");
                            time7.add("24");
                        } else {
                            if (checkWeek.get(16)) {
                                time1.add(saveTime.get(4) + " - " + saveTime.get(5));
                            } else {
                                time1.add("");
                            }
                            if (checkWeek.get(17)) {
                                time2.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time2.add("");
                            }
                            if (checkWeek.get(18)) {
                                time3.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time3.add("");
                            }
                            if (checkWeek.get(19)) {
                                time4.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time4.add("");
                            }
                            if (checkWeek.get(20)) {
                                time5.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time5.add("");
                            }
                            if (checkWeek.get(21)) {
                                time6.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time6.add("");
                            }
                            if (checkWeek.get(22)) {
                                time7.add(saveTime.get(2) + " - " + saveTime.get(3));
                            } else {
                                time7.add("");
                            }
                        }
                        if (checkWeek.get(31)) {
                            time1.add("24");
                            time2.add("24");
                            time3.add("24");
                            time4.add("24");
                            time5.add("24");
                            time6.add("24");
                            time7.add("24");
                        } else {
                            if (checkWeek.get(24)) {
                                time1.add(saveTime.get(6) + " - " + saveTime.get(7));
                            } else {
                                time1.add("");
                            }
                            if (checkWeek.get(25)) {
                                time2.add(saveTime.get(6) + " - " + saveTime.get(7));
                            } else {
                                time2.add("");
                            }
                            if (checkWeek.get(26)) {
                                time3.add(saveTime.get(6) + " - " + saveTime.get(7));
                            } else {
                                time3.add("");
                            }
                            if (checkWeek.get(27)) {
                                time4.add(saveTime.get(6) + " - " + saveTime.get(7));
                            } else {
                                time4.add("");
                            }
                            if (checkWeek.get(28)) {
                                time5.add(saveTime.get(6) + " - " + saveTime.get(7));
                            } else {
                                time5.add("");
                            }
                            if (checkWeek.get(29)) {
                                time6.add(saveTime.get(6) + " - " + saveTime.get(7));
                            } else {
                                time6.add("");
                            }
                            if (checkWeek.get(30)) {
                                time7.add(saveTime.get(6) + " - " + saveTime.get(7));
                            } else {
                                time7.add("");
                            }
                        }
                        mIMainActivity.getCreateData().setTime(new CreateAttractionEntity.Time(time1, time2, time3, time4, time5, time6, time7));
                        getFragmentManager().popBackStack();
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
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
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
                break;
            case 1:
                mTextTime.get(7).setVisibility(state);
                mTextTime.get(8).setVisibility(state);
                mTextTime.get(9).setVisibility(state);
                mTextTime.get(10).setVisibility(state);
                mViewBars.get(3).setVisibility(state);
                mViewBars.get(4).setVisibility(state);
                mViewBars.get(5).setVisibility(state);
                break;
            case 2:
                mTextTime.get(11).setVisibility(state);
                mTextTime.get(12).setVisibility(state);
                mTextTime.get(13).setVisibility(state);
                mTextTime.get(14).setVisibility(state);
                mViewBars.get(6).setVisibility(state);
                mViewBars.get(7).setVisibility(state);
                mViewBars.get(8).setVisibility(state);
                break;
            case 3:
                mTextTime.get(15).setVisibility(state);
                mTextTime.get(16).setVisibility(state);
                mTextTime.get(17).setVisibility(state);
                mTextTime.get(18).setVisibility(state);
                mViewBars.get(9).setVisibility(state);
                mViewBars.get(10).setVisibility(state);
                mViewBars.get(11).setVisibility(state);
                break;
            default:
                break;
        }
    }
}
