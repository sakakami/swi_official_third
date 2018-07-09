package com.switube.www.swiofficialthird.map.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.home.view.IMainActivity;
import com.switube.www.swiofficialthird.info.view.InfoFragment;
import com.switube.www.swiofficialthird.map.adapter.SpinnerAdapter;
import com.switube.www.swiofficialthird.util.SharePreferencesUtil;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {


    public MessageFragment() {
        // Required empty public constructor
    }

    private Unbinder mUnbinder;
    @BindViews({R.id.textPostInMessage, R.id.textBackInMessage, R.id.textSelectedTagInMessage})
    List<TextView> mTextViews;
    @BindView(R.id.editMessageInMessage)
    EditText mEditMessage;
    @BindView(R.id.spinnerInMessage)
    Spinner mSpinner;
    @BindView(R.id.imageCameraInMessage)
    ImageView mImageView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mSpinner.setAdapter(new SpinnerAdapter(container.getContext()));
        mTextViews.get(2).setText(SharePreferencesUtil.getInstance().getSharedPreferences(container.getContext().getApplicationContext()).getString("selectedTag", "finish"));
        RxView.clicks(mTextViews.get(1))
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
        RxView.clicks(mTextViews.get(0))
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        SharePreferencesUtil.getInstance().getEditor(container.getContext().getApplicationContext()).putString("selectedTag", "finish");
                        FragmentManager.BackStackEntry backStackEntry = getFragmentManager().getBackStackEntryAt(1);
                        getFragmentManager().popBackStack(backStackEntry.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new InfoFragment()).commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        RxView.clicks(mImageView)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(Object o) {
                        getFragmentManager().beginTransaction().replace(R.id.layoutContainer, new PhotoFragment(), "photoFragment").addToBackStack("message").commit();
                    }

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onComplete() {}
                });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    private IMainActivity mIMainActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mIMainActivity = (IMainActivity)context;
    }

    private void handlePhotos() {
        Luban.with(getContext())
                .load(mIMainActivity.getSelectedPhoto())
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {}

                    @Override
                    public void onSuccess(File file) {

                    }

                    @Override
                    public void onError(Throwable e) {}
                })
                .launch();
    }
}
