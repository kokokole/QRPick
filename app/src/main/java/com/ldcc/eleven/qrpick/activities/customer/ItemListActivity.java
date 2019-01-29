package com.ldcc.eleven.qrpick.activities.customer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ldcc.eleven.qrpick.R;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;


public class ItemListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Intent intent = getIntent();
        Toast.makeText(getApplicationContext(), "data : " + intent.getStringExtra("data"), Toast.LENGTH_SHORT).show();
        // Intent로 넘겨받은 데이터를 가지고 매대에 있는 상품들을 리스트로 만들어 보여준다. (좌우 스크롤 리스트)

        PagerContainer mContainer = (PagerContainer) findViewById(R.id.pager_container);
        final ViewPager pager = mContainer.getViewPager();

        PagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);

        pager.setOffscreenPageLimit(adapter.getCount());

        pager.setClipChildren(false);

        mContainer.setPageItemClickListener(new PageItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ItemListActivity.this,"position:" + position,Toast.LENGTH_SHORT).show();
            }
        });


        boolean showRotate = getIntent().getBooleanExtra("showRotate",true);

        new CoverFlow.Builder()
                .with(pager)
                .pagerMargin(0f)
                .scale(0.3f)
                .spaceSize(0f)
                .rotationY(0f)
                .build();


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
            LinearLayout lView = new LinearLayout(ItemListActivity.this);
            lView.setOrientation(LinearLayout.VERTICAL);
            lView.setLayoutParams(new LinearLayout.LayoutParams(180, 425));
//            lView.setBackgroundColor(Color.BLACK);


            ImageView view = new ImageView(ItemListActivity.this);
            TextView textView = new TextView(ItemListActivity.this);
            textView.setTextColor(Color.BLACK);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1600);
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


            textView.setText(1+position+"  내용내용  ");
            lView.addView(view);
            lView.addView(textView);



            container.addView(lView);
            return lView;
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
