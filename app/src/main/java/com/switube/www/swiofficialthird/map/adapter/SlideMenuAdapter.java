package com.switube.www.swiofficialthird.map.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.database.AppDatabase;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;
import com.switube.www.swiofficialthird.map.MenuListItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SlideMenuAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<MenuListItem> mItemList = new ArrayList<>();
    private List<AttractionModeEntity> mModeEntities;
    private List<AttractionStyleEntity> mStyleEntities;
    public SlideMenuAdapter(Context context, List<AttractionModeEntity> modeEntities,
                            List<AttractionStyleEntity> styleEntities) {
        //mItemList.addAll(defaultList);
        mModeEntities = modeEntities;
        mStyleEntities = styleEntities;
        mLayoutInflater = LayoutInflater.from(context);
        init();
    }
    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int i) {
        return mItemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MenuListItem menuListItem = mItemList.get(i);
        if (menuListItem.isHasSub()) {
            view = mLayoutInflater.inflate(R.layout.list_item_main_sub, viewGroup, false);
            ImageView imageView = view.findViewById(R.id.imageIconInListItemMainSub);
            imageView.setImageResource(menuListItem.getIcon());
            TextView textView = view.findViewById(R.id.textTitleInListItemMainSub);
            textView.setText(menuListItem.getTitle());
        } else {
            if (menuListItem.isSub()) {
                view = mLayoutInflater.inflate(R.layout.list_item_sub, viewGroup, false);
                ImageView imageView = view.findViewById(R.id.imageIconInListItemSub);
                imageView.setImageResource(menuListItem.getIcon());
                TextView textView = view.findViewById(R.id.textTitleInListItemSub);
                textView.setText(menuListItem.getTitle());
            } else {
                view = mLayoutInflater.inflate(R.layout.list_item_main, viewGroup, false);
                ImageView imageView = view.findViewById(R.id.imageIconInListItemMain);
                imageView.setImageResource(menuListItem.getIcon());
                TextView textView = view.findViewById(R.id.textTitleInListItemMain);
                textView.setText(menuListItem.getTitle());
            }
        }
        return view;
    }

    //private int mMode = 0;
    public void switchData(String mode) {
        for (int i = 0; i < mItemList.size(); i++) {
            if (mItemList.get(i).getOpenId().equals(mode)) {
                if (mItemList.get(i).isOpened()) {
                    if (mItemList.get(i).isSub()) {
                        mItemList.remove(i);
                        i--;
                    } else {
                        mItemList.get(i).setOpened(false);
                    }
                } else {
                    mItemList.get(i).setOpened(true);
                    //int count = 0;
                    for (int j = 0; j < mStyleEntities.size(); j++) {
                        if (mStyleEntities.get(j).getMmid().equals(mode)) {
                            //count++;
                            i++;
                            mItemList.add(i, new MenuListItem(R.drawable.a0001, mStyleEntities.get(j).getMstitle_tw(), false, true, mode, true));
                        }
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    private void init() {
        mItemList.add(new MenuListItem(R.drawable.a0001, "個人串流", false, false, "default", false));
        mItemList.add(new MenuListItem(R.drawable.a0001, "我的收藏", false, false, "default", false));
        mItemList.add(new MenuListItem(R.drawable.a0001, "我的任務", false, false, "default", false));
        mItemList.add(new MenuListItem(R.drawable.a0001, "新增地標", false, false, "default", false));
        for (int i = 0; i < mModeEntities.size(); i++) {
            mItemList.add(new MenuListItem(R.drawable.a0001, mModeEntities.get(i).getMmtitle_tw(), true, false, mModeEntities.get(i).getMmid(), false));
        }
        mItemList.add(new MenuListItem(R.drawable.a0001, "設定", false, false, "default", false));
        mItemList.add(new MenuListItem(R.drawable.a0001, "消費記錄", false, false, "default", false));
        notifyDataSetChanged();
    }
}
