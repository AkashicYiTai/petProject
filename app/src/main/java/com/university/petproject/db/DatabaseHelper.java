package com.university.petproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.university.petproject.R;


public class DatabaseHelper extends SQLiteOpenHelper {

    // 数据库版本号
    private static Integer Version = 2;
    public final static String tableName = "storage";

    public DatabaseHelper(@Nullable Context context) {
        super(context, tableName, null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户
       // String sql = "create table if not exists user(id integer primary key autoincrement,name varchar(64),psw varchar(64),nick varchar(64),type int)";
        //用户
        String sql = "create table if not exists user(id integer primary key autoincrement,name varchar(64),psw varchar(64),nick varchar(1024),type int,avatar varchar(1024),des varchar(1024))";
        db.execSQL(sql);
        db.execSQL("INSERT INTO user(name,psw,nick,type) values('admin','123456','管理员',1)",new Integer[]{});

        //饮食方案
        String food = "create table if not exists food(id integer primary key autoincrement,name varchar(64),des varchar(1024),type int,img varchar(1024))";
        db.execSQL(food);
        addFood(db);

        //聊天
        String chats = "create table if not exists chats(id integer primary key autoincrement,name varchar(64),des varchar(1024),type int,img varchar(1024),userid int)";
        db.execSQL(chats);
        addChat(db);

        //宠物店
        String petShop = "create table if not exists petShop(id integer primary key autoincrement,name varchar(64),des varchar(1024),img varchar(1024),adress varchar(1024))";
        db.execSQL(petShop);
        addPetShop(db);

        //评论
        String commend = "create table if not exists commend(id integer primary key autoincrement,content varchar(1024),foodid int,username varchar(1024),userid int)";
        db.execSQL(commend);
    }

    private void addChat(SQLiteDatabase db) {
        db.execSQL("INSERT INTO chats(name,des,type,img,userid) values('喵喵','宠物好可爱！！！',1,?,0)",new Integer[]{R.mipmap.img_pett1});
        db.execSQL("INSERT INTO chats(name,des,type,img,userid) values('大大大西瓜','有领养喵咪的么',1,?,0)",new Integer[]{R.mipmap.img_cat1});
        db.execSQL("INSERT INTO chats(name,des,type,img,userid) values('熊熊熊孩子','我家可以收养。联系电话：13666668888',1,?,0)",new Integer[]{R.mipmap.img_cat1});
        db.execSQL("INSERT INTO chats(name,des,type,img,userid) values('哈哈嘻嘻','宠物猫猫可爱可爱',1,?,0)",new Integer[]{R.mipmap.img_cat2});
        db.execSQL("INSERT INTO chats(name,des,type,img,userid) values('嘻哈哈希','旺旺旺旺旺旺......',1,?,0)",new Integer[]{R.mipmap.img_dog1});
    }

    private void addPetShop(SQLiteDatabase db) {
        db.execSQL("INSERT INTO petShop(name,des,img,adress) values('喵咪咪宠物店','营业时间8：00~21：00  \n宠物店（pet shop）是专门为宠物提供宠物用品零售、宠物美容、宠物寄养、宠物活体销售的场所。其经营项目一般包括宠物用品超市、活体销售、宠物美容、宠物寄养、宠物医疗、宠物乐园、宠物摄影、待产养护。有时宠物店又等同于宠物用品店、宠物美容店、宠物寄存、宠物医院、宠物驯养等。',?" +
                ",'猫咪宠物路179号')",new Integer[]{R.mipmap.img_pet1});

        db.execSQL("INSERT INTO petShop(name,des,img,adress) values('旺旺宠物店','营业时间9：00~23：00  \n经营项目一般包括宠物用品超市、活体销售、宠物美容、宠物寄养、宠物医疗、宠物乐园、宠物摄影、待产养护。有时宠物店又等同于宠物用品店、宠物美容店、宠物寄存、宠物医院、宠物驯养等。',?" +
                ",'旺旺路133号')",new Integer[]{R.mipmap.img_pet2});
    }

    private void addFood(SQLiteDatabase db) {
        db.execSQL("INSERT INTO food(name,des,type,img) values ('简单的宠物食谱','" +
                "只要有个烘干机，你就可以给家里的狗子做超级无敌简单的无添加零食哟。\n" +
                "\n" +
                "By 丢丢的短发\n" +
                "\n" +
                "用料\n" +
                "\n" +
                "藕 1节\n" +
                "鸭肉 500克\n" +
                "三文鱼 500克\n" +
                "鸡肉 500克\n" +
                "做法步骤" +
                "1、藕切片，塞上鸭肉或者鸡肉进烘干机" +
                "2、烘干后的样子，我买的这个烘干机比较便宜，几十块钱，基本是70度，要一晚上，放那里不用管的。\n" +
                "3、烘干的样子，藕片鸭肉一般我都是天热的时候做给狗子吃，不上火。" +
                "4、烘干后，尽快给狗子吃完，密封保存，实在吃不完，就冷冻。',1,?)",new Integer[]{
                R.mipmap.img_1});
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
