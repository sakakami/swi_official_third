package com.switube.www.landmark2018test;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.switube.www.landmark2018test.adapter.ASlideMenu;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.entity.ECreateAttraction;
import com.switube.www.landmark2018test.presenter.MainActivityPresenter;
import com.switube.www.landmark2018test.service.FloatPlayerService;
import com.switube.www.landmark2018test.service.callback.IFloatPlayerService;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.BackHandlerHelper;
import com.switube.www.landmark2018test.util.LogToFile;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.VLogo;
import com.switube.www.landmark2018test.view.VMap;
import com.switube.www.landmark2018test.view.VPlayer;
import com.switube.www.landmark2018test.view.VPlaylist;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.youtube.YouTubePlayerController;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    private MainActivityPresenter mMainActivityPresenter;
    private IFloatPlayerService iFloatPlayerService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int resourceId = getApplicationContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            MyApplication.getAppData().setStatusHeight(getApplicationContext().getResources().getDimensionPixelSize(resourceId));
        }
        mMainActivityPresenter = new MainActivityPresenter(this);
        SharePreferencesUtil.getInstance().getEditor().putString("selectedTag", "finish");
        LogToFile.init(this);
        mMainActivityPresenter.handleCheckPermission(this);
        mMainActivityPresenter.getGoogleApiClient();
        mMainActivityPresenter.initFacebookClient();
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.layoutContainer, new VLogo())
                    .commit();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction() != null) {
                    switch (intent.getAction()) {
                        case Intent.ACTION_SCREEN_OFF:
                            if (iFloatPlayerService != null && iFloatPlayerService.getCheckFloatPlayerCreate()) {
                                MyApplication.getAppData().setPlaying(false);
                                iFloatPlayerService.handlePlayingSwitch();
                                if (iFloatPlayerService.checkIsBigMode()) {
                                    iFloatPlayerService.showSmallMode();
                                }
                            }
                            break;
                        case Intent.ACTION_USER_PRESENT:
                            if (iFloatPlayerService != null && iFloatPlayerService.getCheckFloatPlayerCreate()) {
                                MyApplication.getAppData().setPlaying(true);
                                iFloatPlayerService.handlePlayingSwitch();
                                if (MyApplication.getAppData().isPlayerPage() && !iFloatPlayerService.checkDeskTop()) {
                                    iFloatPlayerService.showBigMode();
                                }
                            }
                            break;
                        case Intent.ACTION_CLOSE_SYSTEM_DIALOGS:
                            if (intent.getStringExtra("reason") != null) {
                                if (intent.getStringExtra("reason").equals("homekey") && iFloatPlayerService.getIsFullScreenMode()) {
                                    switchScreenOrientation();
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }, intentFilter);
        Intent intent = new Intent(this, FloatPlayerService.class);
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                iFloatPlayerService = ((FloatPlayerService.ServiceBinder)iBinder).getService();
                MyApplication.getAppData().setiFloatPlayerService(iFloatPlayerService);
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                iFloatPlayerService = null;
            }
        }, BIND_AUTO_CREATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (MyApplication.getAppData().getiFloatPlayerService() != null) {
            MyApplication.getAppData().getiFloatPlayerService().showFloatPlayerKiller(false);
        }
        if (requestCode == 2000) {
            mMainActivityPresenter.handleGoogleResult(data);
        } else {
            mMainActivityPresenter.getCallbackManager().onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            iFloatPlayerService.switchFullscreenMode(true);
        } else {
            iFloatPlayerService.switchFullscreenMode(false);
        }
        iFloatPlayerService.handleSwitchFullscreen();
    }

    @Override
    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    private GoogleMap googleMap;
    @Override
    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    @Override
    public void handleClearPolyline() {
        mMainActivityPresenter.handleClearPolyline();
    }

    @Override
    public void handleCleanGoogleMap() {
        googleMap = null;
    }

    @Override
    public void handleRefreshLocation() {
        mMainActivityPresenter.refreshMyLocation();
    }

    @Override
    public void handleStartTimer(boolean isTimer) {
        mMainActivityPresenter.startTimer(isTimer);
    }

    @Override
    public void handlePauseTimer() {
        mMainActivityPresenter.stopTimer();
    }

    @Override
    public void handleStopTimer() {
        mMainActivityPresenter.clearTimer();
    }

    private TextView mTextCenter;
    private TextView mTextRight;
    private TextView mTextLeft;
    @Override
    public void handleSetTextView(TextView textCenter, TextView textRight, TextView textLeft) {
        mTextCenter = textCenter;
        mTextRight = textRight;
        mTextLeft = textLeft;
    }

    @Override
    public void handleChangeTextCenter(String text, int size) {
        if (mTextCenter != null) {
            mTextCenter.setText(text);
        }
    }

    @Override
    public void handleChangeTextRight(String right) {
        if (mTextRight != null) {
            mTextRight.setText(right);
        }
    }

    @Override
    public void handleChangeTextLeft(String left) {
        if (mTextLeft != null) {
            mTextLeft.setText(left);
        }
    }

    private AttractionStyleEntity mType;
    @Override
    public void setSelectedAttractionType(AttractionStyleEntity attractionType) {
        mType = attractionType;
    }
    @Override
    public AttractionStyleEntity getSelectedAttractionType() {
        return mType;
    }

    private ECreateAttraction entity;
    @Override
    public void setCreateData(ECreateAttraction eCreateAttraction) {
        entity = eCreateAttraction;
    }
    @Override
    public ECreateAttraction getCreateData() {
        return entity;
    }

    private String mPlaceId = "";
    @Override
    public void setPlaceId(String id) {
        mPlaceId = id;
    }
    @Override
    public String getPlaceId() {
        return mPlaceId;
    }

    private TextView mTextTimer;
    private TextView mTextCost;
    @Override
    public void handleBikeTextViews(TextView timer, TextView cost) {
        mTextTimer = timer;
        mTextCost = cost;
    }

    @Override
    public void handleChangeTimeAndCost(String time, String cost) {
        if (mTextTimer != null) {
            mTextTimer.setText(time);
        }
        if (mTextCost != null) {
            mTextCost.setText(cost);
        }
    }

    @Override
    public void setCost(double cost) {
        mMainActivityPresenter.setCostPerSec(cost);
    }
    @Override
    public double getCost() {
        return mMainActivityPresenter.getmMoney();
    }

    @Override
    public void handleDrawLine() {
        mMainActivityPresenter.drawLine();
    }

    @Override
    public void handleClearLatLngList() {
        mMainActivityPresenter.clearLatLngList();
    }

    private String mBikeNumbmer;
    @Override
    public void saveSelectBikeNubmer(String number) {
        mBikeNumbmer = number;
    }
    @Override
    public String getSelectBikeNumber() {
        return mBikeNumbmer;
    }

    @Override
    public String getBikeMoney() {
        return String.valueOf(mMainActivityPresenter.getmMoney());
    }

    @Override
    public String getBikeDate() {
        return mMainActivityPresenter.getmStringBuilder().toString();
    }

    private String mCentPlace;
    @Override
    public void saveCentPlace(String place) {
        mCentPlace = place;
    }
    @Override
    public String getCentPlace() {
        return mCentPlace;
    }

    private String mReturnPlace;
    @Override
    public void saveReturnPlace(String place) {
        mReturnPlace = place;
    }
    @Override
    public String getReturnPlace() {
        return mReturnPlace;
    }

    @Override
    public List<LatLng> getLatLngList() {
        return mMainActivityPresenter.getmLatLngList();
    }

    @Override
    public LatLng getNowGps() {
        return mMainActivityPresenter.getmLatLng();
    }

    private List<String> mSpid;
    @Override
    public void saveSelectedAttractionId(List<String> spid) {
        mSpid = spid;
    }
    @Override
    public List<String> getSelectedAttractionId() {
        return mSpid;
    }

    private String spid;
    @Override
    public void saveAttractionId(String spid) {
        this.spid = spid;
    }
    @Override
    public String getAttractionId() {
        return spid;
    }

    @Override
    public void handleSignIn(String type) {
        mMainActivityPresenter.handleSignIn(type, this);
    }

    @Override
    public void handleSignOut() {
        mMainActivityPresenter.handleSignOut();
    }

    private TextView txtName;
    private ImageView imagePhoto;
    private ASlideMenu aSlideMenu;
    private boolean isSlideMenu = false;
    @Override
    public void saveSlideMenuUnits(TextView textView, ImageView imageView, ASlideMenu aSlideMenu, boolean isSlideMenu) {
        txtName = textView;
        imagePhoto = imageView;
        this.aSlideMenu = aSlideMenu;
        this.isSlideMenu = isSlideMenu;
    }

    @Override
    public void handleRefreshSlideMenu(String name, String image) {
        if (txtName != null && isSlideMenu) {
            if (name.equals("null")) {
                txtName.setText(R.string.map_slide_visitor);
            } else {
                txtName.setText(name);
            }
        }
        if (imagePhoto != null && isSlideMenu) {
            if (image.equals("null")) {
                imagePhoto.setImageResource(R.drawable.person_unlogin);
            } else {
                Glide.with(this)
                        .load(Uri.parse(image))
                        .into(imagePhoto);
            }
        }
        if (aSlideMenu != null && isSlideMenu) {
            aSlideMenu.init(new ArrayList<>(), new ArrayList<>(), false);
        }
        isSlideMenu = false;
        VMap vMap = (VMap) getSupportFragmentManager().findFragmentByTag("Map");
        if (vMap != null) {
            vMap.handleReloadAttractionPoint();
        }
    }

    @Override
    public void initForResult(Intent intent) {
        startActivityForResult(intent, 2000);
    }

    @Override
    public void setMessageIndex(int index) {
        mMainActivityPresenter.setMessageIndex(index);
    }
    @Override
    public Integer getMessageIndex() {
        return mMainActivityPresenter.getMessageIndex();
    }

    @Override
    public void setPlaceName(String name) {
        mMainActivityPresenter.setPlaceName(name);
    }
    @Override
    public String getPlaceName() {
        return mMainActivityPresenter.getPlaceName();
    }

    @Override
    public void getPermission() {
        if (!Settings.canDrawOverlays(this)) {
            AlertDialogUtil.getInstance().initDialogBuilder(this,
                    R.string.music_permission_one,
                    R.string.global_ok,
                    (dialogInterface, i) -> {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    },
                    R.string.music_permission_cancel,
                    (dialogInterface, i) -> handleNeverCheckPermission()
            );
            AlertDialogUtil.getInstance().showAlertDialog();
        } else {
            SharePreferencesUtil.getInstance().getSharedPreferences().edit().putBoolean("checkPermission", true).apply();
        }
    }

    @Override
    public void handleNeverCheckPermission() {
        AlertDialogUtil.getInstance().initDialogBuilder(this,
                R.string.music_permission_two,
                R.string.music_permission_get,
                (dialogInterface, i) -> {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                },
                R.string.music_permission_cancel,
                (dialogInterface, i) -> SharePreferencesUtil.getInstance().getEditor().putBoolean("checkPermission", true)
        );
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    private boolean canSet = false;
    @Override
    protected void onResume() {
        super.onResume();
        final View view = findViewById(android.R.id.content);
        view.postDelayed(() -> MyApplication.getAppData().setUseableHeight(view.getHeight()), 1000);
        YouTubePlayerController.isFirst = true;
        if (canSet) {
            canSet = false;
            if (iFloatPlayerService != null) {
                iFloatPlayerService.setCheckIsDeskTop(false);
            }
        }

        if (iFloatPlayerService != null) {
            if (MyApplication.getAppData().isPlayerPage() && !iFloatPlayerService.checkIsBigMode()) {
                iFloatPlayerService.showBigMode();
                MyApplication.getAppData().setPlaying(true);
                iFloatPlayerService.handlePlayingSwitch();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        canSet = true;
        if (iFloatPlayerService != null && iFloatPlayerService.getCheckFloatPlayerCreate()) {
            iFloatPlayerService.showSmallMode();
            iFloatPlayerService.setCheckIsDeskTop(true);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (iFloatPlayerService.getCheckFloatPlayerCreate()) {
            iFloatPlayerService.handleRemoveFloatView();
        }
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            SharePreferencesUtil.getInstance().getEditor().putString("isCheck", "null").commit();
        }
        System.exit(0);
    }

    @Override
    public void onBackPressed() {
        Log.e("main", "back pressed");
        if (MyApplication.getAppData().getiFloatPlayerService().getIsFullScreenMode()) {
            switchScreenOrientation();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                if (MyApplication.getAppData().isBackWithFloat()) {
                    MyApplication.getAppData().setBackWithFloat(false);
                    MyApplication.getAppData().setCanRefreshList(false);
                    MyApplication.getAppData().setPlayerPage(false);
                    MyApplication.getAppData().getiFloatPlayerService().showSmallMode();
                    getSupportFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMap()).commit();
                } else if (!MyApplication.getAppData().isNormalMap()) {
                    VMap vMap = (VMap) getSupportFragmentManager().findFragmentByTag("Map");
                    vMap.backToNormalMap();
                } else {
                    finish();
                }
            } else {
                if (!BackHandlerHelper.handleBackPress(this)) {
                    super.onBackPressed();
                }

                if (MyApplication.getAppData().isExchangeFinish()) {
                    MyApplication.getAppData().setExchangeFinish(false);
                    int count = getSupportFragmentManager().getBackStackEntryCount();
                    for (int i = 0; i < count; i++) {
                        FragmentManager.BackStackEntry backStackEntry = getSupportFragmentManager().getBackStackEntryAt(i);
                        getSupportFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    }
                    getSupportFragmentManager().popBackStack();
                }
            }
        }
    }

    //切換全螢幕與非全螢幕
    @Override
    public void switchScreenOrientation() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            iFloatPlayerService.setIsFullScreenMode(true);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        } else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
            iFloatPlayerService.setIsFullScreenMode(false);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void handleStartRecordLocation() {
        iFloatPlayerService.handleLocation();
    }

    public void handleRefreshList() {
        if (MyApplication.getAppData().isPlayerPage()) {
            VPlayer vPlayer = (VPlayer) getSupportFragmentManager().findFragmentByTag("Player");
            if (vPlayer != null) {
                vPlayer.handleRefreshAdapter();
            }
        } else if (MyApplication.getAppData().isPlaylist()) {
            VPlaylist vPlaylist = (VPlaylist) getSupportFragmentManager().findFragmentByTag("Playlist");
            if (vPlaylist != null) {
                vPlaylist.handleRefreshAdapter();
            }
        }
    }
}
