package com.switube.www.swiofficialthird.map.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.map.PhotoItem;
import com.switube.www.swiofficialthird.map.view.IPhotoFragment;
import com.switube.www.swiofficialthird.map.view.PhotoFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {
    private Context mContext;
    private List<String> mSelectedPhoto;
    private IPhotoFragment mIPhotoFragment;
    public PhotoAdapter(Context context, IPhotoFragment iPhotoFragment) {
        mContext = context;
        mIPhotoFragment = iPhotoFragment;
        mSelectedPhoto = new ArrayList<>();
        getAllPhoto();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File imageFile = new File(mPhotoItem.get(position).getData());
        Glide.with(mContext)
                .load(Uri.fromFile(imageFile))
                .into(holder.mImageView);
        if (mSelectedPhoto.contains(mPhotoItem.get(position).getData())) {
            holder.mTextView.setBackground(mContext.getDrawable(R.drawable.background_red_surround));
        } else {
            holder.mTextView.setBackground(null);
        }
    }

    @Override
    public int getItemCount() {
        return mPhotoItem.size();
    }

    public void handleReloadAllPhoto() {
        getAllPhoto();
    }

    public List<String> getmSelectedPhoto() {
        return mSelectedPhoto;
    }

    private List<PhotoItem> mPhotoItem = new ArrayList<>();
    private void getAllPhoto() {
        mPhotoItem.clear();
        mSelectedPhoto.clear();

        String[] projection = new String[] {
                MediaStore.Images.ImageColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATA
        };

        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = mContext.getContentResolver().query(images,
                projection, null, null, MediaStore.Images.Media.DATE_TAKEN + " DESC");

        if (cursor != null && cursor.moveToFirst()) {
            int nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
            int dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

            do {
                PhotoItem newItem = new PhotoItem(cursor.getString(nameColumn), cursor.getString(dataColumn));
                mPhotoItem.add(newItem);
            } while (cursor.moveToNext());
            cursor.close();
        }
        if (PhotoFragment.isTakePhoto) {
            mSelectedPhoto.add(mPhotoItem.get(0).getData());
            mIPhotoFragment.handleSwitchImage(true);
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imagePhotoInItemPhoto)
        ImageView mImageView;
        @BindView(R.id.textSelectedInItemPhoto)
        TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(mTextView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (mSelectedPhoto.contains(mPhotoItem.get(getAdapterPosition()).getData())) {
                                mSelectedPhoto.remove(mPhotoItem.get(getAdapterPosition()).getData());
                            } else {
                                if (mSelectedPhoto.size() < 5) {
                                    mSelectedPhoto.add(mPhotoItem.get(getAdapterPosition()).getData());
                                }
                            }
                            if (mSelectedPhoto.size() == 0) {
                                PhotoFragment.isTakePhoto = false;
                                mIPhotoFragment.handleSwitchImage(false);
                            } else {
                                mIPhotoFragment.handleSwitchImage(true);
                            }
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
