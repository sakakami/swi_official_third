package com.switube.www.landmark2018test.util;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogToFile {
    private static String logPath = null;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.TAIWAN);
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
    private static Date date = new Date();

    public static void init(Context context) {
        logPath = getFilePath(context) + "/Logs";
    }

    private static String getFilePath(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.MEDIA_MOUNTED) || !Environment.isExternalStorageRemovable()) {
            return context.getExternalFilesDir(null).getPath();
        } else {
            return context.getFilesDir().getPath();
        }
    }

    private static final char ERROR = 'e';
    public static void e(String tag, String msg) {
        writeToFile(tag, msg);
    }

    private static void writeToFile(String tag, String msg) {
        if (null == logPath) {
            return;
        }

        String fileName = logPath + "/log_" + simpleDateFormat.format(new Date()) + ".txt";
        String log = dateFormat.format(date) + " " + tag + " " + msg + "\n";

        File file = new File(logPath);
        if (!file.exists()) {
            file.mkdirs();
        }

        FileOutputStream fos = null;
        BufferedWriter bw = null;

        try {
            fos = new FileOutputStream(fileName, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
