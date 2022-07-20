package com.ctim.simpledemo.plugins;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ctim.simpledemo.R;

public class MyPluginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myplugin);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
