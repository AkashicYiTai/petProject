package com.university.petproject.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.university.petproject.R;
import com.university.petproject.adapter.ChatAdapter;
import com.university.petproject.adapter.FoodSolutionAdapter;
import com.university.petproject.bean.ChatsBean;
import com.university.petproject.bean.FoodSolutionBean;
import com.university.petproject.db.DBManage;

import java.util.ArrayList;

public class PetChatActivity extends AppCompatActivity {
    private DBManage dbManage;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        dbManage = DBManage.getInstance(this);
        initView();
    }
    private void initView() {
        findViewById(R.id.finish).setOnClickListener(view -> finish());

        findViewById(R.id.add).setOnClickListener(view ->
                startActivity(new Intent(PetChatActivity.this, AddPetChatctivity.class)));




        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter = new ChatAdapter();
        mRecyclerView.setAdapter(chatAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<ChatsBean> chatsBeans = dbManage.selectAllChats();

        chatAdapter.setList(chatsBeans);
    }
}
