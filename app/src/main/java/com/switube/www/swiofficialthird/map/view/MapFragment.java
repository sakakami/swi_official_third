package com.switube.www.swiofficialthird.map.view;


import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxRadioGroup;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.view.AttractionTypeFragment;
import com.switube.www.swiofficialthird.create.view.CreateAttractionFragment;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.info.view.InfoFragment;
import com.switube.www.swiofficialthird.map.MenuListItem;
import com.switube.www.swiofficialthird.map.adapter.SlideMenuAdapter;
import com.switube.www.swiofficialthird.map.gson.PlaceBaseDataEntity;
import com.switube.www.swiofficialthird.map.presenter.MapPresenter;
import com.switube.www.swiofficialthird.util.ClusterItemUtil;
import com.switube.www.swiofficialthird.util.ClusterRendererUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements IMapFragment, OnMapReadyCallback,
        ClusterManager.OnClusterClickListener<ClusterItemUtil>,
        ClusterManager.OnClusterItemInfoWindowClickListener<ClusterItemUtil>,
        ClusterManager.OnClusterItemClickListener<ClusterItemUtil>,
        ClusterManager.OnClusterInfoWindowClickListener<ClusterItemUtil> {
    public static boolean isCent = false;
    public static boolean isLocked = false;
    private MapPresenter mMapPresenter;
    public MapFragment() {
        mMapPresenter = new MapPresenter(this);
    }

    private SupportMapFragment supportMapFragment;
    private Context mContext;
    private Unbinder mUnbinder;
    private boolean isHotKeyShow = false;
    private boolean isCreditCard = false;
    private boolean isNeedReturn = false;
    private List<String> textContent = new ArrayList<>();
    private List<String> textTitle = new ArrayList<>();
    @BindView(R.id.imageMenuInMap)
    ImageView mImageMenu;
    @BindView(R.id.layoutDrawer)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.listView)
    ListView mListView;
    @BindViews({R.id.textTitleInMap, R.id.textCheckInInMap, R.id.textSearchInMap,
            R.id.textTimerInMap, R.id.textStringInMap, R.id.textGpsStateInMap, R.id.textTitleGpsInMap,
            R.id.textCenterInMap, R.id.textCenterSubInMap, R.id.textRightInMap, R.id.textRightSubInMap,
            R.id.textLeftInMap, R.id.textLeftSubInMap, R.id.textMessageInMap, R.id.textDurationInMap,
            R.id.textCostInMap})
    List<TextView> mTextViews;
    @BindViews({R.id.imageFocusInMap, R.id.imageHotKeyInMap, R.id.imageCheckInInMap,
            R.id.imageSearchInMap, R.id.imageTimerInMap, R.id.imageStringInMap, R.id.imageCheckInMap,
            R.id.imageMarkInMap, R.id.imageCameraInMap, R.id.imageStartInMap, R.id.imagePauseInMap,
            R.id.imageStopInMap, R.id.imageLockInMap, R.id.imageLockBigInMap, R.id.imageBillingInMap})
    List<ImageView> mImageViews;
    @BindViews({R.id.viewBarTimerAInMap, R.id.viewBarTimerBInMap, R.id.viewBarBInMap, R.id.viewBarCInMap})
    List<View> mViews;
    @BindViews({R.id.layoutItemFourEbike, R.id.layoutItemFiveEbike, R.id.layoutItemNineEbike,
            R.id.layoutItemThreeEbike, R.id.layoutItemThreeFinishEbike})
    List<RelativeLayout> relativeLayouts;
    @BindViews({R.id.textTitleInItemFourEbike, R.id.textMessageInItemFourEbike,
            R.id.textLeftInItemFourEbike, R.id.textRightInItemFourEbike})
    List<TextView> mTextViewsInFour;
    @BindViews({R.id.textTitleInItemFiveEbike, R.id.textMessageInItemFiveEbike,
            R.id.textContentInItemFiveEbike, R.id.textLeftInItemFiveEbike, R.id.textRightInItemFiveEbike})
    List<TextView> mTextViewsInFive;
    @BindView(R.id.imageCancelInItemFiveEbike)
    ImageView mImageInFive;
    @BindViews({R.id.textTitleInItemNineEbike, R.id.textCenterInItemNineEbike, R.id.textCenterSubInItemNineEbike,
            R.id.textLeftInItemNineEbike, R.id.textLeftSubInItemNineEbike, R.id.textRightInItemNineEbike,
            R.id.textRightSubInItemNineEbike, R.id.textLeftBottomInItemNineEbike, R.id.textRightBottomInItemNineEbike})
    List<TextView> mTextViewsInNine;
    @BindViews({R.id.textTitleInItemThreeFinishEbike, R.id.textMessageOneInItemThreeFinishEbike, R.id.textMessageTwoInItemThreeFinishEbike})
    List<TextView> mTextViewsInThreeFinish;
    @BindView(R.id.radioGroupInItemNineEbike)
    RadioGroup mRadioGroupInNine;
    @BindViews({R.id.textTitleInItemThreeEbike, R.id.textLeftInItemThreeEbike, R.id.textRightInItemThreeEbike})
    List<TextView> mTextViewsInThree;
    @BindView(R.id.radioGroupInItemThreeEbike)
    RadioGroup mRadioGroupInThree;
    @BindView(R.id.editSearchInMap)
    EditText mEditSearch;
    //textContext預設順序為center->right->left,textTitle也一樣
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_map, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        textContent.add("center");
        textContent.add("right");
        textContent.add("left");
        textTitle.add("TOTAL TIME");
        textTitle.add("AVG. PACE");
        textTitle.add("DISTANCE");
        if (isCent) {
            mTextViews.get(14).setVisibility(View.VISIBLE);
            mTextViews.get(15).setVisibility(View.VISIBLE);
            mImageViews.get(14).setVisibility(View.VISIBLE);
            mTextViews.get(13).setVisibility(View.VISIBLE);
            mViews.get(2).setVisibility(View.VISIBLE);
            mViews.get(3).setVisibility(View.VISIBLE);
            mIMainActivity.handleBikeTextViews(mTextViews.get(14), mTextViews.get(15));
            mIMainActivity.handleStartTimer(false);
            if (isLocked) {
                mTextViews.get(13).setText(R.string.ebike_unlock);
            } else {
                mTextViews.get(13).setText(R.string.ebike_lock);
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
                        if (isHotKeyShow) {
                            isHotKeyShow = false;
                            mTextViews.get(0).setVisibility(View.GONE);
                            mTextViews.get(1).setVisibility(View.GONE);
                            mTextViews.get(2).setVisibility(View.GONE);
                            mTextViews.get(3).setVisibility(View.GONE);
                            mTextViews.get(4).setVisibility(View.GONE);
                            mImageViews.get(2).setVisibility(View.GONE);
                            mImageViews.get(3).setVisibility(View.GONE);
                            mImageViews.get(4).setVisibility(View.GONE);
                            mImageViews.get(5).setVisibility(View.GONE);
                        } else {
                            isHotKeyShow = true;
                            mTextViews.get(0).setVisibility(View.VISIBLE);
                            mTextViews.get(1).setVisibility(View.VISIBLE);
                            mTextViews.get(2).setVisibility(View.VISIBLE);
                            mTextViews.get(3).setVisibility(View.VISIBLE);
                            mTextViews.get(4).setVisibility(View.VISIBLE);
                            mImageViews.get(2).setVisibility(View.VISIBLE);
                            mImageViews.get(3).setVisibility(View.VISIBLE);
                            mImageViews.get(4).setVisibility(View.VISIBLE);
                            mImageViews.get(5).setVisibility(View.VISIBLE);
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
                        AttractionTypeFragment.isClickHotKey = true;
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new AttractionTypeFragment()).addToBackStack("map").commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //focus location
        RxView.clicks(mImageViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.handleRefreshLocation();
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
                        isHotKeyShow = false;
                        mIMainActivity.handleSetTextView(mTextViews.get(7), mTextViews.get(9), mTextViews.get(11));
                        mIMainActivity.handleStartTimer(true);
                        mTextViews.get(0).setVisibility(View.GONE);
                        mTextViews.get(1).setVisibility(View.GONE);
                        mTextViews.get(2).setVisibility(View.GONE);
                        mTextViews.get(3).setVisibility(View.GONE);
                        mTextViews.get(4).setVisibility(View.GONE);
                        mImageViews.get(2).setVisibility(View.GONE);
                        mImageViews.get(3).setVisibility(View.GONE);
                        mImageViews.get(4).setVisibility(View.GONE);
                        mImageViews.get(5).setVisibility(View.GONE);
                        mImageViews.get(0).setVisibility(View.GONE);
                        mImageViews.get(1).setVisibility(View.GONE);
                        mViews.get(0).setVisibility(View.VISIBLE);
                        mViews.get(1).setVisibility(View.VISIBLE);
                        mImageViews.get(8).setVisibility(View.VISIBLE);
                        mImageViews.get(10).setVisibility(View.VISIBLE);
                        mImageViews.get(12).setVisibility(View.VISIBLE);
                        mImageViews.get(7).setVisibility(View.VISIBLE);
                        mTextViews.get(5).setVisibility(View.VISIBLE);
                        mTextViews.get(6).setVisibility(View.VISIBLE);
                        mTextViews.get(7).setVisibility(View.VISIBLE);
                        mTextViews.get(8).setVisibility(View.VISIBLE);
                        mTextViews.get(9).setVisibility(View.VISIBLE);
                        mTextViews.get(10).setVisibility(View.VISIBLE);
                        mTextViews.get(11).setVisibility(View.VISIBLE);
                        mTextViews.get(12).setVisibility(View.VISIBLE);
                        mClustermanager.clearItems();
                        mClustermanager.cluster();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
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
                        mTextViews.get(5).setVisibility(View.GONE);
                        mTextViews.get(6).setVisibility(View.GONE);
                        mTextViews.get(7).setVisibility(View.GONE);
                        mTextViews.get(8).setVisibility(View.GONE);
                        mTextViews.get(9).setVisibility(View.GONE);
                        mTextViews.get(10).setVisibility(View.GONE);
                        mTextViews.get(11).setVisibility(View.GONE);
                        mTextViews.get(12).setVisibility(View.GONE);
                        mImageViews.get(0).setVisibility(View.VISIBLE);
                        mImageViews.get(1).setVisibility(View.VISIBLE);
                        mIMainActivity.handleStopTimer();
                        mClustermanager.clearItems();
                        mClustermanager.addItems(mMapPresenter.getmAttraction());
                        mClustermanager.cluster();
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
        //click right in timer
        RxView.clicks(mTextViews.get(9))
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
                        Log.e("contentRight", textContent.get(0) + "," + textContent.get(1) + "," + textContent.get(2));
                        Log.e("baseRight", base);
                        Log.e("targetRight", target);
                        handleSwitchTextView();
                        mTextViews.get(8).setText(textTitle.get(0));
                        mTextViews.get(10).setText(textTitle.get(1));
                        mTextViews.get(12).setText(textTitle.get(2));
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click left in timer
        RxView.clicks(mTextViews.get(11))
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
                        Log.e("contentLeft", textContent.get(0) + "," + textContent.get(1) + "," + textContent.get(2));
                        handleSwitchTextView();
                        mTextViews.get(8).setText(textTitle.get(0));
                        mTextViews.get(10).setText(textTitle.get(1));
                        mTextViews.get(12).setText(textTitle.get(2));
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
                        //relativeLayouts.get(1).setVisibility(View.GONE);
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
        //點下租車
        RxView.clicks(mTextViewsInFive.get(4))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mMapPresenter.checkBluetoothState(getActivity());
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
                                mTextViews.get(14).setVisibility(View.GONE);
                                mTextViews.get(15).setVisibility(View.GONE);
                                mImageViews.get(14).setVisibility(View.GONE);
                                mTextViews.get(13).setVisibility(View.GONE);
                                mViews.get(2).setVisibility(View.GONE);
                                mViews.get(3).setVisibility(View.GONE);
                                relativeLayouts.get(4).setVisibility(View.VISIBLE);
                                isCent = false;
                                isNeedReturn = false;
                                isLocked = false;
                                //mMapPresenter.handleSwitchMapPoint(false, mClusterItemUtil);
                            } else {
                                if (isLocked) {
                                    isLocked = false;
                                    mTextViews.get(13).setVisibility(View.VISIBLE);
                                    mTextViews.get(13).setText(R.string.ebike_lock);
                                } else {
                                    isLocked = true;
                                    mTextViews.get(13).setVisibility(View.VISIBLE);
                                    mTextViews.get(13).setText(R.string.ebike_unlock);
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
                            mTextViews.get(13).setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click layout
        RxView.clicks(relativeLayouts.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        relativeLayouts.get(0).setVisibility(View.GONE);
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
                        relativeLayouts.get(4).setVisibility(View.GONE);
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
        //click ok in pay
        RxView.clicks(mTextViewsInThree.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        if (isCreditCard) {
                            relativeLayouts.get(3).setVisibility(View.GONE);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.layoutContainer, new CreditCardFragment())
                                    .addToBackStack("map").commit();
                        }
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
        //click lock or unlock car
        RxView.clicks(mTextViews.get(13))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mTextViews.get(13).setVisibility(View.GONE);
                        if (isCent) {
                            relativeLayouts.get(0).setVisibility(View.VISIBLE);
                            if (isLocked) {
                                mTextViewsInFour.get(0).setText(R.string.ebike_unlock);
                                mTextViewsInFour.get(1).setText(R.string.ebike_unlock_message);
                            } else {
                                mTextViewsInFour.get(0).setText(R.string.ebike_lock);
                                mTextViewsInFour.get(1).setText(R.string.ebike_lock_message);
                            }
                        } else {
                            getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new DetailFragment()).addToBackStack("map").commit();
                        }
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
                        mIMainActivity.saveReturnPlace(mName);
                        relativeLayouts.get(4).setVisibility(View.GONE);
                        mTextViews.get(13).setText("NEXT");
                        mTextViews.get(13).setVisibility(View.VISIBLE);
                        mIMainActivity.handleDrawLine();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //click finish return detial
        RxView.clicks(mTextViewsInThreeFinish.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        mIMainActivity.saveReturnPlace(mName);
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new DetailFragment()).addToBackStack("map").commit();
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
                        Log.e("check", String.valueOf(integer));
                        double cost = 0f;
                        switch (integer) {
                            case R.id.radioOneInItemNineEbike:
                                Log.e("click", "801");
                                mTextViewsInNine.get(1).setText(R.string.ebike_distance_1);
                                mTextViewsInNine.get(3).setText(R.string.ebike_power_100);
                                mTextViewsInNine.get(5).setText(R.string.ebike_cost_1);
                                cost = 1 / (2.4 * 60);
                                mIMainActivity.saveSelectBikeNubmer("801");
                                break;
                            case R.id.radioTwoInItemNineEbike:
                                Log.e("click", "802");
                                mTextViewsInNine.get(1).setText(R.string.ebike_distance_2);
                                mTextViewsInNine.get(3).setText(R.string.ebike_power_80);
                                mTextViewsInNine.get(5).setText(R.string.ebike_cost_2);
                                cost = 1 / (2.7 * 60);
                                mIMainActivity.saveSelectBikeNubmer("802");
                                break;
                            case R.id.radioThreeInItemNineEbike:
                                Log.e("click", "803");
                                mTextViewsInNine.get(1).setText(R.string.ebike_distance_3);
                                mTextViewsInNine.get(3).setText(R.string.ebike_power_60);
                                mTextViewsInNine.get(5).setText(R.string.ebike_cost_3);
                                cost = 1 / (3 * 60);
                                mIMainActivity.saveSelectBikeNubmer("803");
                                break;
                            default:
                                break;
                        }
                        mIMainActivity.setCost(cost);
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
                        if (R.id.radioOneInItemThreeEbike == integer) {
                            Log.e("select", "信用卡");
                            isCreditCard = true;
                        } else {
                            isCreditCard = false;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        //search attractions
        RxTextView.textChanges(mEditSearch)
                .subscribe(new Observer<CharSequence>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(CharSequence charSequence) {
                        //mEditSearch.setText(charSequence);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        mContext = container.getContext();
        //mMapPresenter.getAttractionData(container.getContext().getApplicationContext());
        mMapPresenter.getMenuData(container.getContext());
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            supportMapFragment.getMapAsync(this);
        }
        getChildFragmentManager().beginTransaction().replace(R.id.layoutMap, supportMapFragment).commit();
        return view;
    }

    private ClusterManager<ClusterItemUtil> mClustermanager;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mIMainActivity.setGoogleMap(googleMap);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        googleMap.setMyLocationEnabled(true);
        mClustermanager = new ClusterManager<>(mContext, googleMap);
        mClustermanager.setRenderer(new ClusterRendererUtil(mContext, googleMap, mClustermanager));
        googleMap.setOnCameraIdleListener(mClustermanager);
        googleMap.setOnMarkerClickListener(mClustermanager);
        googleMap.setOnInfoWindowClickListener(mClustermanager);
        mClustermanager.setOnClusterClickListener(this);
        mClustermanager.setOnClusterItemClickListener(this);
        mClustermanager.setOnClusterInfoWindowClickListener(this);
        mClustermanager.setOnClusterItemInfoWindowClickListener(this);
        mClustermanager.clearItems();
        mClustermanager.addItems(mMapPresenter.getmAttraction());
        mClustermanager.cluster();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mListView);
        }
    }

    @Override
    public boolean onClusterClick(Cluster<ClusterItemUtil> cluster) {
        return true;
    }

    @Override
    public void onClusterInfoWindowClick(Cluster<ClusterItemUtil> cluster) {}

    private String mName;
    @Override
    public boolean onClusterItemClick(ClusterItemUtil clusterItemUtil) {
        if (clusterItemUtil.getMsid().equals("ca") || clusterItemUtil.getMsid().equals("cb")) {
            relativeLayouts.get(1).setVisibility(View.VISIBLE);
            mTextViewsInFive.get(0).setText(clusterItemUtil.getTitle());
            mTextViewsInFive.get(1).setText(clusterItemUtil.getAddress());
            mTextViewsInFive.get(3).setText(R.string.ebike_rent_directions);
            mName = clusterItemUtil.getTitle();
            if (isCent) {
                mTextViewsInFive.get(2).setText(R.string.ebike_retrun_count);
                mTextViewsInFive.get(4).setText(R.string.ebike_return_return);
            } else {
                mTextViewsInFive.get(2).setText(R.string.ebike_rent_count);
                mTextViewsInFive.get(4).setText(R.string.ebike_rent_rent);
            }
        }
        return true;
    }

    @Override
    public void onClusterItemInfoWindowClick(ClusterItemUtil clusterItemUtil) {}

    private List<ClusterItemUtil> mClusterItemUtil = new ArrayList<>();
    @Override
    public void handleUpdateAttractionData(List<ClusterItemUtil> list) {
        mClusterItemUtil = list;
        mClustermanager.clearItems();
        mClustermanager.addItems(list);
        mClustermanager.cluster();
    }

    @Override
    public void saveAPIKeys(List<String> keys) {
        mIMainActivity.setKeys(keys);
    }

    @Override
    public void savePlaceId(String id) {
        mIMainActivity.setPlaceId(id);
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new CreateAttractionFragment()).addToBackStack("map").commit();
    }

    @Override
    public void initDrawer(List<AttractionModeEntity> modeEntities, List<AttractionStyleEntity> styleEntities, PlaceBaseDataEntity placeBaseDataEntity) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        final SlideMenuAdapter slideMenuAdapter = new SlideMenuAdapter(getContext(), modeEntities, styleEntities);
        mListView.addHeaderView(inflater.inflate(R.layout.navigation_view_item_header, mListView, false));
        mListView.setAdapter(slideMenuAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MenuListItem menuListItem = (MenuListItem) slideMenuAdapter.getItem(i - 1);
                /*if (menuListItem.getCanOpenId() == 1) {
                    slideMenuAdapter.chickItemOne();
                } else if (menuListItem.getCanOpenId() == 2) {
                    slideMenuAdapter.chickItemTwo();
                }*/
                if (menuListItem.isHasSub()) {
                    slideMenuAdapter.switchData(menuListItem.getOpenId());
                }

                if (i - 1 == 0) {
                    getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new InfoFragment()).addToBackStack("map").commit();
                    mIMainActivity.handleCleanGoogleMap();
                } else if (i - 1 == 3) {
                    mIMainActivity.setCreateData(null);
                    mIMainActivity.setSelectedPhoto(new ArrayList<String>());
                    mIMainActivity.setSelectedAttractionType(null);
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    try {
                        mDrawerLayout.closeDrawer(mListView);
                        startActivityForResult(intentBuilder.build(getActivity()), 2000);
                    } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        mMapPresenter.handleMapPoint(styleEntities, placeBaseDataEntity);
    }

    @Override
    public void handleOpenBluetooth() {
        relativeLayouts.get(1).setVisibility(View.GONE);
        relativeLayouts.get(0).setVisibility(View.VISIBLE);
        mTextViewsInFour.get(0).setText(R.string.ebike_buletooth_title);
        mTextViewsInFour.get(1).setText(R.string.ebike_buletooth_message);
    }

    @Override
    public void handleSelectBike() {
        relativeLayouts.get(1).setVisibility(View.GONE);
        relativeLayouts.get(2).setVisibility(View.VISIBLE);
    }

    @Override
    public void handleReturnBike() {
        isNeedReturn = true;
        relativeLayouts.get(1).setVisibility(View.GONE);
        relativeLayouts.get(0).setVisibility(View.VISIBLE);
        mTextViewsInFour.get(0).setText(R.string.ebike_return_title);
        mTextViewsInFour.get(1).setText(R.string.ebike_return_message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getContext(), data);
                if (place.getName().toString().contains("°")) {
                    mMapPresenter.getPlaceData(place.getLatLng());
                } else {
                    savePlaceId(place.getId());
                }
                //handleSavePlace(place);

            }
        } else if (requestCode == 3000) {
            if (isCent) {
                isNeedReturn = true;
                mTextViewsInFour.get(0).setText(R.string.ebike_return_title);
                mTextViewsInFour.get(1).setText(R.string.ebike_return_message);
            } else {
                relativeLayouts.get(0).setVisibility(View.GONE);
                relativeLayouts.get(2).setVisibility(View.VISIBLE);
            }

        }
    }

    private void handleSwitchTextView() {
        switch (textContent.get(0)) {
            case "center":
                if (textContent.get(1).equals("right")) {
                    mIMainActivity.handleSetTextView(mTextViews.get(7), mTextViews.get(9), mTextViews.get(11));
                } else {
                    mIMainActivity.handleSetTextView(mTextViews.get(7), mTextViews.get(11), mTextViews.get(9));
                }
                break;
            case "right":
                if (textContent.get(1).equals("center")) {
                    mIMainActivity.handleSetTextView(mTextViews.get(9), mTextViews.get(7), mTextViews.get(11));
                } else {
                    mIMainActivity.handleSetTextView(mTextViews.get(9), mTextViews.get(11), mTextViews.get(7));
                }
                break;
            case "left":
                if (textContent.get(1).equals("center")) {
                    mIMainActivity.handleSetTextView(mTextViews.get(11), mTextViews.get(7), mTextViews.get(9));
                } else {
                    mIMainActivity.handleSetTextView(mTextViews.get(11), mTextViews.get(9), mTextViews.get(7));
                }
                break;
            default:
                break;
        }
    }
}
