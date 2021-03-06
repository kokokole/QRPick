package com.ldcc.eleven.qrpick.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
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
import org.w3c.dom.Comment;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter implements Parcelable {

    Context context;
    LayoutInflater layoutInflater;
    //    ArrayList<ProductsList> productsList;
    ArrayList<Item> productsList;

//    ArrayList<Item> filteredItemList;


    /**
     * Pacelable
     */

    public MyAdapter(Parcel src) {
        productsList = (ArrayList<Item>) src.readSerializable();
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel source) {  // Parcelable를 가지고 CVAdapter를 만들 때 호출됨
            return new MyAdapter(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new MyAdapter[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) { // CVAdapter를 Parcelable로 만들 때 호출됨
        dest.writeSerializable(productsList);
    }


    /**
     * 필터추가
     */

//    Filter listFilter;
//
//    @Override
//    public Filter getFilter() {
//        if(listFilter == null){
//            listFilter = new ListFilter();
//        }
//        return listFilter;
//    }
//
//    private class ListFilter extends Filter {
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            FilterResults results = new FilterResults() ;
//            Log.d("MyAdapter con", constraint.toString());
//            if (constraint == null || constraint.length() == 0) {
//                results.values = productsList ;
//                results.count = productsList.size() ;
//
//            } else {
//                ArrayList<Item> itemList = new ArrayList<Item>() ;
//
//                for (Item item : productsList) {
//                    /**
//                     * 상품을 검색할 때 이름으로 검색? 아님 모델명으로 검색? => 둘 다 가능하나??
//                     */
//                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase()) ||
//                            item.getModelNumber().toUpperCase().contains(constraint.toString().toUpperCase()))
//                    {
//                        itemList.add(item) ;
//                    }
//                }
//
//                results.values = itemList ;
//                results.count = itemList.size() ;
//                Log.d("adap size", ""+ itemList.size());
//            }
//            return results;
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//
//            // update listview by filtered data list.
//            Log.d("MyAdapter", ((ArrayList<Item>) results.values).size()+"");
//            filteredItemList = (ArrayList<Item>) results.values;
//
//            // notify
//            if (results.count > 0) {
//                notifyDataSetChanged() ;
//            } else {
//                notifyDataSetInvalidated() ;
//            }
//        }
//    }
//
//    // 필터추가 끝
    public MyAdapter(Context context, ArrayList<Item> productsList) {
        this.context = context;
        this.productsList = productsList;
//        filteredItemList = productsList;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Item> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Item> productsList) {
        this.productsList = productsList;
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

    public void add(Item item) {
        productsList.add(item);
        notifyDataSetChanged();
    }
    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 100, 100, false);
        return new BitmapDrawable(context.getResources(), bitmapResized);
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        View view = convertview;
        final int current = position;
        ProductViewHolder holder;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.recyclerview_item, null);
            holder = new ProductViewHolder();
            holder.iv_pImg = view.findViewById(R.id.iv_pImg);
            holder.tv_pName = view.findViewById(R.id.tv_pName);
            holder.tv_pPrice = view.findViewById(R.id.tv_pPrice);
            holder.tv_pSize = view.findViewById(R.id.tv_pSize);
            holder.tv_pQuantity = view.findViewById(R.id.tv_pQuantity);
            holder.tv_pBrand = view.findViewById(R.id.tv_pBrand);
            view.setTag(holder);
        } else {
            holder = (ProductViewHolder) view.getTag();
        }

        /**데이터 셋팅*/
        //holder.iv_pImg.setImageResource(R.drawable.test);
        //"http://www.usausashop.com/web/product/big/201707/427_shop1_650014.jpg"

        if (productsList.get(current).getImageUrl() == null || productsList.get(current).getImageUrl().equals("")) {
            Log.e("image", "imageeeee");
            if (productsList.get(current).getModelNumber().equals("7218410082")) {
                holder.iv_pImg.setImageDrawable(resize(context.getResources().getDrawable(R.drawable.a7218410082)));
            } else if (productsList.get(current).getModelNumber().equals("OW9SJ150")) {
                holder.iv_pImg.setImageDrawable(resize(context.getResources().getDrawable(R.drawable.ow9sj150)));

            } else if (productsList.get(current).getModelNumber().equals("MABLHGLDJ510121E23")) {
                holder.iv_pImg.setImageDrawable(resize(context.getResources().getDrawable(R.drawable.mam)));
            } else if (productsList.get(current).getModelNumber().equals("HSSW8D753")) {
                holder.iv_pImg.setImageDrawable(resize(context.getResources().getDrawable(R.drawable.hssw8b753)));
            }
        }else{
            Glide.with(context)
                    .load(productsList.get(current).getImageUrl())
                    .into(holder.iv_pImg);
        }



//        Log.d("Adapter", filteredItemList .get(current).getImageUrl());
            holder.tv_pName.setText(productsList.get(current).getModelNumber());
            holder.tv_pPrice.setText(productsList.get(current).getPrice() + "");
            holder.tv_pBrand.setText(productsList.get(current).getBrandId() + "");
//        holder.tv_pSize.setText(filteredItemList .get(current).getInformation());
            holder.tv_pQuantity.setText(productsList.get(current).getAmount() + "");

            return view;
        }
    }
