package com.switube.www.swiofficialthird.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import org.jetbrains.annotations.Contract;

public class AlertDialogUtil {
    private AlertDialog mAlertDialog;
    private AlertDialog.Builder mBuilder;
    private AlertDialogUtil() {}

    @Contract(pure = true)
    public static AlertDialogUtil getInstance() {
        return AlertDialogHolder.INSTANCE;
    }

    private AlertDialog.Builder getsBuilder(Context context) {
        if (mBuilder == null) {
            mBuilder = new AlertDialog.Builder(context);
        }
        return mBuilder;
    }

    public void initDialogBuilder(Context context, int resourceId, int positiveResourceId,
                                         DialogInterface.OnClickListener positiveClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setMessage(resourceId)
                .setPositiveButton(positiveResourceId, positiveClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, String message, String positiveMessage,
                                         DialogInterface.OnClickListener positiveClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setMessage(message)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, int message, String[] items,
                                         DialogInterface.OnClickListener itemClick, String positiveMessage,
                                         DialogInterface.OnClickListener positiveClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setMessage(message)
                .setItems(items, itemClick)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, int resourceId, int positiveResourceId,
                                         DialogInterface.OnClickListener positiveClickListener,
                                         int negativeResourceId, DialogInterface.OnClickListener negativeClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setMessage(resourceId)
                .setPositiveButton(positiveResourceId, positiveClickListener)
                .setNegativeButton(negativeResourceId, negativeClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, int resourceId, String positiveMessage,
                                         DialogInterface.OnClickListener positiveClickListener,
                                         String negativeMessage, DialogInterface.OnClickListener negativeClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setMessage(resourceId)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setNegativeButton(negativeMessage, negativeClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, String message, String positiveMessage,
                                         DialogInterface.OnClickListener positiveClickListener,
                                         String negativeMessage, DialogInterface.OnClickListener negativeClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setMessage(message)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setNegativeButton(negativeMessage, negativeClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, String[] items,
                                         DialogInterface.OnClickListener itemClick, String positiveMessage,
                                         DialogInterface.OnClickListener positiveClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setItems(items, itemClick)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, int message, String[] items,
                                         DialogInterface.OnClickListener itemClick, String positiveMessage,
                                         DialogInterface.OnClickListener positiveClickListener,
                                         String negativeMessage, DialogInterface.OnClickListener negativeClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setMessage(message)
                .setItems(items, itemClick)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setNegativeButton(negativeMessage, negativeClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, View view, String positiveMessage,
                                         DialogInterface.OnClickListener positiveClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setView(view)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, View view, String message,
                                  String positiveMessage, DialogInterface.OnClickListener positiveClickListener,
                                  String negativeMessage, DialogInterface.OnClickListener negativeClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setView(view)
                .setTitle(message)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setNegativeButton(negativeMessage, negativeClickListener)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void showAlertDialog() {
        mAlertDialog.show();
    }

    private void clearAlertDialog() {
        if (mAlertDialog != null) {
            mAlertDialog.cancel();
            //sAlertDialog.dismiss();
            //mAlertDialog = null;
            mBuilder = null;
        }
    }

    private static class AlertDialogHolder {
        private static final AlertDialogUtil INSTANCE = new AlertDialogUtil();
    }
}