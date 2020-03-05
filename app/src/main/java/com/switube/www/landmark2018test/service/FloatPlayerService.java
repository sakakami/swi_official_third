package com.switube.www.landmark2018test.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.switube.www.landmark2018test.MainActivity;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.ECarbon;
import com.switube.www.landmark2018test.entity.EEco;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.view.VMap;
import com.switube.www.landmark2018test.youtube.FloatPlayerKiller;
import com.switube.www.landmark2018test.service.callback.IFloatPlayer;
import com.switube.www.landmark2018test.service.callback.IFloatPlayerService;
import com.switube.www.landmark2018test.youtube.YouTubePlayerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FloatPlayerService extends Service implements IFloatPlayerService, IFloatPlayer {
    private WindowManager windowManager;
    //private FloatPlayerKiller floatPlayerKiller;
    private DisplayMetrics displayMetrics;
    private View floatPlayerKiller;
    private int bigViewX;
    private int bigViewY;
    private int bigViewWidth;
    private int bigViewHeight;
    private int smallViewX;
    private int smallViewY;
    private int smallViewWidth;
    private int smallViewHeight;
    private int screenWidth;
    private int screenHeight;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel("ebike", "Carbon", NotificationManager.IMPORTANCE_HIGH);
            channel.setBypassDnd(true);
            channel.setLockscreenVisibility(Notification.VISIBILITY_SECRET);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.createNotificationChannel(channel);

        }
        NotificationCompat.Builder ntf = new NotificationCompat.Builder(MyApplication.getInstance(), "ebike");
        ntf.setOngoing(true);
        Notification notification = ntf.build();
        startForeground(111111, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mYouTubePlayerView != null) {
            mYouTubePlayerView.pauseVideo();
            mYouTubePlayerView.releasePlayer();
        }
    }

    @Override
    public void showFloatPlayerKiller(boolean needShow) {
        if (floatPlayerKiller != null) {
            if (mIsFullScreenMode) {
                mLayoutParamsForKiller.x = screenHeight / 2 - mLayoutParamsForKiller.width / 2;
                mLayoutParamsForKiller.y = screenWidth - mLayoutParamsForKiller.height;
            } else {
                mLayoutParamsForKiller.x = screenWidth / 2 - mLayoutParamsForKiller.width / 2;
                mLayoutParamsForKiller.y = screenHeight - mLayoutParamsForKiller.height - MyApplication.getAppData().getStatusHeight();
            }
            windowManager.updateViewLayout(floatPlayerKiller, mLayoutParamsForKiller);
            if (needShow) {
                floatPlayerKiller.setVisibility(View.VISIBLE);
            } else {
                floatPlayerKiller.setVisibility(View.INVISIBLE);
            }
        }
    }

    private Context mContext;
    private YouTubePlayerView mYouTubePlayerView;
    private WindowManager.LayoutParams mLayoutParams;
    private boolean mFloatPlayerCreate = false;
    @Override
    public void initFloatPlayer(Context context) {
        mContext = context;
        if (windowManager == null) {
            windowManager = (WindowManager)context.getSystemService(WINDOW_SERVICE);
        }

        displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        bigViewWidth = screenWidth - (int)(16 * displayMetrics.density);
        bigViewX = (int)(8 * displayMetrics.density);
        if (screenHeight == MyApplication.getAppData().getUseableHeight()) {
            bigViewHeight = screenHeight / 2 - (int)(100 * displayMetrics.density);
            bigViewY = (int)(50 * displayMetrics.density);
        } else {
            bigViewHeight = screenHeight / 2 - (int)(84 * displayMetrics.density) - MyApplication.getAppData().getStatusHeight();
            bigViewY = (int)(50 * displayMetrics.density) + MyApplication.getAppData().getStatusHeight();
        }

        smallViewWidth = (int)(200 * displayMetrics.density);
        smallViewHeight = (int)(110 * displayMetrics.density);
        smallViewX = screenWidth - (int)(205 * displayMetrics.density);
        smallViewY = screenHeight / 2;

        if (mYouTubePlayerView == null) {
            mYouTubePlayerView = new YouTubePlayerView(context);
        }

        if (mLayoutParams == null) {
            mLayoutParams = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
            mLayoutParams.format = PixelFormat.RGBA_8888;
            mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            mLayoutParams.gravity = Gravity.TOP | Gravity.START;
            mLayoutParams.width = bigViewWidth;
            mLayoutParams.height = bigViewHeight;
        }
        windowManager.addView(mYouTubePlayerView, mLayoutParams);
        mLayoutParams.x = bigViewX;
        mLayoutParams.y = bigViewY;
        windowManager.updateViewLayout(mYouTubePlayerView, mLayoutParams);
        mFloatPlayerCreate = true;
    }

    private WindowManager.LayoutParams mLayoutParamsForKiller;
    @Override
    public void initFloatPlayerKiller(Context context) {
        if (floatPlayerKiller == null) {
            floatPlayerKiller = new View(context);
            floatPlayerKiller.setBackgroundResource(R.drawable.btn_close_v1_2x);
        }
        if (mLayoutParamsForKiller == null) {
            mLayoutParamsForKiller = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            } else {
                mLayoutParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            }
            mLayoutParamsForKiller.format = PixelFormat.RGBA_8888;
            mLayoutParamsForKiller.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            mLayoutParamsForKiller.gravity = Gravity.TOP | Gravity.START;
            mLayoutParamsForKiller.width = (int)(50 * displayMetrics.density);
            mLayoutParamsForKiller.height = (int)(50 * displayMetrics.density);
            mLayoutParamsForKiller.x = screenWidth / 2 - mLayoutParamsForKiller.width / 2;
            if (screenHeight == MyApplication.getAppData().getUseableHeight()) {
                mLayoutParamsForKiller.y = screenHeight - mLayoutParamsForKiller.height;
            } else {
                mLayoutParamsForKiller.y = screenHeight - mLayoutParamsForKiller.height - MyApplication.getAppData().getStatusHeight();
            }
        }
        windowManager.addView(floatPlayerKiller, mLayoutParamsForKiller);
        floatPlayerKiller.setVisibility(View.INVISIBLE);
    }

    private boolean mIsBigMode = true;
    @Override
    public void showSmallMode() {
        mIsBigMode = false;
        mYouTubePlayerView.handleCloseTopLayout();
        mLayoutParams.width = smallViewWidth;
        mLayoutParams.height = smallViewHeight;
        if (mIsFullScreenMode) {
            mLayoutParams.x = screenHeight - smallViewWidth - (int)(10 * displayMetrics.density);
            if (screenHeight == MyApplication.getAppData().getUseableHeight()) {
                mLayoutParams.y = (int)(50 * displayMetrics.density);
            } else {
                mLayoutParams.y = (int)(50 * displayMetrics.density) - MyApplication.getAppData().getStatusHeight();
            }
        } else {
            mLayoutParams.x = smallViewX;
            mLayoutParams.y = smallViewY;
        }
        windowManager.updateViewLayout(mYouTubePlayerView, mLayoutParams);
    }

    @Override
    public void showBigMode() {
        mIsBigMode = true;
        mLayoutParams.width = bigViewWidth;
        mLayoutParams.height = bigViewHeight;
        mLayoutParams.x = bigViewX;
        mLayoutParams.y = bigViewY;
        windowManager.updateViewLayout(mYouTubePlayerView, mLayoutParams);
    }

    private boolean mIsFullScreenMode = false;
    @Override
    public void setIsFullScreenMode(boolean mode) {
        mIsFullScreenMode = mode;
    }

    @Override
    public void switchPlayMode(boolean mode) {
        if (mode) {
            mYouTubePlayerView.playVideo();
        } else {
            mYouTubePlayerView.pauseVideo();
        }
    }

    @Override
    public void switchFullscreenMode(boolean mode) {
        if (mode) {
            showBigMode();
        } else {
            mLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            windowManager.updateViewLayout(mYouTubePlayerView, mLayoutParams);
        }
    }

    @Override
    public void handleSwitchFullscreen() {
        mYouTubePlayerView.fullScreen();
    }

    @Override
    public void handleLoadVideo(String webid, float startSecond) {
        mYouTubePlayerView.loadVideo(webid, startSecond);
    }

    @Override
    public void handlePlayingSwitch() {
        if (MyApplication.getAppData().isPlaying()) {
            mYouTubePlayerView.playVideo();
        } else {
            mYouTubePlayerView.pauseVideo();
        }
    }

    @Override
    public void handlePlayerInitialized(final String webid, final float startSecond) {
        mYouTubePlayerView.initialized(webid, startSecond);
    }

    @Override
    public void handleFloatPlayerMoving(int x, int y) {
        int rangeX;
        int rangeY;
        if (mIsFullScreenMode) {
            rangeX = screenHeight - smallViewWidth / 2;
            rangeY = screenWidth - smallViewHeight / 2;
        } else {
            rangeX = screenWidth - smallViewWidth / 2;
            rangeY = screenHeight - smallViewHeight / 2;
        }
        if (x <= smallViewWidth / 2) {
            x = smallViewWidth / 2;
        }
        if (x >= rangeX) {
            x = rangeX;
        }
        if (y <= smallViewHeight / 2) {
            y = smallViewHeight / 2;
        }
        if (y >= rangeY) {
            y = rangeY;
        }
        mLayoutParams.x = x - smallViewWidth / 2;
        mLayoutParams.y = y - smallViewHeight / 2;
        windowManager.updateViewLayout(mYouTubePlayerView, mLayoutParams);
    }

    @Override
    public boolean getCheckFloatPlayerCreate() {
        return mFloatPlayerCreate;
    }

    @Override
    public void handleBeatClicked() {
        showSmallMode();
        int length = MyApplication.getAppData().getgPlayer().getBeatData().size();
        final String[] item = new String[length];
        final String[] url = new String[length];
        for (int i = 0; i < length; i++) {
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    item[i] = MyApplication.getAppData().getgPlayer().getBeatData().get(i).getAsname_tw();
                    url[i] = MyApplication.getAppData().getgPlayer().getBeatData().get(i).getAsurl_tw();
                    break;
                case 2:
                    item[i] = MyApplication.getAppData().getgPlayer().getBeatData().get(i).getAsname_ch();
                    url[i] = MyApplication.getAppData().getgPlayer().getBeatData().get(i).getAsurl_ch();
                    break;
                case 3:
                    item[i] = MyApplication.getAppData().getgPlayer().getBeatData().get(i).getAsname_jp();
                    url[i] = MyApplication.getAppData().getgPlayer().getBeatData().get(i).getAsurl_jp();
                    break;
                default:
                    item[i] = MyApplication.getAppData().getgPlayer().getBeatData().get(i).getAsname();
                    url[i] = MyApplication.getAppData().getgPlayer().getBeatData().get(i).getAsurl();
                    break;
            }
        }
        AlertDialogUtil.getInstance().initDialogBuilder(mContext,
                item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        setCheckIsDeskTop(true);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url[i]));
                        mContext.startActivity(intent);
                    }
                },
                mContext.getString(R.string.global_cancel),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!mIsBigMode) {
                            mIsBigMode = true;
                            showBigMode();
                        }
                    }
                });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    private boolean mCheckFloatCanSee = true;
    @Override
    public void showFloatPlayer(boolean needShow) {
        if (needShow) {
            mCheckFloatCanSee = true;
            mYouTubePlayerView.setVisibility(View.VISIBLE);
        } else {
            mCheckFloatCanSee = false;
            mYouTubePlayerView.setVisibility(View.INVISIBLE);
            mYouTubePlayerView.pauseVideo();
            ((MainActivity)mContext).handleRefreshList();
        }
    }
    @Override
    public boolean getCheckFloatCanSee() {
        return mCheckFloatCanSee;
    }

    @Override
    public void handleBackFromDeskTop(Context context, boolean isFirst) {
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.addCategory(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.switube.www.landmark2018test", "com.switube.www.landmark2018test.MainActivity"));
        if (isFirst) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        } else {
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        }
        context.startActivity(intent);
    }

    @Override
    public void handleKillFloatPlayer() {
        if ((mLayoutParams.x + mLayoutParams.width / 2 > mLayoutParamsForKiller.x - mLayoutParamsForKiller.width / 2
                && mLayoutParams.x < mLayoutParamsForKiller.x + mLayoutParamsForKiller.width / 2) &&
                mLayoutParams.y + smallViewHeight / 2 > mLayoutParamsForKiller.y) {
            if (mCheckIsDeskTop) {
                ((MainActivity) mContext).finish();
            } else {
                showFloatPlayer(false);
                MyApplication.getAppData().setWebId("");
            }
        }
        //floatPlayerKiller.setVisibility(View.GONE);
    }

    private boolean mCheckIsDeskTop = false;
    @Override
    public void setCheckIsDeskTop(boolean b) {
        mCheckIsDeskTop = b;
    }

    @Override
    public boolean getIsFullScreenMode() {
        return mIsFullScreenMode;
    }

    @Override
    public void handleRemoveFloatView() {
        if (mYouTubePlayerView != null) {
            if (mYouTubePlayerView.isShown()) {
                windowManager.removeView(mYouTubePlayerView);
            }
        }
        if (floatPlayerKiller != null) {
            if (floatPlayerKiller.isShown()) {
                windowManager.removeView(floatPlayerKiller);
            }
        }
    }

    @Override
    public boolean checkDeskTop() {
        return mCheckIsDeskTop;
    }

    @Override
    public String handleYouTubeTime(int time) {
        int min = time / 60;
        int sec = time % 60;
        if (min > 60) {
            int hr = time / 3600;
            min = time % 3600;
            min /= 60;
            return String.format(Locale.getDefault(), "%d:%d:%02d", hr, min, sec);
        } else {
            return String.format(Locale.getDefault(), "%d:%02d", min, sec);
        }
    }

    @Override
    public boolean checkIsBigMode() {
        return mIsBigMode;
    }

    @Override
    public void handleLocation() {
        FusedLocationProviderClient client = LocationServices.getFusedLocationProviderClient(MyApplication.getInstance());
        LocationCallback callback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                double lat = locationResult.getLastLocation().getLatitude();
                double lon = locationResult.getLastLocation().getLongitude();
                //Log.e("FPS", "lat -> " + lat + ", long -> " + lon);
                if (MyApplication.getAppData().isCarbon()) {
                    ECarbon eCarbon = new ECarbon();
                    eCarbon.setLatitude(lat);
                    eCarbon.setLongitude(lon);
                    eCarbon.setDateFull(getDate("yyyy/M/d HH:mm:ss:SSS", new Date()));
                    eCarbon.setDateShort(getDate("yyyy/M/d", new Date()));
                    MyApplication.getAppData().getCarbons().add(eCarbon);
                }
                if (MyApplication.getAppData().isEco()) {
                    EEco eEco = new EEco();
                    eEco.setLatitude(lat);
                    eEco.setLongitude(lon);
                    eEco.setDateFull(getDate("yyyy/M/d HH:mm:ss:SSS", new Date()));
                    eEco.setDateShort(getDate("yyyy/M/d", new Date()));
                    //eEco.setDateFull(new SimpleDateFormat("yyyy/M/d HH:mm:ss:SSS", Locale.getDefault()).format(new Date()));
                    MyApplication.getAppData().geteEcos().add(eEco);
                }
            }
        };
        LocationRequest request = new LocationRequest();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setInterval(0);
        request.setFastestInterval(0);
        client.requestLocationUpdates(request, callback, Looper.myLooper());
    }

    public  class ServiceBinder extends Binder {
        public FloatPlayerService getService() {
            return FloatPlayerService.this;
        }
    }

    private String getDate(String s, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(s, Locale.getDefault());
        return format.format(date);
    }
}
