package com.example.androidconstructer.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    // Имя базы данных
    public static final String DATABASE_NAME = "my_db";
    // Версия базы данных
    public static final int DATABASE_VERSION = 1;

    // Имя таблицы
    public static final String TABLE_SETTING = "Setting";
    public static final String CATEGORY_BUTTON = "categoryButton";


    // Названия столбцов таблицы
    public static  String KEY_ID = "id";
    public static  String OneCategory = "OneCategory";
    public static  String TwoCategory = "TwoCategory";
    public static  String ThreeCategory = "ThreeCategory";
    public static  String KEY_LOGO_LOCATION = "logo_location";
    public static  String KEY_MAIN_ACTIVITY_BACKGROUND_COLOR = "main_activity_background_color";
    public static  String KEY_CATEGORY_BUTTON_COLOR = "category_button_color";
    public static  String KEY_PRODUCT_LIST_ITEM_BACKGROUND_COLOR = "product_list_item_background_color";
    public static  String KEY_APP_NAME = "app_name";
    public static  String KEY_USER_AGREEMENT = "user_agreement";
    public static  String KEY_PHONE_NUMBER = "phone_number";
    public static  String KEY_PRODUCT_CARD_BACKGROUND_COLOR = "product_card_background_color";
    public static  String KEY_PRODUCT_CARD_TEXT_COLOR = "product_card_text_color";
    public static  String KEY_APP_NAME_TEXT_COLOR = "app_name_text_color";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы
        String CREATE_SETTING_TABLE = "CREATE TABLE " + TABLE_SETTING + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_LOGO_LOCATION + " TEXT,"
                + KEY_MAIN_ACTIVITY_BACKGROUND_COLOR + " TEXT,"
                + KEY_CATEGORY_BUTTON_COLOR + " TEXT,"
                + KEY_PRODUCT_LIST_ITEM_BACKGROUND_COLOR + " TEXT,"
                + KEY_APP_NAME + " TEXT,"
                + KEY_USER_AGREEMENT + " TEXT,"
                + KEY_PHONE_NUMBER + " TEXT,"
                + KEY_PRODUCT_CARD_BACKGROUND_COLOR + " TEXT,"
                + KEY_PRODUCT_CARD_TEXT_COLOR + " TEXT,"
                + KEY_APP_NAME_TEXT_COLOR + " TEXT,"
                + "bacground_on_item " + " TEXT,"
                + "text_name_color_on_item " +"TEXT,"
                + "text_seil_color_on_item " + " TEXT,"
                + "text_info_color_on_item " + " TEXT,"
                + "text_seil_colorBack_on_item " + " TEXT,"
                + "text_name_colorBack_on_item " + " TEXT,"
                + "text_info_colorBack_on_item " + " TEXT,"
                + "text_name_size_on_item " + " TEXT,"
                + "text_info_size_on_item " + " TEXT,"
                + "text_seil_size_on_item " + " TEXT,"
                + "background_down_on_ite " + " TEXT,"
                + "adapter_name_size " + " TEXT,"
                + "adapter_seil_size " + " TEXT,"
                + "adapter_price_size " + " TEXT,"
                + "adapter_info_size "  + " TEXT,"
                + "adapter_name_color " + " TEXT,"
                + "adapter_backFullScreen "+ " TEXT,"
                + "adapter_info_color " + " TEXT,"
                + "adapter_price_color " + " TEXT,"
                + "adapter_seil_color " + " TEXT,"
                + "adapter_background_color " + " TEXT"
                + ")";
        db.execSQL(CREATE_SETTING_TABLE);

        String favorite = "CREATE TABLE " + "favorite" + "("
                + "name " + " TEXT,"
                + "price " + " TEXT,"
                + "seil " + "TEXT,"
                + "full_info " + "TEXT,"
                + "litle_info " + "TEXT,"
                + "image1 " + "TEXT,"
                + "image2 " + "TEXT,"
                + "image3 " + "TEXT,"
                + "image4 " + "TEXT,"
                + "image5 " + "TEXT,"
                + "image6 " + "TEXT,"
                + "image7 " + "TEXT,"
                + "image8 " + "TEXT,"
                + "image9 " + "TEXT,"
                + "image10 " + "TEX,"
                + "raiting "+"Int"
                +")";
        db.execSQL(favorite);

        String categoryBatton = "CREATE TABLE " + CATEGORY_BUTTON + "("
                + OneCategory + " TEXT,"
                + TwoCategory + " TEXT,"
                + ThreeCategory + " TEXT,"
                + "FourCategory" + " TEXT,"
                + "FiveCategory" + " TEXT"
                +")";
        db.execSQL(categoryBatton);

        String Item = "CREATE TABLE " + "Item" + "("
                + "id " + " INTEGER PRIMARY KEY,"
                + "name " + " TEXT,"
                + "price " + " TEXT,"
                + "seil " + "TEXT,"
                + "full_info " + "TEXT,"
                + "litle_info " + "TEXT,"
                + "image1 " + "TEXT,"
                + "image2 " + "TEXT,"
                + "image3 " + "TEXT,"
                + "image4 " + "TEXT,"
                + "image5 " + "TEXT,"
                + "image6 " + "TEXT,"
                + "image7 " + "TEXT,"
                + "image8 " + "TEXT,"
                + "image9 " + "TEXT,"
                + "image10 " + "TEX,"
                + "raiting "+"Int"
                +")";
        db.execSQL(Item);

        String Category1 = "CREATE TABLE " + "Category1" + "("
                + "id " + " INTEGER PRIMARY KEY,"
                + "name " + " TEXT,"
                + "price " + " TEXT,"
                + "seil " + "TEXT,"
                + "full_info " + "TEXT,"
                + "litle_info " + "TEXT,"
                + "image1 " + "TEXT,"
                + "image2 " + "TEXT,"
                + "image3 " + "TEXT,"
                + "image4 " + "TEXT,"
                + "image5 " + "TEXT,"
                + "image6 " + "TEXT,"
                + "image7 " + "TEXT,"
                + "image8 " + "TEXT,"
                + "image9 " + "TEXT,"
                + "image10 " + "TEX,"
                + "raiting "+"Int"
                +")";
        db.execSQL(Category1);
        String Category2 = "CREATE TABLE " + "Category2" + "("
                + "id " + " INTEGER PRIMARY KEY,"
                + "name " + " TEXT,"
                + "price " + " TEXT,"
                + "seil " + "TEXT,"
                + "full_info " + "TEXT,"
                + "litle_info " + "TEXT,"
                + "image1 " + "TEXT,"
                + "image2 " + "TEXT,"
                + "image3 " + "TEXT,"
                + "image4 " + "TEXT,"
                + "image5 " + "TEXT,"
                + "image6 " + "TEXT,"
                + "image7 " + "TEXT,"
                + "image8 " + "TEXT,"
                + "image9 " + "TEXT,"
                + "image10 " + "TEX,"
                + "raiting "+"Int"
                +")";
        db.execSQL(Category2);

        String Category3 = "CREATE TABLE " + "Category3" + "("
                + "id " + " INTEGER PRIMARY KEY,"
                + "name " + " TEXT,"
                + "price " + " TEXT,"
                + "seil " + "TEXT,"
                + "full_info " + "TEXT,"
                + "litle_info " + "TEXT,"
                + "image1 " + "TEXT,"
                + "image2 " + "TEXT,"
                + "image3 " + "TEXT,"
                + "image4 " + "TEXT,"
                + "image5 " + "TEXT,"
                + "image6 " + "TEXT,"
                + "image7 " + "TEXT,"
                + "image8 " + "TEXT,"
                + "image9 " + "TEXT,"
                + "image10 " + "TEX,"
                + "raiting "+"Int"
                +")";
        db.execSQL(Category3);

        String Category4 = "CREATE TABLE " + "Category4" + "("
                + "id " + " INTEGER PRIMARY KEY,"
                + "name " + " TEXT,"
                + "price " + " TEXT,"
                + "seil " + "TEXT,"
                + "full_info " + "TEXT,"
                + "litle_info " + "TEXT,"
                + "image1 " + "TEXT,"
                + "image2 " + "TEXT,"
                + "image3 " + "TEXT,"
                + "image4 " + "TEXT,"
                + "image5 " + "TEXT,"
                + "image6 " + "TEXT,"
                + "image7 " + "TEXT,"
                + "image8 " + "TEXT,"
                + "image9 " + "TEXT,"
                + "image10 " + "TEX,"
                + "raiting "+"Int"
                +")";
        db.execSQL(Category4);

        String Category5 = "CREATE TABLE " + "Category5" + "("
                + "id " + " INTEGER PRIMARY KEY,"
                + "name " + " TEXT,"
                + "price " + " TEXT,"
                + "seil " + "TEXT,"
                + "full_info " + "TEXT,"
                + "litle_info " + "TEXT,"
                + "image1 " + "TEXT,"
                + "image2 " + "TEXT,"
                + "image3 " + "TEXT,"
                + "image4 " + "TEXT,"
                + "image5 " + "TEXT,"
                + "image6 " + "TEXT,"
                + "image7 " + "TEXT,"
                + "image8 " + "TEXT,"
                + "image9 " + "TEXT,"
                + "image10 " + "TEX,"
                + "raiting "+"Int"
                +")";
        db.execSQL(Category5);



        // Добавление данных в таблицу
        ContentValues values = new ContentValues();
        values.put(KEY_ID, 1);
        values.put(KEY_LOGO_LOCATION, "logo");
        values.put(KEY_MAIN_ACTIVITY_BACKGROUND_COLOR, "#FFFFFF");
        values.put(KEY_CATEGORY_BUTTON_COLOR, "#000000");
        values.put(KEY_PRODUCT_LIST_ITEM_BACKGROUND_COLOR, "#FFFFFF");
        values.put(KEY_APP_NAME, "My App");
        values.put(KEY_USER_AGREEMENT, "Agreement text");
        values.put(KEY_PHONE_NUMBER, "1234567890");
        values.put(KEY_PRODUCT_CARD_BACKGROUND_COLOR, "#FFFFFF");
        values.put(KEY_PRODUCT_CARD_TEXT_COLOR, "#000000");
        values.put(KEY_APP_NAME_TEXT_COLOR, "#000000");
        values.put("bacground_on_item", "#000000");
        values.put("text_name_color_on_item", "#FFFFF");
        values.put("text_seil_color_on_item", "#000000");
        values.put("text_info_color_on_item", "#000000");
        values.put("text_seil_colorBack_on_item", "#000000");
        values.put("text_name_colorBack_on_item", "#000000");
        values.put("text_info_colorBack_on_item", "#000000");
        values.put("text_name_size_on_item", "#000000");
        values.put("text_info_size_on_item", "#000000");
        values.put("text_seil_size_on_item", "#000000");
        values.put("background_down_on_ite", "#000000");
        values.put("adapter_name_size", "10");
        values.put("adapter_seil_size", "10");
        values.put("adapter_price_size", "10");
        values.put("adapter_info_size", "10");
        values.put("adapter_name_color", "#000000");
        values.put("adapter_info_color", "#000000");
        values.put("adapter_seil_color", "#000000");
        values.put("adapter_price_color", "#000000");
        values.put("adapter_background_color", "#000000");
        values.put("adapter_backFullScreen", "#000000");
        db.insert(TABLE_SETTING, null, values);

        ContentValues values2 = new ContentValues();
        values2.put(OneCategory, "1");
        values2.put(TwoCategory, "2");
        values2.put(ThreeCategory, "3");
        db.insert(CATEGORY_BUTTON, null, values2);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Удаление старой таблицы, если она существует
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETTING);
        // Создание новой таблицы
        onCreate(db);
    }
    public static DBHelper instance;

    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    public Cursor getDataFromSettingTable() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {KEY_LOGO_LOCATION, KEY_MAIN_ACTIVITY_BACKGROUND_COLOR, KEY_CATEGORY_BUTTON_COLOR,
                KEY_PRODUCT_LIST_ITEM_BACKGROUND_COLOR, KEY_APP_NAME, KEY_USER_AGREEMENT, KEY_PHONE_NUMBER,
                KEY_PRODUCT_CARD_BACKGROUND_COLOR, KEY_PRODUCT_CARD_TEXT_COLOR, KEY_APP_NAME_TEXT_COLOR,
                "background_down_on_ite", "adapter_name_size",
                "adapter_seil_size", "adapter_price_size",
                "adapter_info_size", "adapter_name_color","adapter_info_color",
                "adapter_price_color","adapter_seil_color","adapter_background_color","adapter_backFullScreen"};
        Cursor cursor = db.query(TABLE_SETTING, columns, null, null, null, null, null);
        return cursor;
    }

    public Cursor getDataFromButtonCategoryTable() {
        SQLiteDatabase db5 = this.getReadableDatabase();
        String[] columns = {OneCategory,TwoCategory,ThreeCategory,"FourCategory","FiveCategory"};
        Cursor cursor4 = db5.query(CATEGORY_BUTTON, columns, null, null, null, null, null);
        return cursor4;
    }

}

