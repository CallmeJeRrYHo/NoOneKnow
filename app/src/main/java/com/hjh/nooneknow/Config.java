package com.hjh.nooneknow;

import android.content.Context;

/**
 * Created by HJH on 2016/6/29.
 */
public class Config {
    public static final String KEY_TOKEN = "token";
    private static final String APP_ID = "com.hjh.nooneknow";
    public static final String SERVER="http://192.168.0.100:8080/api.jsp";
    public static final String KEY_STATUS = "status";
    public static final String KEY_PHONE_MD5 = "phone_md5";
    public static final String KEY_CODE = "code";
    public static final int STATUS_SUCCESS = 1;
    public static final int STATUS_FAIL = 0;
    public static final String KEY_ACTION = "action";
    public static final String LOGIN = "login";
    public static final String KEY_PHONE ="phone" ;
    public static final String UPDATE_CONTACTS = "upload_contacts";
    public static final String KEY_CONTACTS = "contacts";
    public static final int STATUS_INVALID_TOKEN = 2;
    public static final String TIME_LINE = "timeline";
    public static final String KEY_PAGE = "page";
    public static final String KEY_PERPAGE = "perpage";
    public static final String KEY_ITEMS = "items";

    public static String getCacheToken(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_TOKEN,null);
    }
    public static void saveCacheToken(Context context,String token){
        context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_TOKEN,token)
                .commit();
    }
    public static String getPhone(Context context){
        return context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE).getString(KEY_PHONE,null);
    }
    public static void savePhone(Context context,String phone){
        context.getSharedPreferences(APP_ID,Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_PHONE,phone)
                .commit();
    }
}
