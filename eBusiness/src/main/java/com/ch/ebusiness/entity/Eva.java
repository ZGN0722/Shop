package com.ch.ebusiness.entity;

public class Eva {
    private int id;
    private int grating;
    private String goodsEva;
    private int srating;
    private String storeEva;
    private int buser_id;

    public void setId(int id) {
        this.id = id;
    }

    public void setBuser_id(int buser_id) {
        this.buser_id = buser_id;
    }

    public int getBuser_id() {
        return buser_id;
    }

    public int getGrating() {
        return grating;
    }

    public int getId() {
        return id;
    }

    public int getSrating() {
        return srating;
    }

    public String getGoodsEva() {
        return goodsEva;
    }

    public String getStoreEva() {
        return storeEva;
    }

    public void setGoodsEva(String goodsEva) {
        this.goodsEva = goodsEva;
    }

    public void setGrating(int grating) {
        this.grating = grating;
    }

    public void setSrating(int srating) {
        this.srating = srating;
    }

    public void setStoreEva(String storeEva) {
        this.storeEva = storeEva;
    }
}
