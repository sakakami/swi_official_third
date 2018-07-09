package com.switube.www.swiofficialthird.info.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.info.view.IMessageRepliesFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MessageRepliesAdapter extends RecyclerView.Adapter<MessageRepliesAdapter.ViewHolder> {
    private Context mContext;
    private List<Integer> mIsLike = new ArrayList<>();
    private IMessageRepliesFragment mIMessageRepliesFragment;
    public MessageRepliesAdapter(Context context, IMessageRepliesFragment iMessageRepliesFragment) {
        mContext = context;
        mIMessageRepliesFragment = iMessageRepliesFragment;
        mIsLike.add(0);
        mIsLike.add(0);
        mIsLike.add(0);
        mIsLike.add(0);
        mIsLike.add(0);
        mIsLike.add(0);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_item_message_replies, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position == 0) {
            holder.mlayouts.get(0).setVisibility(View.VISIBLE);
            holder.mlayouts.get(1).setVisibility(View.GONE);
            if (mIsLike.get(position) == 0) {
                holder.mImageViews.get(1).setImageResource(R.drawable.unlike_v1_1);
                holder.mImageViews.get(2).setImageResource(R.drawable.like_v1_1);
            } else if (mIsLike.get(position) == 1) {
                holder.mImageViews.get(1).setImageResource(R.drawable.unlike_v1_1);
                holder.mImageViews.get(2).setImageResource(R.drawable.like_on_v1_2);
            } else {
                holder.mImageViews.get(1).setImageResource(R.drawable.unlike_on_v1_1);
                holder.mImageViews.get(2).setImageResource(R.drawable.like_v1_1);
            }
        } else {
            holder.mlayouts.get(0).setVisibility(View.GONE);
            holder.mlayouts.get(1).setVisibility(View.VISIBLE);
            if (mIsLike.get(position) == 0) {
                holder.mImageViewsTwo.get(1).setImageResource(R.drawable.like_v1_1);
                holder.mImageViewsTwo.get(2).setImageResource(R.drawable.unlike_v1_1);
            } else if (mIsLike.get(position) == 1) {
                holder.mImageViewsTwo.get(1).setImageResource(R.drawable.like_on_v1_2);
                holder.mImageViewsTwo.get(2).setImageResource(R.drawable.unlike_v1_1);
            } else {
                holder.mImageViewsTwo.get(1).setImageResource(R.drawable.like_v1_1);
                holder.mImageViewsTwo.get(2).setImageResource(R.drawable.unlike_on_v1_1);
            }
        }
    }

    private int count = 6;
    @Override
    public int getItemCount() {
        return count;
    }

    public void handlePlusCount() {
        count++;
        mIsLike.add(0);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.textNameTopInItemMessageReplies, R.id.textMessageInItemMessageReplies, R.id.textDateTopInItemMessageReplies,
                R.id.textReplyTopInItemMessageReplies, R.id.textCountTopInItemMessageReplies,})
        List<TextView> mTextViews;
        @BindViews({R.id.textNameInItemMessageReplies,R.id.textMessageInItemMessageReplies, R.id.textDateInItemMessageReplies,
                R.id.textReplyInItemMessageReplies, R.id.textCountInItemMessageReplies})
        List<TextView> mTextViewsTwo;
        @BindViews({R.id.imageHeadTopInItemMessageReplies, R.id.imageUnlikeTopInItemMessageReplies, R.id.imageLikeTopInItemMessageReplies,})
        List<ImageView> mImageViews;
        @BindViews({R.id.imageHeadInItemMessageReplies, R.id.imageLikeInItemMessageReplies, R.id.imageUnlikeInItemMessageReplies})
        List<ImageView> mImageViewsTwo;
        @BindViews({R.id.layoutHeadInItemMessageReplies, R.id.layoutContentInItemMessageReplies})
        List<RelativeLayout> mlayouts;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(mImageViews.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIsLike.set(0, 2);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViews.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIsLike.set(0, 1);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsTwo.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIsLike.set(getAdapterPosition(), 1);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViewsTwo.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIsLike.set(getAdapterPosition(), 2);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViewsTwo.get(3))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIMessageRepliesFragment.handleFocusEditView();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
