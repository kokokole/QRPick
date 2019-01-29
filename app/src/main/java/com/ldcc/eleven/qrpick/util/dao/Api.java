package com.ldcc.eleven.qrpick.util.dao;

import android.content.Context;
import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ldcc.eleven.qrpick.util.vo.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Api {
    String url = "http://18.223.57.133:3000/"; //http://18.223.57.133:3000/display/detail";

    final static String TAG = "API";
    private Context context;
    private void println(String msg) {
        Log.d(TAG, msg);
    }
    public Api(Context context){
        this.context = context;
    }
    private String result = "";

    public String getDisplayList(String id){
        result = "";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url+"/display/list",
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
                params.put("param1", "isGood");
                return params;
            }
        };

        queue.add(request);

        return result;
    }
    public String getDetailDisplay(String id) {
        Log.d(TAG, "display");


        result = "";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest request = new StringRequest(Request.Method.POST, url,
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
                params.put("param1", "isGood");
                return params;
            }
        };

        queue.add(request);


        return result;
    }

    public void updateDisplay(String id){
        result = "";
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
                params.put("param1", "isGood");
                return params;
            }
        };

        queue.add(request);
    }

    public void deleteDisplay(String id){
        result = "";
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
                params.put("param1", "isGood");
                return params;
            }
        };

        queue.add(request);
    }




    //할인가, brandId, amount, information, image
    public void createItem(String modelNumber, String category, int price, String name, int discountPrice, int amount, String information, int brandId, String imageUrl) {

    }

    public List<Item> getListItem(int brandId) {
        return null;
    }


}
