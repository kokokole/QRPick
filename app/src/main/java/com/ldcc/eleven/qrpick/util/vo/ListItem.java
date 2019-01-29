package com.ldcc.eleven.qrpick.util.vo;

import java.util.List;

/**
 * 특정 매대의 상품 리스트
 */
public class ListItem {
    String code;
    List<Item> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }
}
