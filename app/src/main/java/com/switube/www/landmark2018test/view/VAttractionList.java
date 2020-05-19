package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AAttractionList;
import com.switube.www.landmark2018test.adapter.callback.IAAttractionList;
import com.switube.www.landmark2018test.gson.GAttractionListData;
import com.switube.www.landmark2018test.presenter.PAttractionList;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVAttractionList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class VAttractionList extends Fragment implements IAAttractionList, IVAttractionList {
    @BindViews({R.id.textBackInAttractionSelect, R.id.textTitleInAttractionSelect, R.id.textCreateInAttractionSelect})
    List<TextView> textViews;
    @BindView(R.id.imageCancelInAttractionSelect)
    ImageView imageView;
    @BindView(R.id.recyclerInAttractionSelect)
    RecyclerView recyclerView;
    @BindView(R.id.searchInAttractionSelect)
    SearchView searchView;
    private IMainActivity iMainActivity;
    private PAttractionList pAttractionList;
    private Unbinder mUnbinder;
    private GAttractionListData gAttractionListData;
    private List<String> distance = new ArrayList<>();
    private AAttractionList aAttractionList;

    public VAttractionList() {
        pAttractionList = new PAttractionList(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_attraction_list, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        aAttractionList = new AAttractionList(this);
        recyclerView.setAdapter(aAttractionList);
        pAttractionList.getAllAttractionData(iMainActivity.getNowGps());
        RxView.clicks(textViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getParentFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(imageView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        textViews.get(2).setVisibility(View.GONE);
                        imageView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViews.get(2))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                        try {
                            startActivityForResult(intentBuilder.build(getActivity()), 2000);
                        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pAttractionList.handleSearch(query, gAttractionListData, distance);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() == 0) {
                    pAttractionList.getAllAttractionData(iMainActivity.getNowGps());
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void init(GAttractionListData gAttractionListData, List<String> distance) {
        this.gAttractionListData = gAttractionListData;
        this.distance = distance;
        aAttractionList.refreshData(gAttractionListData, distance);
    }

    @Override
    public void handleSwitch(String msid, String spid, String place) {
        iMainActivity.setPlaceId(msid);
        iMainActivity.saveAttractionId(spid);
        iMainActivity.setPlaceName(place);
        SharePreferencesUtil.getInstance().getEditor().putString("selectedTag", "finish").apply();
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VTag()).addToBackStack("AttractionList").commit();
    }

    @Override
    public void savePlaceId(String id) {
        iMainActivity.setPlaceId(id);
        getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VCreateAttraction()).addToBackStack("AttractionList").commit();
    }

    @Override
    public void refreshAdapter(GAttractionListData gAttractionListData, List<String> distance) {
        aAttractionList.refreshData(gAttractionListData, distance);
    }

    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity) context;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (resultCode == RESULT_OK) {
                iMainActivity.setCreateData(null);
                MyApplication.getAppData().getSelectedPhotos().clear();
                iMainActivity.setSelectedAttractionType(null);
                Place place = PlacePicker.getPlace(getContext(), data);
                if (place.getName().toString().contains("°")) {
                    pAttractionList.getPlaceData(place.getLatLng());
                } else {
                    iMainActivity.setPlaceId(place.getId());
                    getParentFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VCreateAttraction()).addToBackStack("AttractionList").commit();
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
