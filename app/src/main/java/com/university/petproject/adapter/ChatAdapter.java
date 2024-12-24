package com.university.petproject.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.university.petproject.R;
import com.university.petproject.bean.ChatsBean;

import java.util.ArrayList;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.FoodViewHolder>{
    ArrayList<ChatsBean> list ;

    public void setList(ArrayList<ChatsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new FoodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  FoodViewHolder holder, int position) {
        if (list!=null){
            ChatsBean chatsBean = list.get(position);
            holder.username.setText(chatsBean.getUsername());
            holder.name.setText(chatsBean.getName());
            holder.des.setText(chatsBean.getDes());
            Bitmap bitmap = BitmapFactory.decodeFile(chatsBean.getImg());
            if (bitmap==null){
                holder.image.setImageResource(Integer.parseInt(chatsBean.getImg()));
            }else {
                holder.image.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{



        private TextView name,username;
        private TextView des;
        private ImageView image;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            name = itemView.findViewById(R.id.name);
            des = itemView.findViewById(R.id.des);
            image = itemView.findViewById(R.id.image);

        }
    }
}
