package com.switube.www.swiofficialthird.create.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.create.view.IAttractionType;
import com.switube.www.swiofficialthird.database.entity.AttractionModeEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionStyleEntity;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AttractionTypeAdapter extends RecyclerView.Adapter<AttractionTypeAdapter.ViewHolder> {
    private Context mContext;
    private List<AttractionModeEntity> mMode;
    private List<AttractionStyleEntity> mStyle;
    private IAttractionType mIAttractionType;
    public AttractionTypeAdapter(Context context, List<AttractionModeEntity> attractionModeEntities,
                                 List<AttractionStyleEntity> attractionStyleEntities,
                                 IAttractionType iAttractionType) {
        mContext = context;
        mMode = attractionModeEntities;
        mStyle = attractionStyleEntities;
        mIAttractionType = iAttractionType;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_item_attraction_type, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.textView.setText(mMode.get(0).getMmtitle_tw());
        } else if (position < 5) {
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.textView.setText(mStyle.get(position - 1).getMstitle_tw());
        } else if (position == 5) {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.textView.setText(mMode.get(1).getMmtitle_tw());
        } else if (position < 10){
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.textView.setText(mStyle.get(position - 2).getMstitle_tw());
        } else if (position == 10) {
            holder.imageView.setVisibility(View.VISIBLE);
            holder.textView.setText(mMode.get(2).getMmtitle_tw());
        } else {
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.textView.setText(mStyle.get(position - 3).getMstitle_tw());
        }
    }

    @Override
    public int getItemCount() {
        return mMode.size() + mStyle.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageHeadInItemAttractionType)
        ImageView imageView;
        @BindView(R.id.textTitleInItemAttractionType)
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(textView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (getAdapterPosition() != 0 || getAdapterPosition() != 5) {
                                if (getAdapterPosition() < 5) {
                                    mIAttractionType.handleSelectType(mStyle.get(getAdapterPosition() - 1));
                                } else if (getAdapterPosition() < 10 && getAdapterPosition() > 4){
                                    mIAttractionType.handleSelectType(mStyle.get(getAdapterPosition() - 2));
                                } else {
                                    mIAttractionType.handleSelectType(mStyle.get(getAdapterPosition() - 3));
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
