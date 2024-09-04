package com.example.appproduct;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLClientInfoException;

public class classDB extends SQLiteOpenHelper {

    String tblProduct = "Create Table product(reference text, description text, price integer, typeRef integer)";

    public classDB( Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //crear la tabla producto a través de la variable tblProduct
        db.execSQL(tblProduct);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("Drop table product");
        db.execSQL(tblProduct);
    }
}
