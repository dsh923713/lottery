package com.lottery.bean;

/**
 * Created by Administrator on 2017/5/17.
 */

public class BusinessBean {
    private String name;
    private String dateTime;
    private String style;
    private String moneyNum;

    public BusinessBean(String name, String dateTime, String style, String moneyNum) {
        this.name = name;
        this.dateTime = dateTime;
        this.style = style;
        this.moneyNum = moneyNum;
    }

    public String getName() {
        return name;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getStyle() {
        return style;
    }

    public String getMoneyNum() {
        return moneyNum;
    }
}
