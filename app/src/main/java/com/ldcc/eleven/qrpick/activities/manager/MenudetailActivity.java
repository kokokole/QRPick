package com.ldcc.eleven.qrpick.activities.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.util.vo.DetailItem;
import com.ldcc.eleven.qrpick.util.vo.Item;

import java.util.HashMap;
import java.util.Map;

public class MenudetailActivity extends AppCompatActivity {


    Item item=null;
    EditText modelnm;//상품명
    EditText itemnm ; //모델명
    EditText itemprice;//할인가격
    EditText itemamount; // 수량
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudetail);


        Button insertbtn = findViewById(R.id.insertbtn);

        Intent intent = getIntent();

        String json = intent.getStringExtra("data");
        Gson gson = new Gson();
        item = gson.fromJson(json, Item.class);
        Log.d("detail", item.getName());



        int mode = 2;//intent.getExtras().getInt("mode"); //등록 or 수정
        int itemid = 1;//intent.getExtras().getInt("id"); //아이템id


        modelnm = findViewById(R.id.modelnm);//상품명
        itemnm = findViewById(R.id.itemnm); //모델명
        itemprice = findViewById(R.id.itemprice);//할인가격
        itemamount = findViewById(R.id.itemamount); // 수량

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

        insertbtn.setOnClickListener(new View.OnClickListener() { // TODO  상품 정보 업데이트
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "ddddd", Toast.LENGTH_SHORT).show();
                String url = "http://18.223.57.133:3000";
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                StringRequest request = new StringRequest(Request.Method.POST, url+"/item/update",
                        //요청 성공 시
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("result", "[" + response + "]");

                            }
                        },
                        // 에러 발생 시
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("error", "[" + error.getMessage() + "]");
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("modelNumber", item.getModelNumber());
                        params.put("category", item.getCategory());
                        params.put("price", item.getPrice()+"");
                        params.put("name", item.getName());
                        params.put("discountPrice", itemprice.getText()+"");
                        params.put("amount", itemamount.getText()+"");
                        params.put("information", item.getInformation());
                        params.put("image", item.getImageUrl());
                        params.put("brandId", item.getBrandId()+"");
                        params.put("id", item.getId()+"");


                        return params;
                    }
                };

                queue.add(request);

            }
        });




    }

    private void selectItem(int itemid){
        //상품id로 select


        //db 연결 후, itemid로 select

        //display


    }
}
