package com.hjh.nooneknow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hjh.nooneknow.activity.LoginActivity;
import com.hjh.nooneknow.activity.TimeLineActivity;
import com.hjh.nooneknow.localdata.MyContact;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyContact.getContact(this);
        String token=Config.getCacheToken(getApplicationContext());
        String phone=Config.getPhone(getApplicationContext());
        if (token!=null&&phone!=null){
            Intent i=new Intent(MainActivity.this,TimeLineActivity.class);
            i.putExtra(Config.KEY_PHONE,phone);
            i.putExtra(Config.KEY_TOKEN,token);
            startActivity(i);
            finish();
        }else {
            Intent i=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(i);
            finish();
        }
    }
}
