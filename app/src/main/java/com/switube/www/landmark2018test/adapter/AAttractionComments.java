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
import com.switube.www.landmark2018test.adapter.callback.IAAttractionComments;
import com.switube.www.landmark2018test.gson.GCommentsData;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindViews;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AAttractionComments extends RecyclerView.Adapter<AAttractionComments.ViewHolder> {
    private List<GInfoData.Message> messageList;
    private IAAttractionComments iaAttractionComments;
    public AAttractionComments(List<GInfoData.Message> messageList, IAAttractionComments iaAttractionComments) {
        this.messageList = messageList;
        this.iaAttractionComments = iaAttractionComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_all_message, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(MyApplication.getInstance())
                .load(Uri.parse(AppConstant.BASE_URL2 + messageList.get(position).getMaimg()))
                .into(holder.circleImageViews.get(0));
        holder.textViews.get(0).setText(messageList.get(position).getManame());
        holder.textViews.get(1).setText(messageList.get(position).getTxt());
        holder.textViews.get(2).setText(messageList.get(position).getTime_txt());
        holder.textViews.get(4).setText(messageList.get(position).getCount());
        if (messageList.get(position).getMaid().equals(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null"))) {
            holder.imageViews.get(2).setVisibility(View.VISIBLE);
        } else {
            holder.imageViews.get(2).setVisibility(View.GONE);
        }
        switch (messageList.get(position).getLike()) {
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
        switch (messageList.get(position).getReply().size()) {
            case 0:
                holder.textViews.get(5).setVisibility(View.GONE);
                holder.textViews.get(6).setVisibility(View.GONE);
                holder.textViews.get(7).setVisibility(View.GONE);
                holder.textViews.get(8).setVisibility(View.GONE);
                holder.textViews.get(9).setVisibility(View.GONE);
                holder.circleImageViews.get(1).setVisibility(View.GONE);
                holder.circleImageViews.get(2).setVisibility(View.GONE);
                holder.views.get(1).setVisibility(View.GONE);
                holder.views.get(2).setVisibility(View.GONE);
                break;
            case 1:
                holder.textViews.get(5).setVisibility(View.GONE);
                holder.textViews.get(6).setVisibility(View.VISIBLE);
                holder.textViews.get(7).setVisibility(View.VISIBLE);
                holder.textViews.get(8).setVisibility(View.GONE);
                holder.textViews.get(9).setVisibility(View.GONE);
                holder.circleImageViews.get(1).setVisibility(View.VISIBLE);
                holder.circleImageViews.get(2).setVisibility(View.GONE);
                holder.views.get(1).setVisibility(View.VISIBLE);
                holder.views.get(2).setVisibility(View.GONE);
                holder.textViews.get(6).setText(messageList.get(position).getReply().get(0).getManame());
                holder.textViews.get(7).setText(messageList.get(position).getReply().get(0).getTxt());
                Glide.with(MyApplication.getInstance())
                        .load(Uri.parse(AppConstant.BASE_URL2 + messageList.get(position).getReply().get(0).getMaimg()))
                        .into(holder.circleImageViews.get(1));
                break;
            case 2:
                holder.textViews.get(5).setVisibility(View.GONE);
                holder.textViews.get(6).setVisibility(View.VISIBLE);
                holder.textViews.get(7).setVisibility(View.VISIBLE);
                holder.textViews.get(8).setVisibility(View.VISIBLE);
                holder.textViews.get(9).setVisibility(View.VISIBLE);
                holder.circleImageViews.get(1).setVisibility(View.VISIBLE);
                holder.circleImageViews.get(2).setVisibility(View.VISIBLE);
                holder.views.get(1).setVisibility(View.VISIBLE);
                holder.views.get(2).setVisibility(View.VISIBLE);
                holder.textViews.get(6).setText(messageList.get(position).getReply().get(0).getManame());
                holder.textViews.get(7).setText(messageList.get(position).getReply().get(0).getTxt());
                Glide.with(MyApplication.getInstance())
                        .load(Uri.parse(AppConstant.BASE_URL2 + messageList.get(position).getReply().get(0).getMaimg()))
                        .into(holder.circleImageViews.get(1));
                holder.textViews.get(8).setText(messageList.get(position).getReply().get(1).getManame());
                holder.textViews.get(9).setText(messageList.get(position).getReply().get(1).getTxt());
                Glide.with(MyApplication.getInstance())
                        .load(Uri.parse(AppConstant.BASE_URL2 + messageList.get(position).getReply().get(1).getMaimg()))
                        .into(holder.circleImageViews.get(2));
                break;
            default:
                holder.textViews.get(5).setVisibility(View.GONE);
                holder.textViews.get(6).setVisibility(View.VISIBLE);
                holder.textViews.get(7).setVisibility(View.VISIBLE);
                holder.textViews.get(8).setVisibility(View.VISIBLE);
                holder.textViews.get(9).setVisibility(View.VISIBLE);
                holder.circleImageViews.get(1).setVisibility(View.VISIBLE);
                holder.circleImageViews.get(2).setVisibility(View.VISIBLE);
                holder.views.get(1).setVisibility(View.VISIBLE);
                holder.views.get(2).setVisibility(View.VISIBLE);
                holder.textViews.get(6).setText(messageList.get(position).getReply().get(0).getManame());
                holder.textViews.get(7).setText(messageList.get(position).getReply().get(0).getTxt());
                Glide.with(MyApplication.getInstance())
                        .load(Uri.parse(AppConstant.BASE_URL2 + messageList.get(position).getReply().get(0).getMaimg()))
                        .into(holder.circleImageViews.get(1));
                holder.textViews.get(8).setText(messageList.get(position).getReply().get(1).getManame());
                holder.textViews.get(9).setText(messageList.get(position).getReply().get(1).getTxt());
                Glide.with(MyApplication.getInstance())
                        .load(Uri.parse(AppConstant.BASE_URL2 + messageList.get(position).getReply().get(1).getMaimg()))
                        .into(holder.circleImageViews.get(2));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public void refreshAdapter(GCommentsData gCommentsData) {
        messageList = gCommentsData.getMsgData();
        notifyDataSetChanged();
    }

    public void refreshAdapter(List<GInfoData.Message> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.imagePhotoHeadInItemAllMessage, R.id.imagePhotoAInItemAllMessage, R.id.imagePhotoBInItemAllMessage})
        List<CircleImageView> circleImageViews;
        @BindViews({R.id.imageGoodInItemAllMessage, R.id.imageBadInItemAllMessage, R.id.imageMenuInItemAllMessage})
        List<ImageView> imageViews;
        @BindViews({R.id.textNameInItemAllMessage, R.id.textMessageInItemAllMessage, R.id.textDateInItemAllMessage,
                R.id.textReplyInItemAllMessage, R.id.textCountInItemAllMessage, R.id.textMoreInItemAllMessage,
                R.id.textNameAInItemAllMessage, R.id.textMessageAInItemAllMessage, R.id.textNameBInItemAllMessage,
                R.id.textMessageBInItemAllMessage})
        List<TextView> textViews;
        @BindViews({R.id.viewBarInItemAllMessage, R.id.viewBarAInItemAllMessage, R.id.viewBarBInItemAllMessage})
        List<View> views;
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(textViews.get(5))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClick(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(views.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClick(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(views.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClick(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(views.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            handleClick(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViews.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                iaAttractionComments.handleSignIn();
                            } else {
                                if (!messageList.get(getAdapterPosition()).getLike().equals("1")) {
                                    iaAttractionComments.handleClickLike(getAdapterPosition(), "1");
                                }
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
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                iaAttractionComments.handleSignIn();
                            } else {
                                if (!messageList.get(getAdapterPosition()).getLike().equals("2")) {
                                    iaAttractionComments.handleClickLike(getAdapterPosition(), "2");
                                }
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
                            handleClick(getAdapterPosition());
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

        private void handleClick(int index) {
            iaAttractionComments.handleClickMessage(index);
        }

        private void showMenu(Context context) {
            PopupMenu popupMenu = new PopupMenu(context, imageViews.get(2));
            popupMenu.getMenuInflater().inflate(R.menu.menu_info_reply, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.menuEditInReply) {
                        iaAttractionComments.handleEditComments(getAdapterPosition());
                    } else {
                        iaAttractionComments.handleDelComments(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}
