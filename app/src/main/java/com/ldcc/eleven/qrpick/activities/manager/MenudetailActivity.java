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
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MenudetailActivity extends AppCompatActivity {


    Item item = null;
    EditText modelnm;//상품명
    EditText itemnm; //모델명
    EditText itemprice;//할인가격
    EditText itemamount; // 수량
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudetail);


        Button insertbtn = findViewById(R.id.insertbtn);

        Intent intent = getIntent();
        flag = intent.getStringExtra("flag");
        String json = intent.getStringExtra("data");
        Log.d("json", json);
        Gson gson = new Gson();
        item = gson.fromJson(json, Item.class);
        Log.d("detail", item.toString());


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
        modelnm.setText(item.getModelNumber());
        itemnm.setText(item.getName());
        itemprice.setText(item.getDiscountPrice()+"");


        if (flag.equals("crate")) {
            insertbtn.setText("등록");


        } else if(flag.equals("update")){
            insertbtn.setText("수정");
            selectItem(itemid);
        }

        insertbtn.setOnClickListener(new View.OnClickListener() { // TODO  상품 정보 업데이트
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "ddddd", Toast.LENGTH_SHORT).show();
                String url = "http://18.223.57.133:3000";
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                if (flag.equals("create")) {

                    StringRequest request = new StringRequest(Request.Method.POST, url + "/item/create",
                            //요청 성공 시
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("result1", "[" + response + "]");
                                }
                            },
                            // 에러 발생 시
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d("error", "[" + error.getMessage() + "]");
                                }
                            }) {
                        //요청보낼 때 추가로 파라미터가 필요할 경우
                        //url?a=xxx 이런식으로 보내는 대신에 아래처럼 가능.
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("modelNumber", item.getModelNumber());
                            params.put("category", item.getCategory());
                            params.put("price", item.getPrice() + "");
                            params.put("name", item.getName());
                            params.put("discountPrice", item.getDiscountPrice() + "");
                            params.put("amount", item.getAmount() + "");
                            params.put("information", item.getInformation());
                            params.put("brandId", item.getBrandId() + "");
//                            params.put("imageUrl", item.getImageUrl());
                            return params;
                        }
                    };

                    queue.add(request);

                } else if (flag.equals("update")) {
                    StringRequest request = new StringRequest(Request.Method.POST, url + "/item/update",
                            //요청 성공 시
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.d("result2", "[" + response + "]");
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
                            params.put("price", item.getPrice() + "");
                            params.put("name", item.getName());
                            params.put("discountPrice", itemprice.getText() + "");
                            params.put("amount", itemamount.getText() + "");
                            params.put("information", item.getInformation());
                            params.put("image", item.getImageUrl());
                            params.put("brandId", item.getBrandId() + "");
                            params.put("id", item.getId() + "");


                            return params;
                        }

                    };
                    queue.add(request);
                }
                finish();

            }

        });


    }

    private void selectItem(int itemid) {
        //상품id로 select


        //db 연결 후, itemid로 select

        //display


    }
}
