package com.hjh.nooneknow.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.hjh.nooneknow.Config;
import com.hjh.nooneknow.fragment.TimeLineFragment;

public class TimeLineActivity extends SingleFragmentActivity {


    @Override
    protected Fragment CreateFragment() {
        Intent i=getIntent();
        return TimeLineFragment.newInstance(i.getStringExtra(Config.KEY_PHONE),i.getStringExtra(Config.KEY_TOKEN));
    }
}
