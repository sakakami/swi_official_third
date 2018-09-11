package com.switube.www.swiofficialthird.home.view;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.switube.www.swiofficialthird.R;
import com.switube.www.swiofficialthird.home.presenter.LogoPresenter;
import com.switube.www.swiofficialthird.map.view.MapFragment;
import com.switube.www.swiofficialthird.util.AlertDialogUtil;
import com.switube.www.swiofficialthird.util.AppConstant;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

public class LogoFragment extends Fragment implements ILogoFragment {
    private LogoPresenter mLogoPresenter;
    public LogoFragment() {
        mLogoPresenter = new LogoPresenter(this);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mLogoPresenter.checkNetworkState(container.getContext().getApplicationContext())) {
            //mLogoPresenter.getData(container.getContext().getApplicationContext());
            handleFinishUpdate();
        } else {
            showNetworkMessage();
        }
        return inflater.inflate(R.layout.fragment_logo, container, false);
    }

    private void showNetworkMessage() {
        AlertDialogUtil
                .getInstance()
                .initDialogBuilder(
                        getContext(),
                        R.string.network_error_message,
                        R.string.network_error_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //((MainActivity) Objects.requireNonNull(getContext())).finish();
                                Objects.requireNonNull(getActivity()).onBackPressed();
                            }
                        }
                );
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void showUpgradeMessage(String[] words) {
        AlertDialogUtil
                .getInstance()
                .initDialogBuilder(
                        getContext(),
                        words[0],
                        words[1],
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(AppConstant.APP_URI));
                                startActivity(intent);
                                Objects.requireNonNull(getActivity()).onBackPressed();
                            }
                        },
                        words[2],
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Objects.requireNonNull(getActivity()).onBackPressed();
                            }
                        }
                );
        AlertDialogUtil.getInstance().showAlertDialog();
    }

    @Override
    public void handleFinishUpdate() {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layoutContainer, new MapFragment()).commit();
    }
}
