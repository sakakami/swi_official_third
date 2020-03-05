package com.switube.www.landmark2018test.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IALeaveComments;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ALeaveCommnets extends RecyclerView.Adapter<ALeaveCommnets.ViewHolder> {
    private IALeaveComments iaLeaveComments;
    public ALeaveCommnets(IALeaveComments iaLeaveComments) {
        this.iaLeaveComments = iaLeaveComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_comments, parent, false));
    }

    private int photoSize = 0;
    private List<Boolean> isFromEdit = new ArrayList<>();
    private List<String> deleteImage = new ArrayList<>();
    private String message = "";
    private  String privacy;
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StringBuilder stringBuilder1 = new StringBuilder();
        stringBuilder1.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
        stringBuilder1.delete(0, 2);
        Glide.with(MyApplication.getInstance())
                .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder1.toString()))
                .into(holder.imageViewList.get(1));
        holder.textViewList.get(1).setText(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userName", "null"));
        if (MyApplication.getAppData().isEditMode()) {
            photoSize = MyApplication.getAppData().geteEditComment().getImage().size() + MyApplication.getAppData().getSelectedPhotos().size() - MyApplication.getAppData().geteEditComment().getImageDel().size();
            if (MyApplication.getAppData().geteEditComment().getImageDel().size() > 0) {
                deleteImage = MyApplication.getAppData().geteEditComment().getImageDel();
            }
            if (MyApplication.getAppData().geteEditComment().getTagA().size() == 0) {
                holder.textViewList.get(2).setText("");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < MyApplication.getAppData().geteEditComment().getTagA().size(); i++) {
                    stringBuilder.append("#");
                    stringBuilder.append(MyApplication.getAppData().geteEditComment().getTagA().get(i));
                    stringBuilder.append("\n");
                }
                holder.textViewList.get(2).setText(stringBuilder.toString());
            }
            if (MyApplication.getAppData().geteEditComment().getTagB().size() == 0) {
                holder.textViewList.get(3).setText("");
            } else {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < MyApplication.getAppData().geteEditComment().getTagB().size(); i++) {
                    stringBuilder.append(MyApplication.getAppData().geteEditComment().getTagB().get(i));
                    stringBuilder.append("\n");
                }
                holder.textViewList.get(3).setText(stringBuilder.toString());
            }
            holder.editText.setText(MyApplication.getAppData().geteEditComment().getMessage());
            List<Boolean> hadPhoto = new ArrayList<>();
            hadPhoto.add(false);
            hadPhoto.add(false);
            hadPhoto.add(false);
            if (isFromEdit.size() > 0) {
                isFromEdit.clear();
            }
            isFromEdit.add(false);
            isFromEdit.add(false);
            isFromEdit.add(false);
            for (int i = 0; i < MyApplication.getAppData().geteEditComment().getImage().size(); i++) {
                StringBuilder stringBuilder = new StringBuilder(MyApplication.getAppData().geteEditComment().getImage().get(i));
                int index = stringBuilder.indexOf("2");
                stringBuilder.delete(0, index);
                if (!MyApplication.getAppData().geteEditComment().getImageDel().contains(stringBuilder.toString())) {
                    if (!hadPhoto.get(0)) {
                        hadPhoto.set(0, true);
                        isFromEdit.set(0, true);
                        Glide.with(MyApplication.getInstance())
                                .load(Uri.parse(AppConstant.BASE_URL + MyApplication.getAppData().geteEditComment().getImage().get(0)))
                                .into(holder.imageViewList.get(6));
                        holder.imageViewList.get(3).setVisibility(View.VISIBLE);
                    } else if (!hadPhoto.get(1)) {
                        hadPhoto.set(1, true);
                        isFromEdit.set(1, true);
                        Glide.with(MyApplication.getInstance())
                                .load(Uri.parse(AppConstant.BASE_URL + MyApplication.getAppData().geteEditComment().getImage().get(1)))
                                .into(holder.imageViewList.get(5));
                        holder.imageViewList.get(2).setVisibility(View.VISIBLE);
                    } else if (!hadPhoto.get(2)) {
                        hadPhoto.set(2, true);
                        isFromEdit.set(2, true);
                        Glide.with(MyApplication.getInstance())
                                .load(Uri.parse(AppConstant.BASE_URL + MyApplication.getAppData().geteEditComment().getImage().get(2)))
                                .into(holder.imageViewList.get(7));
                        holder.imageViewList.get(4).setVisibility(View.VISIBLE);
                    }
                }
            }
            for (int i = 0; i < MyApplication.getAppData().getSelectedPhotos().size(); i++) {
                if (!hadPhoto.get(0)) {
                    hadPhoto.set(0, true);
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(i))))
                            .into(holder.imageViewList.get(6));
                    holder.imageViewList.get(3).setVisibility(View.VISIBLE);
                } else if (!hadPhoto.get(1)) {
                    hadPhoto.set(1, true);
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(i))))
                            .into(holder.imageViewList.get(5));
                    holder.imageViewList.get(2).setVisibility(View.VISIBLE);
                } else if (!hadPhoto.get(2)) {
                    hadPhoto.set(2, true);
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(i))))
                            .into(holder.imageViewList.get(7));
                    holder.imageViewList.get(4).setVisibility(View.VISIBLE);
                }
            }
        } else {
            isFromEdit.add(false);
            isFromEdit.add(false);
            isFromEdit.add(false);
            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("selectedTagA", "finish").equals("finish")) {
                holder.textViewList.get(2).setText("");
            } else {
                holder.textViewList.get(2).setText(SharePreferencesUtil.getInstance().getSharedPreferences().getString("selectedTagA", "finish"));
            }
            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("selectedTagB", "finish").equals("finish")) {
                holder.textViewList.get(3).setText("");
            } else {
                holder.textViewList.get(3).setText(SharePreferencesUtil.getInstance().getSharedPreferences().getString("selectedTagB", "finish"));
            }
            switch (MyApplication.getAppData().getSelectedPhotos().size()) {
                case 1:
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(0))))
                            .into(holder.imageViewList.get(6));
                    holder.imageViewList.get(3).setVisibility(View.VISIBLE);
                    break;
                case 2:
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(0))))
                            .into(holder.imageViewList.get(6));
                    holder.imageViewList.get(3).setVisibility(View.VISIBLE);
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(1))))
                            .into(holder.imageViewList.get(5));
                    holder.imageViewList.get(2).setVisibility(View.VISIBLE);
                    break;
                case 3:
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(0))))
                            .into(holder.imageViewList.get(6));
                    holder.imageViewList.get(3).setVisibility(View.VISIBLE);
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(1))))
                            .into(holder.imageViewList.get(5));
                    holder.imageViewList.get(2).setVisibility(View.VISIBLE);
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(2))))
                            .into(holder.imageViewList.get(7));
                    holder.imageViewList.get(4).setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
            holder.editText.setText(MyApplication.getAppData().getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public String getMessage() {
        return message;
    }

    public String getPrivacy() {
        return privacy;
    }

    public List<String> getDeleteImage() {
        return deleteImage;
    }

    public int getPhotoSize() {
        return photoSize;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.imageCameraInItemComments, R.id.imageHeadInItemComments, R.id.imageDeleteCenterInItemComments,
                R.id.imageDeleteLeftInItemComments, R.id.imageDeleteRightInItemComments, R.id.imagePhotoCenterInItemComments,
                R.id.imagePhotoLeftInItemComments, R.id.imagePhotoRightInItemComments})
        List<ImageView> imageViewList;
        @BindViews({R.id.textCountInItemComments, R.id.textIDInItemComments, R.id.textSelectedTagAInItemComments,
                R.id.textSelectedTagBInItemComments})
        List<TextView> textViewList;
        @BindView(R.id.spinnerInItemComments)
        Spinner spinner;
        @BindView(R.id.editMessageInItemComments)
        EditText editText;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxTextView.textChanges(editText)
                    .subscribe(new Observer<CharSequence>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(CharSequence charSequence) {
                            if (charSequence.length() > 0) {
                                Log.e("length", String.valueOf(charSequence.length()));
                                MyApplication.getAppData().setEditCommentMode(true);
                            }
                            message = charSequence.toString();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            spinner.setAdapter(new ASpinner());
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    switch (i) {
                        case 0:
                            privacy = "1";
                            break;
                        case 1:
                            privacy = "2";
                            break;
                        default:
                            privacy = "0";
                            break;
                    }
                    if (MyApplication.getAppData().isEditMode()) {
                        MyApplication.getAppData().setEditCommentMode(true);
                        MyApplication.getAppData().geteEditComment().setPrivacy(privacy);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            RxView.clicks(imageViewList.get(3))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (isFromEdit.get(0)) {
                                StringBuilder stringBuilder = new StringBuilder(MyApplication.getAppData().geteEditComment().getImage().get(0));
                                int index = stringBuilder.indexOf("2");
                                stringBuilder.delete(0, index);
                                deleteImage.add(stringBuilder.toString());
                                isFromEdit.set(0, false);
                            } else {
                                MyApplication.getAppData().getSelectedPhotos().remove(0);
                            }
                            photoSize--;
                            imageViewList.get(3).setVisibility(View.GONE);
                            imageViewList.get(6).setImageResource(0);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewList.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (isFromEdit.get(1)) {
                                StringBuilder stringBuilder = new StringBuilder(MyApplication.getAppData().geteEditComment().getImage().get(1));
                                int index = stringBuilder.indexOf("2");
                                stringBuilder.delete(0, index);
                                deleteImage.add(stringBuilder.toString());
                                isFromEdit.set(1, false);
                            } else {
                                if (MyApplication.getAppData().getSelectedPhotos().size() == 1) {
                                    MyApplication.getAppData().getSelectedPhotos().remove(0);
                                } else {
                                    MyApplication.getAppData().getSelectedPhotos().remove(1);
                                }
                            }
                            photoSize--;
                            imageViewList.get(2).setVisibility(View.GONE);
                            imageViewList.get(5).setImageResource(0);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewList.get(4))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (isFromEdit.get(2)) {
                                StringBuilder stringBuilder = new StringBuilder(MyApplication.getAppData().geteEditComment().getImage().get(2));
                                int index = stringBuilder.indexOf("2");
                                stringBuilder.delete(0, index);
                                deleteImage.add(stringBuilder.toString());
                                isFromEdit.set(2, false);
                            } else {
                                if (MyApplication.getAppData().getSelectedPhotos().size() == 1) {
                                    MyApplication.getAppData().getSelectedPhotos().remove(0);
                                } else if (MyApplication.getAppData().getSelectedPhotos().size() == 2) {
                                    MyApplication.getAppData().getSelectedPhotos().remove(1);
                                } else {
                                    MyApplication.getAppData().getSelectedPhotos().remove(2);
                                }
                            }
                            photoSize--;
                            imageViewList.get(4).setVisibility(View.GONE);
                            imageViewList.get(7).setImageResource(0);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewList.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (MyApplication.getAppData().isEditMode()) {
                                MyApplication.getAppData().geteEditComment().setMessage(message);
                            } else {
                                MyApplication.getAppData().setMessage(message);
                            }
                            iaLeaveComments.handleSelectPhoto();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
