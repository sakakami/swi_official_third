package com.switube.www.landmark2018test.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IATag;
import com.switube.www.landmark2018test.database.entity.TagQBAEntity;
import com.switube.www.landmark2018test.database.entity.TagQBNEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ATag extends RecyclerView.Adapter<ATag.ViewHolder> {
    private IATag iATag;
    public ATag(IATag iATag) {
        this.iATag = iATag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (isTagA) {
            holder.mTextTitle.setText(tagQBNEntities.get(position).getNorm());
        } else {
            holder.mTextTitle.setText(tagQBAEntities.get(position).getAssess());
        }
    }

    private List<TagQBNEntity> tagQBNEntities;
    public void setTagA(List<TagQBNEntity> tagQBNEntities) {
        this.tagQBNEntities = tagQBNEntities;
        notifyDataSetChanged();
    }

    private List<TagQBAEntity> tagQBAEntities;
    public void setTagB(List<TagQBAEntity> tagQBAEntities) {
        this.tagQBAEntities = tagQBAEntities;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (isTagA) {
            if (tagQBNEntities != null) {
                return tagQBNEntities.size();
            } else {
                return 0;
            }
        } else {
            if (tagQBAEntities != null) {
                return tagQBAEntities.size();
            } else {
                return 0;
            }
        }
    }

    public void handleDefault() {
        isTagA = true;
        notifyDataSetChanged();
    }

    public boolean isTagA() {
        return isTagA;
    }

    private boolean isTagA = true;
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textTitleInItemTag) TextView mTextTitle;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(mTextTitle)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (getAdapterPosition() >= 0) {
                                iATag.handleTagSelected();
                                if (isTagA) {
                                    iATag.handleAddTagA(tagQBNEntities.get(getAdapterPosition()));
                                    isTagA = false;
                                    notifyDataSetChanged();
                                } else {
                                    iATag.handleAddTagB(tagQBAEntities.get(getAdapterPosition()));
                                    isTagA = true;
                                    notifyDataSetChanged();
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
