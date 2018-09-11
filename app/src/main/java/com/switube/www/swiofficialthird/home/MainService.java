package com.switube.www.swiofficialthird.home;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.LocationResult;
import com.switube.www.swiofficialthird.home.view.MainActivity;

import java.util.Locale;

public class MainService extends Service {
    private MainBinder mMainBinder = new MainBinder();
    public class MainBinder extends Binder {
        public MainService getService() {
            return MainService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMainBinder;
    }

    private HandlerThread mHandlerThread = new HandlerThread("myLooper");
    private Handler mHandler;
    private Runnable mRunnable;
    private int mSec = 0;
    private int mMin = 0;
    private int mHour = 0;
    private int mCount = 0;
    private StringBuilder mStringBuilder;
    @Override
    public void onCreate() {
        super.onCreate();
        mHandlerThread.start();
        mHandler = new Handler(mHandlerThread.getLooper());
        mStringBuilder = new StringBuilder();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (isStart) {
                        mCount++;
                        mSec++;
                        Log.e("run", String.valueOf(mCount));
                        if (mSec == 60) {
                            mMin++;
                            mSec = 0;
                        }
                        if (mMin == 60) {
                            mHour++;
                            mMin = 0;
                        }
                        ((MainActivity)mContext).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showTimer();
                                calculateAvg();
                            }
                        });
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
        mHandlerThread.quit();
    }

    private boolean isStart = false;
    public void startMyTimer() {
        isStart = true;
        mHandler.post(mRunnable);
    }

    public void pauseMyTimer() {
        isStart = false;
        mHandler.removeCallbacksAndMessages(null);
        //mHandler.removeCallbacks(mRunnable);
    }

    public void stopMyTimer() {
        mSec = 0;
        mMin = 0;
        mHour = 0;
        mCount = 0;
        mDistance = 0;
        mAvg = 0;
    }

    private TextView mTimerView;
    private TextView mDistanceView;
    private TextView mAvgView;
    public void setTimerView(TextView timerView, TextView distanceView, TextView avgView) {
        mTimerView = timerView;
        mDistanceView = distanceView;
        mAvgView = avgView;
    }

    public void clearTimerView() {
        mTimerView = null;
    }

    private void showTimer() {
        if (mTimerView != null) {
            mStringBuilder.delete(0, mStringBuilder.length());
            if (mHour > 0) {
                if (mHour < 10) {
                    mStringBuilder.append(0);
                }
                mStringBuilder.append(mHour);
                mStringBuilder.append(":");
                mTimerView.setTextSize(30);
            } else {
                mTimerView.setTextSize(50);
            }
            if (mMin < 10) {
                mStringBuilder.append(0);
            }
            mStringBuilder.append(mMin);
            mStringBuilder.append(":");
            if (mSec < 10) {
                mStringBuilder.append(0);
            }
            mStringBuilder.append(mSec);
            mTimerView.setText(mStringBuilder.toString());
        }
    }

    private LocationResult mLocationResult;
    private float mDistance = 0;
    public void setLocation(LocationResult locationResult) {
        if (isStart) {
            if (mLocationResult == null) {
                mLocationResult = locationResult;
            } else {
                float[] results = new float[1];
                Location.distanceBetween(mLocationResult.getLastLocation().getLatitude(),
                        mLocationResult.getLastLocation().getLongitude(),
                        locationResult.getLastLocation().getLatitude(),
                        locationResult.getLastLocation().getLongitude(), results);
                mLocationResult = locationResult;
                if (results[0] <= 11) {
                    mDistance += results[0];
                }
                float result = mDistance / 1000;
                if (result < 0.01) {
                    mDistanceView.setText("0.00");
                } else {
                    mDistanceView.setText(String.format(Locale.TAIWAN, "%.2f", result));
                }
            }
        }
    }

    private double mAvg = 0;
    private void calculateAvg() {
        if (mDistance != 0 && mCount != 0) {
            mAvg = mCount / (mDistance / 1000) ;
            double sec = 0;
            double min = 0;
            if (mAvg > 59) {
                sec = mAvg % 60;
                min = mAvg / 60;
            } else {
                sec = mAvg;
            }
            StringBuilder stringBuffer = new StringBuilder();
            stringBuffer.append((int)min);
            stringBuffer.append(":");
            if (sec < 10) {
                stringBuffer.append("0");
            }
            stringBuffer.append((int)sec);
            mAvgView.setText(stringBuffer.toString());
        } else {
            mAvgView.setText("99+");
        }
    }

    private Context mContext;
    public void setContext(Context context) {
        mContext = context;
    }
}
