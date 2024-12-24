package com.university.petproject.bean;

import java.io.Serializable;

public class PetShopBean implements Serializable {
    private int id;
    private String name;
    private String des;
    private String img;
    private String adress;

    public PetShopBean(int id, String name, String des, String img, String adress) {
        this.id = id;
        this.name = name;
        this.des = des;
        this.img = img;
        this.adress = adress;
    }

    public PetShopBean(String name, String des, String img, String adress) {
        this.name = name;
        this.des = des;
        this.img = img;
        this.adress = adress;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
