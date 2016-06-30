package com.hjh.nooneknow.net;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by HJH on 2016/6/29.
 */
public class NetConnection {
    private static NetConnection mInstance;
    private OkHttpClient mClient;
    public NetConnection(){
        mClient=new OkHttpClient();
    }
    public static NetConnection getmInstance(){
        if (mInstance==null)
            mInstance=new NetConnection();
            return mInstance;
    }
    public void get(final String url, final ResultCallback reslutCallback){
        Request r=new Request.Builder().url(url).build();
        new AsyncTask<Request,Void,String>(){
            @Override
            protected String doInBackground(Request... params) {
                Response response;
                String result=null;
                try {
                    response=mClient.newCall(params[0]).execute();
                    result=response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;

            }

            @Override
            protected void onPostExecute(String s) {
                if (s!=null){
                    reslutCallback.onSuccess(s);
                }else{
                    reslutCallback.onFail(s);
                }
                super.onPostExecute(s);
            }
        }.execute(r);

    }

    public void post(final String url, final ResultCallback resultCallback, final RequestBody body){
        new AsyncTask<Void,Void,String>(){
            Request request=new Request.Builder()
                    .url(url)
                    .post(body)
                    .build();
            @Override
            protected String doInBackground(Void... params) {
                String result=null;
                Response r;
                try {
                    r=mClient.newCall(request).execute();
                    result=r.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s==null)
                    resultCallback.onFail(s);
                else
                    resultCallback.onSuccess(s);
                super.onPostExecute(s);
            }
        }.execute();
    }
    public interface ResultCallback{
        void onSuccess(String s);
        void onFail(String s);
    }
}
