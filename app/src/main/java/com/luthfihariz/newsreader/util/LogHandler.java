package com.luthfihariz.newsreader.util;

import android.util.Log;

import com.luthfihariz.newsreader.BuildConfig;


/**
 * Created by Luthfi Haris on 21/09/2016.
 * <p>
 * Log handler class, it won't print log if build type is not debug
 */
public final class LogHandler {

    public static void logDebug(String tag, String message) {
        Log.d(tag, message);
    }

    public static void logInfo(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.i(tag, message);
        }
    }

    public static void logWarning(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.d(tag, message);
        }
    }

    public static void logError(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.d(tag, message);
        }
    }

    public static void logVerbose(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.d(tag, message);
        }
    }

    public static void logException(String tag, Throwable throwable) {
        if (BuildConfig.DEBUG && throwable != null) {
            Log.e(tag, throwable.getMessage(), throwable);
        }
    }

    public static void logWtf(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.wtf(tag, message);
        }
    }

}
