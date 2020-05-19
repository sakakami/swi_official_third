package com.switube.www.landmark2018test.adapter;

import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAEditAttraction;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AEditAttraction extends RecyclerView.Adapter<AEditAttraction.HolderView> {
    private IAEditAttraction iaEditAttraction;
    public AEditAttraction(IAEditAttraction iaEditAttraction) {
        this.iaEditAttraction = iaEditAttraction;
        if (MyApplication.getAppData().getIsEdit().size() == 0) {
            for (int i = 0; i < 9; i++) {
                MyApplication.getAppData().getIsEdit().add(false);
            }
        }
        init();
    }

    @NonNull
    @Override
    public HolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HolderView(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_create_attraction, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HolderView holder, int position) {
        switch (position) {
            case 0:
                holder.imageViewList.get(1).setVisibility(View.GONE);
                holder.imageViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(1).setVisibility(View.GONE);
                holder.textViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(3).setVisibility(View.GONE);
                holder.textViewList.get(4).setVisibility(View.GONE);
                holder.textViewList.get(5).setVisibility(View.GONE);
                holder.textViewList.get(7).setVisibility(View.GONE);
                holder.viewBar.setVisibility(View.GONE);
                holder.editText.setVisibility(View.VISIBLE);
                holder.imageViewList.get(0).setImageResource(R.drawable.a_name_v11);
                holder.textViewList.get(0).setText(handleString(holder.itemView.getContext().getString(R.string.create_name)));
                holder.editText.setText(MyApplication.getAppData().getgInfoData().getData().get(0).getPlace());
                break;
            case 1:
                holder.imageViewList.get(1).setVisibility(View.GONE);
                holder.imageViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(1).setVisibility(View.GONE);
                holder.textViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(3).setVisibility(View.GONE);
                holder.textViewList.get(4).setVisibility(View.GONE);
                holder.textViewList.get(5).setVisibility(View.VISIBLE);
                holder.textViewList.get(7).setVisibility(View.GONE);
                holder.editText.setVisibility(View.GONE);
                holder.viewBar.setVisibility(View.VISIBLE);
                holder.imageViewList.get(0).setImageResource(R.drawable.a_add_v11);
                holder.textViewList.get(0).setText(handleString(holder.itemView.getContext().getString(R.string.create_address)));
                holder.textViewList.get(5).setText(MyApplication.getAppData().getgInfoData().getData().get(0).getAddr());
                break;
            case 2:
                holder.imageViewList.get(1).setVisibility(View.GONE);
                holder.imageViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(1).setVisibility(View.GONE);
                holder.textViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(3).setVisibility(View.GONE);
                holder.textViewList.get(4).setVisibility(View.GONE);
                holder.textViewList.get(5).setVisibility(View.VISIBLE);
                holder.textViewList.get(7).setVisibility(View.GONE);
                holder.editText.setVisibility(View.GONE);
                holder.viewBar.setVisibility(View.VISIBLE);
                holder.imageViewList.get(0).setImageResource(R.drawable.a_cate_v11);
                holder.textViewList.get(0).setText(handleString(holder.itemView.getContext().getString(R.string.create_category)));
                holder.textViewList.get(5).setText(MyApplication.getAppData().getStyle());
                break;
            case 3:
                holder.imageViewList.get(1).setVisibility(View.GONE);
                holder.imageViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(1).setVisibility(View.VISIBLE);
                holder.textViewList.get(2).setVisibility(View.VISIBLE);
                holder.textViewList.get(3).setVisibility(View.GONE);
                holder.textViewList.get(4).setVisibility(View.GONE);
                holder.textViewList.get(5).setVisibility(View.GONE);
                holder.textViewList.get(7).setVisibility(View.GONE);
                holder.editText.setVisibility(View.GONE);
                holder.viewBar.setVisibility(View.GONE);
                holder.imageViewList.get(0).setImageResource(R.drawable.a_time_v11);
                holder.textViewList.get(0).setText(R.string.create_hours);
                if (timeA.length() > 0) {
                    holder.textViewList.get(1).setText(timeA);
                    holder.textViewList.get(2).setText(timeB);
                } else {
                    holder.textViewList.get(1).setText(R.string.create_hours_hint);
                }
                break;
            case 4:
                holder.imageViewList.get(1).setVisibility(View.GONE);
                holder.imageViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(1).setVisibility(View.GONE);
                holder.textViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(3).setVisibility(View.GONE);
                holder.textViewList.get(4).setVisibility(View.GONE);
                holder.textViewList.get(5).setVisibility(View.GONE);
                holder.textViewList.get(7).setVisibility(View.GONE);
                holder.editText.setVisibility(View.VISIBLE);
                holder.viewBar.setVisibility(View.GONE);
                holder.imageViewList.get(0).setImageResource(R.drawable.a_ad_phone_v11);
                holder.textViewList.get(0).setText(R.string.create_phone);
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getPhone().length() > 0) {
                    holder.editText.setText(MyApplication.getAppData().getgInfoData().getData().get(0).getPhone());
                } else {
                    holder.editText.setHint(R.string.create_phone_hint);
                }
                break;
            case 5:
                holder.imageViewList.get(1).setVisibility(View.GONE);
                holder.imageViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(1).setVisibility(View.GONE);
                holder.textViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(3).setVisibility(View.GONE);
                holder.textViewList.get(4).setVisibility(View.GONE);
                holder.textViewList.get(5).setVisibility(View.GONE);
                holder.textViewList.get(7).setVisibility(View.GONE);
                holder.editText.setVisibility(View.VISIBLE);
                holder.viewBar.setVisibility(View.GONE);
                holder.imageViewList.get(0).setImageResource(R.drawable.a_message_v11);
                holder.textViewList.get(0).setText(R.string.create_website);
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getWebsite().length() > 0) {
                    holder.editText.setText(MyApplication.getAppData().getgInfoData().getData().get(0).getWebsite());
                } else {
                    holder.editText.setHint(R.string.create_website_hint);
                }
                break;
            case 6:
                holder.imageViewList.get(1).setVisibility(View.GONE);
                holder.imageViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(1).setVisibility(View.GONE);
                holder.textViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(3).setVisibility(View.GONE);
                holder.textViewList.get(4).setVisibility(View.GONE);
                holder.textViewList.get(5).setVisibility(View.GONE);
                holder.textViewList.get(7).setVisibility(View.GONE);
                holder.editText.setVisibility(View.VISIBLE);
                holder.viewBar.setVisibility(View.GONE);
                holder.imageViewList.get(0).setImageResource(R.drawable.a_video);
                holder.textViewList.get(0).setText(R.string.create_video);
                if (MyApplication.getAppData().getTubeUrl().length() > 0) {
                    holder.editText.setText(MyApplication.getAppData().getTubeUrl());
                } else {
                    holder.editText.setHint(R.string.create_video_hint);
                }
                break;
            case 7:
                holder.imageViewList.get(1).setVisibility(View.GONE);
                holder.imageViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(1).setVisibility(View.GONE);
                holder.textViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(3).setVisibility(View.VISIBLE);
                holder.textViewList.get(4).setVisibility(View.GONE);
                holder.textViewList.get(5).setVisibility(View.GONE);
                holder.textViewList.get(7).setVisibility(View.GONE);
                holder.editText.setVisibility(View.VISIBLE);
                holder.viewBar.setVisibility(View.GONE);
                holder.imageViewList.get(0).setImageResource(R.drawable.a_detail_v11);
                holder.textViewList.get(0).setText(R.string.create_description);
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getInfo().length() > 0) {
                    holder.editText.setText(MyApplication.getAppData().getgInfoData().getData().get(0).getInfo());
                    String count = MyApplication.getAppData().getgInfoData().getData().get(0).getInfo().length() + "/150";
                    holder.textViewList.get(3).setText(count);
                } else {
                    holder.editText.setHint(R.string.create_description_hint);
                    String temp = "0/150";
                    holder.textViewList.get(3).setText(temp);
                }
                break;
            case 8:
                holder.imageViewList.get(1).setVisibility(View.VISIBLE);
                if (MyApplication.getAppData().getSelectedPhotos().size() > 0) {
                    holder.imageViewList.get(2).setVisibility(View.VISIBLE);
                    Glide.with(MyApplication.getInstance())
                            .load(Uri.fromFile(new File(MyApplication.getAppData().getSelectedPhotos().get(0))))
                            .into(holder.imageViewList.get(1));
                } else {
                    holder.imageViewList.get(2).setVisibility(View.GONE);
                }
                holder.textViewList.get(1).setVisibility(View.GONE);
                holder.textViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(3).setVisibility(View.GONE);
                holder.textViewList.get(4).setVisibility(View.VISIBLE);
                holder.textViewList.get(5).setVisibility(View.GONE);
                holder.textViewList.get(7).setVisibility(View.GONE);
                holder.editText.setVisibility(View.GONE);
                holder.viewBar.setVisibility(View.GONE);
                holder.imageViewList.get(0).setImageResource(R.drawable.camera_off_v11);
                holder.textViewList.get(0).setText(R.string.create_add_photo);
                String count = "+ " + MyApplication.getAppData().getSelectedPhotos().size() + " PHOTOS";
                holder.textViewList.get(4).setText(count);
                break;
            case 9:
                holder.imageViewList.get(1).setVisibility(View.GONE);
                holder.imageViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(1).setVisibility(View.GONE);
                holder.textViewList.get(2).setVisibility(View.GONE);
                holder.textViewList.get(3).setVisibility(View.GONE);
                holder.textViewList.get(4).setVisibility(View.GONE);
                holder.textViewList.get(5).setVisibility(View.GONE);
                holder.textViewList.get(7).setVisibility(View.VISIBLE);
                holder.viewBar.setVisibility(View.GONE);
                holder.editText.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public void refreshAdapter() {
        init();
        notifyDataSetChanged();
    }

    private StringBuilder timeA;
    private StringBuilder timeB;
    private void init() {
        StringBuilder mon = new StringBuilder();
        StringBuilder tue = new StringBuilder();
        StringBuilder web = new StringBuilder();
        StringBuilder thu = new StringBuilder();
        StringBuilder fri = new StringBuilder();
        StringBuilder sat = new StringBuilder();
        StringBuilder sun = new StringBuilder();
        timeA = new StringBuilder();
        timeB = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getMon().get(i).length() > 0) {
                if (mon.length() == 0) {
                    mon.append(MyApplication.getInstance().getString(R.string.weekly_monday));
                } else {
                    mon.append(System.lineSeparator());
                }
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getMon().get(i).equals("24")) {
                    timeB.append(MyApplication.getInstance().getString(R.string.weekly_24hour));
                } else {
                    timeB.append(MyApplication.getAppData().getgInfoData().getData().get(0).getMon().get(i));
                }
                timeB.append(System.lineSeparator());
            }
        }
        for (int i = 0; i < 4; i++) {
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getTue().get(i).length() > 0) {
                if (tue.length() == 0) {
                    tue.append(MyApplication.getInstance().getString(R.string.weekly_tuesday));
                } else {
                    tue.append(System.lineSeparator());
                }
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getTue().get(i).equals("24")) {
                    timeB.append(MyApplication.getInstance().getString(R.string.weekly_24hour));
                } else {
                    timeB.append(MyApplication.getAppData().getgInfoData().getData().get(0).getTue().get(i));
                }
                timeB.append(System.lineSeparator());
            }
        }
        for (int i = 0; i < 4; i++) {
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getWed().get(i).length() > 0) {
                if (web.length() == 0) {
                    web.append(MyApplication.getInstance().getString(R.string.weekly_wednesday));
                } else {
                    web.append(System.lineSeparator());
                }
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getWed().get(i).equals("24")) {
                    timeB.append(MyApplication.getInstance().getString(R.string.weekly_24hour));
                } else {
                    timeB.append(MyApplication.getAppData().getgInfoData().getData().get(0).getWed().get(i));
                }
                timeB.append(System.lineSeparator());
            }
        }
        for (int i = 0; i < 4; i++) {
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getThu().get(i).length() > 0) {
                if (thu.length() == 0) {
                    thu.append(MyApplication.getInstance().getString(R.string.weekly_thursday));
                } else {
                    thu.append(System.lineSeparator());
                }
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getThu().get(i).equals("24")) {
                    timeB.append(MyApplication.getInstance().getString(R.string.weekly_24hour));
                } else {
                    timeB.append(MyApplication.getAppData().getgInfoData().getData().get(0).getThu().get(i));
                }
                timeB.append(System.lineSeparator());
            }
        }
        for (int i = 0; i < 4; i++) {
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getFri().get(i).length() > 0) {
                if (fri.length() == 0) {
                    fri.append(MyApplication.getInstance().getString(R.string.weekly_friday));
                } else {
                    fri.append(System.lineSeparator());
                }
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getFri().get(i).equals("24")) {
                    timeB.append(MyApplication.getInstance().getString(R.string.weekly_24hour));
                } else {
                    timeB.append(MyApplication.getAppData().getgInfoData().getData().get(0).getFri().get(i));
                }
                timeB.append(System.lineSeparator());
            }
        }
        for (int i = 0; i < 4; i++) {
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getSat().get(i).length() > 0) {
                if (sat.length() == 0) {
                    sat.append(MyApplication.getInstance().getString(R.string.weekly_saturday));
                } else {
                    sat.append(System.lineSeparator());
                }
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getSat().get(i).equals("24")) {
                    timeB.append(MyApplication.getInstance().getString(R.string.weekly_24hour));
                } else {
                    timeB.append(MyApplication.getAppData().getgInfoData().getData().get(0).getSat().get(i));
                }
                timeB.append(System.lineSeparator());
            }
        }
        for (int i = 0; i < 4; i++) {
            if (MyApplication.getAppData().getgInfoData().getData().get(0).getSun().get(i).length() > 0) {
                if (sun.length() == 0) {
                    sun.append(MyApplication.getInstance().getString(R.string.weekly_sunday));
                } else {
                    sun.append(System.lineSeparator());
                }
                if (MyApplication.getAppData().getgInfoData().getData().get(0).getSun().get(i).equals("24")) {
                    timeB.append(MyApplication.getInstance().getString(R.string.weekly_24hour));
                } else {
                    timeB.append(MyApplication.getAppData().getgInfoData().getData().get(0).getSun().get(i));
                }
                timeB.append(System.lineSeparator());
            }
        }
        if (mon.length() > 0) {
            timeA.append(mon);
        }
        if (tue.length() > 0) {
            if (timeA.length() > 0) {
                timeA.append(System.lineSeparator());
            }
            timeA.append(tue);
        }
        if (web.length() > 0) {
            if (timeA.length() > 0) {
                timeA.append(System.lineSeparator());
            }
            timeA.append(web);
        }
        if (thu.length() > 0) {
            if (timeA.length() > 0) {
                timeA.append(System.lineSeparator());
            }
            timeA.append(thu);
        }
        if (fri.length() > 0) {
            if (timeA.length() > 0) {
                timeA.append(System.lineSeparator());
            }
            timeA.append(fri);
        }
        if (sat.length() > 0) {
            if (timeA.length() > 0) {
                timeA.append(System.lineSeparator());
            }
            timeA.append(sat);
        }
        if (sun.length() > 0) {
            if (timeA.length() > 0) {
                timeA.append(System.lineSeparator());
            }
            timeA.append(sun);
        }
        if (MyApplication.getAppData().getgInfoData().getData().get(0).getTubeWeb().size() > 0) {
            MyApplication.getAppData().setTubeUrl(MyApplication.getAppData().getgInfoData().getData().get(0).getTubeWeb().get(0).getUrl());
        } else {
            MyApplication.getAppData().setTubeUrl("");
        }
    }

    private SpannableString handleString(String word) {
        StringBuilder stringBuilder = new StringBuilder(word);
        int index = stringBuilder.indexOf("*");
        SpannableString spannableString = new SpannableString(stringBuilder.toString());
        spannableString.setSpan(new ForegroundColorSpan(Color.RED), index, stringBuilder.length(), 0);
        return spannableString;
    }

    class HolderView extends RecyclerView.ViewHolder {
        @BindViews({R.id.textTitleInItemCreateAttraction, R.id.textTimeAInItemCreateAttraction,
                R.id.textTimeBInItemCreateAttraction, R.id.textCountInItemCreateAttraction,
                R.id.textPhotoCountInItemCreateAttraction, R.id.textMessageInItemCreateAttraction,
                R.id.textCheckMessageInItemCreateAttraction, R.id.textRemoveInItemCreateAttraction})
        List<TextView> textViewList;
        @BindView(R.id.editMessageInItemCreateAttraction)
        EditText editText;
        @BindView(R.id.checkboxInItemCreateAttraction)
        CheckBox checkBox;
        @BindViews({R.id.imageMarkInItemCreateAttraction, R.id.imageAInItemCreateAttraction, R.id.imageBInItemCreateAttraction})
        List<ImageView> imageViewList;
        @BindView(R.id.viewBarInItemCreateAttraction)
        View viewBar;
        HolderView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            textViewList.get(6).setVisibility(View.GONE);
            checkBox.setVisibility(View.GONE);
            RxTextView.textChanges(editText)
                    .subscribe(new Observer<CharSequence>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(CharSequence charSequence) {
                            String count = charSequence.length() + "/150";
                            textViewList.get(3).setText(count);
                            switch (getAdapterPosition()) {
                                case 0:
                                    if (charSequence.length() != MyApplication.getAppData().getgInfoData().getData().get(0).getPlace().length()) {
                                        MyApplication.getAppData().getIsEdit().set(0, true);
                                        MyApplication.getAppData().getgInfoData().getData().get(0).setPlace(charSequence.toString());
                                    }
                                    break;
                                case 4:
                                    if (charSequence.length() != MyApplication.getAppData().getgInfoData().getData().get(0).getPhone().length()) {
                                        MyApplication.getAppData().getIsEdit().set(4, true);
                                        MyApplication.getAppData().getgInfoData().getData().get(0).setPhone(charSequence.toString());
                                    }
                                    break;
                                case 5:
                                    if (charSequence.length() != MyApplication.getAppData().getgInfoData().getData().get(0).getWebsite().length()) {
                                        MyApplication.getAppData().getIsEdit().set(5, true);
                                        MyApplication.getAppData().getgInfoData().getData().get(0).setWebsite(charSequence.toString());
                                    }
                                    break;
                                case 6:
                                    if (!MyApplication.getAppData().getTubeUrl().equals(charSequence.toString())) {
                                        MyApplication.getAppData().getIsEdit().set(6, true);
                                        MyApplication.getAppData().setTubeUrl(charSequence.toString());
                                    }
                                    break;
                                case 7:
                                    if (charSequence.length() != MyApplication.getAppData().getgInfoData().getData().get(0).getInfo().length()) {
                                        MyApplication.getAppData().getIsEdit().set(7, true);
                                        MyApplication.getAppData().getgInfoData().getData().get(0).setInfo(charSequence.toString());
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewList.get(5))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (getAdapterPosition() == 1) {
                                MyApplication.getAppData().getIsEdit().set(1, true);
                                iaEditAttraction.handleClick(0);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewList.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaEditAttraction.handleClick(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewList.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaEditAttraction.handleClick(2);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewList.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaEditAttraction.handleClick(3);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewList.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaEditAttraction.handleClick(3);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewList.get(7))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaEditAttraction.handleRemove();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }
    }
}
