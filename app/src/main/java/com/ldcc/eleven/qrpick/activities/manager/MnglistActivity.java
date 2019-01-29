package com.ldcc.eleven.qrpick.activities.manager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.adapter.MyAdapter;

import java.util.ArrayList;

public class MnglistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SwipeMenuListView swipelist;
    MyAdapter myAdapter;
    ArrayList<ProductsList> items= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnglist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Toast.makeText(this, "" + intent.getStringExtra("data"), Toast.LENGTH_SHORT).show();
        // 매대에 있는 상품을 등록/수정/삭제할 수 있는 MngListActivity를 띄운다.
        // Intent로 넘겨받은 데이터를 가지고 해당되는 매대 데이터를 읽어온다.

        /**Swipe listview*/
        swipelist = findViewById(R.id.swipelist);

        myAdapter = new MyAdapter(MnglistActivity.this, items);

        /**초기 데이터 입력*/
        initData();

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                swipeMenuSet(menu);
            }
        };

        swipelist.setMenuCreator(creator);
        swipelist.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        /**edit 클릭한 경우*/
                        Toast.makeText(MnglistActivity.this, "edit num : "+position, Toast.LENGTH_SHORT).show();
                        //여기서 수정 페이지로 Activity 이동
                        //startActivity(new Intent(getApplicationContext(),));
                        break;
                    case 1:
                        /**delete 클릭한 경우*/
                        Toast.makeText(MnglistActivity.this, items.get(position).getpName()+" 이(가) 삭제되었습니다.", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(getApplicationContext(), ManagerActivity.class));
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Log.e("[ITEM CHK]","test");
        ProductsList item = (ProductsList) myAdapter.getItem(position);
        Toast.makeText(this, position+" 클릭됨", Toast.LENGTH_SHORT).show();
    }

    /**데이터 초기화 및 리스트에 설정하는 부분. 현재는 테스트 데이터 입력해 놓았음*/
    private void initData() {
        items.add(new ProductsList());
        items.add(new ProductsList());
        items.add(new ProductsList());
        items.add(new ProductsList());
        items.add(new ProductsList());
        items.add(new ProductsList());
        items.add(new ProductsList());
        items.add(new ProductsList());
        items.add(new ProductsList());
        items.add(new ProductsList());
        swipelist.setAdapter(myAdapter);
    }

    /**swipe메뉴 설정-수정 및 삭제*/
    private void swipeMenuSet(SwipeMenu menu){
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


}
