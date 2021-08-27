package com.quocdat.recyclerview20072021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder>{

    List<Food> listFood;

    FoodAdapter(List<Food> listFood){
        this.listFood = listFood;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Khai bao lop doi tuong de convert kieu int sang kieu view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        holder.onBindView(listFood.get(position));
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        ImageView imgFood;
        TextView tvName, tvPrice;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFood = itemView.findViewById(R.id.imgFood);
            tvName = itemView.findViewById(R.id.tvNameFood);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }

        public void onBindView(Food food){
            imgFood.setImageResource(food.getImage());
            tvName.setText(food.getName());
            NumberFormat format = new DecimalFormat("#,###");
            tvPrice.setText("Giá: " + format.format(food.getPrice()));
        }
    }
}
