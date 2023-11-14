package com.example.ph35992_crud_product.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ph35992_crud_product.DTO.CatDTO;
import com.example.ph35992_crud_product.R;


import java.util.ArrayList;


public class CatAdapter extends BaseAdapter {
    ArrayList<CatDTO> listCat;
    Context context;

    public CatAdapter(ArrayList<CatDTO> listCat, Context context) {
        this.listCat = listCat;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listCat.size();
    }

    @Override
    public Object getItem(int i) {
        return listCat.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listCat.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row;
        if(view != null)
            row = view;
        else {
            row = View.inflate(context, R.layout.layout_row_cat, null);
        }

        CatDTO objCat = listCat.get(i);
        TextView tv_cat = row.findViewById(R.id.tv_catname);

        tv_cat.setText( objCat.getName()  );

        return row;
    }

}
