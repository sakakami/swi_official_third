package com.switube.www.swiofficialthird.map.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerViewAdapter;
import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.map.InterfaceAttraction;
import com.switube.www.swiofficialthird.map.adapter.AttractionAdapter;
import com.switube.www.swiofficialthird.util.SharePreferencesUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttractionSelectFragment extends Fragment implements InterfaceAttraction {

    public AttractionSelectFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attraction_select, container, false);
        Log.e("create", "view");
        TextView textBack = view.findViewById(R.id.textBackInAttractionSelect);
        TextView textTitle = view.findViewById(R.id.textTitleInAttractionSelect);
        SearchView searchView = view.findViewById(R.id.searchInAttractionSelect);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerInAttractionSelect);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(new AttractionAdapter(container.getContext(), this));
        TextView textCreate = view.findViewById(R.id.textCreateInAttractionSelect);
        ImageView imageCancel = view.findViewById(R.id.imageCancelInAttractionSelect);
        RxView.clicks(textBack)
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
        return view;
    }

    @Override
    public void handleSwitch() {
        SharePreferencesUtil.getInstance().getEditor(this.getContext().getApplicationContext()).putString("selectedTag", "finish").apply();
        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new TagFragment()).addToBackStack("attraction").commit();
    }
}
