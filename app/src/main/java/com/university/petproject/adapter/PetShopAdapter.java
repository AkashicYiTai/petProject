package com.university.petproject.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.university.petproject.R;
import com.university.petproject.activity.FoodActivity;
import com.university.petproject.activity.PetShopActivity;
import com.university.petproject.activity.PetShopctivity;
import com.university.petproject.bean.PetShopBean;

import java.util.ArrayList;


public class PetShopAdapter extends RecyclerView.Adapter<PetShopAdapter.FoodViewHolder>{
    ArrayList<PetShopBean> list ;
    private boolean isadmin;

    public PetShopAdapter(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public void setList(ArrayList<PetShopBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new FoodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_petshop,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  FoodViewHolder holder, int position) {
        if (list!=null){
            PetShopBean petShopBean = list.get(position);
            holder.name.setText(petShopBean.getName());
            holder.des.setText(petShopBean.getDes());
            if (petShopBean.getAdress()!=null) {
                holder.address.setText(String.format("店铺地址：%s", petShopBean.getAdress()));
            }

            Bitmap bitmap = BitmapFactory.decodeFile(petShopBean.getImg());
            if (bitmap==null){
                holder.image.setImageResource(Integer.parseInt(petShopBean.getImg()));
            }else {
                holder.image.setImageBitmap(bitmap);
            }
            if (isadmin){
                holder.del.setVisibility(View.VISIBLE);
                holder.del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //删除
                        int id = petShopBean.getId();
                        if (del!=null){
                            del.delclick(String.valueOf(id));
                        }
                    }
                });
            }else {
                holder.del.setVisibility(View.GONE);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PetShopctivity.class);
                    intent.putExtra("item",petShopBean);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list==null){
            return 0;
        }
        return list.size();
    }

    private Del del;

    public void setDel(Del del) {
        this.del = del;
    }

    public interface Del{
        void delclick(String id);
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{



        private TextView name,address;
        private TextView des;
        private ImageView image;
        private Button del;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            address = itemView.findViewById(R.id.address);
            des = itemView.findViewById(R.id.des);
            image = itemView.findViewById(R.id.image);
            del = itemView.findViewById(R.id.del);

        }
    }
}
