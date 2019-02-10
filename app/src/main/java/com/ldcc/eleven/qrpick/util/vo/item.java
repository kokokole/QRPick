package com.ldcc.eleven.qrpick.util.vo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

public class Item implements Parcelable, Serializable {
    private String modelNumber, category, name;
    private int price, discountPrice, amount, brandId, id;
//    private List<information> information;
private String information;

    @Override
    public String toString() {
        return modelNumber + " // " + category + " // " + name + " // " + price + " / " + discountPrice + " // " + amount + " // " + brandId + " // " + id + " // " + information ;
    }

    private String imagePath, imageUrl, createdAt, updatedAt;

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(modelNumber);
        parcel.writeString(category);
        parcel.writeString(name);
        parcel.writeString(information);
        parcel.writeInt(price);
        parcel.writeInt(discountPrice);
        parcel.writeInt(amount);
        parcel.writeInt(brandId);
        parcel.writeInt(id);

    }

    public Item(Parcel src){
        this.modelNumber = src.readString();
        this.category= src.readString();
        this.name= src.readString();
        this.information= src.readString();
        this.price= src.readInt();
        this.discountPrice= src.readInt();
        this.amount= src.readInt();
        this.brandId= src.readInt();
        this.id= src.readInt();

    }

    public static final Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return new Item(parcel);
        }

        @Override
        public Object[] newArray(int i) {
            return new Item[i];
        }
    };
}

