package com.switube.www.landmark2018test.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAAttractionSteaming;
import com.switube.www.landmark2018test.gson.GInfoData;
import com.switube.www.landmark2018test.util.AppConstant;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ASteamingPage extends RecyclerView.Adapter<ASteamingPage.ViewHolder> {
    private String name;
    private String imageUrl;
    private List<GInfoData.Article> articleList;
    private IAAttractionSteaming iaAttractionSteaming;
    public ASteamingPage(String name, String imageUrl, List<GInfoData.Article> articleList,
                         IAAttractionSteaming iaAttractionSteaming) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.articleList = articleList;
        this.iaAttractionSteaming = iaAttractionSteaming;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_steaming, parent, false));
    }

    private SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        int size;
        String count;
        int index = (position - 3) / 8;
        if (position == 0) {
            switchLayout(holder, 7);
            Glide.with(MyApplication.getInstance())
                    .load(Uri.parse(AppConstant.BASE_URL2 + imageUrl))
                    .into(holder.imageViewInHeadPicture);
        } else if (position == 1) {
            switchLayout(holder, 8);
            holder.textViewInHeadTitle.setText(name);
        } else if (position == 2) {
            switchLayout(holder, 9);
        } else {
            switch ((position - 2) % 8) {
                case 0:
                    switchLayout(holder, 6);
                    stringBuilder.delete(0, stringBuilder.length());
                    stringBuilder.append(SharePreferencesUtil.getInstance().getSharedPreferences().getString("userImg", "null"));
                    if (stringBuilder.toString().equals("null")) {
                        holder.imageViewInMessageEnd.setImageResource(R.drawable.person_unlogin);
                    } else {
                        stringBuilder.delete(0, 2);
                        Glide.with(holder.itemView.getContext())
                                .load(Uri.parse(AppConstant.BASE_URL2 + stringBuilder.toString()))
                                .into(holder.imageViewInMessageEnd);
                    }
                    break;
                case 1:
                    switchLayout(holder, 1);
                    holder.textViewListInMessageHead.get(0).setText(articleList.get(index).getManame());
                    Glide.with(holder.itemView.getContext())
                            .load(Uri.parse(AppConstant.BASE_URL + articleList.get(index).getMaimg()))
                            .into(holder.imageViewListInMessageHead.get(0));
                    String date = articleList.get(index).getTime_txt() + " ·";
                    holder.textViewListInMessageHead.get(1).setText(date);
                    if (articleList.get(index).getPrivacy().equals("1")) {
                        holder.imageViewListInMessageHead.get(1).setImageResource(R.drawable.pr_public_v1_1);
                    } else {
                        holder.imageViewListInMessageHead.get(1).setImageResource(R.drawable.pr_anonymous_v1_1);
                    }
                    if (articleList.get(index).getCoding().equals("0")) {
                        holder.imageViewListInMessageHead.get(2).setVisibility(View.VISIBLE);
                    } else {
                        holder.imageViewListInMessageHead.get(2).setVisibility(View.GONE);
                    }
                    if (articleList.get(index).getCoding().equals("0")) {
                        holder.imageViewListInMessageHead.get(2).setVisibility(View.VISIBLE);
                    } else {
                        holder.imageViewListInMessageHead.get(2).setVisibility(View.GONE);
                    }
                    break;
                case 2:
                    switchLayout(holder, 2);
                    if (articleList.get(index).getCoding().equals("1")) {
                        holder.textViewListInMessageHeadCustomer.get(1).setVisibility(View.VISIBLE);
                        int length = articleList.get(index).getTitle().get(0).length();
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder = new StringBuilder(articleList.get(index).getManame());
                        stringBuilder.append(" 建立了地標『");
                        stringBuilder.append(articleList.get(index).getTitle().get(0));
                        stringBuilder.append("』");
                        int lengthAll = stringBuilder.length();
                        SpannableString spannableString = new SpannableString(stringBuilder.toString());
                        spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                        holder.textViewListInMessageHeadCustomer.get(0).setText(spannableString);
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append(articleList.get(index).getManame());
                        stringBuilder.append(" created a new place ");
                        stringBuilder.append(articleList.get(index).getTitle().get(1));
                        stringBuilder.append(".");
                        length = articleList.get(index).getTitle().get(1).length();
                        lengthAll = stringBuilder.length();
                        spannableString = new SpannableString(stringBuilder.toString());
                        spannableString.setSpan(new UnderlineSpan(), lengthAll - length - 1, lengthAll - 1, 0);
                        spannableString.setSpan(new ForegroundColorSpan(Color.BLUE), lengthAll - length - 1, lengthAll - 1, 0);
                        holder.textViewListInMessageHeadCustomer.get(1).setText(spannableString);
                    } else {
                        holder.textViewListInMessageHeadCustomer.get(1).setVisibility(View.GONE);
                        holder.textViewListInMessageHeadCustomer.get(0).setText(articleList.get(index).getContent());
                    }
                    break;
                case 3:
                    switchLayout(holder, 3);
                    if (articleList.get(index).getImg().size() != 0) {
                        Glide.with(holder.itemView.getContext())
                                .load(Uri.parse(AppConstant.BASE_URL + articleList.get(index).getImg().get(0)))
                                .into(holder.imageViewInMessageHeadPhoto);
                        holder.textViewListInMessageHeadPhoto.get(1).setVisibility(View.VISIBLE);
                        String photoCount = "1/" + String.valueOf(articleList.get(index).getImg().size());
                        holder.textViewListInMessageHeadPhoto.get(1).setText(photoCount);
                    } else {
                        holder.textViewListInMessageHeadPhoto.get(1).setVisibility(View.GONE);
                    }
                    size = articleList.get(index).getTag().size();
                    spannableStringBuilder.delete(0, spannableStringBuilder.length());
                    for (int i = 0; i < size; i++) {
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append("#");
                        stringBuilder.append(articleList.get(index).getTag().get(i));
                        int indexOf = stringBuilder.indexOf(":");
                        stringBuilder.delete(indexOf, stringBuilder.length());
                        spannableStringBuilder.append(stringBuilder.toString(), new StyleSpan(Typeface.BOLD), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        stringBuilder.delete(0, stringBuilder.length());
                        stringBuilder.append("#");
                        stringBuilder.append(articleList.get(index).getTag().get(i));
                        if (i != size - 1) {
                            stringBuilder.append("\n");
                        }
                        stringBuilder.delete(0, indexOf + 1);
                        spannableStringBuilder.append(stringBuilder.toString());
                    }
                    holder.textViewListInMessageHeadPhoto.get(0).setText(spannableStringBuilder);
                    break;
                case 4:
                    switchLayout(holder, 4);
                    size = articleList.get(index).getMsg().size();
                    count = String.valueOf(size) + " " + holder.itemView.getContext().getString(R.string.info_comments);
                    holder.textViewListInMessageBody.get(3).setText(count);
                    holder.textViewListInMessageBody.get(2).setText(articleList.get(index).getCount());
                    switch (articleList.get(index).getLike()) {
                        case "0":
                            holder.imageViewListInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                            holder.imageViewListInMessageBody.get(2).setImageResource(R.drawable.like_fornote_v11);
                            break;
                        case "1":
                            holder.imageViewListInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_v11);
                            holder.imageViewListInMessageBody.get(2).setImageResource(R.drawable.like_fornote_on_v11);
                            break;
                        case "2":
                            holder.imageViewListInMessageBody.get(3).setImageResource(R.drawable.dislike_fornote_on_v11);
                            holder.imageViewListInMessageBody.get(2).setImageResource(R.drawable.like_fornote_v11);
                            break;
                        default:
                            break;
                    }
                    break;
                case 5:
                    if (articleList.get(index).getMsg().size() == 0) {
                        switchLayout(holder, 0);
                    } else {
                        switchLayout(holder, 5);
                        holder.textViewListInMessageEndMessage.get(0).setText(articleList.get(index).getMsg().get(0).getManame());
                        holder.textViewListInMessageEndMessage.get(1).setText(articleList.get(index).getMsg().get(0).getTxt());
                        Glide.with(holder.itemView.getContext())
                                .load(Uri.parse(AppConstant.BASE_URL + articleList.get(index).getMsg().get(0).getMaimg()))
                                .into(holder.imageViewInMessageEndMessage);
                    }
                    break;
                case 6:
                    if (articleList.get(index).getMsg().size() <= 1) {
                        switchLayout(holder, 0);
                    } else {
                        switchLayout(holder, 5);
                        holder.textViewListInMessageEndMessage.get(0).setText(articleList.get(index).getMsg().get(1).getManame());
                        holder.textViewListInMessageEndMessage.get(1).setText(articleList.get(index).getMsg().get(1).getTxt());
                        Glide.with(holder.itemView.getContext())
                                .load(Uri.parse(AppConstant.BASE_URL + articleList.get(index).getMsg().get(1).getMaimg()))
                                .into(holder.imageViewInMessageEndMessage);
                    }
                    break;
                case 7:
                    if (articleList.get(index).getMsg().size() <= 2) {
                        switchLayout(holder, 0);
                    } else {
                        switchLayout(holder, 5);
                        holder.textViewListInMessageEndMessage.get(0).setText(articleList.get(index).getMsg().get(2).getManame());
                        holder.textViewListInMessageEndMessage.get(1).setText(articleList.get(index).getMsg().get(2).getTxt());
                        Glide.with(holder.itemView.getContext())
                                .load(Uri.parse(AppConstant.BASE_URL + articleList.get(index).getMsg().get(2).getMaimg()))
                                .into(holder.imageViewInMessageEndMessage);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    public void refreshAdapter(String count, int num) {
        articleList.get(num).setCount(count);
        notifyDataSetChanged();
    }

    public void refreshAdapter(int index) {
        //articleList.remove(index);
        notifyDataSetChanged();
    }

    private void switchLayout(ViewHolder viewHolder, int index) {
        for (int i = 0; i < 8; i++) {
            viewHolder.layoutList.get(i).setVisibility(View.GONE);
        }
        viewHolder.textView.setVisibility(View.GONE);
        switch (index) {
            case 1:
                viewHolder.layoutList.get(2).setVisibility(View.VISIBLE);
                break;
            case 2:
                viewHolder.layoutList.get(3).setVisibility(View.VISIBLE);
                break;
            case 3:
                viewHolder.layoutList.get(4).setVisibility(View.VISIBLE);
                break;
            case 4:
                viewHolder.layoutList.get(5).setVisibility(View.VISIBLE);
                break;
            case 5:
                viewHolder.layoutList.get(6).setVisibility(View.VISIBLE);
                break;
            case 6:
                viewHolder.layoutList.get(7).setVisibility(View.VISIBLE);
                break;
            case 7:
                viewHolder.layoutList.get(0).setVisibility(View.VISIBLE);
                break;
            case 8:
                viewHolder.layoutList.get(1).setVisibility(View.VISIBLE);
                break;
            case 9:
                viewHolder.textView.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return articleList.size() * 8 + 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imagePictureInHeadPicture)
        ImageView imageViewInHeadPicture;
        @BindView(R.id.textTitleInHeadTitle)
        TextView textViewInHeadTitle;
        @BindViews({R.id.imageHeadInMessageHead, R.id.imageStateInMessageHead, R.id.imageEditInMessageHead})
        List<ImageView> imageViewListInMessageHead;
        @BindViews({R.id.textIDInMessageHead, R.id.textDateInMessageHead})
        List<TextView> textViewListInMessageHead;
        @BindViews({R.id.textMessageInMessageHeadCustomer, R.id.textMessage2InMessageHeadCustomer})
        List<TextView> textViewListInMessageHeadCustomer;
        @BindViews({R.id.textTagAInMessageHeadPhoto, R.id.textCountInMessageHeadPhoto})
        List<TextView> textViewListInMessageHeadPhoto;
        @BindView(R.id.imagePhotoInMessageHeadPhoto)
        ImageView imageViewInMessageHeadPhoto;
        @BindViews({R.id.imageMessageInMessageBody, R.id.imageShareInMessageBody,
                R.id.imageGoodInMessageBody, R.id.imageBadInMessageBody})
        List<ImageView> imageViewListInMessageBody;
        @BindViews({R.id.textMessageInMessageBody, R.id.textShareInMessageBody,
                R.id.textCountInMessageBody, R.id.textAllMessageInMessageBody})
        List<TextView> textViewListInMessageBody;
        @BindView(R.id.imagePhotoInMessageEnd)
        ImageView imageViewInMessageEnd;
        @BindView(R.id.viewBarInMessageEnd)
        View viewInMessageEnd;
        @BindView(R.id.imagePhotoInMessageEndMessage)
        ImageView imageViewInMessageEndMessage;
        @BindViews({R.id.textNameInMessageEndMessage, R.id.textMessageInMessageEndMessage})
        List<TextView> textViewListInMessageEndMessage;
        @BindViews({R.id.layoutHeadPicture, R.id.layoutHeadTitle, R.id.layoutMessageHead,
                R.id.layoutMessageHeadCustomer, R.id.layoutMessageHeadPhoto, R.id.layoutMessageBody,
                R.id.layoutMessageEndMessage, R.id.layoutMessageEnd})
        List<RelativeLayout> layoutList;
        @BindView(R.id.textTitleInItemSteaming)
        TextView textView;
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            RxView.clicks(imageViewListInMessageBody.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaAttractionSteaming.handleSwitchPage((getAdapterPosition() - 3) / 8);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewListInMessageBody.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaAttractionSteaming.handleSwitchPage((getAdapterPosition() - 3) / 8);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewListInMessageBody.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            Toast.makeText(itemView.getContext(), R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewListInMessageBody.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            Toast.makeText(itemView.getContext(), R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(viewInMessageEnd)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaAttractionSteaming.handleSwitchPage((getAdapterPosition() - 3) / 8);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewListInMessageEndMessage.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaAttractionSteaming.handleSwitchPage((getAdapterPosition() - 3) / 8);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewListInMessageEndMessage.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaAttractionSteaming.handleSwitchPage((getAdapterPosition() - 3) / 8);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            /*RxView.clicks(textViewListInMessageEndMessage.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaAttractionSteaming.handleSwitchPage((getAdapterPosition() - 3) / 8);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });*/
            RxView.clicks(imageViewListInMessageBody.get(2))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            int index = getAdapterPosition() / 8;
                            String artid = articleList.get(index).getArtid();
                            articleList.get(index).setLike("1");
                            iaAttractionSteaming.handleLikeOrUnlike(artid, "1", index);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewListInMessageBody.get(3))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            int index = getAdapterPosition() / 8;
                            String artid = articleList.get(index).getArtid();
                            articleList.get(index).setLike("2");
                            iaAttractionSteaming.handleLikeOrUnlike(artid, "2", index);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewInHeadPicture)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaAttractionSteaming.handleClickPhoto(-1);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewInMessageHeadPhoto)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaAttractionSteaming.handleClickPhoto((getAdapterPosition() - 3) / 8);
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewListInMessageHeadCustomer.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (articleList.get((getAdapterPosition() - 3) / 8).getCoding().equals("1")) {
                                iaAttractionSteaming.handleLinkClick(articleList.get((getAdapterPosition() - 3) / 8).getTitle().get(2));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewListInMessageHeadCustomer.get(1))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (articleList.get((getAdapterPosition() - 3) / 8).getCoding().equals("1")) {
                                iaAttractionSteaming.handleLinkClick(articleList.get((getAdapterPosition() - 3) / 8).getTitle().get(2));
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(imageViewListInMessageHead.get(2))
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
            PopupMenu popupMenu = new PopupMenu(context, imageViewListInMessageHead.get(2));
            popupMenu.getMenuInflater().inflate(R.menu.menu_info_comment, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.menuDeleteInComment) {
                        iaAttractionSteaming.handleDeleteComment((getAdapterPosition() - 3) / 8);
                    } else if (menuItem.getItemId() == R.id.menuEditCommentInComment) {
                        iaAttractionSteaming.handleEdit((getAdapterPosition() - 3) / 8, true);
                    } else {
                        iaAttractionSteaming.handleEdit((getAdapterPosition() - 3) / 8, false);
                    }
                    return true;
                }
            });
        }
    }
}
