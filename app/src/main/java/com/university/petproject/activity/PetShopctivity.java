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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.university.petproject.R;
import com.university.petproject.bean.FoodSolutionBean;
import com.university.petproject.bean.PetShopBean;
import com.university.petproject.db.DBManage;
import com.university.petproject.utils.SpUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PetShopctivity extends AppCompatActivity {

    TextView mName,mDetail,mAddress;
    private ImageView mLoadImgShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petshop1);
        PetShopBean petShopBean = (PetShopBean) getIntent().getSerializableExtra("item");

        findViewById(R.id.finish).setOnClickListener(view -> finish());
        //找控件id
        mName = findViewById(R.id.title);
        mDetail = findViewById(R.id.detail);
        mAddress = findViewById(R.id.address);

        mLoadImgShow = findViewById(R.id.loadImgShow);


        mName.setText(petShopBean.getName());
        mDetail.setText(petShopBean.getDes());
        mAddress.setText(petShopBean.getAdress());

        Bitmap bitmap = BitmapFactory.decodeFile(petShopBean.getImg());
        if (bitmap==null){
            mLoadImgShow.setImageResource(Integer.parseInt(petShopBean.getImg()));
        }else {
            mLoadImgShow.setImageBitmap(bitmap);
        }


    }











}
