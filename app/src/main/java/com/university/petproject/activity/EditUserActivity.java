package com.university.petproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.university.petproject.R;
import com.university.petproject.bean.UserBean;
import com.university.petproject.db.DBManage;
import com.university.petproject.utils.SpUtil;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EditUserActivity extends AppCompatActivity {
    private ImageView head;
    private EditText tv_nick,tv_info;
    private DBManage dbManage;
    private String imgPath;


    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManage = DBManage.getInstance(EditUserActivity.this);
        setContentView(R.layout.activity_edituser);
        initView();
    }

    private void initView() {
        findViewById(R.id.finish).setOnClickListener(view -> finish());
        head =  findViewById(R.id.head);
        tv_nick =  findViewById(R.id.tv_nick);
        tv_info = findViewById(R.id.tv_info);

        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //相册选择图片
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 2);
            }
        });


        Button mLogin = (Button) findViewById(R.id.login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strNick = tv_nick.getText().toString();
                String strInfo = tv_info.getText().toString();


//                if (TextUtils.isEmpty(strNick)){
//                    Toast.makeText(RePswActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(strInfo)){
//                    Toast.makeText(RePswActivity.this, "请输入用户名", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                ArrayList<UserBean> userBeans = dbManage.selectUser(SpUtil.getInstance().getString("userName",""));
                if (userBeans.size()>0){
                    UserBean userBean = userBeans.get(0);
                    if (!TextUtils.isEmpty(imgPath)) {
                        userBean.setAvatar(imgPath);
                    }
                    if (!TextUtils.isEmpty(strNick)) {
                        userBean.setNick(strNick);
                    }
                    if (!TextUtils.isEmpty(strInfo)) {
                        userBean.setDes(strInfo);
                    }

                    dbManage.updateUser(userBean);

                    Toast.makeText(EditUserActivity.this, "修改完成", Toast.LENGTH_SHORT).show();
                    finish();



                }else {
                    Toast.makeText(EditUserActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }



            }
        });



        ArrayList<UserBean> userBeans = dbManage.selectUser(SpUtil.getInstance().getString("userName",""));
        if (userBeans.size()>0) {
            UserBean userBean = userBeans.get(0);
            if (!TextUtils.isEmpty(userBean.getNick())){
                tv_nick.setText(userBean.getNick());
            }
            if (!TextUtils.isEmpty(userBean.getDes())){
                tv_info.setText(userBean.getDes());
            }
            if (!TextUtils.isEmpty(userBean.getAvatar())){
                Bitmap bitmap = BitmapFactory.decodeFile(userBean.getAvatar());
                if (bitmap!=null) {
                    head.setImageBitmap(bitmap);
                }
            }
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            // 从相册返回的数据
            Log.e(this.getClass().getName(), "Result:" + data.toString());
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                head.setImageURI(uri);
                imgPath = ImageSizeCompress(uri);
            }
        }
    }

    private String ImageSizeCompress(Uri uri){
        InputStream Stream = null;
        InputStream inputStream = null;
        try {
            //根据uri获取图片的流
            inputStream = getContentResolver().openInputStream(uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            //options的in系列的设置了，injustdecodebouond只解析图片的大小，而不加载到内存中去
            options.inJustDecodeBounds = true;
            //1.如果通过options.outHeight获取图片的宽高，就必须通过decodestream解析同options赋值
            //否则options.outheight获取不到宽高
            BitmapFactory.decodeStream(inputStream,null,options);
            //2.通过 btm.getHeight()获取图片的宽高就不需要1的解析，我这里采取第一张方式
//            Bitmap btm = BitmapFactory.decodeStream(inputStream);
            //以屏幕的宽高进行压缩
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int heightPixels = displayMetrics.heightPixels;
            int widthPixels = displayMetrics.widthPixels;
            //获取图片的宽高
            int outHeight = options.outHeight;
            int outWidth = options.outWidth;
            //heightPixels就是要压缩后的图片高度，宽度也一样
            int a = (int) Math.ceil((outHeight/(float)heightPixels));
            int b = (int) Math.ceil(outWidth/(float)widthPixels);
            //比例计算,一般是图片比较大的情况下进行压缩
            int max = Math.max(a, b);
            if(max > 1){
                options.inSampleSize = max;
            }
            //解析到内存中去
            options.inJustDecodeBounds = false;
//            根据uri重新获取流，inputstream在解析中发生改变了
            Stream = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(Stream, null, options);
            String filePath = saveBitmap(bitmap);


            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(inputStream != null) {
                    inputStream.close();
                }
                if(Stream != null){
                    Stream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return  null;
    }

    private String saveBitmap(Bitmap bm) {
        Log.d("Save Bitmap", "Ready to save picture");

        //如果指定文件夹创建成功，那么我们则需要进行图片存储操作

        File saveFile = new File(getExternalCacheDir(), "food"+System.currentTimeMillis()+".png");

        try {
            FileOutputStream saveImgOut = new FileOutputStream(saveFile);
            // compress - 压缩的意思
            bm.compress(Bitmap.CompressFormat.JPEG, 80, saveImgOut);
            //存储完成后需要清除相关的进程
            saveImgOut.flush();
            saveImgOut.close();
            Log.d("Save Bitmap", "The picture is save to your phone!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return saveFile.getAbsolutePath();
    }


}
