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
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.database.entity.AttractionClassEntity;
import com.switube.www.landmark2018test.database.entity.AttractionItemEntity;
import com.switube.www.landmark2018test.database.entity.AttractionTermEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AFeatures extends RecyclerView.Adapter<AFeatures.ViewHolder> {
    private List<AttractionClassEntity> mClassList;
    private List<AttractionTermEntity> mTermList;
    private List<AttractionItemEntity> mItemList;
    private String mTitle;
    private boolean isCreate;
    public AFeatures(List<AttractionClassEntity> classEntities,
                     List<AttractionTermEntity> termEntities,
                     List<AttractionItemEntity> itemEntities, String title, boolean isCreate) {
        mClassList = classEntities;
        mTermList = termEntities;
        mItemList = itemEntities;
        mTitle = title;
        this.isCreate = isCreate;
        init();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_select_service, parent, false));
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
            holder.mImageView.setImageResource(R.drawable.check_w_v1_1);
        } else {
            holder.mViews.get(0).setBackgroundResource(R.drawable.bg_gray_2_surround);
            holder.mImageView.setImageResource(R.drawable.add_v1_1);
        }
    }

    @Override
    public int getItemCount() {
        return serviceItemEntities.size();
    }

    public List<EFeatures> getServiceItemEntities() {
        return serviceItemEntities;
    }

    private List<EFeatures> serviceItemEntities = new ArrayList<>();
    private void init() {
        if (MyApplication.getAppData().isUsingSettingData()) {
            serviceItemEntities = MyApplication.getAppData().geteFeaturesListForSetting();
        } else {
            serviceItemEntities.add(new EFeatures("", "", "", mTitle, true, false, false, false, "t"));
            int size = mClassList.size();
            String title;
            for (int i = 0; i < size; i++) {
                switch (MyApplication.getLanguageIndex()) {
                    case 1:
                        title = mClassList.get(i).getMctitle_tw();
                        break;
                    case 2:
                        title = mClassList.get(i).getMctitle_ch();
                        break;
                    case 3:
                        title = mClassList.get(i).getMctitle_jp();
                        break;
                    default:
                        title = mClassList.get(i).getMctitle_en();
                        break;
                }
                if (i == 0) {
                    serviceItemEntities.add(new EFeatures(
                            mClassList.get(i).getMscid(), mClassList.get(i).getMcid(), "", title,
                            true, true, true, true, "class"));
                } else {
                    serviceItemEntities.add(new EFeatures(
                            mClassList.get(i).getMscid(), mClassList.get(i).getMcid(), "", title,
                            true, true, false, true, "class"));
                }
            }
            serviceItemEntities.add(new EFeatures("", "", "", "", false, false, false, false, "n"));
            size = mTermList.size();
            int size2 = mItemList.size();
            for (int i = 0; i < size; i++) {
                switch (MyApplication.getLanguageIndex()) {
                    case 1:
                        title = mTermList.get(i).getMtitle_tw();
                        break;
                    case 2:
                        title = mTermList.get(i).getMtitle_ch();
                        break;
                    case 3:
                        title = mTermList.get(i).getMtitle_jp();
                        break;
                    default:
                        title = mTermList.get(i).getMtitle_en();
                        break;
                }
                serviceItemEntities.add(new EFeatures(mTermList.get(i).getMtid(), "", "", title,
                        true, false, false, false, "term"));
                for (int j = 0; j < size2; j++) {
                    switch (MyApplication.getLanguageIndex()) {
                        case 1:
                            title = mItemList.get(j).getMititle_tw();
                            break;
                        case 2:
                            title = mItemList.get(j).getMititle_ch();
                            break;
                        case 3:
                            title = mItemList.get(j).getMititle_jp();
                            break;
                        default:
                            title = mItemList.get(j).getMititle_en();
                            break;
                    }
                    if (mItemList.get(j).getMtid().equals(mTermList.get(i).getMtid())) {
                        serviceItemEntities.add(new EFeatures(mItemList.get(j).getMstid(), mItemList.get(j).getMiid(), mItemList.get(i).getMtid(),
                                title, true, true, false, false, "item" + String.valueOf(i)));
                    }
                }
                serviceItemEntities.add(new EFeatures("", "", "", "", false, false, false, false, "n"));
            }
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
                                if (isCreate) {
                                    if (serviceItemEntities.get(getAdapterPosition()).isSingle()) {
                                        int size = serviceItemEntities.size();
                                        for (int i = 0; i < size; i++) {
                                            if (serviceItemEntities.get(i).isSingle()) {
                                                serviceItemEntities.get(i).setSelect(false);
                                            }
                                        }
                                        serviceItemEntities.get(getAdapterPosition()).setSelect(true);
                                    } else {
                                        if (serviceItemEntities.get(getAdapterPosition()).isSelect()) {
                                            serviceItemEntities.get(getAdapterPosition()).setSelect(false);
                                        } else {
                                            serviceItemEntities.get(getAdapterPosition()).setSelect(true);
                                        }
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
