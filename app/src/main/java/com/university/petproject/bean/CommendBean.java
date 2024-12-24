package com.university.petproject.bean;

import java.io.Serializable;

public class CommendBean implements Serializable {
    private int id;
    private String content;
    private int foodid;
    private String username;
    private int userid;

    public CommendBean(int id, String content, int foodid, String username, int userid) {
        this.id = id;
        this.content = content;
        this.foodid = foodid;
        this.username = username;
        this.userid = userid;
    }

    public CommendBean(String content, int foodid, String username, int userid) {
        this.content = content;
        this.foodid = foodid;
        this.username = username;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getFoodid() {
        return foodid;
    }

    public void setFoodid(int foodid) {
        this.foodid = foodid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
