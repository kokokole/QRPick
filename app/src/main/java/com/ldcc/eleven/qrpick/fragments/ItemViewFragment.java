package com.ldcc.eleven.qrpick.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.util.vo.Information;

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
        information = information.replaceAll("\\\"", "");
        Log.d("ItemViewFragment", information);

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
        TextView textileView = rootView.findViewById(R.id.textileView);


        if(modelNumber==null){
            Log.d("ItemViewFragment", "null");

            Glide.with(getActivity().getApplicationContext()).load("http://media.dcshoes.kr/media/catalog/product/cache/image/1000x1000/9df78eab33525d08d6e5fb8d27136e95/d/8/d851fh234wej-1.jpg").into(mainImageView);

            modelNumberView.setText("ASDADAS");
            priceView.setText("12312");
            nameView.setText("QWeqwe");
        }
        else{
            Log.d("ItemViewFragment", "not null");
            Glide.with(getActivity().getApplicationContext()).load(imageUrl).into(mainImageView);
            Log.d(TAG, modelNumber);
            Log.d(TAG, discountPrice);
            Log.d(TAG, name);

            modelNumberView.setText(modelNumber);
            priceView.setText(discountPrice);
            nameView.setText(name);
            Gson gson = new Gson();
            Information info = gson.fromJson(information, Information.class);
            colorView.setText(info.getColor());
            textileView.setText(info.getTextile());


        }

        return rootView;
    }

}
