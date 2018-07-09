package com.switube.www.swiofficialthird.map.presenter;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.switube.www.swiofficialthird.database.entity.AttractionEntity;
import com.switube.www.swiofficialthird.map.gson.AttractionDataEntity;
import com.switube.www.swiofficialthird.map.model.MapModel;
import com.switube.www.swiofficialthird.map.view.IMapFragment;
import com.switube.www.swiofficialthird.util.ClusterItemUtil;
import com.switube.www.swiofficialthird.util.NetworkUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapPresenter implements IMapPresenter {
    private MapModel mMapModel;
    private IMapFragment mIMapFragment;
    public MapPresenter(IMapFragment iMapFragment) {
        mIMapFragment = iMapFragment;
        mMapModel = new MapModel(this);
    }

    public void getAttractionData(Context context) {
        mMapModel.getAttractionData(context, new HashMap<>(NetworkUtil.getInstance().getMap(context)));
    }

    public List<ClusterItemUtil> getmAttraction() {
        return mAttraction;
    }

    private List<ClusterItemUtil> mAttraction = new ArrayList<>();
    @Override
    public void handleParseAttractionGson(Context context, AttractionDataEntity attractionDataEntity) {
        int size = attractionDataEntity.getTData().size();
        List<AttractionEntity> attractionEntityList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            attractionEntityList.add(new AttractionEntity(
                    attractionDataEntity.getTData().get(i).getTrid(),
                    attractionDataEntity.getTData().get(i).getTrname(),
                    attractionDataEntity.getTData().get(i).getTraddr(),
                    attractionDataEntity.getTData().get(i).getTrgoid(),
                    attractionDataEntity.getTData().get(i).getTcid(),
                    attractionDataEntity.getTData().get(i).getLat(),
                    attractionDataEntity.getTData().get(i).getLongs(),
                    attractionDataEntity.getTData().get(i).getTrphone(),
                    attractionDataEntity.getTData().get(i).getTrurl(),
                    attractionDataEntity.getTData().get(i).getScore()
            ));
            LatLng latLng = new LatLng(Double.parseDouble(attractionDataEntity.getTData().get(i).getLat()), Double.parseDouble(attractionDataEntity.getTData().get(i).getLongs()));
            mAttraction.add(new ClusterItemUtil(latLng, attractionDataEntity.getTData().get(i).getTrid(), attractionDataEntity.getTData().get(i).getTrname()));
        }
        mIMapFragment.handleUpdateAttractionData(mAttraction);
        mMapModel.handleInsertAttractionData(context, attractionEntityList);
    }
}
