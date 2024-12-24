package com.university.petproject.bean;

import java.io.Serializable;

public class FoodSolutionBean implements Serializable {
    private int id;
    private String name;
    private String des;
    private int type;
    private String img;


    public FoodSolutionBean(int id, String name, String des, int type, String img) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.type = type;
        this.img = img;
    }

    public FoodSolutionBean(String name, String des, int type, String img) {
        this.name = name;
        this.des = des;
        this.type = type;
        this.img = img;
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
}
