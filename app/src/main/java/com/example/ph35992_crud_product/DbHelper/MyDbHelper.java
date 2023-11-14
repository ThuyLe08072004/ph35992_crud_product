package com.example.ph35992_crud_product.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
    static  String DB_NAME = "ql_banhang";
    static  int DB_VERSION = 1;


    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // câu lệnh tạo bảng viết ở đây
        // tạo bảng thê loại:
        String sql_cat = "CREATE TABLE tb_cat ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE NOT NULL );";

        sqLiteDatabase.execSQL( sql_cat ); // tạo xong bảng tb_cat

        // tạo bảng sản phẩm
        String sql_product = "CREATE TABLE tb_product ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, price INTEGER DEFAULT (0) NOT NULL,id_cat INTEGER NOT NULL CONSTRAINT fk_pro_cat REFERENCES tb_cat (id) );";

        sqLiteDatabase.execSQL( sql_product );

        //======== Thêm dòng dữ liệu ======
        String insert_cat = "INSERT INTO tb_cat (name) VALUES ('Tivi'), ('Tủ lạnh'), ('Điều hòa') ";
        sqLiteDatabase.execSQL( insert_cat );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // cập nhật
        if(i<i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  tb_product");
            // tb_product là bảng bên nhiều phải xóa trước
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS  tb_cat");

        }
    }
}
