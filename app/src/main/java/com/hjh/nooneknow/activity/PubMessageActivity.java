package com.hjh.nooneknow.activity;

import android.support.v4.app.Fragment;

import com.hjh.nooneknow.fragment.PubMessageFragment;

/**
 * Created by HJH on 2016/6/29.
 */
public class PubMessageActivity extends SingleFragmentActivity {
    @Override
    protected Fragment CreateFragment() {
        return new PubMessageFragment();
    }
}
