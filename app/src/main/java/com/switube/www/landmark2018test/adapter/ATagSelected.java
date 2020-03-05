package com.switube.www.landmark2018test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.database.entity.TagQBAEntity;
import com.switube.www.landmark2018test.database.entity.TagQBNEntity;
import com.switube.www.landmark2018test.adapter.callback.IATag;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ATagSelected extends RecyclerView.Adapter<ATagSelected.ViewHolder> {
    public static List<TagQBNEntity> tagQBNEntities = new ArrayList<>();
    public static List<TagQBAEntity> tagQBAEntities = new ArrayList<>();
    private IATag iATag;
    public ATagSelected(IATag iATag) {
        this.iATag = iATag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_tiem_selected, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (tagQBNEntities.size() == tagQBAEntities.size()) {
            holder.mTextViews.get(0).setText(tagQBNEntities.get(position).getNorm());
            holder.mTextViews.get(1).setVisibility(View.VISIBLE);
            holder.mTextViews.get(1).setText(tagQBAEntities.get(position).getAssess());
            holder.mImageViews.get(1).setVisibility(View.VISIBLE);
        } else {
            holder.mTextViews.get(0).setText(tagQBNEntities.get(position).getNorm());
            if (position < tagQBAEntities.size()) {
                holder.mTextViews.get(1).setVisibility(View.VISIBLE);
                holder.mTextViews.get(1).setText(tagQBAEntities.get(position).getAssess());
                holder.mImageViews.get(1).setVisibility(View.VISIBLE);
            } else {
                holder.mTextViews.get(1).setVisibility(View.GONE);
                holder.mImageViews.get(1).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return tagQBNEntities.size();
    }

    public void handleAddTagA(TagQBNEntity tagQBNEntity) {
        tagQBNEntities.add(tagQBNEntity);
        notifyDataSetChanged();
    }

    public void handleAddTagB(TagQBAEntity tagQBAEntity) {
        tagQBAEntities.add(tagQBAEntity);
        notifyDataSetChanged();
    }

    public void refreshAdapter(List<TagQBNEntity> tagQBNEntityList, List<TagQBAEntity> tagQBAEntityList) {
        tagQBAEntities = tagQBAEntityList;
        tagQBNEntities = tagQBNEntityList;
        notifyDataSetChanged();
    }

    public List<TagQBNEntity> getTitleA() {
        return tagQBNEntities;
    }

    public List<TagQBAEntity> getTitleB() {
        return tagQBAEntities;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.textTitleAInItemSelected, R.id.textTitleBInItemSelected})
        List<TextView> mTextViews;
        @BindViews({R.id.imageCleanAInItemSelected, R.id.imageCleanBInItemSelected})
        List<ImageView> mImageViews;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(mTextViews.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClickItem(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViews.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClickItem(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(1))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClickItem(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViews.get(1))
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClickItem(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }

        private void handleClickItem(int position) {
            if (tagQBNEntities.size() == tagQBAEntities.size()) {
                tagQBNEntities.remove(position);
                tagQBAEntities.remove(position);
            } else {
                tagQBNEntities.remove(position);
                iATag.handleDefault();
            }
            MyApplication.getAppData().setEditTagMode(true);
            notifyDataSetChanged();
        }
    }
}
