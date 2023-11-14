package com.example.ph35992_crud_product;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph35992_crud_product.Adapter.CatAdapter;
import com.example.ph35992_crud_product.DAO.CatDAO;
import com.example.ph35992_crud_product.DTO.CatDTO;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<CatDTO> listCat;
    CatDAO dao;
    RecyclerView rc_cat;
    CatAdapter catAdapter;
    Button btn_sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        dao = new CatDAO(this);
//        listCat = dao.getList();
//
//        rc_cat = findViewById(R.id.rc_cat);
//
//        catAdapter = new CatAdapter(this, listCat);
//
//        rc_cat.setAdapter( catAdapter );
        Button btn_sp = findViewById(R.id.btn_sp);
        btn_sp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProductActivity.class));
            }
        });

    }
}