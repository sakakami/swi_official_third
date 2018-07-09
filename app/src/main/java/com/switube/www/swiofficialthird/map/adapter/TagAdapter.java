package com.switube.www.swiofficialthird.map.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.map.InterfaceTag;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
    private Context mContext;
    private InterfaceTag mInterfaceTag;
    private String[] tagA = new String[] {"衛生", "平均價格", "你推薦", "裝潢", "餐桌",
            "服務評價", "房間整潔", "客房服務", "速度", "是否會推薦給其他人", "外觀", "交通", "位置", "便利度", "滿意度",
            "衛生", "平均價格", "你推薦", "裝潢", "餐桌",
            "服務評價", "房間整潔", "客房服務", "速度", "是否會推薦給其他人", "外觀", "交通", "位置", "便利度", "滿意度",
            "衛生", "平均價格", "你推薦", "裝潢", "餐桌",
            "服務評價", "房間整潔", "客房服務", "速度", "是否會推薦給其他人", "外觀", "交通", "位置", "便利度", "滿意度"};
    private String[] tagB = new String[] {"滿意", "非常滿意", "普通", "差", "非常差",
            "一星", "二星", "三星", "四星", "五星", "一般", "無法評價", "不予置評", "還可以", "不錯",
            "滿意", "非常滿意", "普通", "差", "非常差",
            "一星", "二星", "三星", "四星", "五星", "一般", "無法評價", "不予置評", "還可以", "不錯",
            "滿意", "非常滿意", "普通", "差", "非常差",
            "一星", "二星", "三星", "四星", "五星", "一般", "無法評價", "不予置評", "還可以", "不錯"};
    public TagAdapter(Context context, InterfaceTag interfaceTag) {
        mContext = context;
        mInterfaceTag = interfaceTag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.holder_item_tag, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (isTagA) {
            holder.mTextTitle.setText(tagA[position]);
        } else {
            holder.mTextTitle.setText(tagB[position]);
        }
    }

    @Override
    public int getItemCount() {
        return tagA.length;
    }

    public void handleDefault() {
        isTagA = true;
        notifyDataSetChanged();
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
                                mInterfaceTag.handleTagSelected();
                                if (isTagA) {
                                    mInterfaceTag.handleAddTagA(tagA[getAdapterPosition()]);
                                    isTagA = false;
                                    notifyDataSetChanged();
                                } else {
                                    mInterfaceTag.handleAddTagB(tagB[getAdapterPosition()]);
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

        /*private void handleCreateNewTag() {
            View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_item, null);
            final EditText editText = view.findViewById(R.id.editInDialogItem);
            AlertDialogUtil
                    .getInstance()
                    .initDialogBuilder(
                            mContext,
                            view,
                            "請輸入欲創建的標籤",
                            "創建",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (editText.getText().length() > 0) {
                                        if (isTagA) {
                                            mInterfaceTag.handleAddTagA(editText.getText().toString());
                                            isTagA = false;
                                            notifyDataSetChanged();
                                        } else {
                                            mInterfaceTag.handleAddTagB(editText.getText().toString());
                                            isTagA = true;
                                            notifyDataSetChanged();
                                        }
                                    }
                                }
                            },
                            "取消",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) { }
                            }
                    );
            AlertDialogUtil.getInstance().showAlertDialog();
        }*/
    }
}
