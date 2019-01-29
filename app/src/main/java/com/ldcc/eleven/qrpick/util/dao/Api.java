package com.ldcc.eleven.qrpick.util.dao;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.util.vo.DetailItem;

import java.util.HashMap;
import java.util.Map;

public class Api {
    String url = "http://18.223.57.133:3000"; //http://18.223.57.133:3000/display/detail";

    final static String TAG = "API";
    private Context context;
    private void println(String msg) {
        Log.d(TAG, msg);
    }
    public Api(Context context){
        this.context = context;
    }
    private String result = "";

    public String getDisplayList(){
        Log.d(TAG,"DisplayList");
        result = "";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.GET, url+"/display/list",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                return params;
            }
        };

        queue.add(request);

        return result;
    }

    public void createDisplay(final String branch, final String brand, final String location){
        Log.d(TAG,"createDisplay");

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/display/create",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                params.put("branch", branch);
                params.put("brand", brand);
                params.put("location", location);

                return params;
            }
        };

        queue.add(request);
    }

    public String getDetailDisplay(final String id) {
        Log.d(TAG, "getDetailDisplay");


        result = "";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/display/detail",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                params.put("id", id);

                return params;
            }
        };

        queue.add(request);


        return result;
    }

    public void updateDisplay(final String id, final String branch, final String brand, final String location){
        Log.d(TAG, "updateDisplay");

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/display/update",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                params.put("id", id);
                params.put("branch", branch);
                params.put("brand", brand);
                params.put("location", location);
                return params;
            }
        };

        queue.add(request);
    }

    public void deleteDisplay(final String id){
        Log.d(TAG, "deleteDisplay");

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/display/delete",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                params.put("id", id);
                return params;
            }
        };

        queue.add(request);
    }




    //할인가, brandId, amount, information, image

    /**
     * brandId 존재해야하고
     * 매대에 상품을 추가함
     */
    public void createItem(final String modelNumber, final String category, final String price, final String name, final String discountPrice, final String amount, final String information, final String brandId, final String imageUrl) {
        Log.d(TAG, "createItem");

        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/item/create",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                params.put("modelNumber", modelNumber);
                params.put("category", category);
                params.put("price", price);
                params.put("name", name);
                params.put("discountPrice", discountPrice);
                params.put("amount", amount);
                params.put("information", information);
                params.put("brandId", brandId);
                params.put("imageUrl", imageUrl);


                return params;
            }
        };

        queue.add(request);
    }

    /**
     * dldlfkdklf
     * @param brandId
     * @return
     */
    public String getListItem(final String brandId) {
        Log.d(TAG, "getListItem");

        result = "";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/item/list",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                params.put("brandId", brandId);
                return params;
            }
        };

        queue.add(request);


        return result;
    }


    public void updateItem(final String modelNumber, final String category, final String price, final String name, final String discountPrice, final String amount, final String information, final String imageUrl, final String brandId, final String id){
        Log.d(TAG, "updateItem");

        result = "";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/item/update",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                params.put("modelNumber", modelNumber);
                params.put("category", category);
                params.put("price", price);
                params.put("name", name);
                params.put("discountPrice", discountPrice);
                params.put("amount", amount);
                params.put("information", information);
                params.put("image", imageUrl);
                params.put("brandId", brandId);
                params.put("id", id);

                return params;
            }
        };

        queue.add(request);
    }

    public String detailItem(final String id){
        Log.d(TAG, "detailItem");

        result = "";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/item/detail",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;

                        // TODO 상품 상세정보 보여주기
                        // 상품 id를 가지고 상품정보를 읽어온다.
                        /**
                         * Json 파싱
                         */
                        Gson gson = new Gson();
                        DetailItem item = gson.fromJson(result, DetailItem.class);
                        TextView textView1 =  ((Activity)context).findViewById(R.id.textView7);
                        textView1.setText(item.getData().getName());

                        Log.d(TAG, item.getCode());

                        TextView textView2 =  ((Activity)context).findViewById(R.id.textView3);
                        textView2.setText(item.getData().getModelNumber());

                        TextView textView3 =  ((Activity)context).findViewById(R.id.textView8);
                        textView3.setText(item.getData().getPrice()+"");

                        TextView textView4 =  ((Activity)context).findViewById(R.id.textView9);
                        textView4.setText(item.getData().getCategory());

                        TextView textView5 =  ((Activity)context).findViewById(R.id.textView4);
                        textView5.setText(item.getData().getInformation());

                        TextView textView6 =  ((Activity)context).findViewById(R.id.textView6);
                        textView6.setText(item.getData().getAmount()+"");




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
                params.put("id", id);
                return params;
            }
        };

        queue.add(request);
        Log.d(TAG, result);
        return result;
    }

    public void deleteItem(final String id){
        Log.d(TAG, "deleteItem");

        result = "";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/item/delete",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                params.put("id", id);
                return params;
            }
        };

        queue.add(request);
    }

    public void decreseAmountItem(final String id, final String amount){
        Log.d(TAG, "decreseAmountItem");

        result = "";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/item/decreseAmount",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
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
                params.put("id", id);
                params.put("amount", amount);

                return params;
            }
        };

        queue.add(request);
    }


}
