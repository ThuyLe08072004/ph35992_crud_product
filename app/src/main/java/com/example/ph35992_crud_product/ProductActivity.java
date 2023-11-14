package com.example.ph35992_crud_product;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ph35992_crud_product.Adapter.CatAdapter;
import com.example.ph35992_crud_product.Adapter.ProductAdapter;
import com.example.ph35992_crud_product.DAO.CatDAO;
import com.example.ph35992_crud_product.DAO.ProductDAO;
import com.example.ph35992_crud_product.DTO.CatDTO;
import com.example.ph35992_crud_product.DTO.ProductDTO;



import java.util.ArrayList;

public class ProductActivity extends AppCompatActivity {
    RecyclerView rc_product;
    EditText ed_name, ed_price;
    Spinner sp_cat;
    Button btnAdd;

    CatAdapter catAdapter;
    ProductAdapter productAdapter;
    ArrayList<CatDTO> listCat;
    ArrayList<ProductDTO> listPro;
    CatDAO catDAO;
    ProductDAO productDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        rc_product = findViewById(R.id.rc_product);
        ed_name = findViewById(R.id.ed_name);
        ed_price = findViewById(R.id.ed_price);
        sp_cat = findViewById(R.id.sp_cat);
        btnAdd = findViewById(R.id.btn_add);

        catDAO = new CatDAO(this);
        productDAO = new ProductDAO(this);
        listCat = catDAO.getList();
        listPro = productDAO.getList();

        // tạo adapter
        catAdapter = new CatAdapter(listCat, this);
        productAdapter = new ProductAdapter(listPro, this);

        // đổ lên spinner và list
        sp_cat.setAdapter( catAdapter);
        rc_product.setAdapter( productAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed_name.getText().toString();
                int price = Integer.parseInt(ed_price.getText().toString());
                int id_cat = (int) sp_cat.getSelectedItemId(); // lấy ID của cat được chọn trong spinner.

                ProductDTO objPro = new ProductDTO(name, price, id_cat);
                int id = productDAO.addRow(objPro);

                if(id>0){
                    // thêm mới thành công
                    listPro.clear();
                    listPro.addAll( productDAO.getList());
                    productAdapter.notifyDataSetChanged(); // cập nhật lại dữ liệu lên giao diện

                    // Tạo dialog thông báo

                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);

                    builder.setMessage("Thêm thành công");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();;
                        }
                    });
                    builder.create().show();


                }else{
                    // tạo dialog
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductActivity.this);

                    builder.setMessage("Lỗi thêm");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();;
                        }
                    });
                    builder.create().show();


                }

            }
        });


    }
}
