package com.example.sessionfive;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {

    private final ItemClickInterface itemClickInterface;
    Context context;
    ArrayList<SQLiteService> servicesArrayList;

    public ServiceAdapter(ItemClickInterface itemClickInterface, Context context, ArrayList<SQLiteService> servicesArrayList) {
        this.itemClickInterface = itemClickInterface;
        this.context = context;
        this.servicesArrayList = servicesArrayList;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ServiceViewHolder(LayoutInflater.from(context).inflate(R.layout.service_list_item,parent,false),itemClickInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceViewHolder holder, int position) {
        holder.img_logo.setImageBitmap(servicesArrayList.get(position).getImage());
        holder.txt_title.setText(servicesArrayList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return servicesArrayList.size();
    }
}
