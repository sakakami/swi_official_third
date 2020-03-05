package com.switube.www.landmark2018test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;

import java.util.ArrayList;

public class AFavorable extends RecyclerView.Adapter<AFavorable.ViewHodler> {
    private ArrayList<Integer> photos;
    public AFavorable(ArrayList<Integer> list) {
        photos = new ArrayList<>();
        photos.addAll(list);
    }

    @NonNull
    @Override
    public ViewHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHodler(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_favorable, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHodler holder, int position) {
        Glide.with(MyApplication.getInstance())
                .load(photos.get(position))
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class ViewHodler extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHodler(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagePhotoItemFavorable);
        }
    }
}
