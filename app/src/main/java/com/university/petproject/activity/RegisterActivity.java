package com.university.petproject.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.university.petproject.R;
import com.university.petproject.bean.UserBean;
import com.university.petproject.db.DBManage;
import com.university.petproject.utils.SpUtil;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private DBManage dbManage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dbManage = DBManage.getInstance(RegisterActivity.this);
        initView();
    }

    private void initView() {
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);



        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String name = mUsername.getText().toString();
                String psd = mPassword.getText().toString();

                if (TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(psd)){
                    Toast.makeText(RegisterActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                //存入数据库
                ArrayList<UserBean> userBeans = dbManage.selectUser(name);
                if (userBeans.size()>0){
                    Toast.makeText(RegisterActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
                }else {
                    long l = dbManage.addUser(new UserBean(null, name, psd, "", 1));
                    if (l>0){
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        SpUtil.getInstance().putString("userName",name);

                        //登录成功
                        finish();
//                        Intent intent=null;
//                        switch (userType){
//                            case 0://学生
//                                intent = new Intent(RegisterActivity.this, HomeStudentActivity.class);
//                                break;
//                            case 1://辅导员
//                                intent = new Intent(RegisterActivity.this, HomeActivity.class);
//                                break;
//                            case 2://门卫
//                                intent = new Intent(RegisterActivity.this, HomeGuardActivity.class);
//                                break;
//                        }
//
//                        startActivity(intent);

                    }else {
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }

                }



            }
        });
    }
}
