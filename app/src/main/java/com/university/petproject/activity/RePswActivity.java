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

public class RePswActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword,mPassword1;
    private DBManage dbManage;


    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManage = DBManage.getInstance(RePswActivity.this);
        setContentView(R.layout.activity_repsw);
        initView();
    }

    private void initView() {
        findViewById(R.id.finish).setOnClickListener(view -> finish());
        mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        mPassword1 = (EditText) findViewById(R.id.password1);




        Button mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = mUsername.getText().toString();
                String strPassword = mPassword.getText().toString();
                String strPassword1 = mPassword1.getText().toString();

                if (!strPassword.equals(strPassword1)){
                    Toast.makeText(RePswActivity.this, "密码不相同", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(strUsername)){
                    Toast.makeText(RePswActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(strPassword)){
                    Toast.makeText(RePswActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                    return;
                }

                ArrayList<UserBean> userBeans = dbManage.selectUser(SpUtil.getInstance().getString("userName",""));
                if (userBeans.size()>0){
                    UserBean userBean = userBeans.get(0);

                    if (strUsername.equals(userBean.getPassWord())){

                        userBean.setPassWord(strPassword);
                        
                        dbManage.updateUser(userBean);

                        Toast.makeText(RePswActivity.this, "修改完成", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RePswActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(RePswActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }



            }
        });

 
    }
}
