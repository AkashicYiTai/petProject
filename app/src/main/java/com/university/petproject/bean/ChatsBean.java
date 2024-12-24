package com.university.petproject.bean;

import java.io.Serializable;

public class ChatsBean implements Serializable {
    private int id;
    private String name;
    private String des;
    private int type;
    private String img;
    private int userid;
    private String username;

    public ChatsBean(int id, String name, String des, int type, String img, int userid) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.type = type;
        this.img = img;
        this.userid = userid;
    }

    public ChatsBean(String name, String des, int type, String img, int userid) {
        this.name = name;
        this.des = des;
        this.type = type;
        this.img = img;
        this.userid = userid;
    }

    public ChatsBean(int id, String name, String des, int type, String img, int userid, String username) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.type = type;
        this.img = img;
        this.userid = userid;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
