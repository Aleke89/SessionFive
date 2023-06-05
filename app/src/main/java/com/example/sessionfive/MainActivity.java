package com.example.sessionfive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sessionfive.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.edtUsername.getText().toString().equals("admin") && binding.edtPassword.getText().toString().equals("admin")){
                    Intent intent = new Intent(MainActivity.this, ServiceActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}