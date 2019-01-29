package com.ldcc.eleven.qrpick.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.bumptech.glide.Glide;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.activities.manager.ProductViewHolder;
import com.ldcc.eleven.qrpick.activities.manager.ProductsList;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<ProductsList> productsList;

    public MyAdapter(Context context, ArrayList<ProductsList> productsList){
        this.context = context;
        this.productsList = productsList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return productsList.size();
    }

    @Override
    public Object getItem(int position) {
        return productsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        View view = convertview;
        final int current = position;
        ProductViewHolder holder;

        if(view == null){
            view =layoutInflater.inflate(R.layout.recyclerview_item, null);
            holder = new ProductViewHolder();
            holder.iv_pImg = view.findViewById(R.id.iv_pImg);
            holder.tv_pName = view.findViewById(R.id.tv_pName);
            holder.tv_pPrice = view.findViewById(R.id.tv_pPrice);
            holder.tv_pSize = view.findViewById(R.id.tv_pSize);
            holder.tv_pQuantity = view.findViewById(R.id.tv_pQuantity);
            view.setTag(holder);
        }else{
            holder = (ProductViewHolder)view.getTag();
        }

        /**데이터 셋팅*/
        //holder.iv_pImg.setImageResource(R.drawable.test);
        Glide.with(context)
                .load(productsList.get(current).getpImg())
                .into(holder.iv_pImg);
        holder.tv_pName.setText(productsList.get(current).getpName());
        holder.tv_pPrice.setText(productsList.get(current).getpPrice());
        holder.tv_pSize.setText(productsList.get(current).getpSize());
        holder.tv_pQuantity.setText(productsList.get(current).getpQuantity());

        return view;
    }
}
