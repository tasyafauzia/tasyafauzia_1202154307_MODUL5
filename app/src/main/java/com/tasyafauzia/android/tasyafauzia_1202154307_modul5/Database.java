package com.tasyafauzia.android.tasyafauzia_1202154307_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
/**
 * Created by Tasya Fauzia on 3/24/2018.
 */

public class Database extends SQLiteOpenHelper{
    Context cont;
    SQLiteDatabase db;

    public static final String name_db = "listtodo.db";
    public static final String name_tabel = "daftartodo";
    public static final String column_1 = "todo";
    public static final String column_2 = "description";
    public static final String column_3= "priority";


    public Database(Context context) {
        super(context, name_db, null, 1);
        this.cont = context;
        db = this.getWritableDatabase();
        db.execSQL("create table if not exists "+name_tabel+" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    //Pada saat database sedang dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+name_tabel+" (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+name_tabel);
        onCreate(sqLiteDatabase);
    }

    public boolean inputdata(Data list) {

        ContentValues val = new ContentValues();
        val.put(column_1, list.getTodo());
        val.put(column_2, list.getDesc());
        val.put(column_3, list.getPrior());
        long hasil = db.insert(name_tabel, null, val);
        if (hasil==-1) {
            return false;
        }else {
            return true;
        }
    }

    //Merupakan method yang digunakan untuk menghapus data yang ada pada database
    public boolean removedata(String ToDo) {
        return db.delete(name_tabel, column_1+"=\""+ToDo+"\"", null)>0;
    }

    //Merupakan method yang digunakan untuk membaca dan mengakses database
    public void readdata(ArrayList<Data> daftar){
        Cursor cursor = this.getReadableDatabase().rawQuery("select todo, description, priority from "+name_tabel, null);
        while (cursor.moveToNext()){
            daftar.add(new Data(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}
