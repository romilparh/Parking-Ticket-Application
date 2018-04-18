package com.romilparh.shadybond.shadyparkingsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shadybond on 2018-04-17.
 * This is the Database Helper class for defining all operations related to database implementation
 */

public class DBHelper extends SQLiteOpenHelper {



    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
