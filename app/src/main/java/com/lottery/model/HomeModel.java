package com.lottery.model;

/**
 * Created by Administrator on 2017/5/15.
 */

public class HomeModel {
    private int imageId;
    private String title;

    public HomeModel(int imageId, String title){
        this.imageId = imageId;
        this.title = title;
    }
    public int getImageId() {
        return imageId;
    }

    public String getTitle() {
        return title;
    }

}
