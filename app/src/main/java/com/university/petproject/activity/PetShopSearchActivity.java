package com.university.petproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.university.petproject.R;
import com.university.petproject.adapter.PetShopAdapter;
import com.university.petproject.bean.FoodSolutionBean;
import com.university.petproject.bean.PetShopBean;
import com.university.petproject.db.DBManage;

import java.util.ArrayList;

public class PetShopSearchActivity extends AppCompatActivity {
    private DBManage dbManage;

    private PetShopAdapter petShopAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petshopsearch);
        dbManage = DBManage.getInstance(this);
        initView();
    }
    private void initView() {
        findViewById(R.id.finish).setOnClickListener(view -> finish());




        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        petShopAdapter = new PetShopAdapter(false);
        mRecyclerView.setAdapter(petShopAdapter);


        ((EditText) findViewById(R.id.search)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String key = charSequence.toString();
                if (!TextUtils.isEmpty(key)) {
                    ArrayList<PetShopBean> petShopBeans = dbManage.selectPetShopByName(key);
                    petShopAdapter.setList(petShopBeans);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
