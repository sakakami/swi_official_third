package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;

import java.util.ArrayList;
import java.util.List;

public class ASpinner extends BaseAdapter {
    private List<String> mTitle = new ArrayList<>();
    private List<Integer> mImage = new ArrayList<>();
    public ASpinner() {
        mTitle.add(MyApplication.getInstance().getString(R.string.checkin_message_public));
        mTitle.add(MyApplication.getInstance().getString(R.string.checkin_message_anonymous));
        mImage.add(R.drawable.pr_public_v1_1);
        mImage.add(R.drawable.pr_anonymous_v1_1);
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
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.spinner_item_state, null);
        if (convertView != null) {
            ImageView imageView = convertView.findViewById(R.id.imagePhotoInSpinner);
            TextView textView = convertView.findViewById(R.id.textTitleInSpinner);
            imageView.setImageResource(mImage.get(position));
            textView.setText(mTitle.get(position));
        }
        return convertView;
    }
}
