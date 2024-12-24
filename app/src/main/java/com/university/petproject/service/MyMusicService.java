package com.university.petproject.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.university.petproject.R;

import java.io.IOException;

public class MyMusicService extends Service {

    private MediaPlayer mMediaPlayer;
    private static final int NOTIFICATION_ID = 1;
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("music_channel_id", "Music Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        // 创建通知
        Notification notification = new NotificationCompat.Builder(this, "music_channel_id")
                .setContentTitle("My Music Service")
                .setContentText("Music is playing...")
                .setSmallIcon(R.mipmap.icon_head)
                .build();
        // 启动前台服务
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyMusicBind(this);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public class MyMusicBind extends Binder{

        private MyMusicService mMusicService;

        public MyMusicBind(MyMusicService musicService){
            mMusicService = musicService;
        }

        public void startPlay(Integer res){
            if (mMediaPlayer!= null) {
                // 如果当前有音乐正在播放，先停止并释放MediaPlayer资源
                mMediaPlayer.stop();
                mMediaPlayer.release();
            }
            // 创建MediaPlayer并播放音乐1（假设音乐1在res/raw目录下名为music1.mp3）
            AssetFileDescriptor assetFileDescriptor = getResources().openRawResourceFd(res);
            mMediaPlayer = new MediaPlayer();
            try {
                mMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor()
                        ,assetFileDescriptor.getStartOffset(),
                        assetFileDescriptor.getLength());
                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    //准备资源准备好了会调用这个
                    public void onPrepared(MediaPlayer arg0) {
                        //播放音乐
                        mMediaPlayer.start();
                    }
                });
                mMediaPlayer.prepareAsync();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void start(){
            mMediaPlayer.start();
        }
        public void pause(){
            if (mMediaPlayer!= null) {
                mMediaPlayer.pause();
            }
        }
    }


}
