package com.switube.www.landmark2018test.adapter;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAPhotoList;
import com.switube.www.landmark2018test.entity.EPhotoList;
import com.switube.www.landmark2018test.view.VPhotoList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class APhotoList extends RecyclerView.Adapter<APhotoList.ViewHolder> {
    private List<String> mSelectedPhoto;
    private IAPhotoList iaPhotoList;
    public APhotoList(IAPhotoList iaPhotoList) {
        this.iaPhotoList = iaPhotoList;
        if (MyApplication.getAppData().getSelectedPhotos().size() > 0) {
            mSelectedPhoto = MyApplication.getAppData().getSelectedPhotos();
        } else {
            mSelectedPhoto = new ArrayList<>();
        }
        getAllPhoto();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File imageFile = new File(ePhotoList.get(position).getData());
        Glide.with(MyApplication.getInstance())
                .load(Uri.fromFile(imageFile))
                .into(holder.mImageView);
        if (mSelectedPhoto.contains(ePhotoList.get(position).getData())) {
            holder.mTextView.setBackground(MyApplication.getInstance().getDrawable(R.drawable.bg_red_surround));
        } else {
            holder.mTextView.setBackground(null);
        }
    }

    @Override
    public int getItemCount() {
        return ePhotoList.size();
    }

    public void handleReloadAllPhoto() {
        getAllPhoto();
    }

    public List<String> getmSelectedPhoto() {
        return mSelectedPhoto;
    }

    private List<EPhotoList> ePhotoList = new ArrayList<>();
    private void getAllPhoto() {
        ePhotoList.clear();

        String[] projection = new String[] {
                MediaStore.Images.ImageColumns.DISPLAY_NAME,
                MediaStore.Images.ImageColumns.DATA
        };

        Uri images = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        Cursor cursor = MyApplication.getInstance().getContentResolver().query(images,
                projection, null, null, MediaStore.Images.Media.DATE_TAKEN + " DESC");

        if (cursor != null && cursor.moveToFirst()) {
            int nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME);
            int dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);

            do {
                EPhotoList newItem = new EPhotoList(cursor.getString(nameColumn), cursor.getString(dataColumn));
                ePhotoList.add(newItem);
            } while (cursor.moveToNext());
            cursor.close();
        }
        if (VPhotoList.isTakePhoto) {
            mSelectedPhoto.add(ePhotoList.get(0).getData());
            iaPhotoList.handleSwitchImage(true);
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
                            if (mSelectedPhoto.contains(ePhotoList.get(getAdapterPosition()).getData())) {
                                mSelectedPhoto.remove(ePhotoList.get(getAdapterPosition()).getData());
                            } else {
                                if (MyApplication.getAppData().isEditMode()) {
                                    if ((mSelectedPhoto.size() + MyApplication.getAppData().geteEditComment().getImage().size() - MyApplication.getAppData().geteEditComment().getImageDel().size()) < 3) {
                                        mSelectedPhoto.add(ePhotoList.get(getAdapterPosition()).getData());
                                    }
                                } else {
                                    if (mSelectedPhoto.size() < 3) {
                                        mSelectedPhoto.add(ePhotoList.get(getAdapterPosition()).getData());
                                    }
                                }
                            }
                            if (mSelectedPhoto.size() == 0) {
                                VPhotoList.isTakePhoto = false;
                                iaPhotoList.handleSwitchImage(false);
                            } else {
                                iaPhotoList.handleSwitchImage(true);
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
