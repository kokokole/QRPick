package com.ldcc.eleven.qrpick.activities.customer;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.adapter.ListPagerAdapter;
import com.ldcc.eleven.qrpick.fragments.ItemViewFragment;
import com.ldcc.eleven.qrpick.util.vo.Item;
import com.ldcc.eleven.qrpick.util.vo.ListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TempActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private ListPagerAdapter pagerAdapter;
    String brandId ="17";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

        mViewPager = findViewById(R.id.pager);
        pagerAdapter=new ListPagerAdapter(getSupportFragmentManager());

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final StringBuffer stringBuffer = new StringBuffer();

        String url = "http://18.223.57.133:3000/item/list";
//        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                stringBuffer.append(response);
//                String jsonData = stringBuffer.toString();
//                Log.d("api2", jsonData);
//
//
//                // Gson을 이용해 파싱
//                Gson gson = new Gson();
//                ListItem result = gson.fromJson(jsonData,ListItem.class);
//
//                if(result != null){
//                    Log.d("size", ""+result.getData());
//                }
//
//                setAdapter(result);
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                String errors = error.getMessage();
//                if (errors != null)
//                    Log.e("error", errors);
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("brandId", brandId);
//                return params;
//            }
//        };
//        request.setShouldCache(false); // 캐싱을 안해서 매 요청시 결과를 받아옴, (true 이면 캐싱된 결과가 있으면 안받아 옴)
//        requestQueue.add(request);
        setAdapter(null);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(pagerAdapter);



    }

    private void setAdapter(ListItem items){
        if(items != null){
            for(Item item : items.getData()){
                Bundle bundle = new Bundle();
                bundle.putString("category", item.getCategory());
                bundle.putString("imageUrl", item.getImageUrl());
                bundle.putString("information", item.getInformation());
                bundle.putString("modelNumber", item.getModelNumber());
                bundle.putString("name", item.getName());
                bundle.putString("amount", item.getAmount()+"");
                bundle.putString("discountPrice", item.getDiscountPrice()+"");
                bundle.putString("price", item.getPrice()+"");
                bundle.putString("brandId", item.getBrandId()+"");
                bundle.putString("id", item.getId()+"");


//                fragment.setArguments(bundle);
            }
        }
        ItemViewFragment fragment = new ItemViewFragment();

        pagerAdapter.addItem(fragment);
        fragment = new ItemViewFragment();
        pagerAdapter.addItem(fragment);
        fragment = new ItemViewFragment();
        pagerAdapter.addItem(fragment);

        pagerAdapter.notifyDataSetChanged();

    }

}
