package com.switube.www.swiofficialthird.map.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.util.ItemDecorationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.ViewHolder> {
    private List<List<Integer>> mPhotos = new ArrayList<>();
    public PlaceListAdapter() {
        List<Integer> photo1 = new ArrayList<>();
        List<Integer> photo2 = new ArrayList<>();
        List<Integer> photo3 = new ArrayList<>();
        List<Integer> photo4 = new ArrayList<>();
        List<Integer> photo5 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            photo1.add(R.drawable.a0001);
            photo2.add(R.drawable.a0002);
            photo3.add(R.drawable.a0003);
            photo4.add(R.drawable.a0004);
            photo5.add(R.drawable.a0005);
        }
        mPhotos.add(photo1);
        mPhotos.add(photo2);
        mPhotos.add(photo3);
        mPhotos.add(photo4);
        mPhotos.add(photo5);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_place, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder.mRecyclerView.getAdapter() == null) {
            holder.mRecyclerView.setAdapter(new PlacePlaceAdapter(mPhotos.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerInItemPlace)
        RecyclerView mRecyclerView;
        @BindView(R.id.textTitleInItemPlace)
        TextView mTextTitle;
        @BindView(R.id.imageSaveInItemPlace)
        ImageView mImageSave;
        @BindViews({R.id.layoutAInItemPlace, R.id.layoutBInItemPlace})
        List<RelativeLayout> mLayouts;
        @BindViews({R.id.textRatingAInItemPlace, R.id.textCountAInItemPlace, R.id.textTypeAInItemPlace,
                R.id.textStatusAInItemPlace, R.id.textTimeAInItemPlace})
        List<TextView> mTextViewsA;
        @BindViews({R.id.textLicenceBInItemPlace, R.id.textTypeBInItemPlace, R.id.textOldBInItemPlace,
                R.id.textPlaceBInItemPlace})
        List<TextView> mTextViewB;
        @BindView(R.id.ratingAInItemPlace)
        RatingBar mRatingBar;
        @BindView(R.id.imageLicenceBInItemPlace)
        ImageView mImageLicence;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.addItemDecoration(new ItemDecorationUtil(itemView.getContext(), 0, 0, 4, 0));
            mRecyclerView.setLayoutManager(layoutManager);
        }
    }
}
