package com.ldcc.eleven.qrpick.util.vo;

import java.util.List;

public class DetailItem {
    String code;
    Item data;
//List<Item> data;
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Item getData() {
        return data;
    }

    public void setData(Item data) {
        this.data = data;
    }
//        public List<Item> getData() {
//        return data;
//    }
//
//    public void setData(List<Item> data) {
//        this.data = data;
//    }
}
