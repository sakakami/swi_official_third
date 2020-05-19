package com.switube.www.landmark2018test.view;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ASwapStroke;
import com.switube.www.landmark2018test.entity.ESwapData;
import com.switube.www.landmark2018test.presenter.PSwapStroke;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IVSwapStroke;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class VSwapStroke extends Fragment implements IVSwapStroke {
    private PSwapStroke pSwapStroke;
    private List<Integer> fromPositionList;
    private List<Integer> toPositionList;
    public VSwapStroke() {
        pSwapStroke = new PSwapStroke(this);
        fromPositionList = new ArrayList<>();
        toPositionList = new ArrayList<>();
    }

    @BindViews({R.id.textBackInSwapStroke, R.id.textOKInSwapStroke})
    List<TextView> textViewList;
    @BindView(R.id.recyclerInSwapStroke)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private ASwapStroke aSwapStroke;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_swap_stroke, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.addItemDecoration(new ItemDecorationUtil(container.getContext(), 16, 0, 0, 0));
        aSwapStroke = new ASwapStroke();
        recyclerView.setAdapter(aSwapStroke);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0);
            }

            @Override
            public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder target) {
                int fromPosition = viewHolder.getAdapterPosition();
                int toPosition = target.getAdapterPosition();
                fromPositionList.add(fromPosition);
                toPositionList.add(toPosition);
                Collections.swap(aSwapStroke.getgStrokeList(), fromPosition, toPosition);
                aSwapStroke.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int direction) {}

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                    viewHolder.itemView.setBackgroundColor(Color.parseColor("#F5F5F5"));
                }
                super.onSelectedChanged(viewHolder, actionState);
            }

            @Override
            public boolean isLongPressDragEnabled() {
                return super.isLongPressDragEnabled();
            }

            @Override
            public void clearView(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(0);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        pSwapStroke.init();
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setCollectionPage(false);
                        getParentFragmentManager().popBackStack();
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
                        if (aSwapStroke.getItemCount() > 0) {
                            pSwapStroke.sendMovedData(aSwapStroke.getgStrokeList(), MyApplication.getAppData().getUrid(), fromPositionList, toPositionList);
                        } else {
                            getParentFragmentManager().popBackStackImmediate();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void init(List<ESwapData> eSwapDataList) {
        aSwapStroke.init(eSwapDataList);
    }

    @Override
    public void handleFinishSave() {
        MyApplication.getAppData().setCollectionPage(false);
        getParentFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
