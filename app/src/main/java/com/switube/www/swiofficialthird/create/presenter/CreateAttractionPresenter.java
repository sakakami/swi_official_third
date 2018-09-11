package com.switube.www.swiofficialthird.create.presenter;

import android.util.Log;

import com.switube.www.swiofficialthird.create.CreateAttractionEntity;
import com.switube.www.swiofficialthird.create.gson.AttractionDetailEntity;
import com.switube.www.swiofficialthird.create.model.CreateAttractionModel;
import com.switube.www.swiofficialthird.create.view.ICreateAttraction;
import com.switube.www.swiofficialthird.home.view.IMainActivity;

import java.util.ArrayList;
import java.util.List;

public class CreateAttractionPresenter implements ICreateAttractionPresenter {
    private ICreateAttraction mICreateAttraction;
    private CreateAttractionModel mCreateAttractionModel;
    public CreateAttractionPresenter(ICreateAttraction iCreateAttraction) {
        mICreateAttraction = iCreateAttraction;
        mCreateAttractionModel = new CreateAttractionModel(this);
    }

    private static String mKeyA;
    private List<String> mkey;
    public void getAttractionDetail(String id, List<String> key) {
        mkey = key;
        if (mKeyA == null) {
            mKeyA = key.get(0);
        } else {
            if (key.get(0).equals(mKeyA)) {
                mKeyA = key.get(1);
            } else {
                mKeyA = key.get(0);
            }
        }//"AIzaSyDz6NAg_lGLFcPfeMaHuUtDMk4_9wIpzi4"
        mCreateAttractionModel.getAttractionDetail(id, mKeyA, "en");
    }

