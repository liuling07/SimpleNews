package com.lauren.simplenews.utils;

import android.util.Log;

/**
 * Description : 日子工具类
 * Author : lauren
 * Email  : lauren.liuling@gmail.com
 * Blog   : http://www.liuling123.com
 * Date   : 15/12/14
 */
public class LogUtils {
    public static final boolean DEBUG = true;
    private static final String TAG = "SimpleNews";
    public static void v(String tag, String message) {
        if(DEBUG) {
            Log.v(tag, message);
        }
    }
    public static void v( String message) {
        if(DEBUG) {
            Log.v(TAG, message);
        }
    }
    public static void d(String tag, String message) {
        if(DEBUG) {
            Log.d(tag, message);
        }
    }
      public static void d( String message) {
        if(DEBUG) {
            Log.d(TAG, message);
        }
    }
    public static void i(String tag, String message) {
        if(DEBUG) {
            Log.i(tag, message);
        }
    }
     public static void i( String message) {
        if(DEBUG) {
            Log.i(TAG, message);
        }
    }
    public static void w(String tag, String message) {
        if(DEBUG) {
            Log.w(tag, message);
        }
    }
    public static void w( String message) {
        if(DEBUG) {
            Log.w(TAG, message);
        }
    }
    public static void e(String tag, String message) {
        if(DEBUG) {
            Log.e(tag, message);
        }
    }
      public static void e( String message) {
        if(DEBUG) {
            Log.e(TAG, message);
        }
    }
    public static void e(String tag, String message, Exception e) {
        if(DEBUG) {
            Log.e(tag, message, e);
        }
    }
    public static void e(String message, Exception e) {
        if(DEBUG) {
            Log.e(TAG, message, e);
        }
    }
}
