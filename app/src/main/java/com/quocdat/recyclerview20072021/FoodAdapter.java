package com.quocdat.recyclerview20072021;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private final int TYPE_ITEM = 0;
    private final int TYPE_LOADING = 1;
    private boolean isLoading = false;

    List<Food> listFood;
    OnItemListener onItemListener;

    FoodAdapter(List<Food> listFood){
        this.listFood = listFood;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == listFood.size()-1 && isLoading){
            return TYPE_LOADING;
        }
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Khai bao lop doi tuong de convert kieu int sang kieu view

        if (TYPE_ITEM == viewType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_item, parent, false);
            return new FoodViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == TYPE_ITEM){
            ((FoodViewHolder)holder).onBindView(listFood.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    class FoodViewHolder extends RecyclerView.ViewHolder{

        ImageView imgFood, imgDelete;
        TextView tvName, tvPrice;


        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFood = itemView.findViewById(R.id.imgFood);
            tvName = itemView.findViewById(R.id.tvNameFood);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            imgDelete = itemView.findViewById(R.id.imgDelete);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemListener != null) {
                        onItemListener.onItemClickListener(getAdapterPosition());
                    }
                }
            });

        }

        public void onBindView(Food food){
            tvName.setText(food.getName());
            NumberFormat format = new DecimalFormat("#,###");
            tvPrice.setText("Giá: " + format.format(food.getPrice()));
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    public void setOnItemListener(OnItemListener onItemListener){
        this.onItemListener = onItemListener;
    }

    public void addFooterLoading(){
        isLoading = true;
        listFood.add(null);
    }

    public void removeLoading(){
        isLoading = false;
        int position = listFood.size()-1;
        listFood.remove(position);
        notifyItemRemoved(position);
    }
}
