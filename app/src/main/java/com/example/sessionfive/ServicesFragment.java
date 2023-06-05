package com.example.sessionfive;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sessionfive.R;

import java.util.ArrayList;

public class ServicesFragment extends Fragment implements ItemClickInterface{
    LinearLayout city,attraction,transfer,catering,safety;
    public ServicesFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_servicess,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        city = view.findViewById(R.id.item_city_layout);
        attraction = view.findViewById(R.id.item_attraction_layout);
        transfer = view.findViewById(R.id.item_transfer_layout);
        catering = view.findViewById(R.id.item_food_layout);
        safety = view.findViewById(R.id.item_safety_layout);
        city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title","City tours");
                bundle.putString("title_desc","City tours contain transfer, lunch, attraction tickets");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.service_main_container, fragment);
                fragmentTransaction.commit();
            }
        });
        attraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title","Attraction Tickets");
                bundle.putString("title_desc","Attraction Tickets");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.service_main_container, fragment);
                fragmentTransaction.commit();
            }
        });
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title","Transfer Services");
                bundle.putString("title_desc","Transfer Services gives a lot of ");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.service_main_container, fragment);
                fragmentTransaction.commit();
            }
        });
        catering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title","Catering Services");
                bundle.putString("title_desc","Best foods ever");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.service_main_container, fragment);
                fragmentTransaction.commit();
            }
        });
        safety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment fragment = new DetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title","Safety box");
                bundle.putString("title_desc","Safety box gives a lot of");
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.service_main_container, fragment);
                fragmentTransaction.commit();
            }
        });



    }

    @Override
    public void itemClick(int position) {


    }

    @Override
    public void itemClickDelete(int position) {

    }
}