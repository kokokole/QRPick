package com.ldcc.eleven.qrpick.activities.manager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.gson.Gson;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.activities.dataSetListener;
import com.ldcc.eleven.qrpick.adapter.MyAdapter;
import com.ldcc.eleven.qrpick.util.dao.Api;
import com.ldcc.eleven.qrpick.util.vo.Item;
import com.ldcc.eleven.qrpick.util.vo.ListItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MnglistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, dataSetListener {

    SwipeMenuListView swipelist;
    MyAdapter myAdapter;

    ArrayList<Item> items = new ArrayList<>();

    EditText search; // 검색
    String brandId;
    int requestCode = 0;

    @Override
    public void setData(String data) {
        Gson gson = new Gson();
        Item item = gson.fromJson(data, Item.class);
        items.add(item);
        myAdapter.notifyDataSetChanged();

    }


    @Override
    protected void onStart() {
        super.onStart();

        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnglist);

        Intent intent = getIntent();
//        Toast.makeText(this, "" + intent.getStringExtra("data"), Toast.LENGTH_SHORT).show();
        // 매대에 있는 상품을 등록/수정/삭제할 수 있는 MngListActivity를 띄운다.
        // Intent로 넘겨받은 데이터를 가지고 해당되는 매대 데이터를 읽어온다.
        try{
            brandId = intent.getStringExtra("data");
        }catch(Exception e){
            brandId = "23";
        }
//        brandId = "23";

        /**Swipe listview*/
        swipelist = findViewById(R.id.swipelist);

        myAdapter = new MyAdapter(MnglistActivity.this, items);
        swipelist.setAdapter(myAdapter);

        search = findViewById(R.id.pw);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("Msg", editable.toString());
                String filterText = editable.toString();
                if (filterText.length() > 0) {
                    swipelist.setFilterText(filterText);
                } else {
                    swipelist.clearTextFilter();
                }
//                    Log.d("mng", ((MyAdapter)swipelist.getAdapter()).toString()  + " //// " + myAdapter.toString());
//                ((MyAdapter)(swipelist.getAdapter())).getFilter().filter(filterText) ;

            }
        });

        /**초기 데이터 입력*/
        initData(brandId);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                swipeMenuSet(menu);
            }
        };

        swipelist.setMenuCreator(creator);
        swipelist.setOnItemClickListener(this);

        swipelist.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        /**edit 클릭한 경우*/
                        Toast.makeText(MnglistActivity.this, "edit num : " + position, Toast.LENGTH_SHORT).show();
                        //여기서 수정 페이지로 Activity 이동
                        //startActivity(new Intent(getApplicationContext(),));


                        Item item = (Item) myAdapter.getItem(position);
                        Gson gson = new Gson();
                        String json = gson.toJson(item);
                        Intent intent = new Intent(getApplicationContext(), MenudetailActivity.class).putExtra("data", json);
                        intent.putExtra("position", position);
                        intent.putExtra("flag", "update");
                        startActivityForResult(intent, requestCode+1);
                        myAdapter.notifyDataSetChanged();

                        break;
                    case 1:
                        /**delete 클릭한 경우*/
                        // TODO 삭제구현하기
                        final Item item2 = (Item) myAdapter.getItem(position);
                        Toast.makeText(MnglistActivity.this, items.get(position).getName() + " 이(가) 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

                        StringRequest request = new StringRequest(Request.Method.POST, "http://18.223.57.133:3000"+ "/item/delete",
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

                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("id", item2.getId()+"");
                                return params;
                            }
                        };

                        queue.add(request);
                        items.remove(position);
                        myAdapter.notifyDataSetChanged();
                        break;
                }

                return false;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setImageResource(R.drawable.icon_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**페이지 작성되면 수정*/
                // TODO 등록버튼
                startActivityForResult(new Intent(getApplicationContext(), ManagerActivity.class).putExtra("go", 1).putExtra("adapter", myAdapter), requestCode);
                // TODO MenudatailActivity를 바로 띄우는 걸로 수정해보자.
                // TODO MenudatailActivity에 QR코드 인식 후 데이터 가져오게 해보자
                // TODO 데이터 가져와서 adapter와 연동하기


            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Log.e("[ITEM CHK]", "test");


//        Toast.makeText(this, position + " 클릭됨", Toast.LENGTH_SHORT).show();
//        Gson gson = new Gson();
//        String json = gson.toJson(item);
//        startActivity(new Intent(getApplicationContext(), ItemViewActivity.class).putExtra("data", json));

        // TODO 클릭해도 수정 페이지가 열리게
        Item item = (Item) myAdapter.getItem(position);
        Gson gson = new Gson();
        String json = gson.toJson(item);
        Intent intent = new Intent(getApplicationContext(), MenudetailActivity.class).putExtra("data", json);
        intent.putExtra("flag", "update");
        startActivityForResult(intent, requestCode+1);
        myAdapter.notifyDataSetChanged();


    }


    String result = "";

    /**
     * 데이터 초기화 및 리스트에 설정하는 부분. 현재는 테스트 데이터 입력해 놓았음
     */
    private void initData(final String brandId) {
        Api api = new Api(getApplicationContext());
        ListItem temp = new ListItem();


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://18.223.57.133:3000";

        StringRequest request = new StringRequest(Request.Method.POST, url + "/item/list",
                //요청 성공 시
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("result", "[" + response + "]");
                        result = response;
                        Gson gson = new Gson();
                        ListItem item = gson.fromJson(result, ListItem.class);
                        Log.d("DDFD", item.getCode() + "");
                        Log.d("DDFD", item.getData().size() + "");
                        for (Item i : item.getData()) {
                            items.add(i);
                            Log.d("Mng", i.getName());
                            myAdapter.notifyDataSetChanged();
                        }

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
                params.put("brandId", brandId + "");
                return params;
            }
        };

        queue.add(request);

        myAdapter.notifyDataSetChanged();


    }

    /**
     * swipe메뉴 설정-수정 및 삭제
     */
    private void swipeMenuSet(SwipeMenu menu) {
        /**Update Item*/
        SwipeMenuItem updateItem = new SwipeMenuItem(
                getApplicationContext());
        // set item background
        updateItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                0xCE)));
        // set item width
        updateItem.setWidth(300);
        // set a icon
        updateItem.setIcon(R.drawable.icon_edit);
        // add to menu
        menu.addMenuItem(updateItem);

        /**Delete Item*/
        SwipeMenuItem deleteItem = new SwipeMenuItem(
                getApplicationContext());
        // set item background
        deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                0x3F, 0x25)));
        // set item width
        deleteItem.setWidth(300);
        // set a icon
        deleteItem.setIcon(R.drawable.icon_delete);
        // add to menu
        menu.addMenuItem(deleteItem);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if(this.requestCode == requestCode){
//            Log.d("request", "0");
//            if(data.getStringExtra("json") != null){
//                Log.d("request", "0  ifif");
//
//                Gson gson = new Gson();
//                String json = data.getStringExtra("data");
//                Item item = gson.fromJson(json, Item.class);
//                items.add(item);
//                myAdapter.notifyDataSetChanged();
//            }
//        }else if(this.requestCode == requestCode+1){
//
//        }

    }
}
