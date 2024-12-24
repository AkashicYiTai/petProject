package com.university.petproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.university.petproject.R;
import com.university.petproject.bean.CommendBean;
import com.university.petproject.bean.FoodSolutionBean;
import com.university.petproject.bean.UserBean;
import com.university.petproject.db.DBManage;
import com.university.petproject.utils.SpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    TextView mName,mDetail;
    private ImageView mLoadImgShow;
    private EditText editText;
    private DBManage dbManage;
    private String userName;
    private String userid;
    private LinearLayout layout;
    private FoodSolutionBean foodSolutionBean;
    private UserBean userBean;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        foodSolutionBean = (FoodSolutionBean) getIntent().getSerializableExtra("item");
        dbManage = DBManage.getInstance(FoodActivity.this);

        userName = SpUtil.getInstance().getString("userName", "");
        userid = SpUtil.getInstance().getString("id", "");

        ArrayList<UserBean> userBeans = dbManage.selectUser(userName);
        if (userBeans.size()>0) {
            userBean = userBeans.get(0);
        }

        findViewById(R.id.finish).setOnClickListener(view -> finish());
        //找控件id
        mName = findViewById(R.id.title);
        mDetail = findViewById(R.id.detail);
        mLoadImgShow = findViewById(R.id.loadImgShow);


        mName.setText(foodSolutionBean.getName());
        mDetail.setText(foodSolutionBean.getDes());

        Bitmap bitmap = BitmapFactory.decodeFile(foodSolutionBean.getImg());
        if (bitmap==null){
            mLoadImgShow.setImageResource(Integer.parseInt(foodSolutionBean.getImg()));
        }else {
            mLoadImgShow.setImageBitmap(bitmap);
        }




        editText = findViewById(R.id.edit);
        layout = findViewById(R.id.layout);
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String content = editText.getText().toString();
            if (TextUtils.isEmpty(content)){
                Toast.makeText(FoodActivity.this, "内容为空", Toast.LENGTH_SHORT).show();
                return;
            }
            //增加到数据库
            String nick = userBean.getNick();
            String name;
            if (!TextUtils.isEmpty(nick)){
                name= userBean.getNick();
            }else {
                name=userBean.getUserName();
            }
            //将数据存入实体类
            CommendBean commendBean =
                    new CommendBean(content, foodSolutionBean.getId(), name, Integer.parseInt(userid));
            //实体类添加到数据库中
            dbManage.addCommendBean(commendBean);
            //显示布局
            addCommend(content,name);
            editText.setText("");
        }
    });


        initData();

    }
    private void addCommend(String content,String userName){
        //增加到布局
        View inflate = LayoutInflater.from(FoodActivity.this).inflate(R.layout.include_commend, layout, false);
        TextView textView = inflate.findViewById(R.id.content);
        TextView name = inflate.findViewById(R.id.name);
        textView.setText(content);
        name.setText(userName+":");
        layout.addView(inflate);
    }

    private void initData() {
        ArrayList<CommendBean> commendBeans = dbManage.selectCommendBean(String.valueOf(foodSolutionBean.getId()));
        for (CommendBean commendBean : commendBeans) {

            addCommend(commendBean.getContent(),commendBean.getUsername());


        }




    }


}
