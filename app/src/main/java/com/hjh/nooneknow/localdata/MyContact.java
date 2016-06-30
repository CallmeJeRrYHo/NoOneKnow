package com.hjh.nooneknow.localdata;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.hjh.nooneknow.Config;
import com.hjh.nooneknow.utils.Md5Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by HJH on 2016/6/29.
 */
public class MyContact {
    public static String getContact(Context context){
        Cursor c=context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
        String phoneNum;
        JSONArray ja=new JSONArray();
        JSONObject j;
        while (c.moveToNext()){
            phoneNum=c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (phoneNum.startsWith("+86"))
                phoneNum=phoneNum.substring(3);
            j=new JSONObject();
            try {
                j.put(Config.KEY_PHONE_MD5, Md5Utils.md5(phoneNum));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ja.put(j);
        }
        return ja.toString();
    }
}
