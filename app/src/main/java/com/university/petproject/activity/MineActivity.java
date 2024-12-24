package com.university.petproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.university.petproject.R;
import com.university.petproject.bean.UserBean;
import com.university.petproject.db.DBManage;
import com.university.petproject.utils.SpUtil;

import java.util.ArrayList;

public class MineActivity extends AppCompatActivity {

    private DBManage dbManage;
    private ImageView head;
    private TextView tv_name,tv_nick;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);


        String userName = SpUtil.getInstance().getString("userName", "");

        TextView name = (TextView) findViewById(R.id.name);
        TextView role = (TextView) findViewById(R.id.role);

        if (userName.equals("admin")){
            name.setText("用户：admin");
            role.setText("角色： 管理员用户");
        }else {
            name.setText("用户："+userName);
            role.setText("角色： 普通用户");
        }


        findViewById(R.id.outlogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(MineActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });

        findViewById(R.id.editUser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MineActivity.this, EditUserActivity.class));
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MineActivity.this, RePswActivity.class);
                startActivity(intent);
            }
        });

        initView();

    }

    private void initView() {
        dbManage = DBManage.getInstance(this);
        tv_name = findViewById(R.id.tv_name);
        tv_nick = findViewById(R.id.tv_nick);
        head = findViewById(R.id.head);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置用户信息
        ArrayList<UserBean> userBeans = dbManage.selectUser(SpUtil.getInstance().getString("userName",""));
        if (userBeans.size()>0) {
            UserBean userBean = userBeans.get(0);
            if (!TextUtils.isEmpty(userBean.getNick())){
                tv_name.setText(userBean.getNick());
            }else {
                tv_name.setText(userBean.getUserName());
            }
            if (!TextUtils.isEmpty(userBean.getDes())){
                tv_nick.setText(userBean.getDes());
            }
            if (!TextUtils.isEmpty(userBean.getAvatar())){
                Bitmap bitmap = BitmapFactory.decodeFile(userBean.getAvatar());
                if (bitmap!=null) {
                    head.setImageBitmap(bitmap);
                }
            }
        }
    }
}
