package com.ldcc.eleven.qrpick.activities.manager;

/**리사이클러뷰에서 사용할 아이템 객체 클래스
 * pImg 상품 이미지 pName 상품명 pSize 사이즈 pPrice 상품가격 pQuantity 상품재고량*/

public class ProductsList {
    public String pImg;
    public String pName;
    public String pSize;
    public String pPrice;
    public String pQuantity;

    public ProductsList(){
        pImg = "http://www.usausashop.com/web/product/big/201707/427_shop1_650014.jpg";
        pName = "BO7938D065";
        pSize = "90";
        pPrice = "159,000";
        pQuantity = "5";
    }

    public ProductsList(String pImg, String pName, String pSize, String pPrice, String pQuantity){
        this.pImg = pImg;
        this.pName = pName;
        this.pSize = pSize;
        this.pPrice = pPrice;
        this.pQuantity = pQuantity;
    }

    public String getpImg() {
        return pImg;
    }

    public void setpImg(String pImg) {
        this.pImg = pImg;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpSize() {
        return pSize;
    }

    public void setpSize(String pSize) {
        this.pSize = pSize;
    }

    public String getpPrice() {
        return pPrice;
    }

    public void setpPrice(String pPrice) {
        this.pPrice = pPrice;
    }

    public String getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(String pQuantity) {
        this.pQuantity = pQuantity;
    }
}
