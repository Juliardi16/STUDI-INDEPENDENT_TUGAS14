package com.juliardi.messengger.model;

public class SliderItem {
    private String desc;
    private int imageUrl;

    public SliderItem( int imageUrl,String des) {
        this.desc = des;
        this.imageUrl = imageUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
