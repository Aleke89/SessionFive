package com.example.sessionfive;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ServiceViewHolder extends RecyclerView.ViewHolder {

    ImageView img_logo;
    TextView txt_title;

    public ServiceViewHolder(@NonNull View itemView, ItemClickInterface itemClickInterface) {
        super(itemView);
        img_logo = itemView.findViewById(R.id.img_service);
        txt_title = itemView.findViewById(R.id.service_text_title);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickInterface!=null){
                    int pos = getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        itemClickInterface.itemClick(pos);
                    }
                }
            }
        });
    }
}
