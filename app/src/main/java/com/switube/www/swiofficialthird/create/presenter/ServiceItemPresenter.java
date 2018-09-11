package com.switube.www.swiofficialthird.create.presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.switube.www.swiofficialthird.create.CreateAttractionEntity;
import com.switube.www.swiofficialthird.create.ServiceItemEntity;
import com.switube.www.swiofficialthird.create.model.ServiceItemModel;
import com.switube.www.swiofficialthird.create.view.IServiceItem;
import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class ServiceItemPresenter implements IServiceItemPresenter {
    private IServiceItem mIServiceItem;
    private ServiceItemModel mServiceItemModel;
    public ServiceItemPresenter(IServiceItem iServiceItem) {
        mIServiceItem = iServiceItem;
        mServiceItemModel = new ServiceItemModel(this);
    }

    private String mSid;
    public void getDataOne(Context context, String msid) {
        mSid = msid;
        mServiceItemModel.getDataOne(context, msid);
    }

    public void sendCreateData(Context context, CreateAttractionEntity attractionEntity,
                               List<ServiceItemEntity> itemEntities,
                               AttractionStyleEntity attractionStyleEntity, List<String> photos) {
        attractionEntity.setPhotoCount(String.valueOf(photos.size()));
        List<CreateAttractionEntity.Item> itemOne = new ArrayList<>();
        List<CreateAttractionEntity.Item> itemTwo = new ArrayList<>();
        List<CreateAttractionEntity.Item> itemThree = new ArrayList<>();
        List<String> term = new ArrayList<>();
        int size = itemEntities.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                if (itemEntities.get(i).isSelect()) {
                    switch (itemEntities.get(i).getFrom()) {
                        case "class":
                            attractionEntity.setmClass(new CreateAttractionEntity.Class(itemEntities.get(i).getId(), itemEntities.get(i).getId2()));
                            break;
                        case "item0":
                            if (!term.contains(itemEntities.get(i).getId())) {
                                term.add(itemEntities.get(i).getId());
                            }
                            itemOne.add(new CreateAttractionEntity.Item(itemEntities.get(i).getId(), itemEntities.get(i).getId2()));
                            break;
                        case "item1":
                            if (!term.contains(itemEntities.get(i).getId())) {
                                term.add(itemEntities.get(i).getId());
                            }
                            itemTwo.add(new CreateAttractionEntity.Item(itemEntities.get(i).getId(), itemEntities.get(i).getId2()));
                            break;
                        case "item2":
                            if (!term.contains(itemEntities.get(i).getId())) {
                                term.add(itemEntities.get(i).getId());
                            }
                            itemThree.add(new CreateAttractionEntity.Item(itemEntities.get(i).getId(), itemEntities.get(i).getId2()));
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        List<CreateAttractionEntity.Term> terms = new ArrayList<>();
        if (term.size() != 0) {
            terms.add(new CreateAttractionEntity.Term(term.get(0), itemOne));
            terms.add(new CreateAttractionEntity.Term(term.get(1), itemTwo));
            terms.add(new CreateAttractionEntity.Term(term.get(2), itemThree));
        } else {
            //attractionEntity.setmClass(new CreateAttractionEntity.Class("", ""));
            terms.add(new CreateAttractionEntity.Term("", itemOne));
            terms.add(new CreateAttractionEntity.Term("", itemTwo));
            terms.add(new CreateAttractionEntity.Term("", itemThree));
        }
        attractionEntity.setmTerm(terms);
        attractionEntity.setmStyle(new CreateAttractionEntity.Style(attractionStyleEntity.getMmspid(), attractionStyleEntity.getMmid(), attractionStyleEntity.getMsid()));
        sendData(context, photos, attractionEntity);
    }

    private Map<String, RequestBody> image = new HashMap<>();
    private void sendData(Context context, List<String> photos, final CreateAttractionEntity attractionEntity) {
        final int size = photos.size();
        if (size > 0) {
            Luban.with(context)
                    .load(photos)
                    .ignoreBy(100)
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {}

                        @Override
                        public void onSuccess(File file) {
                            Log.e("success", file.getAbsolutePath());
                            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            MultipartBody.Part part = null;
                            switch (image.size()) {
                                case 0:
                                    image.put("img1" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                                case 1:
                                    image.put("img2" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                                case 2:
                                    image.put("img3" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                                case 3:
                                    image.put("img4" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                                case 4:
                                    image.put("img5" + "\"; filename=\"" + file.getName(), requestBody);
                                    if (image.size() == size) {
                                        send(attractionEntity, image);
                                    }
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}
                    })
                    .launch();
        } else {
            send(attractionEntity, image);
        }
    }

    private void send(CreateAttractionEntity attractionEntity, Map<String, RequestBody> image) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        Map<String, String> map = new HashMap<>();
        String value = gson.toJson(attractionEntity);
        map.put("value", value);
        Log.e("map", map.toString());
        if (image.size() > 0) {
            mServiceItemModel.sendCreateData(map, image);
        } else {
            mServiceItemModel.sendCreateData(map);
        }
    }

    private List<AttractionClassEntity> mClassList = new ArrayList<>();
    @Override
    public void handleDataOne(Context context, List<AttractionClassEntity> attractionClassEntities) {
        mClassList.clear();
        mClassList.addAll(attractionClassEntities);
        mServiceItemModel.getDataTwo(context, mSid);
    }

    private List<AttractionTermEntity> mTermList = new ArrayList<>();
    @Override
    public void handleDataTwo(Context context, List<AttractionTermEntity> attractionTermEntities) {
        mTermList.clear();
        if (attractionTermEntities.size() != 0) {
            mTermList.addAll(attractionTermEntities);
            StringBuilder stringBuilder = new StringBuilder(attractionTermEntities.get(0).getMtid());
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            stringBuilder.append("%");
            mServiceItemModel.getDataThree(context, stringBuilder.toString());
        } else {
            mIServiceItem.init(mClassList, mTermList, new ArrayList<AttractionItemEntity>());
        }
    }

    @Override
    public void handleDataThree(List<AttractionItemEntity> attractionItemEntities) {
        mIServiceItem.init(mClassList, mTermList, attractionItemEntities);
    }

    @Override
    public void handleToNextPage() {
        mIServiceItem.handleToFinish();
    }
}
