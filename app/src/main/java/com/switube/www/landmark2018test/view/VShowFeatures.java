package com.switube.www.landmark2018test.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.adapter.AShowFeatures;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.entity.EFeatures;
import com.switube.www.landmark2018test.presenter.PShowFeatures;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.util.SharePreferencesUtil;
import com.switube.www.landmark2018test.view.callback.IVShowFeatures;

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
 *
 */
public class VShowFeatures extends Fragment implements IVShowFeatures {
    private PShowFeatures pShowFeatures;
    public VShowFeatures() {
        pShowFeatures = new PShowFeatures(this);
    }

    @BindViews({R.id.textBackInShowFeatures, R.id.textEditInShowFeatures})
    List<TextView> textViewList;
    @BindView(R.id.recyclerInShowFeatures)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private AShowFeatures aShowFeatures;
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_show_features, container, false);
        unbinder = ButterKnife.bind(this, view);
        initClick();
        if (!SharePreferencesUtil.getInstance().getSharedPreferences().getString("userMaid", "null").equals(MyApplication.getAppData().getgInfoData().getData().get(0).getMaid())) {
            textViewList.get(1).setVisibility(View.GONE);
        }
        aShowFeatures = new AShowFeatures();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(container.getContext(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                String from = aShowFeatures.geteFeaturesList().get(position).getFrom();
                if (from.equals("t") || from.equals("term") || from.equals("n")) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.addItemDecoration(new ItemDecorationUtil(container.getContext(), 8, 0, 4, 4));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(aShowFeatures);
        pShowFeatures.getStyleData(MyApplication.getAppData().getMsid(), MyApplication.getAppData().getMscid(), MyApplication.getAppData().getMtidList(), MyApplication.getAppData().getMstidList());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void init(List<EFeatures> eFeaturesList) {
        aShowFeatures.init(eFeaturesList);
    }

    private void initClick() {
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getFragmentManager().popBackStack();
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
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new VEditFeatures()).addToBackStack("ShowFeatures").commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
    }
}
