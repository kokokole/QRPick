package com.ldcc.eleven.qrpick.activities.manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.ldcc.eleven.qrpick.R;

public class MenuActivity extends AppCompatActivity {
    final static String TAG = "MenuActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Intent intent = getIntent();

        Log.d(TAG, intent.getStringExtra("data"));
    }
}
