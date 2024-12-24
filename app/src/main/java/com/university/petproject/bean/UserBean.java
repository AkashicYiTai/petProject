package com.university.petproject.bean;

public class UserBean {
    private String id;
    private String userName;
    private String passWord;
    private String nick;
    private int type;
    private String avatar;
    private String des;

    public UserBean(String id, String userName, String passWord, String nick, int type) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.nick = nick;
        this.type = type;
    }
    public UserBean(String id, String userName, String passWord, String nick, int type,String des) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.nick = nick;
        this.type = type;
        this.des = des;
    }



    public UserBean(String id, String userName, String passWord, String nick, int type, String avatar, String des) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.nick = nick;
        this.type = type;
        this.avatar = avatar;
        this.des = des;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
