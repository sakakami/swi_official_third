package com.switube.www.landmark2018test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.callback.IAPlacePhotoList;
import com.switube.www.landmark2018test.adapter.callback.IAStroke;
import com.switube.www.landmark2018test.gson.GPushStroke;
import com.switube.www.landmark2018test.gson.GSaveList;
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

public class AStroke extends RecyclerView.Adapter<AStroke.ViewHolder> {
    private IAStroke iaStroke;
    private GSaveList gSaveList;
    private List<String> photo = new ArrayList<>();
    private IAPlacePhotoList iaPlacePhotoList;
    private IMainActivity iMainActivity;
    public AStroke(IAStroke iaStroke, IAPlacePhotoList iaPlacePhotoList, IMainActivity iMainActivity) {
        this.iaStroke = iaStroke;
        photo.add("null");
        this.iaPlacePhotoList = iaPlacePhotoList;
        this.iMainActivity = iMainActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_item_stroke, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String count;
        switch (MyApplication.getAppData().getStrokeMode()) {
            case 1:
                break;
            case 2:
                holder.textViewList.get(0).setText(gSaveList.getData().get(position).getTitle());
                holder.textViewList.get(3).setText(gSaveList.getData().get(position).getCity());
                count = gSaveList.getData().get(position).getCut() + holder.itemView.getContext().getString(R.string.stroke_place);
                holder.textViewList.get(1).setText(count);
                if (gSaveList.getData().get(position).getPhoto().size() > 0) {
                    holder.aPlacePhotoList.refreshAdapter(gSaveList.getData().get(position).getPhoto(), true, position);
                } else {
                    holder.aPlacePhotoList.refreshAdapter(photo, true, position);
                }
                break;
            default:
                holder.textViewList.get(0).setText(gPushStroke.getData().get(position).getTitle());
                holder.textViewList.get(1).setText(gPushStroke.getData().get(position).getManame());
                holder.textViewList.get(2).setText(gPushStroke.getData().get(position).getCity());
                count = gPushStroke.getData().get(position).getCut() + holder.itemView.getContext().getString(R.string.stroke_place);
                holder.textViewList.get(3).setText(count);
                if (gPushStroke.getData().get(position).getPhoto().size() > 0) {
                    holder.aPlacePhotoList.refreshAdapter(gPushStroke.getData().get(position).getPhoto(), false, position);
                } else {
                    holder.aPlacePhotoList.refreshAdapter(photo, false, position);
                }
                break;
        }
    }

    private int size = 0;
    @Override
    public int getItemCount() {
        return size;
    }

    public void init(GSaveList gSaveList) {
        this.gSaveList = gSaveList;
        size = gSaveList.getData().size();
        notifyDataSetChanged();
    }

    private GPushStroke gPushStroke;
    public void init(GPushStroke gPushStroke) {
        this.gPushStroke = gPushStroke;
        size = gPushStroke.getData().size();
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageAInItemStroke)
        ImageView imageView;
        @BindViews({R.id.textTitleInItemStroke, R.id.textNameInItemStroke,
                R.id.textPlaceInItemStroke, R.id.textCountInItemStroke})
        List<TextView> textViewList;
        @BindView(R.id.viewBarInItemStroke)
        View viewBar;
        @BindView(R.id.recyclerInItemStroke)
        RecyclerView recyclerView;
        APlacePhotoList aPlacePhotoList;
        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            switch (MyApplication.getAppData().getStrokeMode()) {
                case 1:
                    break;
                case 2:
                    textViewList.get(2).setVisibility(View.GONE);
                    imageView.setImageResource(R.drawable.m_4_v11);
                    break;
                default:
                    imageView.setImageResource(R.drawable.add_v1_2);
                    break;
            }
            LinearLayoutManager layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
            recyclerView.addItemDecoration(new ItemDecorationUtil(itemView.getContext(), 0, 0, 0, 4));
            recyclerView.setLayoutManager(layoutManager);
            aPlacePhotoList = new APlacePhotoList(iaPlacePhotoList);
            recyclerView.setAdapter(aPlacePhotoList);
            RxView.clicks(imageView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            if (SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals("null")) {
                                new SignInUtil(itemView.getContext(), iMainActivity);
                            } else {
                                switch (MyApplication.getAppData().getStrokeMode()) {
                                    case 1:
                                        break;
                                    case 2:
                                        showMenu(itemView.getContext());
                                        break;
                                    default:
                                        iaStroke.handleAddStroke(getAdapterPosition());
                                        break;
                                }
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
            RxView.clicks(textViewList.get(0))
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Object>() {
                        @Override
                        public void onSubscribe(Disposable d) {}

                        @Override
                        public void onNext(Object o) {
                            switch (MyApplication.getAppData().getStrokeMode()) {
                                case 1:
                                    break;
                                case 2:
                                    if (Integer.parseInt(gSaveList.getData().get(getAdapterPosition()).getCut()) > 0) {
                                        iaStroke.handleShowAttraction(getAdapterPosition());
                                    } else {
                                        Toast.makeText(itemView.getContext(), R.string.float_message_add_one, Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                default:
                                    if (Integer.parseInt(gPushStroke.getData().get(getAdapterPosition()).getCut()) > 0) {
                                        iaStroke.handleShowAttraction(getAdapterPosition());
                                    } else {
                                        Toast.makeText(itemView.getContext(), R.string.float_message_add_one, Toast.LENGTH_SHORT).show();
                                    }
                                    break;
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
                            switch (MyApplication.getAppData().getStrokeMode()) {
                                case 1:
                                    break;
                                case 2:
                                    if (Integer.parseInt(gSaveList.getData().get(getAdapterPosition()).getCut()) > 0) {
                                        iaStroke.handleShowAttraction(getAdapterPosition());
                                    } else {
                                        Toast.makeText(itemView.getContext(), R.string.float_message_add_one, Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                                default:
                                    if (Integer.parseInt(gPushStroke.getData().get(getAdapterPosition()).getCut()) > 0) {
                                        iaStroke.handleShowAttraction(getAdapterPosition());
                                    } else {
                                        Toast.makeText(itemView.getContext(), R.string.float_message_add_one, Toast.LENGTH_SHORT).show();
                                    }
                                    break;
                            }
                        }

                        @Override
                        public void onError(Throwable e) {}

                        @Override
                        public void onComplete() {}
                    });
        }

        private void showMenu(Context context) {
            PopupMenu popupMenu = new PopupMenu(context, imageView);
            popupMenu.getMenuInflater().inflate(R.menu.menu_my_stroke, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    if (menuItem.getItemId() == R.id.menuDeleteInMyStroke) {
                        iaStroke.handleDeleteStroke(getAdapterPosition());
                    } else {
                        iaStroke.handleEditTitle(getAdapterPosition());
                    }
                    return true;
                }
            });
        }
    }
}
