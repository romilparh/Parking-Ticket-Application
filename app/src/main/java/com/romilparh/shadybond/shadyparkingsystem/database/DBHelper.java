package com.romilparh.shadybond.shadyparkingsystem.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.romilparh.shadybond.shadyparkingsystem.database.model.ParkingTicketDBModel;
import com.romilparh.shadybond.shadyparkingsystem.database.model.PaymentInfoDBModel;
import com.romilparh.shadybond.shadyparkingsystem.database.model.UserInfoDBModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadybond on 2018-04-17.
 * This is the Database Helper class for defining all operations related to database implementation
 */

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper sInstance;
    Context context;

    private static final String DATABASE_NAME = "shady_parking_system";
    private static final int DATABASE_VERSION = 1;
    private static final String USER_TABLE = "user_info";
    private static final String PAYMENT_TABLE = "payment_info";
    private static final String TICKET_TABLE = "ticket_info";

    private static final String FOREIGN_KEY_ENABLE = "PRAGMA foreign_keys = ON;";

    private static final String USER_TABLE_CREATE =
            "CREATE TABLE "+ USER_TABLE + " (email TEXT PRIMARY KEY, name TEXT NOT NULL, gender CHAR(1) NOT NULL, password TEXT NOT NULL, dob TEXT NOT NULL)";

    private static final String PAYMENT_TABLE_CREATE =
            "CREATE TABLE "+ PAYMENT_TABLE+" (card_type CHAR(1) NOT NULL, card_number TEXT NOT NULL, card_cvv TEXT NOT NULL, email TEXT NOT NULL, FOREIGN KEY(email) REFERENCES user_info(email))";

    private static final String TICKET_TABLE_CREATE =
            "CREATE TABLE "+TICKET_TABLE+" (email TEXT, price REAL NOT NULL, car_make INTEGER(4) NOT NULL, car_color TEXT NOT NULL, time_ticket TEXT NOT NULL, parking_lane TEXT NOT NULL, parking_number INTEGER NOT NULL, card_number TEXT, car_number TEXT NOT NULL, FOREIGN KEY(email) REFERENCES user_info(email), FOREIGN KEY(card_number) REFERENCES payment_info(card_number))";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context);
        }
        return sInstance;
    }

    public SQLiteDatabase getDatabase() {
        synchronized (sInstance) {
            return this.getWritableDatabase();
        }
    }

    public long insertIntoTicketDB(ParkingTicketDBModel model) {
        Log.d("insert", "before insert parking ticket");

        // 1.get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("email", model.geteMail());
        values.put("price", model.getPrice());
        values.put("car_make", model.getCar_make());
        values.put("car_color", model.getCar_color());
        values.put("time_ticket", model.getTime_ticket());
        values.put("parking_lane", model.getParking_lane());
        values.put("parking_number", model.getParking_number());
        values.put("card_number", model.getCard_number());
        values.put("car_number", model.getCar_number());

        long id = 0;
        // 3. insert values into table
        id = db.insert(TICKET_TABLE, null, values);

        // 4. Close db
        db.close();
        Log.i("insert into DB", "After insert parking ticket");
        return (id);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FOREIGN_KEY_ENABLE);
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(PAYMENT_TABLE_CREATE);
        db.execSQL(TICKET_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + PAYMENT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TICKET_TABLE);
        onCreate(db);
    }

    public long insertIntoUserDB(UserInfoDBModel user) {
        Log.d("insert", "before insert user info");

        // 1.get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("email", user.getEMail());
        values.put("name", user.getName());
        values.put("password", user.getPassword());
        values.put("dob", user.getDob());
        values.put("gender", String.valueOf(user.getGender()));


        long id = 0;
        // 3. insert values into table
        id = db.insert(USER_TABLE, null, values);

        // 4. Close db
        db.close();
        Log.i("insert into DB", "After insert user info");
        return (id);
    }

    public void updateUserPassword(String email,String password) {
        Log.d("update", "before update user password");

        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("Update "+USER_TABLE+" set password = '"+password+"' where email = '"+email+"'");

        db.close();
        Log.i("update", "After update user password");
    }

    public long insertIntoPaymentDB(PaymentInfoDBModel model) {
        Log.d("insert", "before insert payment info");

        // 1.get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put("card_type", model.getCard_Type());
        values.put("card_number", model.getCard_number());
        values.put("card_cvv", model.getCvv());
        values.put("email", model.geteMail());

        long id = 0;
        // 3. insert values into table
        id = db.insert(PAYMENT_TABLE, null, values);

        // 4. Close db
        db.close();
        Log.i("insert into DB", "After insert payment info");
        return (id);
    }

    public List<UserInfoDBModel> getDataFromUserInfo() {

        List<UserInfoDBModel> modelList = new ArrayList<UserInfoDBModel>();
        String query = "SELECT * FROM " + USER_TABLE+";";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(FOREIGN_KEY_ENABLE);
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                UserInfoDBModel model = new UserInfoDBModel();
                model.seteMail(cursor.getString(0));
                model.setName(cursor.getString(1));
                char[] gender_string = cursor.getString(2).toCharArray();
                model.setGender(gender_string[0]);
                model.setPassword(cursor.getString(3));
                model.setDob(cursor.getString(4));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        Log.d("Users Table Data", modelList.toString());
        return modelList;
    }

    public List<PaymentInfoDBModel> getDataFromPaymentInfo(String eMail) {
        List<PaymentInfoDBModel> modelList = new ArrayList<PaymentInfoDBModel>();
        String query = "SELECT * FROM " + PAYMENT_TABLE+ " where email = "+"'"+eMail+"';";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(FOREIGN_KEY_ENABLE);
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                PaymentInfoDBModel model = new PaymentInfoDBModel();
                char[] card_type = cursor.getString(0).toCharArray();
                model.setCard_type(card_type[0]);
                model.setCard_number(cursor.getString(1));
                model.setCvv(cursor.getString(2));
                model.seteMail(cursor.getString(3));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        Log.d("Payment Table Data", modelList.toString());
        return modelList;
    }

    public List<ParkingTicketDBModel> getDataFromParkingTicketDB(String eMail) {
        List<ParkingTicketDBModel> modelList = new ArrayList<ParkingTicketDBModel>();
        String query = "SELECT * FROM " + TICKET_TABLE+ " where email = "+"'"+eMail+"';";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(FOREIGN_KEY_ENABLE);
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                ParkingTicketDBModel model = new ParkingTicketDBModel();
                model.seteMail(cursor.getString(0));
                model.setPrice(cursor.getDouble(1));
                model.setCar_Make(cursor.getInt(2));
                model.setCar_color(cursor.getString(3));
                model.setTime_ticket(cursor.getString(4));
                model.setParking_lane(cursor.getString(5));
                model.setParking_number(cursor.getInt(6));
                model.setCard_Number(cursor.getString(7));
                model.setCar_number(cursor.getString(8));
                modelList.add(model);
            } while (cursor.moveToNext());
        }
        Log.d("Ticket Table Data", modelList.toString());
        return modelList;
    }
}
