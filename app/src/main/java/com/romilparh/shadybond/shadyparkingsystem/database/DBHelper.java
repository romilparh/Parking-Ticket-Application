package com.romilparh.shadybond.shadyparkingsystem.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shadybond on 2018-04-17.
 * This is the Database Helper class for defining all operations related to database implementation
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "shady_parking_system";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE = "user_info";
    private static final String PAYMENT_TABLE = "payment_info";
    private static final String TICKET_TABLE = "ticket_info";

    private static final String FOREIGN_KEY_ENABLE = "";

    private static final String USER_TABLE_CREATE =
            "CREATE TABLE "+ USER_TABLE + " (email TEXT PRIMARY KEY, gender CHAR(1) NOT NULL, password TEXT NOT NULL, dob TEXT NOT NULL)";

    private static final String PAYMENT_TABLE_CREATE =
            "CREATE TABLE "+ PAYMENT_TABLE+" (card_type CHAR(1) NOT NULL, card_number TEXT NOT NULL, card_cvv TEXT NOT NULL, email TEXT NOT NULL, FOREIGN KEY(email) REFERENCES user_info(email))";

    private static final String TICKET_TABLE_CREATE =
            "CREATE TABLE "+TICKET_TABLE+" (ticket_id INTEGER AUTOINCREMENT PRIMARY KEY, email TEXT, price REAL NOT NULL, car_make INTEGER(4) NOT NULL, car_color TEXT NOT NULL, time_ticket TEXT NOT NULL, parking_lane TEXT NOT NULL, parking_number INTEGER NOT NULL, card_mumber TEXT, FOREIGN KEY(email) REFERENCES user_info(email), FOREIGN KEY(card_number) REFERENCES payment_info(card_number))";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(PAYMENT_TABLE_CREATE);
        db.execSQL(TICKET_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
