package com.example.carl.urlrooter;

import android.util.Log;

/**
 * Created by Carl on 2017/1/19.
 */
public class LogUtil {
    public static final String TAG = "rooter";

    private static boolean IsDebug = !"release".equals(BuildConfig.BUILD_TYPE);

    public static void v(String s, String msg) {
        if (IsDebug)
            android.util.Log.v(TAG, msg);
    }


    public static void v(String msg, Throwable t) {
        if (IsDebug)
            android.util.Log.v(TAG, msg, t);
    }

    public static void d(String log){
        if(IsDebug){
            Log.d(TAG, log);
        }
    }

    public static void i(String msg) {
        if (IsDebug)
            android.util.Log.i(TAG, msg);
    }

    public static void i(String msg, Throwable t) {
        if (IsDebug)
            android.util.Log.i(TAG, msg, t);
    }

    public static void w(String msg) {
        if (IsDebug)
            android.util.Log.w(TAG, msg);
    }

    public static void w(String msg, Throwable t) {
        if (IsDebug)
            android.util.Log.w(TAG, msg, t);
    }

    public static void e(String msg) {
        if (IsDebug)
            android.util.Log.e(TAG, msg);
    }

    public static void e(String msg, Throwable t) {
        if (IsDebug)
            android.util.Log.e(TAG, msg, t);
    }

    public static void e(Throwable t) {
        if (IsDebug)
            android.util.Log.e(TAG, android.util.Log.getStackTraceString(t));
    }

}
