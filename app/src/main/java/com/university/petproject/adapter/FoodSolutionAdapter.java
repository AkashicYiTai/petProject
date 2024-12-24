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
import com.university.petproject.bean.CookbookBean;
import com.university.petproject.bean.FoodSolutionBean;

import java.util.ArrayList;


public class FoodSolutionAdapter extends RecyclerView.Adapter<FoodSolutionAdapter.FoodViewHolder>{
    ArrayList<FoodSolutionBean> list ;
    private boolean isadmin;

    public FoodSolutionAdapter(boolean isadmin) {
        this.isadmin=isadmin;
    }

    public void setList(ArrayList<FoodSolutionBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new FoodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pet,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  FoodViewHolder holder, int position) {
        if (list!=null){
            FoodSolutionBean foodSolutionBean = list.get(position);
            holder.name.setText(foodSolutionBean.getName());
            holder.des.setText(foodSolutionBean.getDes());

            Bitmap bitmap = BitmapFactory.decodeFile(foodSolutionBean.getImg());
            if (bitmap==null){
                holder.image.setImageResource(Integer.parseInt(foodSolutionBean.getImg()));
            }else {
                holder.image.setImageBitmap(bitmap);
            }

            if (isadmin){
                holder.del.setVisibility(View.VISIBLE);
                holder.del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //删除
                        int id = foodSolutionBean.getId();
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
                    Intent intent = new Intent(v.getContext(), FoodActivity.class);
                    intent.putExtra("item",foodSolutionBean);
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



        private TextView name;
        private TextView des;
        private ImageView image;
        private Button del;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            des = itemView.findViewById(R.id.des);
            image = itemView.findViewById(R.id.image);
            del = itemView.findViewById(R.id.del);

        }
    }
}
