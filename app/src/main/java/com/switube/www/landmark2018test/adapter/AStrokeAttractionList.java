package com.switube.www.landmark2018test.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAPlacePhotoList;
import com.switube.www.landmark2018test.adapter.callback.IAStrokeAttractionList;
import com.switube.www.landmark2018test.gson.GStrokeList;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.util.SignInUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AStrokeAttractionList extends RecyclerView.Adapter<AStrokeAttractionList.ViewHolder> {
    private GStrokeList gStrokeList;
    private IAStrokeAttractionList iaStrokeAttractionList;
    private IAPlacePhotoList iaPlacePhotoList;
    private IMainActivity iMainActivity;
    private boolean isOneButtom;
    public AStrokeAttractionList(IAStrokeAttractionList iaStrokeAttractionList, boolean isOneButtom, IAPlacePhotoList iaPlacePhotoList, IMainActivity iMainActivity) {
        this.iaStrokeAttractionList = iaStrokeAttractionList;
        this.isOneButtom = isOneButtom;
        this.iaPlacePhotoList = iaPlacePhotoList;
        this.iMainActivity = iMainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_stroke_attraction_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position < gStrokeList.getData().size()) {
            holder.textViewList.get(0).setText(gStrokeList.getData().get(position).getPlace());
            holder.textViewList.get(1).setText(gStrokeList.getData().get(position).getRating());
            holder.textViewList.get(2).setText(style.get(position));
            switch (isOpen.get(position)) {
                case "0":
                    holder.textViewList.get(3).setText(R.string.map_status_open);
                    break;
                case "1":
                    holder.textViewList.get(3).setText(R.string.map_status_close);
                    break;
                default:
                    holder.textViewList.get(3).setText(R.string.no_time_data);
                    break;
            }
            holder.textViewList.get(4).setText(time.get(position));
            holder.aPlacePhotoList.refreshAdapter(gStrokeList.getData().get(position).getPhoto(), false, position);
        }
        if (!isOneButtom) {
            if (getgStrokeList().getData().get(position).getColl().length() > 0) {
                holder.imageViewList.get(1).setImageResource(R.drawable.mark_on_v14);
            } else {
                holder.imageViewList.get(1).setImageResource(R.drawable.mark_off_v12);
            }
        }
    }

    @Override
    public int getItemCount() {
        return style.size();
    }

    public GStrokeList getgStrokeList() {
        return gStrokeList;
    }

    public List<String> getStyle() {
        return style;
    }

    public List<String> getIsOpen() {
        return isOpen;
    }

    public List<String> getTime() {
        return time;
    }

    public void refreshAdapter(int index, String sucid) {
        gStrokeList.getData().get(index).setColl(sucid);
        notifyDataSetChanged();
    }

    private List<String> style = new ArrayList<>();
    private List<String> time = new ArrayList<>();
    private List<String> isOpen = new ArrayList<>();
    public void init(GStrokeList gStrokeList, List<String> style, List<String> time, List<String> isOpen) {
        this.gStrokeList = gStrokeList;
        this.style = style;
        this.time = time;
        this.isOpen = isOpen;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerInItemStrokeAttractionList)
        RecyclerView recyclerView;
        @BindViews({R.id.imageMenuInItemStrokeAttractionList, R.id.imageSaveInItemStrokeAttractionList, R.id.imageCollectInItemStrokeAttractionList})
        List<ImageView> imageViewList;
        @BindViews({R.id.textTitleInItemStrokeAttractionList, R.id.textRatingInItemStrokeAttractionList,
                R.id.textTypeInItemStrokeAttractionList, R.id.textStatusInItemStrokeAttractionList,
                R.id.textTimeInItemStrokeAttractionList})
        List<TextView> textViewList;
        @BindView(R.id.viewBarInItemStrokeAttractionList)
        View viewBar;
        private APlacePhotoList aPlacePhotoList;
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            if (isOneButtom) {
                imageViewList.get(1).setVisibility(View.GONE);
                imageViewList.get(2).setVisibility(View.GONE);
            } else {
                imageViewList.get(0).setVisibility(View.GONE);
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.addItemDecoration(new ItemDecorationUtil(itemView.getContext(), 0, 0, 0, 4));
            recyclerView.setLayoutManager(layoutManager);
            aPlacePhotoList = new APlacePhotoList(iaPlacePhotoList);
            recyclerView.setAdapter(aPlacePhotoList);
            RxView.clicks(imageViewList.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                new SignInUtil(itemView.getContext(), iMainActivity);
                            } else {
                                if (gStrokeList.getData().get(getAdapterPosition()).getColl().length() > 0) {
                                    if (MyApplication.getAppData().getStrokeMode() != 2) {
                                        showMenu2_1(itemView.getContext());
                                    } else {
                                        showMenu2(itemView.getContext());
                                    }
                                } else {
                                    if (MyApplication.getAppData().getStrokeMode() != 2) {
                                        showMenu_1(itemView.getContext());
                                    } else {
                                        showMenu(itemView.getContext());
                                    }
                                }
                            }
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
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                new SignInUtil(itemView.getContext(), iMainActivity);
                            } else {
                                if (gStrokeList.getData().get(getAdapterPosition()).getColl().length() > 0) {
                                    iaStrokeAttractionList.handleCollectPlace(getAdapterPosition(), false);
                                } else {
                                    iaStrokeAttractionList.handleCollectPlace(getAdapterPosition(), true);
                                }
                            }
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
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                new SignInUtil(itemView.getContext(), iMainActivity);
                            } else {
                                iaStrokeAttractionList.handleSaveTrip(getAdapterPosition());
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(viewBar)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            iaStrokeAttractionList.handlePhotoClicked(getAdapterPosition());
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }

        private void showMenu(Context context) {
            PopupMenu popupMenu = new PopupMenu(context, imageViewList.get(0));
            popupMenu.getMenuInflater().inflate(R.menu.menu_stroke_attraction_list, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menuDeleteInStrokeAttractionList:
                            iaStrokeAttractionList.handleRemoveTrip(getAdapterPosition());
                            break;
                        case R.id.menuCollectInStrokeAttractionList:
                            iaStrokeAttractionList.handleCollectPlace(getAdapterPosition(), true);
                            break;
                        case R.id.menuSaveInStrokeAttractionList:
                            iaStrokeAttractionList.handleSaveTrip(getAdapterPosition());
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
        }

        private void showMenu2(Context context) {
            PopupMenu popupMenu = new PopupMenu(context, imageViewList.get(0));
            popupMenu.getMenuInflater().inflate(R.menu.menu_stroke_attraction_list_2, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menuDeleteInStrokeAttractionList2:
                            iaStrokeAttractionList.handleRemoveTrip(getAdapterPosition());
                            break;
                        case R.id.menuCollectInStrokeAttractionList2:
                            iaStrokeAttractionList.handleCollectPlace(getAdapterPosition(), false);
                            break;
                        case R.id.menuSaveInStrokeAttractionList2:
                            iaStrokeAttractionList.handleSaveTrip(getAdapterPosition());
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
        }

        private void showMenu_1(Context context) {
            PopupMenu popupMenu = new PopupMenu(context, imageViewList.get(0));
            popupMenu.getMenuInflater().inflate(R.menu.menu_stroke_attraction_list_1, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menuCollectInStrokeAttractionList:
                            iaStrokeAttractionList.handleCollectPlace(getAdapterPosition(), true);
                            break;
                        case R.id.menuSaveInStrokeAttractionList:
                            iaStrokeAttractionList.handleSaveTrip(getAdapterPosition());
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
        }

        private void showMenu2_1(Context context) {
            PopupMenu popupMenu = new PopupMenu(context, imageViewList.get(0));
            popupMenu.getMenuInflater().inflate(R.menu.menu_stroke_attraction_list_2_1, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.menuCollectInStrokeAttractionList2:
                            iaStrokeAttractionList.handleCollectPlace(getAdapterPosition(), false);
                            break;
                        case R.id.menuSaveInStrokeAttractionList2:
                            iaStrokeAttractionList.handleSaveTrip(getAdapterPosition());
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });
        }
    }
}
