package com.university.petproject;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.university.petproject.activity.FoodSolutionActivity;
import com.university.petproject.activity.MineActivity;
import com.university.petproject.activity.PetChatActivity;
import com.university.petproject.activity.PetShopActivity;
import com.university.petproject.service.MyMusicService;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection connection;
    boolean isBound = false;
    private MyMusicService.MyMusicBind binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //方案
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FoodSolutionActivity.class));
            }
        });

        //宠物圈
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PetChatActivity.class));
            }
        });

        //宠物店
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PetShopActivity.class));
            }
        });

        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MineActivity.class));
            }
        });


        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName className, IBinder service) {
                // 服务已连接，获取服务实例
                binder = (MyMusicService.MyMusicBind) service;
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName arg0) {
                // 服务已断开连接
                isBound = false;
            }
        };
        Intent intent = new Intent(this, MyMusicService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);



        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //播放
                binder.startPlay(R.raw.a);
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binder.pause();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binder.pause();
        // 解绑服务
        if (isBound) {
            unbindService(connection);
            isBound = false;
        }
    }
}