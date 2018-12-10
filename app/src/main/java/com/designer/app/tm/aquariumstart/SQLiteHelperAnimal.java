package com.designer.app.tm.aquariumstart;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


public class SQLiteHelperAnimal extends SQLiteOpenHelper{

    public SQLiteHelperAnimal(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(String name_animal, String biology_name, String quantity, String total_price, byte[] image_animal){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Animal VALUES (NULL, ?, ?, ?, ?, ?)";

        SQLiteStatement statment = database.compileStatement(sql);
        statment.clearBindings();

        statment.bindString(1, name_animal);
        statment.bindString(2, biology_name);
        statment.bindString(3, quantity);
        statment.bindString(4, total_price);
        statment.bindBlob(5, image_animal);

        statment.executeInsert();
    }

    public void updateData(String name, String biology_name, String quantity, String total_price, byte[] image,int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE Animal SET name_animal = ?, biology_name = ?, quantity = ?, total_price = ?, imb_placeholder = ? WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);


        statement.bindString(1, name);
        statement.bindString(2, biology_name);
        statement.bindString(3, quantity);
        statement.bindString(4, total_price);
        statement.bindBlob(5, image);
        statement.bindDouble(6, (double)id);


        statement.execute();
        database.close();
    }

    public  void deleteData(int id) {
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM Animal WHERE id = ?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)id);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){

        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
