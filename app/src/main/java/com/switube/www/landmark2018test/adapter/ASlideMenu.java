package com.switube.www.landmark2018test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.database.entity.AttractionModeEntity;
import com.switube.www.landmark2018test.database.entity.AttractionStyleEntity;
import com.switube.www.landmark2018test.entity.ESlideMenu;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ASlideMenu extends BaseAdapter {
    private LayoutInflater mLayoutInflater;
    private List<ESlideMenu> mItemList = new ArrayList<>();
    private List<AttractionModeEntity> mModeEntities = new ArrayList<>();
    private List<AttractionStyleEntity> mStyleEntities = new ArrayList<>();
    private boolean isMain = true;
    public ASlideMenu(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int i) {
        if (i >= 0) {
            return mItemList.get(i);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (isMain) {
            view = mLayoutInflater.inflate(R.layout.list_item_main, viewGroup, false);
            ImageView imageView = view.findViewById(R.id.imageIconInListItemMain);
            TextView textTitle = view.findViewById(R.id.textTitleInListItemMain);
            TextView textNext = view.findViewById(R.id.textNextInListItemMain);
            View viewBar = view.findViewById(R.id.viewBarInListItemMain);
            if (mItemList.get(i).isHasSub()) {
                textNext.setVisibility(View.VISIBLE);
                imageView.setImageResource(mItemList.get(i).getIcon());
                textTitle.setText(mItemList.get(i).getTitle());
            } else {
                textNext.setVisibility(View.GONE);
                if (mItemList.get(i).getIcon() == 0) {
                    imageView.setVisibility(View.GONE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setImageResource(mItemList.get(i).getIcon());
                }
                textTitle.setText(mItemList.get(i).getTitle());
            }
            if (i == 5 || i == 7) {
                viewBar.setVisibility(View.VISIBLE);
            } else {
                viewBar.setVisibility(View.INVISIBLE);
            }
        } else {
            view = mLayoutInflater.inflate(R.layout.list_item_main_sub, viewGroup, false);
            TextView textView = view.findViewById(R.id.textTitleInListItemMainSub);
            textView.setText(mItemList.get(i).getTitle());
            CheckBox checkBox = view.findViewById(R.id.checkBoxInListItemMainSub);
            checkBox.setChecked(isCheckedForStyle.get(i));
        }
        return view;
    }

    private List<Boolean> isCheckedForStyle = new ArrayList<>();
    public void handleSwitch(boolean b, String mode, List<Boolean> isChecked) {
        isMain = b;
        if (b) {
            init(new ArrayList<AttractionModeEntity>(), new ArrayList<AttractionStyleEntity>(), false);
        } else {
            mItemList.clear();
            isCheckedForStyle.clear();
            for (int i = 0; i < mStyleEntities.size(); i++) {
                if (mStyleEntities.get(i).getMmid().equals(mode)) {
                    String title;
                    switch (MyApplication.getLanguageIndex()) {
                        case 1:
                            title = mStyleEntities.get(i).getMstitle_tw();
                            break;
                        case 2:
                            title = mStyleEntities.get(i).getMstitle_ch();
                            break;
                        case 3:
                            title = mStyleEntities.get(i).getMstitle_jp();
                            break;
                        default:
                            title = mStyleEntities.get(i).getMstitle_en();
                            break;
                    }
                    isCheckedForStyle.add(isChecked.get(i));
                    mItemList.add(new ESlideMenu(0, title,
                            false, true, mStyleEntities.get(i).getMmid(), true,
                            mStyleEntities.get(i).getMsid()));
                }
            }
            notifyDataSetChanged();
        }
    }

    public void handleCheckBox(String mode, List<Boolean> isChecked) {
        isCheckedForStyle.clear();
        for (int i = 0; i < mStyleEntities.size(); i++) {
            if (mStyleEntities.get(i).getMmid().equals(mode)) {
                isCheckedForStyle.add(isChecked.get(i));
            }
        }
        notifyDataSetChanged();
    }

    public void init(List<AttractionModeEntity> modeEntities,
                     List<AttractionStyleEntity> styleEntities, boolean isInit) {
        if (isInit) {
            mModeEntities = modeEntities;
            mStyleEntities = styleEntities;
        }
        if (mItemList.size() > 0) {
            mItemList.clear();
        }
        mItemList.add(new ESlideMenu(R.drawable.me_11_v11, "優惠活動", false, false, "default", false, "null"));

        mItemList.add(new ESlideMenu(R.drawable.me_12_v11, MyApplication.getInstance().getString(R.string.map_slide_wallet), false, false, "default", false, "null"));
        mItemList.add(new ESlideMenu(R.drawable.me_13_v11, "碳足跡試算", false, false, "default", false, "null"));
        mItemList.add(new ESlideMenu(R.drawable.me_14_v11, "節能駕駛", false, false, "default", false, "null"));

        mItemList.add(new ESlideMenu(R.drawable.me_1_v11, MyApplication.getInstance().getString(R.string.map_slide_timeline),
                false, false, "default", false, "null"));
        mItemList.add(new ESlideMenu(R.drawable.me_2_v11, MyApplication.getInstance().getString(R.string.map_slide_favorites),
                false, false, "default", false, "null"));
        String name;
        if (MyApplication.getAppData().getHotKeyMode() == 1) {
            name = MyApplication.getInstance().getString(R.string.map_slide_music);
        } else {
            name = MyApplication.getInstance().getString(R.string.stroke_title_trip);
        }
        mItemList.add(new ESlideMenu(R.drawable.me_3_v11, name,
                false, false, "default", false, "null"));
        mItemList.add(new ESlideMenu(R.drawable.me_4_v11, MyApplication.getInstance().getString(R.string.map_slide_add),
                false, false, "default", false, "null"));
        mItemList.add(new ESlideMenu(R.drawable.me_5_v11, MyApplication.getInstance().getString(R.string.map_slide_manage),
                false, false, "default", false, "null"));
        int icon;
        for (int i = 0; i < mModeEntities.size(); i++) {
            switch (i) {
                case 0:
                    icon = R.drawable.me_8_v11;
                    break;
                case 1:
                    icon = R.drawable.me_11_v11;
                    break;
                case 2:
                    icon = R.drawable.me_6_v11;
                     break;
                case 3:
                    icon = R.drawable.me_7_v11;
                    break;
                case 4:
                    icon = R.drawable.me_9_v11;
                    break;
                case 5:
                    icon = R.drawable.me_10_v11;
                    break;
                default:
                    icon = 0;
                    break;
            }
            String title;
            switch (MyApplication.getLanguageIndex()) {
                case 1:
                    title = mModeEntities.get(i).getMmtitle_tw();
                    break;
                case 2:
                    title = mModeEntities.get(i).getMmtitle_ch();
                    break;
                case 3:
                    title = mModeEntities.get(i).getMmtitle_jp();
                    break;
                default:
                    title = mModeEntities.get(i).getMmtitle_en();
                    break;
            }
            mItemList.add(new ESlideMenu(icon, title, true,
                    false, mModeEntities.get(i).getMmid(), false, "null"));
        }
        mItemList.add(new ESlideMenu(0, MyApplication.getInstance().getString(R.string.map_slide_settings),
                false, false, "default", false, "null"));
        if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
            mItemList.add(new ESlideMenu(0, MyApplication.getInstance().getString(R.string.map_slide_sign_in),
                    false, false, "default", false, "null"));
        } else {
            mItemList.add(new ESlideMenu(0, MyApplication.getInstance().getString(R.string.map_slide_sign_out),
                    false, false, "default", false, "null"));
        }
        notifyDataSetChanged();
    }
}
