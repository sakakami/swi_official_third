package com.switube.www.swiofficialthird.home.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.switube.www.swiofficialthird.R;
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
        //mMainActivityPresenter.handleCheckPermission(this);

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
