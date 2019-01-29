package com.ldcc.eleven.qrpick.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.util.dao.Api;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Api api = new Api(this);
        Log.d("MainActivity", "DDDDDD");
        api.getDisplay("1");

    }
}
