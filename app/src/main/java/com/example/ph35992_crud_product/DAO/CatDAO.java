package com.example.ph35992_crud_product.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ph35992_crud_product.DTO.CatDTO;
import com.example.ph35992_crud_product.DbHelper.MyDbHelper;


import java.util.ArrayList;

public class CatDAO {
    MyDbHelper dbHelper;
    SQLiteDatabase db;

    public CatDAO(Context ct){
        dbHelper = new MyDbHelper( ct ) ;
        db = dbHelper.getWritableDatabase();
    }

    // hàm lấy danh sách thê loại
    public ArrayList<CatDTO> getList(){

        ArrayList<CatDTO> listCat = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM tb_cat ORDER BY name ASC ", null);
        if(c.getCount()>0){
            c.moveToFirst(); // đưa trỏ về đầu dòng
            do {
                CatDTO objCat = new CatDTO();
                objCat.setId(     c.getInt(0)     );  // cách khác:  c.getInt( c.getColumnIndex("id"))
                objCat.setName(   c.getString(1)  );

                // cho đối tượng vào danh sách
                listCat.add( objCat );

            }while (c.moveToNext());
        }else{
            Log.d("zzzzzzzzzzzz", "getList: Không có dữ liệu");
        }
        return  listCat;
    }
    // hàm thêm mới
    public int addRow(CatDTO objCat){
        ContentValues values = new ContentValues();
        values.put("name", objCat.getName() );
        long kq = db.insert("tb_cat",null, values );
        return  (int) kq; // nêu kq > 0 thì là thêm thành công và id là kq.
        // yêu cầu id của bảng phải là autoincrement
    }





}
