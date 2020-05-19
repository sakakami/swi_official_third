package com.switube.www.landmark2018test.view;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.ACarbonDetail;
import com.switube.www.landmark2018test.entity.ECarbonDetail;
import com.switube.www.landmark2018test.presenter.PCarbon;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IVCarbon;

import java.util.ArrayList;
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
public class VCarbon extends Fragment implements IVCarbon {
    private PCarbon pCarbon;
    public VCarbon() { pCarbon = new PCarbon(this); }

    @BindViews({R.id.textBackInCarbon, R.id.textOkInCarbon})
    List<TextView> textViews;
    @BindView(R.id.recyclerInCarbon)
    RecyclerView recyclerView;

    private Unbinder unbinder;
    private ACarbonDetail adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_carbon, container, false);
        unbinder = ButterKnife.bind(this, view);
        adapter = new ACarbonDetail();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new ItemDecorationUtil(getContext(), 8, 0, 8, 8));
        recyclerView.setAdapter(adapter);
        RxView.clicks(textViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) { getParentFragmentManager().popBackStack(); }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(textViews.get(1))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) { getParentFragmentManager().popBackStack(); }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        pCarbon.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void init(ArrayList<ECarbonDetail> list) {
        adapter.refresh(list);
    }
}
