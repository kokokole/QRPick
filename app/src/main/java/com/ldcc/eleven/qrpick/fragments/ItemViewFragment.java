package com.ldcc.eleven.qrpick.fragments;


import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ldcc.eleven.qrpick.R;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemViewFragment extends Fragment {

    String name, category,imageUrl, modelNumber, information, amount, discountPrice, price, brandId, id;
    String color, size;
    public ItemViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void setArguments(@Nullable Bundle args) {
        this.name = args.getString("name");
        this.category = args.getString("category");
        this.amount = args.getString("amount");
        this.brandId = args.getString("brandId");
        this.imageUrl = args.getString("imageUrl");
        this.modelNumber = args.getString("modelNumber");
        this.information = args.getString("information");
        this.discountPrice = args.getString("discountPrice");
        this.price = args.getString("price");
        this.id = args.getString("id");
        Gson gson = new Gson();
//        information = information.replaceAll("\\\"", "");
        Log.d("ItemViewFragment", information);

    }
    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 200, 200, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }
    final static String TAG = "ItemViewFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_item_view, container, false);
        ImageView mainImageView = rootView.findViewById(R.id.imageView8);
        TextView modelNumberView = rootView.findViewById(R.id.textView3);
        TextView brandNameView = rootView.findViewById(R.id.textView9);
        TextView nameView = rootView.findViewById(R.id.textileView);
        TextView priceView = rootView.findViewById(R.id.textView11);
        TextView colorView = rootView.findViewById(R.id.colorView);
        TextView textileView = rootView.findViewById(R.id.textileView2);
        Button button = rootView.findViewById(R.id.button3);

        FloatingActionButton f =rootView.findViewById(R.id.fab);
//        f.setOnClickListener(new View);

        if(modelNumber==null){
            Log.d("ItemViewFragment", "null");

            Glide.with(getActivity().getApplicationContext()).load("http://media.dcshoes.kr/media/catalog/product/cache/image/1000x1000/9df78eab33525d08d6e5fb8d27136e95/d/8/d851fh234wej-1.jpg").into(mainImageView);

            modelNumberView.setText("ASDADAS");
            priceView.setText("12312");
            nameView.setText("QWeqwe");
        }
        else{
            Log.d("ItemViewFragment", "not null");
            if(imageUrl == null || imageUrl.equals("") ){
                Log.e("image", "imageeeee");
                if(modelNumber.equals("7218410082")){
                    mainImageView.setImageDrawable(resize(getResources().getDrawable(R.drawable.a7218410082)));
                } else if (modelNumber.equals("OW9SJ150")) {
                    mainImageView.setImageDrawable(resize(getResources().getDrawable(R.drawable.ow9sj150)));

                } else if (modelNumber.equals("MABLHGLDJ510121E23")) {
                    mainImageView.setImageDrawable(resize(getResources().getDrawable(R.drawable.mam)));
                } else if (modelNumber.equals("HSSW8D753")) {
                    mainImageView.setImageDrawable(resize(getResources().getDrawable(R.drawable.hssw8b753)));
                }
            }else{
                Glide.with(getActivity().getApplicationContext()).load(imageUrl).into(mainImageView);
            }
            Log.d(TAG, modelNumber);
            Log.d(TAG, discountPrice);
            Log.d(TAG, name);

            brandNameView.setText(category);
            modelNumberView.setText(modelNumber);
            priceView.setText(discountPrice);
            nameView.setText(name);
            Gson gson = new Gson();
            Log.d("information@@", information);
//            Information info = gson.fromJson(information, Information.class);
            JSONObject info = null;
            try {
                info = new JSONObject(information);
                Log.d("ASDAS",info.getString("color"));
                Log.d("ASDAS",info.getString("textile"));
                colorView.setText(info.getString("color"));
                textileView.setText(info.getString("textile"));
                button.setText(info.getString("size"));

            } catch (JSONException e) {
                e.printStackTrace();
            }



        }

        return rootView;
    }

}
