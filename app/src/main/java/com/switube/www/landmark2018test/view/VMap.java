package com.switube.www.landmark2018test.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ACarbon;
import com.switube.www.landmark2018test.adapter.AEco;
import com.switube.www.landmark2018test.adapter.ASaveList;
import com.switube.www.landmark2018test.adapter.ASlideMenu;
import com.switube.www.landmark2018test.adapter.callback.IACarbon;
import com.switube.www.landmark2018test.adapter.callback.IAEco;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.entity.ECarBonList;
import com.switube.www.landmark2018test.entity.EClusterItem;
import com.switube.www.landmark2018test.entity.EEcoList;
import com.switube.www.landmark2018test.entity.EMobileMusic;
import com.switube.www.landmark2018test.entity.ESlideMenu;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.gson.GPlayer;
import com.switube.www.landmark2018test.gson.GSaveList;
import com.switube.www.landmark2018test.gson.GSearchAttractionDetail;
import com.switube.www.landmark2018test.presenter.PMap;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.ClusterRendererUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.util.SignInUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVMap;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class VMap extends Fragment implements IVMap, OnMapReadyCallback,
        ClusterManager.OnClusterClickListener<EClusterItem>,
        ClusterManager.OnClusterItemInfoWindowClickListener<EClusterItem>,
        ClusterManager.OnClusterItemClickListener<EClusterItem>,
        ClusterManager.OnClusterInfoWindowClickListener<EClusterItem>, IACarbon, IAEco {
    public static boolean isCent = false;
    private static boolean isLocked = false;
    static boolean isAttractionList = false;
    @BindViews({R.id.textCheckInInMap, R.id.textSearchInMap, R.id.textTimerInMap,
            R.id.textStringInMap, R.id.textGpsStateInMap, R.id.textTitleGpsInMap,
            R.id.textCenterInMap, R.id.textCenterSubInMap, R.id.textRightInMap,
            R.id.textRightSubInMap, R.id.textLeftInMap, R.id.textLeftSubInMap,
            R.id.textMessageInMap, R.id.textDurationInMap, R.id.textCostInMap,
            R.id.textBackLeftInMap, R.id.textBackBottomInMap, R.id.textTitleForInfoInMap,
            R.id.textRatingInMap, R.id.textCountInMap, R.id.textTypeInMap,
            R.id.textStatusInMap, R.id.textTimeInMap, R.id.textTypeTitleInMap,
            R.id.textBackInMap, R.id.textPauseInMap, R.id.textStartInMap,
            R.id.textStopInMap})
    List<TextView> mTextViews;
    @BindViews({R.id.imageFocusInMap, R.id.imageHotKeyInMap, R.id.imageCheckInInMap,
            R.id.imageSearchInMap, R.id.imageTimerInMap, R.id.imageStringInMap,
            R.id.imageCheckInMap, R.id.imageMarkInMap, R.id.imageCameraInMap,
            R.id.imageStartInMap, R.id.imagePauseInMap, R.id.imageStopInMap,
            R.id.imageLockInMap, R.id.imageLockBigInMap, R.id.imageBillingInMap,
            R.id.imageBackRightInMap, R.id.imageSaveInMap, R.id.imageListInMap,
            R.id.imageAddDelInMap, R.id.imageCollectInMap})
    List<ImageView> mImageViews;
    private SupportMapFragment supportMapFragment;
    private Context mContext;
    private Unbinder mUnbinder;
    private boolean isCreditCard = false;
    //private boolean isWallet = false;
    private boolean isNeedReturn = false;
    private boolean isCreate = true;
    private boolean canClick = true;
    @BindViews({R.id.viewBarTimerAInMap, R.id.viewBarTimerBInMap, R.id.viewBarBInMap,
            R.id.viewBarCInMap, R.id.viewBarAInMap})
    List<View> mViews;
    @BindView(R.id.ratingInMap)
    RatingBar mRatingBar;
    private List<String> textContent = new ArrayList<>();
    private List<String> textTitle = new ArrayList<>();
    @BindView(R.id.imageMenuInMap)
    ImageView mImageMenu;
    @BindView(R.id.layoutDrawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.listView)
    ListView mListView;
    //layouts
    @BindViews({R.id.layoutItemFourEbike, R.id.layoutItemFiveEbike, R.id.layoutItemNineEbike,
            R.id.layoutItemThreeEbike, R.id.layoutItemThreeFinishEbike, R.id.layoutMobileMusic})
    List<RelativeLayout> relativeLayouts;
    //four
    @BindViews({R.id.textTitleInItemFourEbike, R.id.textMessageInItemFourEbike,
            R.id.textLeftInItemFourEbike, R.id.textRightInItemFourEbike})
    List<TextView> mTextViewsInFour;
    //five
    @BindViews({R.id.textTitleInItemFiveEbike, R.id.textMessageInItemFiveEbike,
            R.id.textContentInItemFiveEbike, R.id.textLeftInItemFiveEbike, R.id.textRightInItemFiveEbike})
    List<TextView> mTextViewsInFive;
    //three finish
    @BindViews({R.id.textTitleInItemThreeFinishEbike, R.id.textMessageOneInItemThreeFinishEbike, R.id.textMessageTwoInItemThreeFinishEbike})
    List<TextView> mTextViewsInThreeFinish;
    //three
    @BindViews({R.id.textTitleInItemThreeEbike, R.id.textLeftInItemThreeEbike, R.id.textRightInItemThreeEbike})
    List<TextView> mTextViewsInThree;
    private PMap pMap;
    @BindView(R.id.imageCancelInItemFiveEbike)
    ImageView mImageInFive;
    @BindViews({R.id.textTitleInItemNineEbike, R.id.textCenterInItemNineEbike, R.id.textCenterSubInItemNineEbike,
            R.id.textLeftInItemNineEbike, R.id.textLeftSubInItemNineEbike, R.id.textRightInItemNineEbike,
            R.id.textRightSubInItemNineEbike, R.id.textLeftBottomInItemNineEbike, R.id.textRightBottomInItemNineEbike})
    List<TextView> mTextViewsInNine;
    private boolean canInit = true;
    @BindView(R.id.radioGroupInItemNineEbike)
    RadioGroup mRadioGroupInNine;
    private ClusterManager<EClusterItem> mClustermanager;
    @BindView(R.id.radioGroupInItemThreeEbike)
    RadioGroup mRadioGroupInThree;
    @BindView(R.id.editSearchInMap)
    EditText mEditSearch;
    @BindViews({R.id.textCancelInMapMobileMusic, R.id.textNextInMapMobileMusic, R.id.textTitleInMapMobileMusic, R.id.textCountsInMapMobileMusic})
    List<TextView> textMobileMusicList;
    @BindViews({R.id.imagePictureInMapMobileMusic, R.id.imageLikeInMapMobileMusic})
    List<ImageView> imageMobileMusicList;
    private GoogleMap googleMap;
    private String mMsid;
    private List<EClusterItem> eClusterItem = new ArrayList<>();
    private String location;
    //private int carCash = 0;

    @Override
    public void onResume() {
        super.onResume();
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mListView);
        }
    }

    public VMap() {
        pMap = new PMap(this);
    }

    //textContext預設順序為center->right->left,textTitle也一樣
    private LayoutInflater inflater;
    private ASlideMenu aSlideMenu;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_v_map, container, false);
        this.inflater = LayoutInflater.from(container.getContext());
        aSlideMenu = new ASlideMenu(container.getContext());
        mUnbinder = ButterKnife.bind(this, view);
        textContent.add("center");
        textContent.add("right");
        textContent.add("left");
        textTitle.add("TOTAL TIME");
        textTitle.add("AVG. PACE");
        textTitle.add("DISTANCE");
        MyApplication.getAppData().setTubeUrl("");
        if (isCent) {
            mTextViews.get(13).setVisibility(View.VISIBLE);
            mTextViews.get(14).setVisibility(View.VISIBLE);
            mImageViews.get(14).setVisibility(View.VISIBLE);
            mTextViews.get(12).setVisibility(View.VISIBLE);
            mViews.get(2).setVisibility(View.VISIBLE);
            mViews.get(3).setVisibility(View.VISIBLE);
            mIMainActivity.handleBikeTextViews(mTextViews.get(13), mTextViews.get(14));
            if (isLocked) {
                mTextViews.get(12).setText(R.string.map_ebike_unlock);
            } else {
                mTextViews.get(12).setText(R.string.map_ebike_lock);
            }
        }
        if (isAttractionList) {
            canClick = false;
            mTextViews.get(15).setVisibility(View.VISIBLE);
            mTextViews.get(16).setVisibility(View.VISIBLE);
            mTextViews.get(23).setVisibility(View.VISIBLE);
            mEditSearch.setVisibility(View.GONE);
            mImageViews.get(15).setVisibility(View.VISIBLE);
            mImageViews.get(6).setVisibility(View.GONE);
            mImageViews.get(1).setVisibility(View.GONE);
            mImageViews.get(2).setVisibility(View.GONE);
            mImageViews.get(3).setVisibility(View.GONE);
            mImageViews.get(4).setVisibility(View.GONE);
            mImageViews.get(5).setVisibility(View.GONE);
            mTextViews.get(0).setVisibility(View.GONE);
            mTextViews.get(1).setVisibility(View.GONE);
            mTextViews.get(2).setVisibility(View.GONE);
            mTextViews.get(3).setVisibility(View.GONE);
            mTextViews.get(4).setVisibility(View.GONE);
            mImageMenu.setVisibility(View.GONE);
            switch (Locale.getDefault().getLanguage()) {
                case "zh":
                    switch (Locale.getDefault().getCountry()) {
                        case "TW":
                            mTextViews.get(23).setText(mIMainActivity.getSelectedAttractionType().getMstitle_tw());
                            break;
                        case "CN":
                            mTextViews.get(23).setText(mIMainActivity.getSelectedAttractionType().getMstitle_ch());
                            break;
                        default:
                            mTextViews.get(23).setText(mIMainActivity.getSelectedAttractionType().getMstitle_en());
                            break;
                    }
                    break;
                case "jp":
                    mTextViews.get(23).setText(mIMainActivity.getSelectedAttractionType().getMstitle_jp());
                    break;
                default:
                    mTextViews.get(23).setText(mIMainActivity.getSelectedAttractionType().getMstitle_en());
                    break;
            }
        }
        //打開側邊選單
        RxView.clicks(mImageMenu)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (mDrawerLayout.isDrawerOpen(mListView)) {
                            mDrawerLayout.closeDrawer(mListView);
                        } else {
                            mDrawerLayout.openDrawer(mListView);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        //focus location
        RxView.clicks(mImageViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                        if (mIMainActivity.getGoogleMap() == null) {
                            mIMainActivity.setGoogleMap(googleMap);
                        }
                        MyApplication.getAppData().setFocus(true);
                        MyApplication.getAppData().setManualMode(false);
                        mIMainActivity.handleRefreshLocation();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //hot key
        RxView.clicks(mImageViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mViews.get(4).setVisibility(View.GONE);
                        mTextViews.get(17).setVisibility(View.GONE);
                        mTextViews.get(18).setVisibility(View.GONE);
                        mTextViews.get(19).setVisibility(View.GONE);
                        mTextViews.get(20).setVisibility(View.GONE);
                        mTextViews.get(21).setVisibility(View.GONE);
                        mTextViews.get(22).setVisibility(View.GONE);
                        mImageViews.get(16).setVisibility(View.GONE);
                        mImageViews.get(19).setVisibility(View.GONE);
                        mRatingBar.setVisibility(View.GONE);
                        relativeLayouts.get(1).setVisibility(View.GONE);
                        MyApplication.getAppData().setMobileMusicMode(false);
                        if (MyApplication.getAppData().isDefaultHotKye()) {
                            MyApplication.getAppData().setDefaultHotKye(false);
                            handleHotKey();
                            if (MyApplication.getAppData().getHotKeyMode() == 2 && !MyApplication.getAppData().isNormalMap()) {
                                if (MyApplication.getAppData().getUrid().length() > 0) {
                                    mImageViews.get(17).setVisibility(View.VISIBLE);
                                    mImageViews.get(18).setVisibility(View.VISIBLE);
                                    mTextViews.get(24).setVisibility(View.VISIBLE);
                                    mTextViews.get(23).setVisibility(View.VISIBLE);
                                    if (MyApplication.getAppData().getStrokeMode() == 2) {
                                        mImageViews.get(18).setImageResource(R.drawable.delete_on);
                                    } else {
                                        mImageViews.get(18).setImageResource(R.drawable.add_v1_2);
                                    }
                                    mImageMenu.setVisibility(View.GONE);
                                    mImageViews.get(6).setVisibility(View.GONE);
                                    mEditSearch.setVisibility(View.GONE);
                                    mTextViews.get(23).setText(MyApplication.getAppData().getTitleForUrid());
                                    pMap.getStrokeList();
                                }
                            } else if (MyApplication.getAppData().getHotKeyMode() == 1) {
                                MyApplication.getAppData().setMobileMusicMode(true);
                                if (mIMainActivity.getNowGps() != null) {
                                    pMap.getPushMusicData();
                                }
                            }
                        } else {
                            MyApplication.getAppData().setDefaultHotKye(true);
                            handleHotKey();
                            mImageViews.get(17).setVisibility(View.GONE);
                            mImageViews.get(18).setVisibility(View.GONE);
                            mTextViews.get(24).setVisibility(View.GONE);
                            mTextViews.get(23).setVisibility(View.GONE);
                            mImageMenu.setVisibility(View.VISIBLE);
                            mImageViews.get(6).setVisibility(View.VISIBLE);
                            mEditSearch.setVisibility(View.VISIBLE);
                            eClusterItem = MyApplication.getAppData().getGlobalClusterItemList();
                            if (pMap != null) {
                                mClustermanager.clearItems();
                                mClustermanager.addItems(eClusterItem);
                                mClustermanager.cluster();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //check in
        RxView.clicks(mImageViews.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                new SignInUtil(getContext(), mIMainActivity);
                            } else {
                                mEditSearch.setText("");
                                if (MyApplication.getAppData().isDefaultHotKye()) {
                                    if (mIMainActivity.getNowGps() != null) {
                                        VLeaveComments.IS_IN_MAP = true;
                                        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VAttractionList()).addToBackStack("Map").commit();
                                        mIMainActivity.handleCleanGoogleMap();
                                    } else {
                                        showDialog();
                                    }
                                } else {
                                    switch (MyApplication.getAppData().getHotKeyMode()) {
                                        case 1:
                                            MyApplication.getAppData().setMusicMode(2);
                                            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMobileMusic()).addToBackStack("Map").commit();
                                            break;
                                        case 2:
                                            MyApplication.getAppData().setStrokeMode(2);
                                            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VStroke()).addToBackStack("Map").commit();
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click hot key select place type
        RxView.clicks(mImageViews.get(3))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            if (MyApplication.getAppData().isDefaultHotKye()) {
                                if (mIMainActivity.getNowGps() != null) {
                                    mEditSearch.setText("");
                                    VAttractionType.isClickHotKey = true;
                                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VAttractionType()).addToBackStack("Map").commit();
                                } else {
                                    showDialog();
                                }
                            } else {
                                switch (MyApplication.getAppData().getHotKeyMode()) {
                                    case 1:
                                        mEditSearch.setText("");
                                        MyApplication.getAppData().setMusicMode(0);
                                        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMobileMusic()).addToBackStack("Map").commit();
                                        break;
                                    case 2:
                                        MyApplication.getAppData().setStrokeMode(0);
                                        isCreate = false;
                                        pMap.getPlaceData(mIMainActivity.getNowGps());
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //timer
        RxView.clicks(mImageViews.get(4))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            if (MyApplication.getAppData().isDefaultHotKye()) {
                                Toast.makeText(getContext(), R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
                            } else {
                                switch (MyApplication.getAppData().getHotKeyMode()) {
                                    case 1:
                                        mEditSearch.setText("");
                                        MyApplication.getAppData().setMusicMode(1);
                                        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMobileMusic()).addToBackStack("Map").commit();
                                        break;
                                    case 2:
                                        MyApplication.getAppData().setStrokeMode(1);
                                        isCreate = false;
                                        pMap.getPlaceData(mIMainActivity.getNowGps());
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageViews.get(5))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            mEditSearch.setText("");
                            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMore()).addToBackStack("Map").commit();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //start timer
        RxView.clicks(mImageViews.get(9))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.handleStartTimer(true);
                        mImageViews.get(9).setVisibility(View.GONE);
                        mImageViews.get(11).setVisibility(View.GONE);
                        mImageViews.get(12).setVisibility(View.VISIBLE);
                        mImageViews.get(10).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //pause timer
        RxView.clicks(mImageViews.get(10))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.handlePauseTimer();
                        mImageViews.get(12).setVisibility(View.GONE);
                        mImageViews.get(10).setVisibility(View.GONE);
                        mImageViews.get(9).setVisibility(View.VISIBLE);
                        mImageViews.get(11).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //stop timer
        RxView.clicks(mImageViews.get(11))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mViews.get(0).setVisibility(View.GONE);
                        mViews.get(1).setVisibility(View.GONE);
                        mImageViews.get(8).setVisibility(View.GONE);
                        mImageViews.get(9).setVisibility(View.GONE);
                        mImageViews.get(11).setVisibility(View.GONE);
                        mImageViews.get(7).setVisibility(View.GONE);
                        mTextViews.get(4).setVisibility(View.GONE);
                        mTextViews.get(5).setVisibility(View.GONE);
                        mTextViews.get(6).setVisibility(View.GONE);
                        mTextViews.get(7).setVisibility(View.GONE);
                        mTextViews.get(8).setVisibility(View.GONE);
                        mTextViews.get(9).setVisibility(View.GONE);
                        mTextViews.get(10).setVisibility(View.GONE);
                        mTextViews.get(11).setVisibility(View.GONE);
                        mImageViews.get(0).setVisibility(View.VISIBLE);
                        mImageViews.get(1).setVisibility(View.VISIBLE);
                        mIMainActivity.handleStopTimer();
                        mClustermanager.clearItems();
                        mClustermanager.addItems(pMap.getmAttraction());
                        mClustermanager.cluster();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {
                    }
                });
        //lock timer
        RxView.clicks(mImageViews.get(12))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mImageViews.get(8).setVisibility(View.GONE);
                        mImageViews.get(10).setVisibility(View.GONE);
                        mImageViews.get(12).setVisibility(View.GONE);
                        mImageViews.get(13).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //big lock timer
        RxView.longClicks(mImageViews.get(13))
                .delay(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mImageViews.get(8).setVisibility(View.VISIBLE);
                        mImageViews.get(10).setVisibility(View.VISIBLE);
                        mImageViews.get(12).setVisibility(View.VISIBLE);
                        mImageViews.get(13).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageViews.get(15))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        canInit = false;
                        mEditSearch.setText("");
                        getParentFragmentManager().popBackStackImmediate();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //收藏按鈕
        RxView.clicks(mImageViews.get(16))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                            new SignInUtil(getContext(), mIMainActivity);
                        } else {
                            if (sucid.length() > 0) {
                                pMap.sendAddToCollect(sucid, false);
                            } else {
                                pMap.sendAddToCollect(mIMainActivity.getAttractionId(), true);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click right in timer
        RxView.clicks(mTextViews.get(8))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        String base = textContent.get(1);
                        String target = textContent.get(0);
                        String baseTitle = textTitle.get(1);
                        String baseTarget = textTitle.get(0);
                        textTitle.set(0, baseTitle);
                        textTitle.set(1, baseTarget);
                        textContent.set(0, base);
                        textContent.set(1, target);
                        handleSwitchTextView();
                        mTextViews.get(7).setText(textTitle.get(0));
                        mTextViews.get(9).setText(textTitle.get(1));
                        mTextViews.get(11).setText(textTitle.get(2));
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click left in timer
        RxView.clicks(mTextViews.get(10))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        String base = textContent.get(2);
                        String target = textContent.get(0);
                        String baseTitle = textTitle.get(2);
                        String targetTitle = textTitle.get(0);
                        textTitle.set(0, baseTitle);
                        textTitle.set(2, targetTitle);
                        textContent.set(0, base);
                        textContent.set(2, target);
                        handleSwitchTextView();
                        mTextViews.get(7).setText(textTitle.get(0));
                        mTextViews.get(9).setText(textTitle.get(1));
                        mTextViews.get(11).setText(textTitle.get(2));
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click lock or unlock car
        RxView.clicks(mTextViews.get(12))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mTextViews.get(12).setVisibility(View.GONE);
                        if (isCent) {
                            relativeLayouts.get(0).setVisibility(View.VISIBLE);
                            if (isLocked) {
                                mTextViewsInFour.get(0).setText(R.string.map_ebike_unlock);
                                mTextViewsInFour.get(1).setText(R.string.map_ebike_unlock_message);
                            } else {
                                mTextViewsInFour.get(0).setText(R.string.map_ebike_lock);
                                mTextViewsInFour.get(1).setText(R.string.map_ebike_lock_message);
                            }
                        } else {
                            mEditSearch.setText("");
                            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VFinishTour()).addToBackStack("Map").commit();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click back in attraction mode
        RxView.clicks(mTextViews.get(15))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        canInit = false;
                        mEditSearch.setText("");
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mTextViews.get(16))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        canInit = false;
                        mEditSearch.setText("");
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click attraction point title
        RxView.clicks(mTextViews.get(17))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mEditSearch.setText("");
                        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VInfo()).addToBackStack("Map").commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageInFive)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        relativeLayouts.get(1).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //direction in five
        RxView.clicks(mTextViewsInFive.get(3))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (carbon) {
                            pMap.handleGetCarbon(true);
                        } else if (eco) {
                            pMap.handleGetEco(true);
                        } else {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
                            intent.setPackage("com.google.android.apps.maps");
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //點下租車
        RxView.clicks(mTextViewsInFive.get(4))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (carbon) {
                            mTextViews.get(13).setVisibility(View.VISIBLE);
                            mTextViews.get(14).setVisibility(View.VISIBLE);
                            mImageViews.get(14).setVisibility(View.VISIBLE);
                            mViews.get(2).setVisibility(View.VISIBLE);
                            mViews.get(3).setVisibility(View.VISIBLE);
                            mIMainActivity.handleBikeTextViews(mTextViews.get(13), mTextViews.get(14));
                            mTextViews.get(25).setVisibility(View.VISIBLE);
                            mTextViews.get(26).setVisibility(View.VISIBLE);
                            mTextViews.get(27).setVisibility(View.VISIBLE);
                            mIMainActivity.handleStartTimer(true);
                            relativeLayouts.get(1).setVisibility(View.GONE);
                            MyApplication.getAppData().setCarbon(true);
                            mIMainActivity.handleStartRecordLocation();
                        } else if (eco) {
                            mTextViews.get(13).setVisibility(View.VISIBLE);
                            mTextViews.get(14).setVisibility(View.VISIBLE);
                            mImageViews.get(14).setVisibility(View.VISIBLE);
                            mViews.get(2).setVisibility(View.VISIBLE);
                            mViews.get(3).setVisibility(View.VISIBLE);
                            mIMainActivity.handleBikeTextViews(mTextViews.get(13), mTextViews.get(14));
                            mTextViews.get(25).setVisibility(View.VISIBLE);
                            mTextViews.get(26).setVisibility(View.VISIBLE);
                            mTextViews.get(27).setVisibility(View.VISIBLE);
                            mIMainActivity.handleStartTimer(true);
                            relativeLayouts.get(1).setVisibility(View.GONE);
                            MyApplication.getAppData().setEco(true);
                            mIMainActivity.handleStartRecordLocation();
                        } else {
                            pMap.checkBluetoothState(getActivity());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //點下不開啟藍芽
        RxView.clicks(mTextViewsInFour.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        relativeLayouts.get(0).setVisibility(View.GONE);
                        if (isCent) {
                            if (isNeedReturn) {
                                isNeedReturn = false;
                            }
                            mTextViews.get(12).setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //點下開啟藍芽
        RxView.clicks(mTextViewsInFour.get(3))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (isCent) {
                            relativeLayouts.get(0).setVisibility(View.GONE);
                            if (isNeedReturn) {
                                mIMainActivity.handlePauseTimer();
                                mTextViews.get(13).setVisibility(View.GONE);
                                mTextViews.get(14).setVisibility(View.GONE);
                                mImageViews.get(14).setVisibility(View.GONE);
                                mTextViews.get(12).setVisibility(View.GONE);
                                mViews.get(2).setVisibility(View.GONE);
                                mViews.get(3).setVisibility(View.GONE);
                                relativeLayouts.get(4).setVisibility(View.VISIBLE);
                                isCent = false;
                                isNeedReturn = false;
                                isLocked = false;
                            } else {
                                if (isLocked) {
                                    isLocked = false;
                                    mTextViews.get(13).setTextColor(Color.parseColor("#808080"));
                                    mTextViews.get(14).setTextColor(Color.parseColor("#808080"));
                                    mTextViews.get(12).setVisibility(View.VISIBLE);
                                    mTextViews.get(12).setText(R.string.map_ebike_lock);
                                } else {
                                    isLocked = true;
                                    mTextViews.get(13).setTextColor(Color.parseColor("#ce0000"));
                                    mTextViews.get(14).setTextColor(Color.parseColor("#ce0000"));
                                    mTextViews.get(12).setVisibility(View.VISIBLE);
                                    mTextViews.get(12).setText(R.string.map_ebike_unlock);
                                }
                            }
                        } else {
                            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                            startActivityForResult(intent, 3000);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click layout
        RxView.clicks(relativeLayouts.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        relativeLayouts.get(1).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click layout
        RxView.clicks(relativeLayouts.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        relativeLayouts.get(2).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click layout
        RxView.clicks(relativeLayouts.get(3))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        relativeLayouts.get(3).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click layout
        RxView.clicks(relativeLayouts.get(4))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.handleStopTimer();
                        relativeLayouts.get(4).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click cancel in select car
        RxView.clicks(mTextViewsInNine.get(7))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        relativeLayouts.get(2).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click Ok in select car
        RxView.clicks(mTextViewsInNine.get(8))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.saveCentPlace(mName);
                        relativeLayouts.get(2).setVisibility(View.GONE);
                        relativeLayouts.get(3).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click cancel in pay
        RxView.clicks(mTextViewsInThree.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        relativeLayouts.get(3).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click ok in pay
        RxView.clicks(mTextViewsInThree.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (isCreditCard) {
                            mEditSearch.setText("");
                            relativeLayouts.get(3).setVisibility(View.GONE);
                            getParentFragmentManager().beginTransaction()
                                    .replace(R.id.layoutContainer, new VCreditCard())
                                    .addToBackStack("Map")
                                    .commit();
                        } /*else if (MyApplication.getAppData().isFromWallet()) {
                            Log.e("map", "from wallet");
                            //VWallet.readyToCent = true;
                            //getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VWallet()).addToBackStack("Map").commit();
                            relativeLayouts.get(3).setVisibility(View.GONE);
                            pMap.handleCentBike(carCash);
                        }*/
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click finish return
        RxView.clicks(mTextViewsInThreeFinish.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mTextViews.get(0).setVisibility(View.GONE);
                        mTextViews.get(1).setVisibility(View.GONE);
                        mTextViews.get(2).setVisibility(View.GONE);
                        mTextViews.get(3).setVisibility(View.GONE);
                        mImageViews.get(0).setVisibility(View.GONE);
                        mImageViews.get(1).setVisibility(View.GONE);
                        mImageViews.get(2).setVisibility(View.GONE);
                        mImageViews.get(3).setVisibility(View.GONE);
                        mImageViews.get(4).setVisibility(View.GONE);
                        mImageViews.get(5).setVisibility(View.GONE);
                        mIMainActivity.saveReturnPlace(mName);
                        relativeLayouts.get(4).setVisibility(View.GONE);
                        mTextViews.get(12).setText(R.string.global_next);
                        mTextViews.get(12).setVisibility(View.VISIBLE);
                        mIMainActivity.handleDrawLine();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click finish return detail
        RxView.clicks(mTextViewsInThreeFinish.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.saveReturnPlace(mName);
                        mEditSearch.setText("");
                        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VFinishTour()).addToBackStack("Map").commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //偵測所選車輛
        RxRadioGroup.checkedChanges(mRadioGroupInNine)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Integer integer) {
                        double cost = 0;
                        switch (integer) {
                            case R.id.radioOneInItemNineEbike:
                                mTextViewsInNine.get(1).setText(R.string.ebike_distance_1);
                                mTextViewsInNine.get(3).setText(R.string.ebike_power_100);
                                mTextViewsInNine.get(5).setText(R.string.ebike_cost_1);
                                cost = 1 / (2.4 * 60);
                                mIMainActivity.saveSelectBikeNubmer("801");
                                break;
                            case R.id.radioTwoInItemNineEbike:
                                mTextViewsInNine.get(1).setText(R.string.ebike_distance_2);
                                mTextViewsInNine.get(3).setText(R.string.ebike_power_80);
                                mTextViewsInNine.get(5).setText(R.string.ebike_cost_2);
                                cost = 1 / (2.7 * 60);
                                mIMainActivity.saveSelectBikeNubmer("802");
                                break;
                            case R.id.radioThreeInItemNineEbike:
                                mTextViewsInNine.get(1).setText(R.string.ebike_distance_3);
                                mTextViewsInNine.get(3).setText(R.string.ebike_power_60);
                                mTextViewsInNine.get(5).setText(R.string.ebike_cost_3);
                                cost = 1 / (3 * 60);
                                mIMainActivity.saveSelectBikeNubmer("803");
                                break;
                            default:
                                break;
                        }
                        if (cost > 0) {
                            mIMainActivity.setCost(cost);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //偵測所選付費方式
        RxRadioGroup.checkedChanges(mRadioGroupInThree)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Integer integer) {
                        switch (integer) {
                            case R.id.radioOneInItemThreeEbike:
                                isCreditCard = true;
                                mTextViewsInThree.get(2).setText(R.string.global_next);
                                break;
                            case R.id.radioThreeInItemThreeEbike:
                                mTextViewsInThree.get(2).setText(R.string.global_ok);
                                break;
                            default:
                                isCreditCard = false;
                                break;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        mEditSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                if (textView.getText().length() > 0) {
                    pMap.getPlaceData(textView.getText().toString(), eClusterItem);
                    return true;
                }
            }
            return false;
        });
        //切換回公版地圖頁
        RxView.clicks(mTextViews.get(24))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            mImageViews.get(17).setVisibility(View.GONE);
                            mImageViews.get(18).setVisibility(View.GONE);
                            mTextViews.get(24).setVisibility(View.GONE);
                            mTextViews.get(23).setVisibility(View.GONE);
                            mImageMenu.setVisibility(View.VISIBLE);
                            mImageViews.get(6).setVisibility(View.VISIBLE);
                            mEditSearch.setVisibility(View.VISIBLE);
                            MyApplication.getAppData().setNormalMap(true);
                            eClusterItem = MyApplication.getAppData().getGlobalClusterItemList();
                            mClustermanager.clearItems();
                            mClustermanager.addItems(eClusterItem);
                            mClustermanager.cluster();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //切換至清單模式
        RxView.clicks(mImageViews.get(17))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (canClick) {
                            mEditSearch.setText("");
                            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VStrokeAttractionList()).addToBackStack("Map").commit();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //新增或移除行程
        RxView.clicks(mImageViews.get(18))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                            new SignInUtil(getContext(), mIMainActivity);
                        } else {
                            if (canClick) {
                                if (MyApplication.getAppData().getStrokeMode() == 2 && MyApplication.getAppData().isCanEditInPersonalMap()) {
                                    AlertDialogUtil.getInstance()
                                            .initDialogBuilder(
                                                    getContext(),
                                                    R.string.stroke_title_delete_message,
                                                    R.string.global_confirm,
                                                    (dialogInterface, i) -> pMap.sendAddOrDel("del"),
                                                    R.string.global_cancel,
                                                    (dialogInterface, i) -> {});
                                    AlertDialogUtil.getInstance().showAlertDialog();
                                } else {
                                    pMap.sendAddToMyStroke();
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //加入行程
        RxView.clicks(mImageViews.get(19))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                            new SignInUtil(getContext(), mIMainActivity);
                        } else {
                            if (canClick) {
                                if (MyApplication.getAppData().getRaid().length() == 0) {
                                    MyApplication.getAppData().setRaid("WebSite");
                                }
                                pMap.getSaveList();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //取消行動音樂頁面
        RxView.clicks(textMobileMusicList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        relativeLayouts.get(5).setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //由行動音樂頁面進入播放頁面
        RxView.clicks(textMobileMusicList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (clusterItem != null) {
                            if (!SharePreferencesUtil.getInstance().getSharedPreferences().getBoolean("checkPermission", false)) {
                                mIMainActivity.getPermission();
                            } else {
                                MyApplication.getAppData().setFromFavorite(false);
                                MyApplication.getAppData().setWebId(clusterItem.getSpid());
                                MyApplication.getAppData().setChannelName(clusterItem.getTitle());
                                MyApplication.getAppData().setPhpName(clusterItem.getPhpname());
                                MyApplication.getAppData().setFromMobileMusic(true);
                                if (clusterItem.getLike().equals("0")) {
                                    MyApplication.getAppData().setLove(false);
                                } else {
                                    MyApplication.getAppData().setLove(true);
                                }
                                GPlayer.Push push = new GPlayer.Push();
                                push.setWebid_push(clusterItem.getSpid());
                                push.setWebid(clusterItem.getSpid());
                                push.setMenuid(MyApplication.getAppData().getMenuId());
                                push.setWtitle(clusterItem.getTitle());
                                push.setWtitle_tw(clusterItem.getTitle());
                                push.setWtitle_ch(clusterItem.getTitle());
                                push.setWtitle_jp(clusterItem.getTitle());
                                push.setCount(clusterItem.getName());
                                push.setLove(clusterItem.getLike());
                                push.setPath_img(clusterItem.getMsid());
                                push.setPhpname(clusterItem.getPhpname());
                                push.setRenew("0");
                                MyApplication.getAppData().setPush(push);
                                MyApplication.getAppData().setPush(false);
                                MyApplication.getAppData().setPushBuffer(null);
                                getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPlayer(), "Player").addToBackStack("Map").commit();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //由行動音樂頁面加入我的最愛
        RxView.clicks(imageMobileMusicList.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                            MyApplication.getAppData().setDefaultHotKye(true);
                            new SignInUtil(getContext(), mIMainActivity);
                        } else {
                            if (clusterItem != null) {
                                if (clusterItem.getLike().equals("0")) {
                                    pMap.sendLove("add", MyApplication.getAppData().getTaid(), clusterItem.getSpid(), "", "");
                                } else {
                                    pMap.sendLove("del", MyApplication.getAppData().getTaid(), clusterItem.getSpid(), "", "");
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //碳足跡-pause
        RxView.clicks(mTextViews.get(25))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.handlePauseTimer();
                        if (carbon) MyApplication.getAppData().setCarbon(false);
                        if (eco) MyApplication.getAppData().setEco(false);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //碳足跡-start
        RxView.clicks(mTextViews.get(26))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (carbon) MyApplication.getAppData().setCarbon(true);
                        if (eco) MyApplication.getAppData().setEco(true);
                        mIMainActivity.handleStartTimer(true);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //碳足跡-stop
        RxView.clicks(mTextViews.get(27))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.handlePauseTimer();
                        if (carbon) {
                            MyApplication.getAppData().setCarbon(false);
                            pMap.handleSaveCarbon();
                        }
                        if (eco) {
                            MyApplication.getAppData().setEco(false);
                            pMap.handleSaveEco();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //行動音樂背景
        RxView.clicks(relativeLayouts.get(5))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        mContext = container.getContext();
        canInit = true;
        if (!MyApplication.getAppData().isDefaultHotKye() && !MyApplication.getAppData().isNormalMap()) {
            if (MyApplication.getAppData().getUrid().length() > 0) {
                canClick = false;
                mImageViews.get(17).setVisibility(View.VISIBLE);
                mImageViews.get(18).setVisibility(View.VISIBLE);
                mTextViews.get(24).setVisibility(View.VISIBLE);
                mTextViews.get(23).setVisibility(View.VISIBLE);
                if (MyApplication.getAppData().getStrokeMode() == 2 && MyApplication.getAppData().isCanEditInPersonalMap()) {
                    mImageViews.get(18).setImageResource(R.drawable.delete_on);
                } else {
                    mImageViews.get(18).setImageResource(R.drawable.add_v1_2);
                }
                mImageMenu.setVisibility(View.GONE);
                mImageViews.get(6).setVisibility(View.GONE);
                mEditSearch.setVisibility(View.GONE);
                mTextViews.get(23).setText(MyApplication.getAppData().getTitleForUrid());
            }
        }

        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            supportMapFragment.getMapAsync(this);
        }
        getChildFragmentManager().beginTransaction().replace(R.id.layoutMap, supportMapFragment).commit();
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {}

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {}

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                List<String> msid = new ArrayList<>();
                for (int i = 0; i < isChecked.size(); i++) {
                    if (isChecked.get(i)) {
                        msid.add(styleEntities.get(i).getMsid());
                    }
                }
                Gson gson = new Gson();
                String s = gson.toJson(msid);
                String userMaid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
                if (userMaid.equals("null")) {
                    MyApplication.getAppData().setSelectedMsid(msid);
                } else {
                    SharePreferencesUtil.getInstance().getEditor().putString(userMaid, s).apply();
                }
                textBackInDrawer.setVisibility(View.GONE);
                textTitleInDrawer.setVisibility(View.GONE);
                imageViewInDrawer.setVisibility(View.VISIBLE);
                textViewInDrawer.setVisibility(View.VISIBLE);
                aSlideMenu.handleSwitch(true, "", isChecked);
                MyApplication.getAppData().setMobileMusicMode(false);
                MyApplication.getAppData().setDefaultHotKye(true);
                handleHotKey();
                pMap.getSelectedAttractionDataByStyle(msid, gAttractionListData);
            }

            @Override
            public void onDrawerStateChanged(int newState) {}
        });
        handleHotKey();
        pMap.getMenuData();
        return view;
    }

    private String mName;
    private final CameraPosition[] cameraPositions = {null};
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mIMainActivity.setGoogleMap(googleMap);
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            new RxPermissions(getActivity())
                    .requestEach(Manifest.permission.ACCESS_FINE_LOCATION)
                    .subscribe(new Observer<Permission>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @SuppressLint("MissingPermission")
                        @Override
                        public void onNext(Permission permission) {
                            if (permission.granted) {
                                googleMap.setMyLocationEnabled(true);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
        mClustermanager = null;
        mClustermanager = new ClusterManager<>(mContext, googleMap);
        mClustermanager.setRenderer(new ClusterRendererUtil(getContext(), googleMap, mClustermanager));
        googleMap.setOnMarkerClickListener(mClustermanager);
        googleMap.setOnInfoWindowClickListener(mClustermanager);
        googleMap.setOnMapClickListener(latLng -> {
            mViews.get(4).setVisibility(View.GONE);
            mTextViews.get(17).setVisibility(View.GONE);
            mTextViews.get(18).setVisibility(View.GONE);
            mTextViews.get(19).setVisibility(View.GONE);
            mTextViews.get(20).setVisibility(View.GONE);
            mTextViews.get(21).setVisibility(View.GONE);
            mTextViews.get(22).setVisibility(View.GONE);
            mImageViews.get(16).setVisibility(View.GONE);
            mImageViews.get(19).setVisibility(View.GONE);
            mRatingBar.setVisibility(View.GONE);
        });
        googleMap.setOnCameraIdleListener(() -> {
            if (MyApplication.getAppData().isMobileMusicMode()) {
                if (googleMap.getCameraPosition().target.latitude > 0 && googleMap.getCameraPosition().target.longitude > 0 && mIMainActivity.getNowGps() != null) {
                    float[] distance = new float[1];
                    if (MyApplication.getAppData().isManualMode()) {
                        Location.distanceBetween(MyApplication.getAppData().getManualGPS().latitude, MyApplication.getAppData().getManualGPS().longitude, googleMap.getCameraPosition().target.latitude, googleMap.getCameraPosition().target.longitude, distance);
                    } else {
                        Location.distanceBetween(mIMainActivity.getNowGps().latitude, mIMainActivity.getNowGps().longitude, googleMap.getCameraPosition().target.latitude, googleMap.getCameraPosition().target.longitude, distance);
                    }
                    if (distance[0] > 3000) {
                        MyApplication.getAppData().setManualMode(true);
                        MyApplication.getAppData().setManualGPS(new LatLng(googleMap.getCameraPosition().target.latitude, googleMap.getCameraPosition().target.longitude));
                        pMap.getPushMusicData();
                    }
                }
            }
            CameraPosition position = googleMap.getCameraPosition();
            if (cameraPositions[0] == null || cameraPositions[0].zoom != position.zoom) {
                cameraPositions[0] = googleMap.getCameraPosition();
                mClustermanager.cluster();
            }
        });
        mClustermanager.setOnClusterClickListener(this);
        mClustermanager.setOnClusterItemClickListener(this);
        mClustermanager.setOnClusterInfoWindowClickListener(this);
        mClustermanager.setOnClusterItemInfoWindowClickListener(this);
    }

    @Override
    public boolean onClusterClick(Cluster<EClusterItem> cluster) {
        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<EClusterItem> cluster) {}

    private EClusterItem clusterItem;
    @Override
    public boolean onClusterItemClick(EClusterItem eClusterItem) {
        if (canClick) {
            if (MyApplication.getAppData().isMobileMusicMode()) {
                clusterItem = eClusterItem;
                relativeLayouts.get(5).setVisibility(View.VISIBLE);
                Glide.with(MyApplication.getInstance())
                        .load(Uri.parse(eClusterItem.getMsid()))
                        .into(imageMobileMusicList.get(0));
                if (eClusterItem.getLike().equals("0")) {
                    imageMobileMusicList.get(1).setImageResource(R.drawable.love_off_2x);
                } else {
                    imageMobileMusicList.get(1).setImageResource(R.drawable.love_on_2x);
                }
                textMobileMusicList.get(2).setText(eClusterItem.getTitle());
                textMobileMusicList.get(3).setText(eClusterItem.getName());
            } else {
                clusterItem = null;
                if (mIMainActivity.getNowGps() == null) {
                    showDialog();
                } else {
                    mMsid = eClusterItem.getMsid();
                    if (!eClusterItem.getMsid().equals("ja")) {
                        mIMainActivity.saveAttractionId(eClusterItem.getSpid());
                    }
                    pMap.getPlaceDetail(eClusterItem.getSpid(), eClusterItem.getName(), mIMainActivity.getNowGps());
                }
            }
        }
        return true;
    }

    @Override
    public void onClusterItemInfoWindowClick(EClusterItem eClusterItem) {}

    @Override
    public void handleUpdateAttractionData(List<EClusterItem> list) {
        if (eClusterItem.size() > 0) {
            eClusterItem.clear();
        }
        eClusterItem = list;
        mClustermanager.clearItems();
        mClustermanager.addItems(eClusterItem);
        mClustermanager.cluster();
        canClick = true;
        if (MyApplication.getAppData().isCreateNewAttractionFailure()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getSpid().equals(mIMainActivity.getAttractionId())) {
                    mMsid = list.get(i).getMsid();
                    pMap.getPlaceDetail(list.get(i).getSpid(), list.get(i).getName(), mIMainActivity.getNowGps());
                    return;
                }
            }
        }
    }

    @Override
    public void showPersonalMap(List<EClusterItem> list, int zoomIndex, LatLng latLng) {
        eClusterItem = list;
        canClick = true;
        if (pMap != null) {
            mClustermanager.clearItems();
            mClustermanager.addItems(list);
            mClustermanager.cluster();
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomIndex));
        }
    }

    @Override
    public void savePlaceId(String id) {
        mIMainActivity.setPlaceId(id);
        mEditSearch.setText("");
        if (isCreate) {
            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VCreateAttraction()).addToBackStack("Map").commit();
        } else {
            getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VStroke()).addToBackStack("Map").commit();
        }
    }

    private TextView textViewInDrawer;
    private TextView textBackInDrawer;
    private TextView textTitleInDrawer;
    private CircleImageView imageViewInDrawer;
    private List<Boolean> isChecked = new ArrayList<>();
    private List<AttractionStyleEntity> styleEntities = new ArrayList<>();
    private GAttractionListData gAttractionListData;
    @Override
    public void initDrawer(final List<AttractionModeEntity> modeEntities, final List<AttractionStyleEntity> styleEntities, final GAttractionListData gAttractionListData) {
        if (canInit) {
            this.styleEntities = styleEntities;
            this.gAttractionListData = gAttractionListData;
            View view = inflater.inflate(R.layout.navigation_view_item_header, mListView, false);
            imageViewInDrawer = view.findViewById(R.id.imageHeadInNavigationViewItem);
            textViewInDrawer = view.findViewById(R.id.textNameInNavigationViewItem);
            final TextView textArea = view.findViewById(R.id.textAreaInNavigationViewItem);
            textBackInDrawer = view.findViewById(R.id.textBackInNavigationViewItem);
            textTitleInDrawer = view.findViewById(R.id.textTitleInNavigationViewItem);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
            if (stringBuilder.toString().equals("null")) {
                imageViewInDrawer.setImageResource(R.drawable.person_unlogin);
            } else {
                stringBuilder.delete(0, 2);
                Glide.with(MyApplication.getInstance())
                        .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                        .into(imageViewInDrawer);
            }
            String name = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userName", "null");
            if (name.equals("null")) {
                textViewInDrawer.setText(R.string.map_slide_visitor);
            } else {
                textViewInDrawer.setText(name);
            }
            final List<String> msid = new ArrayList<>();
            isChecked.clear();
            String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
            if (maid.equals("null")) {
                List<String> selectedMsid = new ArrayList<>();
                if (MyApplication.getAppData().getSelectedMsid().size() > 0) {
                    for (int i = 0; i < styleEntities.size(); i++) {
                        msid.add(styleEntities.get(i).getMsid());
                        if (MyApplication.getAppData().getSelectedMsid().contains(styleEntities.get(i).getMsid())) {
                            selectedMsid.add(styleEntities.get(i).getMsid());
                            isChecked.add(true);
                        } else {
                            isChecked.add(false);
                        }
                    }
                } else {
                    for (int i = 0; i < styleEntities.size(); i++) {
                        isChecked.add(true);
                        selectedMsid.add(styleEntities.get(i).getMsid());
                        msid.add(styleEntities.get(i).getMsid());
                    }
                }
                if (MyApplication.getAppData().isMobileMusicMode()) {
                    pMap.getPushMusicData();
                } else {
                    if (isAttractionList) {
                        mIMainActivity.handleRefreshLocation();
                        pMap.getPlaceData(mIMainActivity.getSelectedAttractionId(), styleEntities);
                    } else {
                        if (!MyApplication.getAppData().isNormalMap()) {
                            pMap.getStrokeList();
                        } else {
                            pMap.getSelectedAttractionDataByStyle(selectedMsid, gAttractionListData);
                        }
                    }
                }
            } else {
                String checkData = SharePreferencesUtil.getInstance().getSharedPreferences().getString(maid, "null");
                if (checkData.equals("null")) {
                    for (int i = 0; i < styleEntities.size(); i++) {
                        isChecked.add(true);
                        msid.add(styleEntities.get(i).getMsid());
                    }
                    if (MyApplication.getAppData().isMobileMusicMode()) {
                        pMap.getPushMusicData();
                    } else {
                        if (isAttractionList) {
                            mIMainActivity.handleRefreshLocation();
                            pMap.getPlaceData(mIMainActivity.getSelectedAttractionId(), styleEntities);
                        } else {
                            if (!MyApplication.getAppData().isNormalMap()) {
                                pMap.getStrokeList();
                            } else {
                                pMap.getSelectedAttractionDataByStyle(msid, gAttractionListData);
                            }
                        }
                    }
                } else {
                    Gson gson = new Gson();
                    String[] check = gson.fromJson(checkData, String[].class);
                    List<String> saveData = new ArrayList<>(Arrays.asList(check));
                    List<String> selectedMsid = new ArrayList<>();
                    for (int i = 0; i < styleEntities.size(); i++) {
                        if (saveData.contains(styleEntities.get(i).getMsid())) {
                            selectedMsid.add(styleEntities.get(i).getMsid());
                            isChecked.add(true);
                        } else {
                            isChecked.add(false);
                        }
                        msid.add(styleEntities.get(i).getMsid());
                    }
                    if (MyApplication.getAppData().isMobileMusicMode()) {
                        pMap.getPushMusicData();
                    } else {
                        if (isAttractionList) {
                            mIMainActivity.handleRefreshLocation();
                            pMap.getPlaceData(mIMainActivity.getSelectedAttractionId(), styleEntities);
                        } else {
                            if (!MyApplication.getAppData().isNormalMap()) {
                                pMap.getStrokeList();
                            } else {
                                pMap.getSelectedAttractionDataByStyle(selectedMsid, gAttractionListData);
                            }
                        }
                    }
                }
            }
            aSlideMenu.init(modeEntities, styleEntities, true);
            RxView.clicks(textBackInDrawer)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onNext(Object o) {
                            String userMaid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
                            List<String> msid = new ArrayList<>();
                            for (int i = 0; i < isChecked.size(); i++) {
                                if (isChecked.get(i)) {
                                    msid.add(styleEntities.get(i).getMsid());
                                }
                            }
                            Gson gson = new Gson();
                            String s = gson.toJson(msid);
                            textBackInDrawer.setVisibility(View.GONE);
                            textTitleInDrawer.setVisibility(View.GONE);
                            imageViewInDrawer.setVisibility(View.VISIBLE);
                            textViewInDrawer.setVisibility(View.VISIBLE);
                            aSlideMenu.handleSwitch(true, "", isChecked);
                            pMap.getSelectedAttractionDataByStyle(msid, gAttractionListData);
                            if (!userMaid.equals("null")) {
                                SharePreferencesUtil.getInstance().getEditor().putString(userMaid, s).apply();
                            } else {
                                MyApplication.getAppData().setSelectedMsid(msid);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
            if (mListView != null) {
                mListView.addHeaderView(view);
                mListView.setAdapter(aSlideMenu);
                mListView.setOnItemClickListener((adapterView, view1, i, l) -> {
                    ESlideMenu eSlideMenu = (ESlideMenu) aSlideMenu.getItem(i - 1);

                    if (eSlideMenu != null && eSlideMenu.isHasSub()) {
                        imageViewInDrawer.setVisibility(View.GONE);
                        textArea.setVisibility(View.GONE);
                        textViewInDrawer.setVisibility(View.GONE);
                        textTitleInDrawer.setVisibility(View.VISIBLE);
                        textBackInDrawer.setVisibility(View.VISIBLE);
                        textTitleInDrawer.setText(eSlideMenu.getTitle());
                        aSlideMenu.handleSwitch(false, eSlideMenu.getOpenId(), isChecked);
                    }

                    if (eSlideMenu != null && eSlideMenu.isSub()) {
                        int index = msid.indexOf(eSlideMenu.getMsid());
                        if (index != -1) {
                            if (isChecked.get(index)) {
                                isChecked.set(index, false);
                            } else {
                                isChecked.set(index, true);
                            }
                            aSlideMenu.handleCheckBox(eSlideMenu.getOpenId(), isChecked);
                        }
                    }

                    if (eSlideMenu != null && eSlideMenu.getOpenId().equals("default")) {
                        switch (i - 1) {
                            case 0:
                                getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VFavorable()).addToBackStack("Map").commit();
                                break;
                            case 4:
                                if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                    new SignInUtil(getContext(), mIMainActivity);
                                } else {
                                    mEditSearch.setText("");
                                    MyApplication.getAppData().setPersonalData(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"),
                                            SharePreferencesUtil.getInstance().getSharedPreferences().getString("userWsid", "null"),
                                            "me", "");
                                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VPersonalSteaming()).addToBackStack("Map").commit();
                                }
                                break;
                            case 5:
                                if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                    new SignInUtil(getContext(), mIMainActivity);
                                } else {
                                    mEditSearch.setText("");
                                    if (mIMainActivity.getNowGps() != null) {
                                        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VMyCollection()).addToBackStack("Map").commit();
                                    }
                                }
                                break;
                            case 6:
                                if (MyApplication.getAppData().isDefaultHotKye()) {
                                    MyApplication.getAppData().setDefaultHotKye(false);
                                    handleHotKey();
                                }
                                mDrawerLayout.closeDrawer(mListView);
                                break;
                            case 7:
                                if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                    new SignInUtil(getContext(), mIMainActivity);
                                } else {
                                    mIMainActivity.setCreateData(null);
                                    MyApplication.getAppData().getSelectedPhotos().clear();
                                    mIMainActivity.setSelectedAttractionType(null);
                                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                                    try {
                                        mDrawerLayout.closeDrawer(mListView);
                                        startActivityForResult(intentBuilder.build(getActivity()), 4000);
                                    } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;
                            case 8:
                            case 15:
                                Toast.makeText(getContext(), R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VWallet()).addToBackStack("map").commit();
                                break;
                            case 2:
                                mDrawerLayout.closeDrawer(mListView);
                                handleCarbonFootprint();
                                break;
                            case 3:
                                mDrawerLayout.closeDrawer(mListView);
                                handleEco();
                                break;
                            default:
                                if (i == aSlideMenu.getCount()) {
                                    mIMainActivity.saveSlideMenuUnits(textViewInDrawer, imageViewInDrawer, aSlideMenu, true);
                                    if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                        new SignInUtil(getContext(), mIMainActivity);
                                    } else {
                                        MyApplication.getAppData().getSelectedMsid().clear();
                                        mIMainActivity.handleSignOut();
                                    }
                                    mDrawerLayout.closeDrawer(mListView);
                                }
                                break;
                        }
                    }
                });
            }
        }
        if (googleMap != null) {
            mIMainActivity.setGoogleMap(googleMap);
        }
        if (MyApplication.getAppData().isFromWallet()) {
            MyApplication.getAppData().setFromWallet(false);
            String spid = "";
            String name = "";
            mMsid = "ja";
            if (isCent) {
                spid = "0MXbFw59elbr";
                name = "國立中興大學";
            } else {
                spid = "YkSdOu609nDn";
                name = "E-bike 美和街站點";
            }
            pMap.getPlaceDetail(spid, name, mIMainActivity.getNowGps());
        }
    }

    @Override
    public void handleSelectBike() {
        relativeLayouts.get(1).setVisibility(View.GONE);
        relativeLayouts.get(2).setVisibility(View.VISIBLE);
    }

    @Override
    public void handleOpenBluetooth() {
        relativeLayouts.get(1).setVisibility(View.GONE);
        relativeLayouts.get(0).setVisibility(View.VISIBLE);
        mTextViewsInFour.get(0).setText(R.string.map_buletooth_title);
        mTextViewsInFour.get(1).setText(R.string.map_buletooth_message);
    }

    @Override
    public void handleReturnBike() {
        isNeedReturn = true;
        relativeLayouts.get(1).setVisibility(View.GONE);
        relativeLayouts.get(0).setVisibility(View.VISIBLE);
        mTextViewsInFour.get(0).setText(R.string.ebike_return_title);
        mTextViewsInFour.get(1).setText(R.string.map_ebike_return_message);
    }

    private String sucid = "";
    @Override
    public void showDetailForAttractionInfo(GSearchAttractionDetail gSearchAttractionDetail, String type, String isOpen, String time) {
        if (mMsid.equals("ja")) {
            mViews.get(4).setVisibility(View.GONE);
            mTextViews.get(17).setVisibility(View.GONE);
            mTextViews.get(18).setVisibility(View.GONE);
            mTextViews.get(19).setVisibility(View.GONE);
            mTextViews.get(20).setVisibility(View.GONE);
            mTextViews.get(21).setVisibility(View.GONE);
            mTextViews.get(22).setVisibility(View.GONE);
            mImageViews.get(16).setVisibility(View.GONE);
            mImageViews.get(19).setVisibility(View.GONE);
            mRatingBar.setVisibility(View.GONE);
            relativeLayouts.get(1).setVisibility(View.VISIBLE);
            mTextViewsInFive.get(3).setText(R.string.map_ebike_directions);
            if (isCent) {
                mTextViewsInFive.get(2).setText(R.string.map_ebike_retrun_count);
                mTextViewsInFive.get(4).setText(R.string.map_ebike_return);
            } else {
                mTextViewsInFive.get(2).setText(R.string.map_ebike_available);
                mTextViewsInFive.get(4).setText(R.string.map_ebike_rent);
            }
            mName = gSearchAttractionDetail.getData().get(0).getPlace();
            mTextViewsInFive.get(0).setText(gSearchAttractionDetail.getData().get(0).getPlace());
            mTextViewsInFive.get(1).setText(gSearchAttractionDetail.getData().get(0).getAddr());
            location = "geo:" + "0,0" +
                    "?q=" +
                    gSearchAttractionDetail.getData().get(0).getLat() +
                    "," +
                    gSearchAttractionDetail.getData().get(0).getLng() +
                    "(" +
                    gSearchAttractionDetail.getData().get(0).getPlace() +
                    ")";
        } else {
            relativeLayouts.get(1).setVisibility(View.GONE);
            mViews.get(4).setVisibility(View.VISIBLE);
            mTextViews.get(17).setVisibility(View.VISIBLE);
            mTextViews.get(18).setVisibility(View.VISIBLE);
            mTextViews.get(19).setVisibility(View.VISIBLE);
            mTextViews.get(20).setVisibility(View.VISIBLE);
            mTextViews.get(21).setVisibility(View.VISIBLE);
            mTextViews.get(22).setVisibility(View.VISIBLE);
            mImageViews.get(16).setVisibility(View.VISIBLE);
            mImageViews.get(19).setVisibility(View.VISIBLE);
            mRatingBar.setVisibility(View.VISIBLE);
            mTextViews.get(19).setText("(0)");
            sucid = gSearchAttractionDetail.getData().get(0).getColl();
            if (gSearchAttractionDetail.getData().get(0).getColl().length() > 0) {
                mImageViews.get(16).setImageResource(R.drawable.mark_on_v14);
            } else {
                mImageViews.get(16).setImageResource(R.drawable.mark_off_v12);
            }
            mTextViews.get(17).setText(gSearchAttractionDetail.getData().get(0).getPlace());
            mTextViews.get(18).setText(gSearchAttractionDetail.getData().get(0).getRating());
            mTextViews.get(20).setText(type);
            switch (isOpen) {
                case "0":
                    String text = getString(R.string.map_status_open);
                    mTextViews.get(21).setText(text);
                    break;
                case "1":
                    text = getString(R.string.map_status_close);
                    mTextViews.get(21).setText(text);
                    break;
                default:
                    mTextViews.get(21).setText(R.string.no_time_data);
                    break;
            }
            mTextViews.get(22).setText(time);
            mRatingBar.setRating(Float.parseFloat(gSearchAttractionDetail.getData().get(0).getRating()));
            mIMainActivity.saveAttractionId(gSearchAttractionDetail.getData().get(0).getSpid());
        }
        if (MyApplication.getAppData().isCreateNewAttractionFailure()) {
            LatLng latLng = new LatLng(Double.parseDouble(gSearchAttractionDetail.getData().get(0).getLat()),
                    Double.parseDouble(gSearchAttractionDetail.getData().get(0).getLng()));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }

    @Override
    public void handSearchData(int index) {
        mMsid = eClusterItem.get(index).getMsid();
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(eClusterItem.get(index).getPosition(), 15));
        relativeLayouts.get(1).setVisibility(View.GONE);
        mViews.get(4).setVisibility(View.VISIBLE);
        mTextViews.get(17).setVisibility(View.VISIBLE);
        mTextViews.get(18).setVisibility(View.VISIBLE);
        mTextViews.get(19).setVisibility(View.VISIBLE);
        mTextViews.get(20).setVisibility(View.VISIBLE);
        mTextViews.get(21).setVisibility(View.VISIBLE);
        mTextViews.get(22).setVisibility(View.VISIBLE);
        mImageViews.get(16).setVisibility(View.VISIBLE);
        mImageViews.get(19).setVisibility(View.VISIBLE);
        mRatingBar.setVisibility(View.VISIBLE);
        mTextViews.get(19).setText("(0)");
        mIMainActivity.saveAttractionId(eClusterItem.get(index).getSpid());
        pMap.getPlaceDetail(eClusterItem.get(index).getSpid(), eClusterItem.get(index).getTitle(), mIMainActivity.getNowGps());
    }

    @Override
    public void showFinishAddOrDel(String save) {
        if (save.equals("1")) {
            Toast.makeText(getContext(), R.string.stroke_title_added, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), R.string.stroke_menu_delete, Toast.LENGTH_SHORT).show();
            mImageViews.get(17).setVisibility(View.GONE);
            mImageViews.get(18).setVisibility(View.GONE);
            mTextViews.get(24).setVisibility(View.GONE);
            mTextViews.get(23).setVisibility(View.GONE);
            mImageMenu.setVisibility(View.VISIBLE);
            mImageViews.get(6).setVisibility(View.VISIBLE);
            mEditSearch.setVisibility(View.VISIBLE);
            MyApplication.getAppData().setNormalMap(true);
            pMap.getMenuData();
        }
    }

    @Override
    public void finishInsertCarbon() {
        carbon = false;
        mTextViews.get(13).setVisibility(View.GONE);
        mTextViews.get(14).setVisibility(View.GONE);
        mImageViews.get(14).setVisibility(View.GONE);
        mViews.get(2).setVisibility(View.GONE);
        mViews.get(3).setVisibility(View.GONE);
        mTextViews.get(25).setVisibility(View.GONE);
        mTextViews.get(26).setVisibility(View.GONE);
        mTextViews.get(27).setVisibility(View.GONE);
    }

    @Override
    public void finishInsertEco() {
        eco = false;
        mTextViews.get(13).setVisibility(View.GONE);
        mTextViews.get(14).setVisibility(View.GONE);
        mImageViews.get(14).setVisibility(View.GONE);
        mViews.get(2).setVisibility(View.GONE);
        mViews.get(3).setVisibility(View.GONE);
        mTextViews.get(25).setVisibility(View.GONE);
        mTextViews.get(26).setVisibility(View.GONE);
        mTextViews.get(27).setVisibility(View.GONE);
    }

    @Override
    public void showToast(String msg) { Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show(); }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PermissionChecker.PERMISSION_GRANTED) {
                pMap.handleToFile();
            } else {
                Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4000) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getContext(), data);
                isCreate = true;
                if (place.getName().toString().contains("°")) {
                    pMap.getPlaceData(place.getLatLng());
                } else {
                    savePlaceId(place.getId());
                }
            }
            if (MyApplication.getAppData().getiFloatPlayerService() != null) {
                MyApplication.getAppData().getiFloatPlayerService().showFloatPlayerKiller(false);
            }
        } else if (requestCode == 3000) {
            if (isCent) {
                isNeedReturn = true;
                mTextViewsInFour.get(0).setText(R.string.ebike_return_title);
                mTextViewsInFour.get(1).setText(R.string.map_ebike_return_message);
            } else {
                relativeLayouts.get(0).setVisibility(View.GONE);
                relativeLayouts.get(2).setVisibility(View.VISIBLE);
            }

        }
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
                        (dialogInterface, i) -> {
                            int size = aSaveList.getIsChecked().size();
                            boolean canNext = false;
                            for (int j = 0; j < size; j++) {
                                if (aSaveList.getIsChecked().get(j)) {
                                    canNext = true;
                                    break;
                                }
                            }
                            if (canNext) {
                                pMap.sendSaveData(gSaveList, aSaveList.getIsChecked(), mIMainActivity.getAttractionId());
                                AlertDialogUtil.getInstance().initDialogBuilder(getContext(), view1);
                                AlertDialogUtil.getInstance().showAlertDialog();
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
                                                (dialogInterface, i) -> { });
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
                                                (dialogInterface, i) -> { });
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

    public void backToNormalMap() {
        mImageViews.get(17).setVisibility(View.GONE);
        mImageViews.get(18).setVisibility(View.GONE);
        mTextViews.get(24).setVisibility(View.GONE);
        mTextViews.get(23).setVisibility(View.GONE);
        mImageMenu.setVisibility(View.VISIBLE);
        mImageViews.get(6).setVisibility(View.VISIBLE);
        mEditSearch.setVisibility(View.VISIBLE);
        MyApplication.getAppData().setNormalMap(true);
        pMap.getMenuData();
    }

    public void handleReloadAttractionPoint() {
        List<String> msid = new ArrayList<>();
        isChecked.clear();
        String maid = SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null");
        if (maid.equals("null")) {
            for (int i = 0; i < styleEntities.size(); i++) {
                isChecked.add(true);
                msid.add(styleEntities.get(i).getMsid());
            }
            pMap.getSelectedAttractionDataByStyle(msid, gAttractionListData);
        } else {
            String checkData = SharePreferencesUtil.getInstance().getSharedPreferences().getString(maid, "null");
            if (checkData.equals("null")) {
                for (int i = 0; i < styleEntities.size(); i++) {
                    isChecked.add(true);
                    msid.add(styleEntities.get(i).getMsid());
                }
                pMap.getSelectedAttractionDataByStyle(msid, gAttractionListData);
            } else {
                Gson gson = new Gson();
                String[] check = gson.fromJson(checkData, String[].class);
                List<String> saveData = new ArrayList<>(Arrays.asList(check));
                List<String> selectedMsid = new ArrayList<>();
                for (int i = 0; i < styleEntities.size(); i++) {
                    if (saveData.contains(styleEntities.get(i).getMsid())) {
                        selectedMsid.add(styleEntities.get(i).getMsid());
                        isChecked.add(true);
                    } else {
                        isChecked.add(false);
                    }
                    msid.add(styleEntities.get(i).getMsid());
                }
                pMap.getSelectedAttractionDataByStyle(selectedMsid, gAttractionListData);
            }
        }
    }

    @Override
    public void finishAddToStroke() {
        AlertDialogUtil.getInstance().clearAlertDialog();
        Toast.makeText(getContext(), R.string.stroke_title_added, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFinishCreateStroke() {
        Toast.makeText(getContext(), R.string.stroke_title_trip_created, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishAddToCollect(boolean isAdd, String sucid) {
        if (isAdd) {
            mImageViews.get(16).setImageResource(R.drawable.mark_on_v14);
            Toast.makeText(getContext(), R.string.stroke_collect_add, Toast.LENGTH_SHORT).show();
        } else {
            mImageViews.get(16).setImageResource(R.drawable.mark_off_v12);
            Toast.makeText(getContext(), R.string.stroke_collect_remove, Toast.LENGTH_SHORT).show();
        }
        this.sucid = sucid;
    }

    @Override
    public void showFinishAddToMyStroke() {
        Toast.makeText(getContext(), R.string.stroke_message_add_trip, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleMobileMusic(List<EMobileMusic> mobileMusicList) {
        if (googleMap != null) {
            if (MyApplication.getAppData().isManualMode()) {
                pMap.handleFivePoints(mobileMusicList, MyApplication.getAppData().getManualGPS(), googleMap.getCameraPosition().zoom);
            } else {
                pMap.handleFivePoints(mobileMusicList, mIMainActivity.getNowGps(), googleMap.getCameraPosition().zoom);
            }
        }
    }

    @Override
    public void showFivePoint(List<EMobileMusic> mobileMusicList) {
        mClustermanager.clearItems();
        int size = mobileMusicList.size();
        for (int i = 0; i < size; i++) {
            if (mobileMusicList.get(i).isSelected()) {
                EClusterItem eClusterItem = new EClusterItem(
                        mobileMusicList.get(i).getLatLng(),
                        mobileMusicList.get(i).getCount(),
                        mobileMusicList.get(i).getPhoto(),
                        mobileMusicList.get(i).getWebid(),
                        mobileMusicList.get(i).getTitle());
                eClusterItem.setLike(mobileMusicList.get(i).getLike());
                eClusterItem.setPhpname(mobileMusicList.get(i).getPhpname());
                mClustermanager.addItem(eClusterItem);
            }
            mClustermanager.cluster();
        }
    }

    @Override
    public void refreshLoveData(String save) {
        switch (save) {
            case "1":
                clusterItem.setLike("1");
                imageMobileMusicList.get(1).setImageResource(R.drawable.love_on_2x);
                Toast.makeText(getContext(), R.string.player_subscribed, Toast.LENGTH_SHORT).show();
                break;
            case "10":
                clusterItem.setLike("0");
                imageMobileMusicList.get(1).setImageResource(R.drawable.love_off_2x);
                Toast.makeText(getContext(), R.string.player_unsubscribed, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    public void showSearchCaution(boolean noMatch) {
        int resources = 0;
        if (noMatch) {
            resources = R.string.map_search_caution2;
        } else {
            resources = R.string.map_search_caution;
        }
        AlertDialogUtil.getInstance()
                .initDialogBuilder(getContext(), resources, R.string.global_ok, (dialogInterface, i) -> {});
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void showCaution() {
        AlertDialogUtil.getInstance()
                .initDialogBuilder(
                        getContext(),
                        R.string.stroke_title_create_message,
                        R.string.global_ok,
                        (dialogInterface, i) -> { });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void finishCashFlowInsert() {
        isCent = true;
        mIMainActivity.handleStartTimer(false);
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VWallet()).addToBackStack("Map").commit();
    }

    @Override
    public void showCarbonList(ArrayList<ECarBonList> list) {
        relativeLayouts.get(1).setVisibility(View.VISIBLE);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_carbon_list, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerCarbonList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ACarbon(list, this));
        AlertDialogUtil
                .getInstance()
                .initDialogBuilder(getContext(), view, "碳足跡試算", "確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) { AlertDialogUtil.getInstance().clearAlertDialog(); }
                }, true);
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void showEEcoList(ArrayList<EEcoList> list) {
        relativeLayouts.get(1).setVisibility(View.GONE);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_carbon_list, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerCarbonList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new AEco(list, this));
        AlertDialogUtil
                .getInstance()
                .initDialogBuilder(getContext(), view, "節能清單", "確認", (dialogInterface, i) -> AlertDialogUtil.getInstance().clearAlertDialog(), "匯出", (dialogInterface, i) -> {
                    if (PermissionChecker.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PermissionChecker.PERMISSION_GRANTED) {
                        pMap.handleToFile();
                    } else {
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, 1000);
                    }
                    AlertDialogUtil.getInstance().clearAlertDialog();
                });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void handleItemClick(ECarBonList eCarBonList) {
        AlertDialogUtil.getInstance().clearAlertDialog();
        MyApplication.getAppData().seteCarBonList(eCarBonList);
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layoutContainer, new VCarbon(), "Carbon").addToBackStack("Vmap").commit();
    }

    @Override
    public void handleItemClick(EEcoList eEcoList) {
        AlertDialogUtil.getInstance().clearAlertDialog();
        MyApplication.getAppData().seteEcoList(eEcoList);
        FragmentManager fragmentManager = getParentFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layoutContainer, new VEco(), "Eco").addToBackStack("Vmap").commit();
    }

    private void handleSwitchTextView() {
        switch (textContent.get(0)) {
            case "center":
                if (textContent.get(1).equals("right")) {
                    mIMainActivity.handleSetTextView(mTextViews.get(6), mTextViews.get(8), mTextViews.get(10));
                } else {
                    mIMainActivity.handleSetTextView(mTextViews.get(6), mTextViews.get(10), mTextViews.get(8));
                }
                break;
            case "right":
                if (textContent.get(1).equals("center")) {
                    mIMainActivity.handleSetTextView(mTextViews.get(8), mTextViews.get(6), mTextViews.get(10));
                } else {
                    mIMainActivity.handleSetTextView(mTextViews.get(8), mTextViews.get(10), mTextViews.get(6));
                }
                break;
            case "left":
                if (textContent.get(1).equals("center")) {
                    mIMainActivity.handleSetTextView(mTextViews.get(10), mTextViews.get(6), mTextViews.get(8));
                } else {
                    mIMainActivity.handleSetTextView(mTextViews.get(10), mTextViews.get(8), mTextViews.get(6));
                }
                break;
            default:
                break;
        }
    }

    private void showDialog() {
        AlertDialogUtil.getInstance().initDialogBuilder(getContext(), R.string.map_gps, R.string.global_ok, (dialogInterface, i) -> {});
    }

    private void handleHotKey() {
        if (MyApplication.getAppData().isDefaultHotKye()) {
            mImageViews.get(1).setImageResource(R.drawable.locatemore_v1_2);
            mImageViews.get(2).setImageResource(R.drawable.m_1_v11);
            mImageViews.get(3).setImageResource(R.drawable.m_2_v11);
            mImageViews.get(4).setImageResource(R.drawable.m_5_v11);
            mTextViews.get(0).setText(R.string.map_hotkey_checkin);
            mTextViews.get(1).setText(R.string.map_hotkey_explore);
            mTextViews.get(2).setText(R.string.map_hotkey_walk);
            mTextViews.get(3).setText(R.string.music_title_more);
        } else {
            mImageViews.get(1).setImageResource(R.drawable.moldmore_v1_1);
            switch (MyApplication.getAppData().getHotKeyMode()) {
                case 1:
                    mImageViews.get(2).setImageResource(R.drawable.me_2_v11);
                    mImageViews.get(3).setImageResource(R.drawable.mt_3);
                    mImageViews.get(4).setImageResource(R.drawable.mt_2);
                    mTextViews.get(0).setText(R.string.music_title_library);
                    mTextViews.get(1).setText(R.string.music_title_browse);
                    mTextViews.get(2).setText(R.string.music_title_you);
                    mTextViews.get(3).setText(R.string.music_title_more);
                    break;
                case 2:
                    mImageViews.get(2).setImageResource(R.drawable.me_2_v11);
                    mImageViews.get(3).setImageResource(R.drawable.tt_2);
                    mImageViews.get(4).setImageResource(R.drawable.tt_3);
                    mTextViews.get(0).setText(R.string.stroke_title_trip);
                    mTextViews.get(1).setText(R.string.stroke_title_browse);
                    mTextViews.get(2).setText(R.string.stroke_title_you);
                    mTextViews.get(3).setText(R.string.music_title_more);
                    break;
                default:
                    break;
            }
        }
    }

    private void createNewStroke() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_item, null);
        final EditText editText = view.findViewById(R.id.editInDialogItem);
        AlertDialogUtil.getInstance()
                .initDialogBuilder(
                        getContext(),
                        view,
                        "行程標題",
                        getString(R.string.global_ok),
                        (dialogInterface, i) -> pMap.handleNewStroke(editText.getText().toString()),
                        getString(R.string.global_cancel),
                        (dialogInterface, i) -> {});
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    private boolean carbon = false;
    private void handleCarbonFootprint() {
        carbon = true;
        eco = false;
        mViews.get(4).setVisibility(View.GONE);
        mTextViews.get(17).setVisibility(View.GONE);
        mTextViews.get(18).setVisibility(View.GONE);
        mTextViews.get(19).setVisibility(View.GONE);
        mTextViews.get(20).setVisibility(View.GONE);
        mTextViews.get(21).setVisibility(View.GONE);
        mTextViews.get(22).setVisibility(View.GONE);
        mImageViews.get(16).setVisibility(View.GONE);
        mImageViews.get(19).setVisibility(View.GONE);
        mRatingBar.setVisibility(View.GONE);
        relativeLayouts.get(1).setVisibility(View.VISIBLE);
        mTextViewsInFive.get(3).setText("紀錄");
        SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d HH:mm", Locale.getDefault());
        mTextViewsInFive.get(2).setText(format.format(new Date()));
        mTextViewsInFive.get(4).setText("開始");
        LatLng latLng = mIMainActivity.getNowGps();
        mName = latLng.latitude + "," + latLng.longitude;
        mTextViewsInFive.get(0).setText("碳足跡試算");
        mTextViewsInFive.get(1).setText(mName);
    }

    private boolean eco = false;
    private void handleEco() {
        eco = true;
        carbon = false;
        mViews.get(4).setVisibility(View.GONE);
        mTextViews.get(17).setVisibility(View.GONE);
        mTextViews.get(18).setVisibility(View.GONE);
        mTextViews.get(19).setVisibility(View.GONE);
        mTextViews.get(20).setVisibility(View.GONE);
        mTextViews.get(21).setVisibility(View.GONE);
        mTextViews.get(22).setVisibility(View.GONE);
        mImageViews.get(16).setVisibility(View.GONE);
        mImageViews.get(19).setVisibility(View.GONE);
        mRatingBar.setVisibility(View.GONE);
        relativeLayouts.get(1).setVisibility(View.VISIBLE);
        mTextViewsInFive.get(3).setText("紀錄");
        SimpleDateFormat format = new SimpleDateFormat("yyyy/M/d HH:mm", Locale.getDefault());
        mTextViewsInFive.get(2).setText(format.format(new Date()));
        mTextViewsInFive.get(4).setText("開始");
        LatLng latLng = mIMainActivity.getNowGps();
        mName = latLng.latitude + "," + latLng.longitude;
        mTextViewsInFive.get(0).setText("節能駕駛");
        mTextViewsInFive.get(1).setText(mName);
    }
}
