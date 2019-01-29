package com.ldcc.eleven.qrpick.activities.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import com.ldcc.eleven.qrpick.R;

public class MenudetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudetail);


        Button insertbtn = findViewById(R.id.insertbtn);

        Intent intent = getIntent();

        int mode = 2;//intent.getExtras().getInt("mode"); //등록 or 수정
        int itemid = 1;//intent.getExtras().getInt("id"); //아이템id


        EditText modelnm = findViewById(R.id.modelnm);//상품명
        EditText itemnm = findViewById(R.id.itemnm); //모델명
        EditText itemprice = findViewById(R.id.itemprice);//가격

        Spinner s = (Spinner) findViewById(R.id.spinner);//색상
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Spinner s2 = (Spinner) findViewById(R.id.spinner2);//사이즈
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ///바코드정보 read 후, setting
        modelnm.setText("PKBLUE");
        itemnm.setText("남성PK셔츠");
        itemprice.setText("108000");


        if(mode == 1) {
            insertbtn.setText("등록");


        }
        else {
            insertbtn.setText("수정");
            selectItem(itemid);
        }
/*
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "ddddd", Toast.LENGTH_SHORT).show();

                //startActivity(new Intent(getApplicationContext(), CustomerActivity.class));
            }
        });
*/



    }

    private void selectItem(int itemid){
        //상품id로 select


        //db 연결 후, itemid로 select

        //display


    }
}
