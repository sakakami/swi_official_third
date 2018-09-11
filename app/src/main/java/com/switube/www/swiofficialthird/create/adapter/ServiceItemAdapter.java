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
import com.switube.www.swiofficialthird.create.ServiceItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionClassEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionItemEntity;
import com.switube.www.swiofficialthird.database.entity.AttractionTermEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ServiceItemAdapter extends RecyclerView.Adapter<ServiceItemAdapter.ViewHolder> {
    private Context mContext;
    private List<AttractionClassEntity> mClassList;
    private List<AttractionTermEntity> mTermList;
    private List<AttractionItemEntity> mItemList;
    private String mTitle;
    public ServiceItemAdapter(Context context, List<AttractionClassEntity> classEntities,
                              List<AttractionTermEntity> termEntities,
                              List<AttractionItemEntity> itemEntities, String title) {
        mContext = context;
        mClassList = classEntities;
        mTermList = termEntities;
        mItemList = itemEntities;
        mTitle = title;
        init();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_item_select_service, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (serviceItemEntities.get(position).isCanShow()) {
            if (serviceItemEntities.get(position).isCanSelect()) {
                holder.mImageView.setVisibility(View.VISIBLE);
            } else {
                holder.mImageView.setVisibility(View.GONE);
            }
            if (serviceItemEntities.get(position).isCanSelect()) {
                holder.mViews.get(0).setVisibility(View.VISIBLE);
            } else {
                holder.mViews.get(0).setVisibility(View.INVISIBLE);
            }
            holder.mViews.get(1).setVisibility(View.GONE);
            holder.mTextView.setVisibility(View.VISIBLE);
            holder.mTextView.setText(serviceItemEntities.get(position).getTitle());
        } else {
            holder.mImageView.setVisibility(View.INVISIBLE);
            holder.mTextView.setVisibility(View.INVISIBLE);
            holder.mViews.get(0).setVisibility(View.INVISIBLE);
            holder.mViews.get(1).setVisibility(View.VISIBLE);
        }

        if (serviceItemEntities.get(position).isSelect()) {
            holder.mViews.get(0).setBackgroundResource(R.drawable.bg_blue_full);
        } else {
            holder.mViews.get(0).setBackgroundResource(R.drawable.bg_gray_2_urround);
        }
    }

    @Override
    public int getItemCount() {
        return serviceItemEntities.size();
    }

    public List<ServiceItemEntity> getServiceItemEntities() {
        return serviceItemEntities;
    }

    private List<ServiceItemEntity> serviceItemEntities = new ArrayList<>();
    private void init() {
        serviceItemEntities.add(new ServiceItemEntity("", "", mTitle, true, false, false, false, "t"));
        //serviceItemEntities.add(new ServiceItemEntity("", "", "", false, false, false, false, "n"));
        int size = mClassList.size();
        for (int i = 0; i < size; i++) {
            serviceItemEntities.add(new ServiceItemEntity(
                    mClassList.get(i).getMscid(), mClassList.get(i).getMcid(), mClassList.get(i).getMctitle_tw(),
                    true, true, false, true, "class"));
        }
        serviceItemEntities.add(new ServiceItemEntity("", "", "", false, false, false, false, "n"));
        size = mTermList.size();
        int size2 = mItemList.size();
        for (int i = 0; i < size; i++) {
            serviceItemEntities.add(new ServiceItemEntity(mTermList.get(i).getMtid(), "", mTermList.get(i).getMtitle_tw(),
                    true, false, false, false, "term"));
            //serviceItemEntities.add(new ServiceItemEntity("", "", "", false, false, false, false, "n"));
            int k = 0;
            for (int j = 0; j < size2; j++) {
                if (mItemList.get(j).getMtid().equals(mTermList.get(i).getMtid())) {
                    serviceItemEntities.add(new ServiceItemEntity(mItemList.get(j).getMstid(), mItemList.get(j).getMiid(),
                            mItemList.get(j).getMititle_tw(), true, true, false, false, "item" + String.valueOf(i)));
                    k++;
                }
            }
            serviceItemEntities.add(new ServiceItemEntity("", "", "", false, false, false, false, "n"));
            /*if (k % 2 == 1) {
                serviceItemEntities.add(new ServiceItemEntity("", "", "", false, false, false, false, "n"));
            }*/
        }
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textContentInItemSelectService)
        TextView mTextView;
        @BindView(R.id.imageHeadInItemSelectService)
        ImageView mImageView;
        @BindViews({R.id.viewBarInItemSelectService, R.id.viewBar2InItemSelectService})
        List<View> mViews;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(mViews.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (serviceItemEntities.get(getAdapterPosition()).isCanSelect()) {
                                if (getAdapterPosition() < mClassList.size() + 2) {
                                    int time = -1;
                                    boolean canLoop = true;
                                    int size = mClassList.size() + 2;
                                    do {
                                        time++;
                                        if (serviceItemEntities.get(time).isSelect()) {
                                            canLoop = false;
                                        }
                                    } while (canLoop && time < size);
                                    if (getAdapterPosition() != time) {
                                        serviceItemEntities.get(getAdapterPosition()).setSelect(true);
                                        serviceItemEntities.get(time).setSelect(false);
                                    }
                                } else {
                                    if (serviceItemEntities.get(getAdapterPosition()).isSelect()) {
                                        serviceItemEntities.get(getAdapterPosition()).setSelect(false);
                                    } else {
                                        serviceItemEntities.get(getAdapterPosition()).setSelect(true);
                                    }
                                }
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
