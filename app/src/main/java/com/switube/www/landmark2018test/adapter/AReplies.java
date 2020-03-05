package com.switube.www.landmark2018test.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAReplies;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AReplies extends RecyclerView.Adapter<AReplies.ViewHolder> {
    private List<GInfoData.Reply> replyList;
    private IAReplies iaReplies;
    public AReplies(List<GInfoData.Reply> replyList, IAReplies iaReplies) {
        this.replyList = replyList;
        this.iaReplies = iaReplies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_replies, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(MyApplication.getInstance())
                .load(Uri.parse(AppConstant.BASE_URL2 + replyList.get(position).getMaimg()))
                .into(holder.circleImageView);
        holder.textViews.get(0).setText(replyList.get(position).getManame());
        holder.textViews.get(1).setText(replyList.get(position).getTxt());
        holder.textViews.get(2).setText(replyList.get(position).getTime_txt());
        holder.textViews.get(4).setText(replyList.get(position).getCount());
        if (replyList.get(position).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
            holder.imageViews.get(2).setVisibility(View.VISIBLE);
        } else {
            holder.imageViews.get(2).setVisibility(View.GONE);
        }
        switch (replyList.get(position).getLike()) {
            case "1":
                holder.imageViews.get(0).setImageResource(R.drawable.like_fornote_on_v11);
                holder.imageViews.get(1).setImageResource(R.drawable.dislike_fornote_v11);
                break;
            case "2":
                holder.imageViews.get(0).setImageResource(R.drawable.like_fornote_v11);
                holder.imageViews.get(1).setImageResource(R.drawable.dislike_fornote_on_v11);
                break;
            default:
                holder.imageViews.get(0).setImageResource(R.drawable.like_fornote_v11);
                holder.imageViews.get(1).setImageResource(R.drawable.dislike_fornote_v11);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return replyList.size();
    }

    public void refreshAdapter(List<GInfoData.Reply> replyList) {
        this.replyList = replyList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imagePhotoHeadInItemReplies)
        CircleImageView circleImageView;
        @BindViews({R.id.imageGoodInItemReplies, R.id.imageBadInItemReplies, R.id.imageMenuInItemReplies})
        List<ImageView> imageViews;
        @BindViews({R.id.textNameInItemReplies, R.id.textMessageInItemReplies, R.id.textDateInItemReplies,
                R.id.textReplyInItemReplies, R.id.textCountInItemReplies})
        List<TextView> textViews;
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(imageViews.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (!replyList.get(getAdapterPosition()).getLike().equals("1")) {
                                iaReplies.handleClickLike("1", getAdapterPosition());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViews.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (!replyList.get(getAdapterPosition()).getLike().equals("2")) {
                                iaReplies.handleClickLike("2", getAdapterPosition());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViews.get(3))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaReplies.handleFocus();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViews.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            showMenu(itemView.getContext());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }

        private void showMenu(Context context) {
            PopupMenu popupMenu = new PopupMenu(context, imageViews.get(2));
            popupMenu.getMenuInflater().inflate(R.menu.menu_info_reply, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.menuEditInReply) {
                        iaReplies.handleEdit(getAdapterPosition());
                    } else {
                        iaReplies.handleDelete(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}
