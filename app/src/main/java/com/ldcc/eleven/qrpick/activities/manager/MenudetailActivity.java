package com.ldcc.eleven.qrpick.activities.manager;

import android.content.Intent;
import android.icu.text.IDNA;
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
import com.ldcc.eleven.qrpick.activities.dataSetListener;
import com.ldcc.eleven.qrpick.adapter.MyAdapter;
import com.ldcc.eleven.qrpick.util.vo.DetailItem;
import com.ldcc.eleven.qrpick.util.vo.Information;
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
    Intent intent;
    Gson gson;
    MyAdapter adapter;
    final static String TAG = "MenudetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menudetail);

        Button insertbtn = findViewById(R.id.insertbtn);

        intent = getIntent();
        try{
            adapter = intent.getParcelableExtra("adapter");
        }catch (Exception e){
            Log.d(TAG, "no adapter in intent");
        }
        flag = intent.getStringExtra("flag");
        String json = intent.getStringExtra("data");
        Log.e("json", json);
        gson = new Gson();
        item = gson.fromJson(json, Item.class);
        Log.e("detail", item.toString());
        Log.e("adapter size detail", adapter.getProductsList().size() + "");
        adapter.getProductsList().add(item);
        adapter.add(item);
        adapter.notifyDataSetChanged();
        Information information = gson.fromJson(item.getInformation(), Information.class);
        Log.e("information size", information.getSize());

        modelnm = findViewById(R.id.modelnm);//, 모델명
        itemnm = findViewById(R.id.itemnm); //상품명
        itemprice = findViewById(R.id.itemprice);//할인가격
        itemamount = findViewById(R.id.itemamount); // 수량

        EditText colorInput = (EditText) findViewById(R.id.spinner);//색상
        colorInput.setText(information.getColor());

        EditText sizeInput = (EditText) findViewById(R.id.spinner2);//사이즈
        sizeInput.setText(information.getSize());

        ///바코드정보 read 후, setting
        modelnm.setText(item.getModelNumber());
        itemnm.setText(item.getName());
        itemprice.setText(item.getDiscountPrice()+"");
        itemamount.setText(item.getAmount() + "");

        if (flag.equals("crate")) {
            insertbtn.setText("등록");


        } else if(flag.equals("update")){
            insertbtn.setText("수정");

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
                                    Log.d("adapter size", adapter.getCount() + "");
                                    adapter.add(item);
                                    Log.d("adapter size", adapter.getCount() + "");
                                    adapter.notifyDataSetChanged();
                                    intent.putExtra("item", gson.toJson(item));

                                    setResult(RESULT_OK, intent);
                                    finish();
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

                            params.put("amount", itemamount.getText() + "");  // TODO 수량 넣으면 수량 없으면 기본값으로

                            params.put("information", item.getInformation());
                            params.put("brandId", item.getBrandId() + "");
//                            params.put("imageUrl", item.getImageUrl());
                            return params;
                        }
                    };
                    queue.add(request);
                } else if (flag.equals("update")) {

                    item.setModelNumber(modelnm.getText().toString());
                    item.setName(itemnm.getText().toString());
                    item.setDiscountPrice(Integer.parseInt(itemprice.getText().toString()));
                    item.setAmount(Integer.parseInt(itemamount.getText().toString()));
                    intent.putExtra("item", gson.toJson(item));
                    setResult(RESULT_OK, intent);
                    StringRequest request = new StringRequest(Request.Method.POST, url + "/item/update",
                            //요청 성공 시
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Log.e("result2", "[" + response + "]");
                                    int position = intent.getIntExtra("position", -1);
                                    Log.e("position", position + "");
                                    try{
//                                        Log.d("adapter size", adapter.getCount()+"");
//                                        Item item = (Item) adapter.getItem(position);
//                                        Log.d("item hashcode", item.hashCode()+"");

//                                        item.setModelNumber(modelnm.getText().toString());
//                                        item.setName(itemnm.getText().toString());
//                                        item.setDiscountPrice(Integer.parseInt(itemprice.getText().toString()));
//                                        item.setAmount(Integer.parseInt(itemamount.getText().toString()));
//                                        adapter.notifyDataSetChanged();
                                        setResult(RESULT_OK, intent);
                                        finish();
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        Log.e("result2", "no position in intent");

                                    }
                                }
                            },
                            // 에러 발생 시
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.e("error2222", "[" + error.getMessage() + "]");
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("modelNumber", modelnm.getText().toString());
                            Log.e("null?",modelnm.getText().toString());
                            params.put("category", item.getCategory());
                            Log.e("null?",item.getCategory());

                            params.put("price", item.getPrice() + "");
                            Log.e("null?",item.getPrice()+"");

                            params.put("name", itemnm.getText().toString());
                            Log.e("null?",itemnm.getText().toString());

                            params.put("discountPrice", itemprice.getText().toString());
                            Log.e("null?",itemprice.getText().toString());

                            params.put("amount", itemamount.getText().toString());

                            Log.e("amout", itemamount.getText().toString());
                            params.put("information", item.getInformation());
                            Log.e("null?", item.getInformation());

                            params.put("image", item.getImageUrl()+"");
                            Log.e("null?", item.getImageUrl()+"");

                            params.put("brandId", item.getBrandId() + "");
                            Log.e("null?", item.getBrandId() + "");

                            params.put("id", item.getId() + "");
                            Log.e("ididid", item.getId()+"");
                            return params;
                        }

                    };
                    queue.add(request);
                }


                startActivity(new Intent(getApplicationContext(), MnglistActivity.class));
                adapter.notifyDataSetChanged();
                finish();
            }

        });


    }


}
