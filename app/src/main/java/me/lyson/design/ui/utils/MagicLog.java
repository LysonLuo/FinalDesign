package me.lyson.design.ui.utils;

import android.util.Log;

/**
 * Created by Lyson on 15/3/27.
 */
public class MagicLog {

    private static boolean IS_DEBUG = true;

    public static void setDebug(boolean debug) {
        IS_DEBUG = debug;
    }

    public static void d(String tag, String msg) {
        if (!IS_DEBUG) return;
        Log.d(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (!IS_DEBUG) return;
        Log.v(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (!IS_DEBUG) return;
        Log.i(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (!IS_DEBUG) return;
        Log.e(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (!IS_DEBUG) return;
        Log.w(tag, msg);
    }

}
