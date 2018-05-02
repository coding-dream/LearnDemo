package com.gomo.learndemo.util;

import android.util.Log;

import com.gomo.learndemo.BuildConfig;

public class LogUtils {

    private static boolean debug = BuildConfig.DEBUG;

    private static final String TAG = "le";

    private static final String PRELINE = "=================[ ";

    private static final String SUFLINE = " ]==================";

    public static void d(String msg) {
        if (debug) {
            d(TAG, PRELINE + msg + SUFLINE);
        }
    }
    public static void v(String tag, String msg) {
        if (debug) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (debug) {
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (debug) {
            Log.e(tag, msg);
        }
    }

    public static void setDebug(boolean isDebug) {
        debug = isDebug;
    }
}