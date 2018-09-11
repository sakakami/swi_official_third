package com.switube.www.swiofficialthird.map.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.switube.www.swiofficialthird.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlacePlaceAdapter extends RecyclerView.Adapter<PlacePlaceAdapter.ViewHolder> {
    private List<Integer> mPhoto = new ArrayList<>();
    public PlacePlaceAdapter(List<Integer> photo) {
        mPhoto.addAll(photo);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_place_place, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mImageView.setImageResource(mPhoto.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhoto.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imagePictureInItemPlacePlace)
        ImageView mImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
