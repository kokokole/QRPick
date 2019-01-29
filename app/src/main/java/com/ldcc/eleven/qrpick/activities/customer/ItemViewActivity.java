package com.ldcc.eleven.qrpick.activities.customer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.ldcc.eleven.qrpick.R;

public class ItemViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);
        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), intent.getIntExtra("no",0) + "", Toast.LENGTH_SHORT).show();
        //Intent로 넘겨받은 데이터를 가지고 상품 상세 정보 리스트를 보여준다.

        ImageView imageView = findViewById(R.id.imageView8);
        //iv.setImageResource(R.drawable.img);
        Glide.with(this).load("http://media.dcshoes.kr/media/catalog/product/cache/image/1000x1000/9df78eab33525d08d6e5fb8d27136e95/d/8/d851fh234wej-1.jpg").into(imageView);


        TextView textView1 = findViewById(R.id.textView9);
        textView1.setText("dfgdf");

        TextView textView2 = findViewById(R.id.textView3);
        textView2.setText("tttt");

        TextView textView3 = findViewById(R.id.textView7);
        textView3.setText("tttt");

        TextView textView4 = findViewById(R.id.textView18);
        textView4.setText("tttt");

        TextView textView5 = findViewById(R.id.textView4);
        textView5.setText("tttt");

        TextView textView6 = findViewById(R.id.textView6);
        textView6.setText("tttt");





    }
}
