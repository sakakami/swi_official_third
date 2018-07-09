package com.switube.www.swiofficialthird.map.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.switube.www.swiofficialthird.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mTitle = new ArrayList<>();
    private List<Integer> mImage = new ArrayList<>();
    public SpinnerAdapter(Context context) {
        mContext = context;
        mTitle.add("公開");
        mTitle.add("有限公開");
        mTitle.add("不公開");
        mImage.add(R.drawable.pr_public_v1_1);
        mImage.add(R.drawable.pr_anonymous_v1_1);
        mImage.add(R.drawable.pr_lock_v1_1);
    }
    @Override
    public int getCount() {
        return mTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return mTitle.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.spinner_item_state, null);
        if (convertView != null) {
            ImageView imageView = convertView.findViewById(R.id.imagePhotoInSpinner);
            TextView textView = convertView.findViewById(R.id.textTitleInSpinner);
            imageView.setBackgroundResource(mImage.get(position));
            textView.setText(mTitle.get(position));
        }
        return convertView;
    }
}
