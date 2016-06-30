package com.hjh.nooneknow.net;

import com.hjh.nooneknow.Config;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;

import okhttp3.FormBody;

/**
 * Created by HJH on 2016/6/30.
 */
public class TimeLine {
    public TimeLine(String phone_md5, String token, final int page, final int perpage, final SuccessCallback successCallback, final FailCallback failCallback) {
        RequestBody body=new FormBody.Builder()
                .add(Config.KEY_ACTION,Config.TIME_LINE)
                .add(Config.KEY_PHONE_MD5,phone_md5)
                .add(Config.KEY_TOKEN,token)
                .add(Config.KEY_PAGE,page+"")
                .add(Config.KEY_PERPAGE,perpage+"")
                .build();
        NetConnection.getmInstance().post(Config.SERVER, new NetConnection.ResultCallback() {
            @Override
            public void onSuccess(String s) {
                try {
                    JSONObject j=new JSONObject(s);
                    switch (j.getInt(Config.KEY_STATUS)){
                        case Config.STATUS_SUCCESS:
                            successCallback.OnSuccess(page,perpage,j.getJSONArray(Config.KEY_ITEMS));
                            break;
                        case Config.STATUS_INVALID_TOKEN:
                            failCallback.OnFail(Config.STATUS_INVALID_TOKEN);
                            break;
                        case Config.STATUS_FAIL:
                            failCallback.OnFail(Config.STATUS_FAIL);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    failCallback.OnFail(Config.STATUS_FAIL);
                }
            }

            @Override
            public void onFail(String s) {
                failCallback.OnFail(Config.STATUS_FAIL);
            }
        },body);
    }
    public interface SuccessCallback{
        void OnSuccess(int page, int per, JSONArray timeline);
    }
    public interface FailCallback{
        void OnFail(int errorCode);
    }
}
