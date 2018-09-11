package com.switube.www.swiofficialthird.home.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.CreateAttractionEntity;
import com.switube.www.swiofficialthird.create.TimeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.home.DaggerMainActivityComponent;
import com.switube.www.swiofficialthird.home.MainActivityModule;
import com.switube.www.swiofficialthird.home.presenter.MainActivityPresenter;
import com.switube.www.swiofficialthird.util.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    @Inject
    MainActivityPresenter mMainActivityPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
        SharePreferencesUtil.getInstance().getEditor(this.getApplicationContext()).putString("selectedTag", "finish");
        //mMainActivityPresenter = new MainActivityPresenter(this);
        mMainActivityPresenter.handleCheckPermission(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.layoutContainer, new LogoFragment())
                    .commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            mMainActivityPresenter.handleSavePhotoAndRefresh(this);
        }
    }

    private List<String> mSelectedPhoto = new ArrayList<>();
    @Override
    public void setSelectedPhoto(List<String> selectedPhoto) {
        mSelectedPhoto.addAll(selectedPhoto);
    }

    @Override
    public List<String> getSelectedPhoto() {
        return mSelectedPhoto;
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
    public void handleCleanGoogleMap() {
        googleMap = null;
    }

    @Override
    public void handleRefreshLocation() {
        mMainActivityPresenter.refreshMyLocation();
    }

    private boolean isNeedFocus = false;
    @Override
    public void setNeedFocusEditView(boolean b) {
        isNeedFocus = b;
    }
    @Override
    public boolean getNeedFocusEditView() {
        return isNeedFocus;
    }

    @Override
    public void handleStartTimer(boolean isTimer) {
        mMainActivityPresenter.startTimer(isTimer, this);
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
            //mTextCenter.setTextSize(size);
            mTextCenter.setText(text);
        }
    }

    @Override
    public void handleChangeTextRight(String right) {
        if (mTextRight != null) {
            //mTextRight.setTextSize(14);
            mTextRight.setText(right);
        }
    }

    @Override
    public void handleChangeTextLeft(String left) {
        if (mTextLeft != null) {
            //mTextLeft.setTextSize(14);
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

    private List<TimeEntity> timeEntities = new ArrayList<>();
    @Override
    public void setTimeEntity(List<TimeEntity> timeEntities) {
        timeEntities.clear();
        this.timeEntities.addAll(timeEntities);
    }

    @Override
    public List<TimeEntity> getTimeEntity() {
        return timeEntities;
    }

    private CreateAttractionEntity entity;
    @Override
    public void setCreateData(CreateAttractionEntity createAttractionEntity) {
        entity = createAttractionEntity;
    }

    @Override
    public CreateAttractionEntity getCreateData() {
        return entity;
    }

    private List<String> key = new ArrayList<>();
    @Override
    public void setKeys(List<String> key) {
        this.key.clear();
        this.key.addAll(key);
    }

    @Override
    public List<String> getKey() {
        return key;
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
    public String getTotalTime() {
        return mMainActivityPresenter.getmStringBuilder().toString();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
