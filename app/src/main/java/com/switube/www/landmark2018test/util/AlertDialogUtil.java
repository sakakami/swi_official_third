package com.switube.www.landmark2018test.util;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

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
                                         DialogInterface.OnClickListener positiveClickListener, boolean canCancelable) {
        clearAlertDialog();
        getsBuilder(context)
                .setView(view)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setCancelable(canCancelable);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, View view, String title, String positiveMessage,
                                  DialogInterface.OnClickListener positiveClickListener, boolean canCancelable) {
        clearAlertDialog();
        getsBuilder(context)
                .setView(view)
                .setTitle(title)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setCancelable(canCancelable);
        mAlertDialog = getsBuilder(context).create();
    }

    public void initDialogBuilder(Context context, View view,
                                  String positiveMessage, DialogInterface.OnClickListener positiveClickListener,
                                  String negativeMessage, DialogInterface.OnClickListener negativeClickListener) {
        clearAlertDialog();
        getsBuilder(context)
                .setView(view)
                .setPositiveButton(positiveMessage, positiveClickListener)
                .setNegativeButton(negativeMessage, negativeClickListener)
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

    public void initDialogBuilder(Context context, View view) {
        clearAlertDialog();
        getsBuilder(context)
                .setView(view)
                .setCancelable(false);
        mAlertDialog = getsBuilder(context).create();
    }

    public void showAlertDialog() {
        mAlertDialog.show();
    }

    public void clearAlertDialog() {
        if (mAlertDialog != null) {
            mAlertDialog.cancel();
            mBuilder = null;
        }
    }

    private static class AlertDialogHolder {
        private static final AlertDialogUtil INSTANCE = new AlertDialogUtil();
    }
}
