package com.hjh.nooneknow.activity;

import android.support.v4.app.Fragment;

import com.hjh.nooneknow.fragment.LoginFragment;

/**
 * Created by HJH on 2016/6/29.
 */
public class LoginActivity extends SingleFragmentActivity {
    @Override
    protected Fragment CreateFragment() {
        return new LoginFragment();
    }
}
