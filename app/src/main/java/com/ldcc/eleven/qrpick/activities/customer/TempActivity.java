package com.ldcc.eleven.qrpick.activities.customer;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
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
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.adapter.ListPagerAdapter;
import com.ldcc.eleven.qrpick.fragments.ItemViewFragment;
import com.ldcc.eleven.qrpick.util.vo.Item;
import com.ldcc.eleven.qrpick.util.vo.ListItem;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;

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


        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), "data : " + intent.getStringExtra("data"), Toast.LENGTH_SHORT).show();
        // Intent로 넘겨받은 데이터를 가지고 매대에 있는 상품들을 리스트로 만들어 보여준다. (좌우 스크롤 리스트)


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            PagerContainer mContainer = (PagerContainer) findViewById(R.id.pager_container);
            final ViewPager pager = mContainer.getViewPager();

            PagerAdapter adapter = new MyPagerAdapter();
            pager.setAdapter(adapter);

            pager.setOffscreenPageLimit(adapter.getCount());

            pager.setClipChildren(false);

            mContainer.setPageItemClickListener(new PageItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(getApplicationContext(), "position:" + position, Toast.LENGTH_SHORT).show();
                }
            });


            boolean showRotate = getIntent().getBooleanExtra("showRotate", true);

            new CoverFlow.Builder()
                    .with(pager)
                    .pagerMargin(0f)
                    .scale(0.3f)
                    .spaceSize(0f)
                    .rotationY(0f)
                    .build();

        }else {


            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            final StringBuffer stringBuffer = new StringBuffer();
            // TODO 상품 리스트 서버에서 받아서 보여주기
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



    private class MyPagerAdapter extends PagerAdapter {




        private Drawable resize(Drawable image) {
            Bitmap b = ((BitmapDrawable)image).getBitmap();
            Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 180, 500, false);
            return new BitmapDrawable(getResources(), bitmapResized);
        }




        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            /*TextView view = new TextView(Normal2Activity.this);
            view.setText("Item "+position);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));
*/



            ImageView view = new ImageView(getApplicationContext());

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(50, 50);
            view.setScaleType(ImageView.ScaleType.FIT_XY);

            view.setLayoutParams(layoutParams);


            final int res = R.drawable.item2 + position;

            view.setImageDrawable(resize(getResources().getDrawable(res)));

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), ItemViewActivity.class).putExtra("no", (res-R.drawable.item2)));
                }
            });




            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

    }

}
