package com.ldcc.eleven.qrpick.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import com.bumptech.glide.Glide;
import com.ldcc.eleven.qrpick.R;
import com.ldcc.eleven.qrpick.activities.manager.ProductViewHolder;
import com.ldcc.eleven.qrpick.activities.manager.ProductsList;
import com.ldcc.eleven.qrpick.util.vo.Item;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements Filterable {

    Context context;
    LayoutInflater layoutInflater;
//    ArrayList<ProductsList> productsList;
    ArrayList<Item> productsList;

    ArrayList<Item> filteredItemList;

    /**
     * 필터추가
     */

    Filter listFilter;

    @Override
    public Filter getFilter() {
        if(listFilter == null){
            listFilter = new ListFilter();
        }
        return listFilter;
    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;
            Log.d("MyAdapter con", constraint.toString());
            if (constraint == null || constraint.length() == 0) {
                results.values = productsList ;
                results.count = productsList.size() ;

            } else {
                ArrayList<Item> itemList = new ArrayList<Item>() ;

                for (Item item : productsList) {
                    /**
                     * 상품을 검색할 때 이름으로 검색? 아님 모델명으로 검색? => 둘 다 가능하나??
                     */
                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            item.getModelNumber().toUpperCase().contains(constraint.toString().toUpperCase()))
                    {
                        itemList.add(item) ;
                    }
                }

                results.values = itemList ;
                results.count = itemList.size() ;
                Log.d("adap size", ""+ itemList.size());
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // update listview by filtered data list.
            Log.d("MyAdapter", ((ArrayList<Item>) results.values).size()+"");
            filteredItemList = (ArrayList<Item>) results.values;

            // notify
            if (results.count > 0) {
                notifyDataSetChanged() ;
            } else {
                notifyDataSetInvalidated() ;
            }
        }
    }

    // 필터추가 끝

    public MyAdapter(Context context, ArrayList<Item> productsList){
        this.context = context;
        this.productsList = productsList;
        filteredItemList = productsList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return filteredItemList .size();
    }

    @Override
    public Object getItem(int position) {
        return filteredItemList .get(position);
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
                .load("http://www.usausashop.com/web/product/big/201707/427_shop1_650014.jpg")
                .into(holder.iv_pImg);
        Log.d("Adapter", filteredItemList .get(current).getImageUrl());
        holder.tv_pName.setText(filteredItemList .get(current).getName());
        holder.tv_pPrice.setText(filteredItemList .get(current).getPrice()+"");
        holder.tv_pSize.setText(filteredItemList .get(current).getInformation());
        holder.tv_pQuantity.setText(filteredItemList .get(current).getAmount()+"");

        return view;
    }
}
