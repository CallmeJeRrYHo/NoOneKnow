package com.hjh.nooneknow.net;
import com.hjh.nooneknow.Config;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by HJH on 2016/6/30.
 */
public class UpdateContacts {
    public  UpdateContacts(String phone_md5, String token, String contacts, final SuccessCallback successCallback, final FailCallback failCallback){

        final RequestBody body=new  FormBody.Builder()
                .add(Config.KEY_ACTION,Config.UPDATE_CONTACTS)
                .add(Config.KEY_PHONE_MD5,phone_md5)
                .add(Config.KEY_TOKEN,token)
                .add(Config.KEY_CONTACTS,contacts)
                .build();
        NetConnection.getmInstance().post(Config.SERVER, new NetConnection.ResultCallback() {
            @Override
            public void onSuccess(String s) {

                try {
                    JSONObject j=new JSONObject(s);
                    switch (j.getInt(Config.KEY_STATUS)){
                        case Config.STATUS_SUCCESS:
                            successCallback.onSuccess();
                            break;
                        case Config.STATUS_INVALID_TOKEN:
                            failCallback.onFail(Config.STATUS_INVALID_TOKEN);
                            break;
                        case Config.STATUS_FAIL:
                            failCallback.onFail(Config.STATUS_FAIL);
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    failCallback.onFail(Config.STATUS_FAIL);
                }
            }

            @Override
            public void onFail(String s) {
                failCallback.onFail(Config.STATUS_FAIL);
            }
        },body);
    }
    public  interface SuccessCallback{
        void onSuccess();
    }
    public  interface FailCallback{
        void onFail(int errorCode);
    }

}
