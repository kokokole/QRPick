package com.ldcc.eleven.qrpick.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.util.dao.Api;
import com.ldcc.eleven.qrpick.util.vo.DetailItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DetailItem detailItem = new DetailItem();
        Api api = new Api(this);
        Log.d("MainActivity", "DDDDDD");
//        api.getDisplayList();
//        api.getDetailDisplay("7");
//        api.createDisplay("2222","3333","무엇을 4444");
//        api.createItem("190121232292222", "333시간22", "1333300022","333가짜22", "500", "5", "{ \"color\" : \"blue\" }", "6", "image" );
//        api.detailItem("9");
//        api.decreseAmountItem("9", "3");
//            api.getListItem("6");
//        api.updateDisplay("1", "ddd", "322", "ffff");
//        api.deleteDisplay("6");
//        api.updateItem("190129", "시간", "1000","가짜", "500", "5", "{ \ol"cor\" : \"blue\" }", "1", "9", "13");
//        api.getListItem("9");

            String data  = api.detailItem("13");
            Log.d("Main", data);
//            Toast.makeText(this, detailItem.getData().getName(), Toast.LENGTH_SHORT).show();









    }
}
