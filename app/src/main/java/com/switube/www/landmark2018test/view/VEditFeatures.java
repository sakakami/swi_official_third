package com.switube.www.landmark2018test.view;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AEditFeatures;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.presenter.PEditFeatures;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IMainActivity;
import com.switube.www.landmark2018test.view.callback.IVEditFeatures;

import org.jetbrains.annotations.NotNull;

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
public class VEditFeatures extends Fragment implements IVEditFeatures {
    private PEditFeatures pEditFeatures;
    public VEditFeatures() {
        pEditFeatures = new PEditFeatures(this);
    }

    @BindViews({R.id.textBackInEditFeatures, R.id.textNextInEditFeatures})
    List<TextView> textViewList;
    @BindView(R.id.recyclerInEditFeatures)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private AEditFeatures aEditFeatures;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_edit_features, container, false);
        unbinder = ButterKnife.bind(this, view);
        initClicks();
        aEditFeatures = new AEditFeatures();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(container.getContext(), 10, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                String title = aEditFeatures.geteFeaturesList().get(position).getTitle();
                String from = aEditFeatures.geteFeaturesList().get(position).getFrom();
                if (from.equals("t") || from.equals("term") || from.equals("n")) {
                    return 10;
                } else {
                    if (title.length() <= 2) {
                        return 3;
                    } else if (title.length() <= 4) {
                        return 4;
                    } else {
                        return 5;
                    }
                }
            }
        });
        recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 8, 0, 4, 4));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(aEditFeatures);
        pEditFeatures.getStyleData(MyApplication.getAppData().getMsid());
        return view;
    }

    private IMainActivity iMainActivity;
    @Override
    public void onAttach(@NotNull Context context) {
        super.onAttach(context);
        iMainActivity = (IMainActivity)context;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void init(List<EFeatures> eFeaturesList) {
        aEditFeatures.init(eFeaturesList);
    }

    @Override
    public void finishSend() {
        getParentFragmentManager().popBackStack("Info", FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    private void initClicks() {
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
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
                        pEditFeatures.sendEditData(aEditFeatures.geteFeaturesList(), iMainActivity.getAttractionId());
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
