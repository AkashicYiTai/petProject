package com.university.petproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.university.petproject.R;
import com.university.petproject.adapter.FoodSolutionAdapter;
import com.university.petproject.bean.FoodSolutionBean;
import com.university.petproject.db.DBManage;
import com.university.petproject.utils.SpUtil;

import java.util.ArrayList;

public class FoodSolutionActivity extends AppCompatActivity {
    private DBManage dbManage;
    private FoodSolutionAdapter foodAdapter;
    private boolean isadmin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsolution);
        dbManage = DBManage.getInstance(this);
        initView();
    }
    private void initView() {
        findViewById(R.id.finish).setOnClickListener(view -> finish());

        String userName = SpUtil.getInstance().getString("userName", "");


        View viewById = findViewById(R.id.add);
        if (userName.equals("admin")){
            viewById.setVisibility(View.VISIBLE);
            isadmin=true;
        }else {
            viewById.setVisibility(View.INVISIBLE);
            isadmin=false;
        }
        viewById
                .setOnClickListener(view ->
                startActivity(new Intent(FoodSolutionActivity.this, AddFoodActivity.class)));
        findViewById(R.id.search).setOnClickListener(view ->
                startActivity(new Intent(FoodSolutionActivity.this, FoodSearchActivity.class)));




        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        foodAdapter = new FoodSolutionAdapter(isadmin);
        mRecyclerView.setAdapter(foodAdapter);


        foodAdapter.setDel(new FoodSolutionAdapter.Del() {
            @Override
            public void delclick(String id) {
                dbManage.delFood(id);
                ArrayList<FoodSolutionBean> foodBeans = dbManage.selectAllFoods();

                foodAdapter.setList(foodBeans);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<FoodSolutionBean> foodBeans = dbManage.selectAllFoods();

        foodAdapter.setList(foodBeans);
    }
}
