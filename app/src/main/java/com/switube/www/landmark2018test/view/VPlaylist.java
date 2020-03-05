package com.switube.www.landmark2018test.view;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.landmark2018test.MyApplication;
import com.switube.www.landmark2018test.R;
import com.switube.www.landmark2018test.adapter.APlaylist;
import com.switube.www.landmark2018test.adapter.callback.IAPlaylist;
import com.switube.www.landmark2018test.presenter.PPlaylist;
import com.switube.www.landmark2018test.util.AlertDialogUtil;
import com.switube.www.landmark2018test.view.callback.IVPlaylist;

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
public class VPlaylist extends Fragment implements IAPlaylist, IVPlaylist {
    @BindViews({R.id.textBackInPlaylist, R.id.textTitleInPlaylist})
    List<TextView> textViewList;
    @BindView(R.id.recyclerViewInPlaylist)
    RecyclerView recyclerView;
    @BindView(R.id.searchViewInPlaylist)
    SearchView searchView;
    private PPlaylist pPlaylist;
    private Unbinder unbinder;
    private APlaylist aPlaylist;

    public VPlaylist() {
        pPlaylist = new PPlaylist(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_v_playlist, container, false);
        unbinder = ButterKnife.bind(this, view);
        searchView.setIconifiedByDefault(false);
        searchView.clearFocus();
        MyApplication.getAppData().setPlaylist(true);
        RxView.clicks(textViewList.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        MyApplication.getAppData().setCanRefreshList(false);
                        MyApplication.getAppData().setPlaylist(false);
                        getFragmentManager().popBackStack();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        aPlaylist = new APlaylist(this);
        MyApplication.getAppData().setCanRefreshList(true);
        recyclerView.setAdapter(aPlaylist);
        textViewList.get(1).setText(MyApplication.getAppData().getChannelName());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                pPlaylist.getSearchData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        init();
        return view;
    }

    @Override
    public void init() {
        int size = MyApplication.getAppData().getMusicEntities().size();
        List<String> name = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            name.add(MyApplication.getAppData().getMusicEntities().get(i).getName());
        }
        aPlaylist.refreshAdapter(name);
        searchView.setQuery("", false);
        searchView.clearFocus();
    }

    public void handleRefreshAdapter() {
        aPlaylist.notifyDataSetChanged();
    }

    @Override
    public void handleClick(int index) {
        MyApplication.getAppData().setPlayingIndex(index);
        String stid = MyApplication.getAppData().getMusicEntities().get(index).getStid();
        MyApplication.getAppData().getiFloatPlayerService().handleLoadVideo(stid, 0);
    }

    @Override
    public void showSearchError() {
        AlertDialogUtil
                .getInstance()
                .initDialogBuilder(getContext(), R.string.music_search_error, R.string.music_search_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
