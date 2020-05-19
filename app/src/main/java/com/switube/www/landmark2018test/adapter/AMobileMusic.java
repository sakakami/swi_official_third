package com.switube.www.landmark2018test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAMobileMusic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AMobileMusic extends RecyclerView.Adapter<AMobileMusic.ViewHolder> {
    private IAMobileMusic iaMobileMusic;
    private List<String> titleList = new ArrayList<>();
    private List<String> urlList = new ArrayList<>();
    public AMobileMusic (IAMobileMusic iaMobileMusic) {
        this.iaMobileMusic = iaMobileMusic;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_mobile_music, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (MyApplication.getAppData().isPhotoMode()) {
            holder.layoutList.get(0).setVisibility(View.VISIBLE);
            holder.layoutList.get(1).setVisibility(View.GONE);
            Glide.with(MyApplication.getInstance())
                    .load(urlList.get(position))
                    .into(holder.imageViewListG.get(0));
            holder.textViewListG.get(0).setText(titleList.get(position));
            if (MyApplication.getAppData().getMusicMode() != 0) {
                holder.textViewListG.get(1).setText(countList.get(position));
                holder.imageViewListG.get(1).setVisibility(View.VISIBLE);
                holder.textViewListG.get(1).setVisibility(View.VISIBLE);
                if (loveList.get(position).equals("1")) {
                    holder.imageViewListG.get(1).setImageResource(R.drawable.love_on_2x);
                } else {
                    holder.imageViewListG.get(1).setImageResource(R.drawable.love_off_2x);
                }
            } else {
                if (isSearch) {
                    holder.textViewListG.get(1).setText(countList.get(position));
                    holder.imageViewListG.get(1).setVisibility(View.VISIBLE);
                    holder.textViewListG.get(1).setVisibility(View.VISIBLE);
                    if (loveList.get(position).equals("1")) {
                        holder.imageViewListG.get(1).setImageResource(R.drawable.love_on_2x);
                    } else {
                        holder.imageViewListG.get(1).setImageResource(R.drawable.love_off_2x);
                    }
                } else {
                    holder.imageViewListG.get(1).setVisibility(View.GONE);
                    holder.textViewListG.get(1).setVisibility(View.GONE);
                }
            }
        } else {
            holder.layoutList.get(0).setVisibility(View.GONE);
            holder.layoutList.get(1).setVisibility(View.VISIBLE);
            holder.textViewListL.get(0).setText(titleList.get(position));
            if (MyApplication.getAppData().getMusicMode() != 0) {
                holder.textViewListL.get(1).setText(countList.get(position));
                holder.imageViewL.setVisibility(View.VISIBLE);
                holder.textViewListL.get(1).setVisibility(View.VISIBLE);
                if (loveList.get(position).equals("1")) {
                    holder.imageViewL.setImageResource(R.drawable.love_on_2x);
                } else {
                    holder.imageViewL.setImageResource(R.drawable.love_off_2x);
                }
            } else {
                if (isSearch) {
                    holder.textViewListL.get(1).setText(countList.get(position));
                    holder.imageViewL.setVisibility(View.VISIBLE);
                    holder.textViewListL.get(1).setVisibility(View.VISIBLE);
                    if (loveList.get(position).equals("1")) {
                        holder.imageViewL.setImageResource(R.drawable.love_on_2x);
                    } else {
                        holder.imageViewL.setImageResource(R.drawable.love_off_2x);
                    }
                } else {
                    holder.imageViewL.setVisibility(View.GONE);
                    holder.textViewListL.get(1).setVisibility(View.GONE);
                }
            }
        }

    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public void refreshData(List<String> titleList, List<String> urlList) {
        this.titleList = titleList;
        this.urlList = urlList;
        isSearch = false;
        notifyDataSetChanged();
    }

    private List<String> countList = new ArrayList<>();
    private List<String> loveList = new ArrayList<>();
    private boolean isSearch = false;
    public void refreshData(List<String> titleList, List<String> urlList, List<String> countList, List<String> loveList, boolean isSearch) {
        this.titleList = titleList;
        this.urlList = urlList;
        this.countList = countList;
        this.loveList = loveList;
        this.isSearch = isSearch;
        notifyDataSetChanged();
    }

    public void refreshData(int index, boolean isAdd, boolean isSearch) {
        if (MyApplication.getAppData().getMusicMode() == 2 && !isSearch) {
            titleList.remove(index);
            loveList.remove(index);
            urlList.remove(index);
        } else {
            if (isAdd) {
                loveList.set(index, "1");
            } else {
                loveList.set(index, "0");
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.layoutGrid, R.id.layoutLinear})
        List<RelativeLayout> layoutList;
        @BindViews({R.id.imagePictureGInItemMobileMusic, R.id.imageLikeGInItemMobileMusic})
        List<ImageView> imageViewListG;
        @BindViews({R.id.textTitleGInItemMobileMusic, R.id.textCountsGInItemMobileMusic})
        List<TextView> textViewListG;
        @BindView(R.id.imageLikeLInItemMobileMusic)
        ImageView imageViewL;
        @BindViews({R.id.textTitleLInItemMobileMusic, R.id.textCountsLInItemMobileMusic})
        List<TextView> textViewListL;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if (MyApplication.getAppData().getMusicMode() == 0) {
                imageViewListG.get(1).setVisibility(View.GONE);
                textViewListG.get(1).setVisibility(View.GONE);
            }
            RxView.clicks(imageViewListG.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaMobileMusic.handleClick(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewListG.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaMobileMusic.handleClick(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewListG.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaMobileMusic.handleClickLove(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewListL.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaMobileMusic.handleClick(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewL)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaMobileMusic.handleClickLove(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
