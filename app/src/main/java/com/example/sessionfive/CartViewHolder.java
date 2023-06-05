package com.example.sessionfive;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartViewHolder extends RecyclerView.ViewHolder{

    ImageView detail_img_logo,detail_img_delete;
    TextView txt_detail_title,txt_detail_date,txt_detail_number;

    public CartViewHolder(@NonNull View itemView,ItemClickInterface itemClickInterface) {
        super(itemView);
        detail_img_delete = itemView.findViewById(R.id.detail_img_delete);
        detail_img_logo = itemView.findViewById(R.id.detail_img_logo);
        txt_detail_title = itemView.findViewById(R.id.txt_detail_title);
        txt_detail_date = itemView.findViewById(R.id.txt_detail_date);
        txt_detail_number = itemView.findViewById(R.id.txt_detail_number);
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
        detail_img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickInterface!=null){
                    int pos = getAdapterPosition();
                    if(pos!= RecyclerView.NO_POSITION){
                        itemClickInterface.itemClickDelete(pos);
                    }
                }
            }
        });

    }
}
