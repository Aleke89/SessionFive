package com.example.sessionfive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder>{
    private final ItemClickInterface itemClickInterface;
    Context context;
    ArrayList<SQLiteService> servicesArrayList;

    public CartAdapter(ItemClickInterface itemClickInterface, Context context, ArrayList<SQLiteService> servicesArrayList) {
        this.itemClickInterface = itemClickInterface;
        this.context = context;
        this.servicesArrayList = servicesArrayList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.detail_list_item,parent,false),itemClickInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.detail_img_delete.setImageResource(R.drawable.baseline_delete_24);
        holder.detail_img_logo.setImageBitmap(servicesArrayList.get(position).getImage());
        holder.txt_detail_title.setText(servicesArrayList.get(position).getTitle());
        holder.txt_detail_date.setText("Date "+ servicesArrayList.get(position).getDate_from() + " " + servicesArrayList.get(position).getDate_to());
        holder.txt_detail_number.setText("Number of people "+servicesArrayList.get(position).getNumber_of_people());

    }

    @Override
    public int getItemCount() {
        return servicesArrayList.size();
    }
}