    private CreateAttractionEntity entity = new CreateAttractionEntity();
    public void init(List<AttractionDetailEntity> detailEntities, IMainActivity iMainActivity) {
        /*if (iMainActivity.getPlace() != null) {
            entity.setLat(String.valueOf(iMainActivity.getPlace().getLatLng().latitude));
            entity.setLng(String.valueOf(iMainActivity.getPlace().getLatLng().longitude));
            entity.setPlace_id(iMainActivity.getPlace().getId());
        }*/
        entity.setPlace_id(iMainActivity.getPlaceId());
        entity.setLat(detailEntities.get(0).getResult().getGeometry().getLocation().getLat());
        entity.setLng(detailEntities.get(0).getResult().getGeometry().getLocation().getLng());
        entity.setPlace(new CreateAttractionEntity.Name(
                detailEntities.get(0).getResult().getName(), detailEntities.get(1).getResult().getName(),
                detailEntities.get(2).getResult().getName(), detailEntities.get(3).getResult().getName()));
        entity.setAddress(new CreateAttractionEntity.Address(
                detailEntities.get(0).getResult().getFormatted_address(), detailEntities.get(1).getResult().getFormatted_address(),
                detailEntities.get(2).getResult().getFormatted_address(), detailEntities.get(3).getResult().getFormatted_address()));
        if (detailEntities.get(0).getResult().getFormatted_phone_number() != null) {
            entity.setPhone(detailEntities.get(0).getResult().getFormatted_phone_number());
        }
        if (detailEntities.get(0).getResult().getWebsite() != null) {
            entity.setWebsite(detailEntities.get(0).getResult().getWebsite());
        }
        if (detailEntities.get(0).getResult().getRating() != null) {
            entity.setRating(detailEntities.get(0).getResult().getRating());
        }
        if (detailEntities.get(0).getResult().getPhotos() != null) {
            int size = detailEntities.get(0).getResult().getPhotos().size();
            String base = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1800&photoreference=";
            //String key = "AIzaSyAl4ilUTXXICgz6zvIHHioy64_7pUKOZ14";
            if (size >= 5) {
                size = 5;
            }
            List<String> photos = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                String result = base + detailEntities.get(0).getResult().getPhotos().get(i).getPhoto_reference();
                photos.add(result);
            }
            entity.setDefaultPhoto(photos);
        } else {
            entity.setDefaultPhoto(new ArrayList<String>());
        }
        if (detailEntities.get(0).getResult().getAddress_components().size() > 0) {
            int size = detailEntities.get(0).getResult().getAddress_components().size();
            String postal = "";
            String country = "";
            for (int i = 0; i < size; i++) {
                switch (detailEntities.get(0).getResult().getAddress_components().get(i).getTypes()[0]) {
                    case "administrative_area_level_1":
                        entity.setCity(new CreateAttractionEntity.LocationCity(
                                detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name()));
                        break;
                    case "administrative_area_level_2":
                        entity.setCity(new CreateAttractionEntity.LocationCity(
                                detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name()));
                        break;
                    case "administrative_area_level_3":
                        entity.setCity(new CreateAttractionEntity.LocationCity(
                                detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name()));
                        break;
                    case "locality":
                        entity.setCity(new CreateAttractionEntity.LocationCity(
                                detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(1).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(1).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(2).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(2).getResult().getAddress_components().get(i).getShort_name(),
                                detailEntities.get(3).getResult().getAddress_components().get(i).getLong_name(),
                                detailEntities.get(3).getResult().getAddress_components().get(i).getShort_name()));
                        break;
                    case "country":
                        country = detailEntities.get(0).getResult().getAddress_components().get(i).getShort_name();
                        break;
                    case "postal_code":
                        postal = detailEntities.get(0).getResult().getAddress_components().get(i).getLong_name();
                        break;
                }
            }
            StringBuilder stringBuilder = new StringBuilder(postal);
            if (stringBuilder.length() > 3) {
                stringBuilder.delete(3, stringBuilder.length());
            }
            entity.getCity().setCity_id(stringBuilder.toString());
            entity.getCity().setCountry(country);
        }
        List<String> time1 = new ArrayList<>();
        List<String> time2 = new ArrayList<>();
        List<String> time3 = new ArrayList<>();
        List<String> time4 = new ArrayList<>();
        List<String> time5 = new ArrayList<>();
        List<String> time6 = new ArrayList<>();
        List<String> time7 = new ArrayList<>();
        if (detailEntities.get(0).getResult().getOpening_hours() != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(detailEntities.get(0).getResult().getOpening_hours().getWeekday_text()[0]);
            int index = stringBuilder.indexOf(":");
            stringBuilder.delete(0, index + 1);
            time1.add(stringBuilder.toString());
            time1.add("");
            time1.add("");
            time1.add("");
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(detailEntities.get(0).getResult().getOpening_hours().getWeekday_text()[1]);
            index = stringBuilder.indexOf(":");
            stringBuilder.delete(0, index + 1);
            time2.add(stringBuilder.toString());
            time2.add("");
            time2.add("");
            time2.add("");
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(detailEntities.get(0).getResult().getOpening_hours().getWeekday_text()[2]);
            index = stringBuilder.indexOf(":");
            stringBuilder.delete(0, index + 1);
            time3.add(stringBuilder.toString());
            time3.add("");
            time3.add("");
            time3.add("");
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(detailEntities.get(0).getResult().getOpening_hours().getWeekday_text()[3]);
            index = stringBuilder.indexOf(":");
            stringBuilder.delete(0, index + 1);
            time4.add(stringBuilder.toString());
            time4.add("");
            time4.add("");
            time4.add("");
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(detailEntities.get(0).getResult().getOpening_hours().getWeekday_text()[4]);
            index = stringBuilder.indexOf(":");
            stringBuilder.delete(0, index + 1);
            time5.add(stringBuilder.toString());
            time5.add("");
            time5.add("");
            time5.add("");
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(detailEntities.get(0).getResult().getOpening_hours().getWeekday_text()[5]);
            index = stringBuilder.indexOf(":");
            stringBuilder.delete(0, index + 1);
            time6.add(stringBuilder.toString());
            time6.add("");
            time6.add("");
            time6.add("");
            stringBuilder.delete(0, stringBuilder.length());
            stringBuilder.append(detailEntities.get(0).getResult().getOpening_hours().getWeekday_text()[6]);
            index = stringBuilder.indexOf(":");
            stringBuilder.delete(0, index + 1);
            time7.add(stringBuilder.toString());
            time7.add("");
            time7.add("");
            time7.add("");
            stringBuilder.delete(0, stringBuilder.length());
            entity.setTime(new CreateAttractionEntity.Time(time1, time2, time3, time4, time5, time6, time7));
        } else {
            time1.add("");
            time1.add("");
            time1.add("");
            time1.add("");
            time2.add("");
            time2.add("");
            time2.add("");
            time2.add("");
            time3.add("");
            time3.add("");
            time3.add("");
            time3.add("");
            time4.add("");
            time4.add("");
            time4.add("");
            time4.add("");
            time5.add("");
            time5.add("");
            time5.add("");
            time5.add("");
            time6.add("");
            time6.add("");
            time6.add("");
            time6.add("");
            time7.add("");
            time7.add("");
            time7.add("");
            time7.add("");
            entity.setTime(new CreateAttractionEntity.Time(time1, time2, time3, time4, time5, time6, time7));
        }
        mICreateAttraction.init(entity);
    }

    private List<AttractionDetailEntity> detailEntities = new ArrayList<>();
    private static String mKeyB;
    @Override
    public void handleAttractionDetailOne(String id, AttractionDetailEntity attractionDetailEntity) {
        detailEntities.clear();
        detailEntities.add(attractionDetailEntity);
        if (mKeyB == null) {
            mKeyB = mkey.get(2);
        } else {
            if (mkey.get(2).equals(mKeyB)) {
                mKeyB = mkey.get(3);
            } else {
                mKeyB = mkey.get(2);
            }
        }//"AIzaSyAl4ilUTXXICgz6zvIHHioy64_7pUKOZ14"
        mCreateAttractionModel.getAttractionDetail(id, mKeyB, "zh-TW");
    }

    private static String mKeyC;
    @Override
    public void handleAttractionDetailTwo(String id, AttractionDetailEntity attractionDetailEntity) {
        detailEntities.add(attractionDetailEntity);
        if (mKeyC == null) {
            mKeyC = mkey.get(4);
        } else {
            if (mkey.get(4).equals(mKeyC)) {
                mKeyC = mkey.get(5);
            } else {
                mKeyC = mkey.get(4);
            }
        }//"AIzaSyAEuzS2MQdorEoinkYstL05HQKoRHkdUKc"
        mCreateAttractionModel.getAttractionDetail(id, mKeyC, "zh-CN");
    }

    private static String mKeyD;
    @Override
    public void handleAttractionDetailThree(String id, AttractionDetailEntity attractionDetailEntity) {
        detailEntities.add(attractionDetailEntity);
        if (mKeyD == null) {
            mKeyD = "AIzaSyCrjPbUjvqfCfiwDyyZhp5iF0X6B6nubro";
        } else {
            if (mKeyD.equals("AIzaSyCrjPbUjvqfCfiwDyyZhp5iF0X6B6nubro")) {
                mKeyD = "AIzaSyCozUP-ub5ZHNcY954ImnmPyv2HhXL8ya8";
            } else {
                mKeyD = "AIzaSyCrjPbUjvqfCfiwDyyZhp5iF0X6B6nubro";
            }
        }
        mCreateAttractionModel.getAttractionDetail(id, mKeyD, "jp");
    }

    @Override
    public void handleAttractionDetail(AttractionDetailEntity attractionDetailEntity) {
        detailEntities.add(attractionDetailEntity);
        mICreateAttraction.init(detailEntities);
    }
}
