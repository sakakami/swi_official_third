package com.switube.www.landmark2018test.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.AMore;
import com.switube.www.landmark2018test.adapter.callback.IAMore;
import com.switube.www.landmark2018test.gson.GMusicRadio;
import com.switube.www.landmark2018test.presenter.PMore;
import com.switube.www.landmark2018test.util.ItemDecorationUtil;
import com.switube.www.landmark2018test.view.callback.IVMore;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class VMore extends Fragment implements IVMore, IAMore {
    @BindView(R.id.textBackInMore)
    TextView textView;
    @BindView(R.id.recyclerInMore)
    RecyclerView recyclerView;
    private PMore pMore;
    private Unbinder unbinder;
    private AMore aMore;
    private GMusicRadio gMusicRadio;

    public VMore() {
        pMore = new PMore(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_more, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new GridLayoutManager(container.getContext(), 3));
        recyclerView.addItemDecoration(new ItemDecorationUtil(container.getContext(), 0, 0, 12, 0));
        aMore = new AMore(this);
        recyclerView.setAdapter(aMore);
        pMore.getMoreData();
        RxView.clicks(textView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Object o) {
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void handleClick(int index) {
        int taSize = gMusicRadio.getTaData().size();
        boolean canNext;
        if (index < taSize) {
            String taid = gMusicRadio.getTaData().get(index).getTaid();
            if (taid.equals("YT0egbR6iXn")) {
                MyApplication.getAppData().setTaid(taid);
                MyApplication.getAppData().setHotKeyMode(1);
                MyApplication.getAppData().setMobileMusicMode(true);
                canNext = true;
            } else {
                canNext = false;
            }
        } else {
            String raid = gMusicRadio.getRaData().get(index - taSize).getRaid();
            if (raid.equals("WebSite")) {
                MyApplication.getAppData().setRaid(raid);
                MyApplication.getAppData().setHotKeyMode(2);
                MyApplication.getAppData().setMobileMusicMode(false);
                canNext = true;
            } else {
                canNext = false;
            }
        }
        if (canNext) {
            MyApplication.getAppData().setDefaultHotKye(false);
            MyApplication.getAppData().setNormalMap(true);
            getFragmentManager().popBackStack();
        } else {
            Toast.makeText(getContext(), R.string.float_message_coming_soon, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void init(GMusicRadio gMusicRadio, List<String> name, List<String> img) {
        this.gMusicRadio = gMusicRadio;
        aMore.refreshData(name, img);
    }
}
