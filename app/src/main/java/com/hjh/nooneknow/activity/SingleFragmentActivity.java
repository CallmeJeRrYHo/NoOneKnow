package com.hjh.nooneknow.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.hjh.nooneknow.R;

/**
 * Created by HJH on 2016/1/17.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {
    protected abstract Fragment CreateFragment();

    protected int getLayoutResId(){
        return R.layout.activity_main;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager fm=getSupportFragmentManager();
        Fragment fragment=fm.findFragmentById(R.id.ll_fragment_container);
        if(fragment==null){
            fragment= CreateFragment();
            fm.beginTransaction().add(R.id.ll_fragment_container,fragment).commit();
        }
    }
}
