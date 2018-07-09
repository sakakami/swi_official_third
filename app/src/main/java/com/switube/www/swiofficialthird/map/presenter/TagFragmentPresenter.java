package com.switube.www.swiofficialthird.map.presenter;

import android.content.Context;

import com.switube.www.swiofficialthird.util.SharePreferencesUtil;

import java.util.List;

public class TagFragmentPresenter {
    public TagFragmentPresenter() {}

    public boolean handleTag(Context context, List<String> listA, List<String> listB) {
        //List<String> listA = mTagSelectedAdapter.getTitleA();
        //List<String> listB = mTagSelectedAdapter.getTitleB();
        if (listA.size() == listB.size()) {
            int size = listA.size();
            StringBuilder stringBuilder = new StringBuilder();
            if (size > 1) {
                for (int i = 0; i < size - 1; i++) {
                    stringBuilder.append("#");
                    stringBuilder.append(listA.get(i));
                    stringBuilder.append(":");
                    stringBuilder.append(listB.get(i));
                    if (listB.size() - 2 > i) {
                        stringBuilder.append("\n");
                    }
                }
                SharePreferencesUtil.getInstance().getEditor(context).putString("selectedTag", stringBuilder.toString()).apply();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
