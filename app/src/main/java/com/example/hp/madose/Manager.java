package com.example.hp.madose;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



/**
 * Created by HP on 05/12/2017.
 */

public class Manager extends BaseDeDonne {
    BaseDeDonne bb;
    SQLiteDatabase db;

    public Manager(Context context) {
        super(context);
    }


    public void open()
    {
        db=bb.getWritableDatabase();
    }

    public void close()
    {
        db.close();
    }

    // session Departement
    public long insertDepartement(DepartementC dep)
    {

        ContentValues vals=new ContentValues();
        vals.put("idDep", dep.getIdDep());
        vals.put("LibDep",dep.getLibDep());

        Log.i("DATABSE","insert invoked");

        return db.insert("DepartementC",null,vals);


    }

    public void insert(String nom)
    {

        String entre="insert into Departement ( nom )values("+nom+");";
        bb.getWritableDatabase().execSQL(entre);


    }




}
