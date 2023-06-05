package com.example.sessionfive;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "database";
    private static final int DATABASE_VERSION = 4;

    public static final String TABLE_NAME = "my_table";
    public static final String COLUMN_SERVICE_NAME = "service_name";
    public static final String COLUMN_SIMPLE_DESCRIPTION = "simple_description";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_DATE_FROM = "date_from";
    public static final String COLUMN_DATE_TO = "date_to";
    public static final String COLUMN_SPOTS = "spots";
    public static final String COLUMN_NUMBER_OF_PEOPLE = "number_of_people";
    public static final String COLUMN_COUPON_PERCENTAGE = "coupon_percentage";
    public static final String COLUMN_ADD_TO_CART = "add_to_cart";
    public static final String COLUMN_MONEY = "money";

    private Context context;


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_SERVICE_NAME + " TEXT, " +
                COLUMN_SIMPLE_DESCRIPTION + " TEXT, " +
                COLUMN_IMAGE + " BLOB, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DATE_FROM + " TEXT, " +
                COLUMN_DATE_TO + " TEXT, " +
                COLUMN_SPOTS + " TEXT, " +
                COLUMN_COUPON_PERCENTAGE + " TEXT, " +
                COLUMN_NUMBER_OF_PEOPLE + " TEXT, " +
                COLUMN_MONEY + " INT, " +
                COLUMN_ADD_TO_CART + " INT " +
                ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(int drawableId, String service_name,
                           String simple_description,
                           String title,
                           String description,
                           String date_from,
                           String date_to,
                           String spots,
                           String number_of_people,
                           String coupon_percentage,int add_to_cart,int money) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), drawableId);
        byte[] imageData = getByteArrayFromBitmap(bitmap);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SERVICE_NAME, service_name);
        contentValues.put(COLUMN_SIMPLE_DESCRIPTION, simple_description);
        contentValues.put(COLUMN_IMAGE, imageData);
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_DESCRIPTION, description);
        contentValues.put(COLUMN_DATE_FROM, date_from);
        contentValues.put(COLUMN_DATE_TO, date_to);
        contentValues.put(COLUMN_SPOTS, spots);
        contentValues.put(COLUMN_NUMBER_OF_PEOPLE, number_of_people);
        contentValues.put(COLUMN_COUPON_PERCENTAGE, coupon_percentage);
        contentValues.put(COLUMN_ADD_TO_CART, add_to_cart);
        contentValues.put(COLUMN_MONEY, money);
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public byte[] getByteArrayFromBitmap(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void updateProperty(SQLiteService sqLiteService) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_SERVICE_NAME, sqLiteService.getService_name());
        contentValues.put(COLUMN_SIMPLE_DESCRIPTION, sqLiteService.getSimple_description());
        contentValues.put(COLUMN_IMAGE, getByteArrayFromBitmap(sqLiteService.getImage()));
        contentValues.put(COLUMN_TITLE, sqLiteService.getTitle());
        contentValues.put(COLUMN_DESCRIPTION, sqLiteService.getDescription());
        contentValues.put(COLUMN_DATE_FROM, sqLiteService.getDate_from());
        contentValues.put(COLUMN_DATE_TO, sqLiteService.getDate_to());
        contentValues.put(COLUMN_SPOTS, sqLiteService.getSpots());
        contentValues.put(COLUMN_COUPON_PERCENTAGE, sqLiteService.getCoupon_percentage());
        contentValues.put(COLUMN_MONEY, sqLiteService.getMoney());
        contentValues.put(COLUMN_ADD_TO_CART, sqLiteService.getAdd_to_Cart());
        db.update(TABLE_NAME, contentValues, COLUMN_SERVICE_NAME + " = ?", new String[]{sqLiteService.getService_name()});
        db.close();
    }
}
