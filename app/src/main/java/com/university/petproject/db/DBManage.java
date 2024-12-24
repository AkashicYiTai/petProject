package com.university.petproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.university.petproject.bean.*;

import java.util.ArrayList;

public class DBManage {


    private final DatabaseHelper databaseHelper;
    private static DBManage dbManage;

    private DBManage(Context context) {
        databaseHelper = new DatabaseHelper(context);

    }

    public static DBManage getInstance(Context context){
        if (dbManage==null){
            synchronized (DBManage.class){
                if (dbManage==null) {
                    dbManage = new DBManage(context);
                }
            }
        }
        return dbManage;
    }


    //添加用户
    public long addUser(UserBean user){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",user.getUserName());
        cv.put("psw",user.getPassWord());
        cv.put("nick",user.getNick());
        cv.put("type",user.getType());
        cv.put("des",user.getDes());
        return database.insert("user", null, cv);
    }

    public ArrayList<UserBean> selectUser(String userName){
        ArrayList<UserBean> booksBeans = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM user WHERE name LIKE ?",new String[]{userName});

        while (cursor.moveToNext()){

            String  id =cursor.getString(0);
            String  name =cursor.getString(1);
            String  psw =cursor.getString(2);
            String  nick =cursor.getString(3);
            int  type =cursor.getInt(4);
            String  avatar =cursor.getString(5);
            String  des =cursor.getString(6);
            booksBeans.add(new UserBean(id,name,psw,nick,type,avatar,des));
        }

        return booksBeans;
    }

    public long updateUser(UserBean user){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        if (user.getUserName()!=null)
            cv.put("name",user.getUserName());
        if (user.getPassWord()!=null)
            cv.put("psw",user.getPassWord());
        if (user.getNick()!=null)
            cv.put("nick",user.getNick());
        if (user.getAvatar()!=null)
            cv.put("avatar",user.getAvatar());
        if (user.getDes()!=null)
            cv.put("des",user.getDes());
        //修改条件
        String whereClause = "id=?";

        return database.update("user",cv,whereClause,new String[]{user.getId()});
    }




    public long addFood(FoodSolutionBean foodBean){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",foodBean.getName());
        cv.put("des",foodBean.getDes());
        cv.put("type",foodBean.getType());
        cv.put("img",foodBean.getImg());
        return database.insert("food", null, cv);
    }


    public long delFood(String foodid){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        return database.delete("food", "id=?", new String[]{foodid});
    }


    public long addCommendBean(CommendBean commendBean){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("content",commendBean.getContent());
        cv.put("foodid",commendBean.getFoodid());
        cv.put("username",commendBean.getUsername());
        cv.put("userid",commendBean.getUserid());
        return database.insert("commend", null, cv);
    }

    public ArrayList<CommendBean> selectCommendBean(String foodid) {
        ArrayList<CommendBean> commendBeans = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM commend WHERE foodid = ?", new String[]{foodid});

        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String content = cursor.getString(1);
            int foodid1 = cursor.getInt(2);
            String username = cursor.getString(3);
            int userid = cursor.getInt(4);
            commendBeans.add(new CommendBean(id, content, foodid1, username, userid));
        }
        return commendBeans;
    }

    public int selectFoodIDByName(String foodName){
        ArrayList<FoodSolutionBean> booksBeans = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM food WHERE name LIKE ?",new String[]{foodName});

        while (cursor.moveToNext()){

            int  id =cursor.getInt(0);
            String  name =cursor.getString(1);
            String  des =cursor.getString(2);
            int  type =cursor.getInt(3);
            String  img =cursor.getString(4);
            booksBeans.add(new FoodSolutionBean(id,name,des,type,img));
        }
        if (booksBeans.size()>0){
            FoodSolutionBean foodBean = booksBeans.get(0);
            return foodBean.getId();
        }

        return 0;
    }

    public ArrayList<FoodSolutionBean> selectFoodsByName(String foodName){
        ArrayList<FoodSolutionBean> booksBeans = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM food WHERE name LIKE ?",new String[]{"%"+foodName+"%"});

        while (cursor.moveToNext()){

            int  id =cursor.getInt(0);
            String  name =cursor.getString(1);
            String  des =cursor.getString(2);
            int  type =cursor.getInt(3);
            String  img =cursor.getString(4);
            booksBeans.add(new FoodSolutionBean(id,name,des,type,img));
        }


        return booksBeans;
    }

    public ArrayList<FoodSolutionBean> selectAllFoods(){
        ArrayList<FoodSolutionBean> booksBeans = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM food",null);

        while (cursor.moveToNext()){

            int  id =cursor.getInt(0);
            String  name =cursor.getString(1);
            String  des =cursor.getString(2);
            int  type =cursor.getInt(3);
            String  img =cursor.getString(4);
            booksBeans.add(new FoodSolutionBean(id,name,des,type,img));
        }


        return booksBeans;
    }


    //增加聊天记录
    public long addChats(ChatsBean chatsBean){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",chatsBean.getName());
        cv.put("des",chatsBean.getDes());
        cv.put("type",chatsBean.getType());
        cv.put("img",chatsBean.getImg());
        cv.put("userid",chatsBean.getUserid());
        return database.insert("chats", null, cv);
    }

    //查询所有聊天
    public ArrayList<ChatsBean> selectAllChats(){
        ArrayList<ChatsBean> booksBeans = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM chats Left Join user on chats.userid=user.id",null);

        while (cursor.moveToNext()){

            int  id =cursor.getInt(0);
            String  name =cursor.getString(1);
            String  des =cursor.getString(2);
            int  type =cursor.getInt(3);
            String  img =cursor.getString(4);
            int  userid =cursor.getInt(5);
            String  username =cursor.getString(7);
            booksBeans.add(new ChatsBean(id,name,des,type,img,userid,username));
        }


        return booksBeans;
    }







    //增加宠物店
    public long addPetShop(PetShopBean petShopBean){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",petShopBean.getName());
        cv.put("des",petShopBean.getDes());
        cv.put("img",petShopBean.getImg());
        cv.put("adress",petShopBean.getAdress());
        return database.insert("petShop", null, cv);
    }

    public long delPetShop(String petshopid){
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        return database.delete("petShop", "id=?", new String[]{petshopid});
    }

    //查询所有店铺
    public ArrayList<PetShopBean> selectAllPetShop(){
        ArrayList<PetShopBean> petShopBeans = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM petShop",null);

        while (cursor.moveToNext()){

            int  id =cursor.getInt(0);
            String  name =cursor.getString(1);
            String  des =cursor.getString(2);
            String  img =cursor.getString(3);
            String  address =cursor.getString(4);
            petShopBeans.add(new PetShopBean(id,name,des,img,address));
        }


        return petShopBeans;
    }

    public ArrayList<PetShopBean> selectPetShopByName(String name){
        ArrayList<PetShopBean> petShopBeans = new ArrayList<>();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();

        Cursor cursor=database.rawQuery("SELECT * FROM petShop WHERE name LIKE ?",new String[]{"%"+name+"%"});

        while (cursor.moveToNext()){

            int  id =cursor.getInt(0);
            String  name1 =cursor.getString(1);
            String  des =cursor.getString(2);
            String  img =cursor.getString(3);
            String  address =cursor.getString(4);
            petShopBeans.add(new PetShopBean(id,name1,des,img,address));
        }


        return petShopBeans;
    }

























}
