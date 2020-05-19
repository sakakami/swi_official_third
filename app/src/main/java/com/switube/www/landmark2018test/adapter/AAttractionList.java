package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAAttractionList;
import com.switube.www.landmark2018test.gson.GAttractionListData;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AAttractionList extends RecyclerView.Adapter<AAttractionList.ViewHolder> {
    private IAAttractionList iaAttractionList;
    private GAttractionListData gAttractionListData;
    private List<String> mDistance;
    private int size;
    public AAttractionList(IAAttractionList iaAttractionList) {
        this.iaAttractionList = iaAttractionList;
        size = 0;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_create, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViews.get(0).setText(gAttractionListData.getData().get(position).getPlace());
        holder.textViews.get(1).setText(mDistance.get(position));
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void refreshData(GAttractionListData gAttractionListData, List<String> distance) {
        this.gAttractionListData = gAttractionListData;
        mDistance = distance;
        size = gAttractionListData.getData().size();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imagePhotoInItemCreate)
        ImageView mImagePhoto;
        @BindViews({R.id.textTitleInItemCreate, R.id.textMessageInItemCreate})
        List<TextView> textViews;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(textViews.get(0)).subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {}

                @Override
                public void onNext(Object o) {
                    iaAttractionList.handleSwitch(
                            gAttractionListData.getData().get(getAdapterPosition()).getMsid(),
                            gAttractionListData.getData().get(getAdapterPosition()).getSpid(),
                            gAttractionListData.getData().get(getAdapterPosition()).getPlace());
                }

                @Override
                public void onError(Throwable e) {}

                @Override
                public void onComplete() {}
            });
        }
    }
}
