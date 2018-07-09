package com.switube.www.swiofficialthird.info.adapter;

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
import com.switube.www.swiofficialthird.info.view.IMessageListFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {
    private Context mContext;
    private IMessageListFragment mIMessageListFragment;
    private List<Integer> isLike = new ArrayList<>();
    public MessageListAdapter(Context context, IMessageListFragment iMessageListFragment) {
        mContext = context;
        mIMessageListFragment = iMessageListFragment;
        isLike.add(0);
        isLike.add(0);
        isLike.add(0);
        isLike.add(0);
        isLike.add(0);
        isLike.add(0);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_item_message_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (isLike.get(position) == 0) {
            holder.mImageViews.get(1).setImageResource(R.drawable.unlike_v1_1);
            holder.mImageViews.get(2).setImageResource(R.drawable.like_v1_1);
        } else if (isLike.get(position) == 1) {
            holder.mImageViews.get(1).setImageResource(R.drawable.unlike_v1_1);
            holder.mImageViews.get(2).setImageResource(R.drawable.like_on_v1_2);
        } else {
            holder.mImageViews.get(1).setImageResource(R.drawable.unlike_on_v1_1);
            holder.mImageViews.get(2).setImageResource(R.drawable.like_v1_1);
        }
    }

    private int count = 6;
    @Override
    public int getItemCount() {
        return count;
    }

    public void handlePlusCount() {
        count++;
        isLike.add(0);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindViews({R.id.textTitleInItemMessageList, R.id.textMessageInItemMessageList, R.id.textDateInItemMessageList,
                R.id.textReplyInItemMessageList, R.id.textCountInItemMessageList, R.id.textMoreInItemMessageList,
                R.id.textNameOneInItemMessageList, R.id.textContentOneInItemMessageList, R.id.textNameTwoInItemMessageList,
                R.id.textContentTwoInItemMessageList, R.id.textNameThreeInItemMessageList, R.id.textContentThreeInItemMessageList})
        List<TextView> mTextViews;
        @BindViews({R.id.imageHeadInItemMessageList, R.id.imageUnlikeInItemMessageList, R.id.imageLikeInItemMessageList,
                R.id.imageHeadOneInItemMessageList, R.id.imageHeadTwoInItemMessageList, R.id.imageHeadThreeInItemMessageList})
        List<ImageView> mImageViews;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(mTextViews.get(3))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            //mIMessageListFragment.handleSwitchPage();
                            //mIMessageListFragment.handleFocusEditView();
                            mIMessageListFragment.handleSwitchPageWithFocus();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mImageViews.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            isLike.set(getAdapterPosition(), 2);
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
                            isLike.set(getAdapterPosition(), 1);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIMessageListFragment.handleSwitchPage();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(5))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIMessageListFragment.handleSwitchPage();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(7))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIMessageListFragment.handleSwitchPage();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(9))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIMessageListFragment.handleSwitchPage();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(mTextViews.get(10))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            mIMessageListFragment.handleSwitchPage();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
