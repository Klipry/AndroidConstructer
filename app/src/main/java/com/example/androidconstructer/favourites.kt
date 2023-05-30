package com.example.androidconstructer

import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidconstructer.Data.DBHelper
import com.example.androidconstructer.Data.ImageLoader

class favourites : AppCompatActivity() {
    var namingsize:String?=null
    var seillsize:String?=null
    var pricesize:String?=null
    var namecolor:String?=null
    var infocolor:String?=null
    var ingosize:String?=null
    var seilcolor:String?=null
    var adapterback:String?=null
    var pricecolor:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)
        val imagecall=findViewById<ImageView>(R.id.imageView4)
        val imageHoll=findViewById<ImageView>(R.id.imageView2)
        imageHoll.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        imagecall.setOnClickListener {

            val dbHelper3 = DBHelper(this) // объект класса YourDbHelper
            val db3 = dbHelper3.readableDatabase // база данных для чтения

            val cursor2 = db3.query(
                "Setting",
                arrayOf("phone_number"),
                null,
                null,
                null,
                null,
                null
            )

            if (cursor2.moveToFirst()) {
                val phoneNumber = cursor2.getString(cursor2.getColumnIndex("phone_number")?:0)

                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialIntent)
            }

            cursor2.close()
            db3.close()
        }
        val dbHelper = DBHelper(this)
        val recyclerView = findViewById<RecyclerView>(R.id.recycle)
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        // Получаем данные из таблицы Setting

        // Получаем данные из таблицы Setting
        val cursor: Cursor = dbHelper.getDataFromSettingTable()

        // Переходим к первой записи в результате запроса

        // Переходим к первой записи в результате запроса
        if (cursor.moveToFirst()) {
            // Получаем значения столбцов и сохраняем их в соответствующие переменные
            val background_down_on_ite = cursor.getString(cursor.getColumnIndex("background_down_on_ite")?:0)
            namingsize =
                cursor.getString(cursor.getColumnIndex("adapter_name_size")?:1)
            seillsize =
                cursor.getString(cursor.getColumnIndex("adapter_seil_size")?:1)
            pricesize =
                cursor.getString(cursor.getColumnIndex("adapter_price_size")?:1)
            ingosize =
                cursor.getString(cursor.getColumnIndex("adapter_info_size")?:1)
            namecolor =
                cursor.getString(cursor.getColumnIndex("adapter_name_color")?:1)
            infocolor =
                cursor.getString(cursor.getColumnIndex("adapter_info_color")?:1)
            pricecolor =
                cursor.getString(cursor.getColumnIndex("adapter_price_color")?:1)
            seilcolor =
                cursor.getString(cursor.getColumnIndex("adapter_seil_color")?:1)
            adapterback =
                cursor.getString(cursor.getColumnIndex("adapter_background_color")?:1)
            val BackScreen =
                cursor.getString(cursor.getColumnIndex("adapter_backFullScreen")?:1)
            val favoritescr=findViewById<ConstraintLayout>(R.id.FavoriteScreen)
            favoritescr.setBackgroundColor(Color.parseColor(BackScreen))
            val lto =findViewById<LinearLayout>(R.id.DownLine)
            lto.setBackgroundColor(Color.parseColor(background_down_on_ite))

        }





        // Закрываем курсор и базу данных

        // Закрываем курсор и базу данных
        cursor.close()
        dbHelper.close()
        var items = getItemsFromDatabaseFav()
        Log.d(" ЯЯЯЯЯЯЯЯЯЯЯЯ","$items")
        var adapter = ItemAdapter2(this, items)
        recyclerView.adapter = adapter

    }

    private fun getItemsFromDatabaseFav(): List<ItemAdapter2.ItemFav6> {
        val itemkl = mutableListOf<ItemAdapter2.ItemFav6>()
        val dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            "name", "price", "seil", "full_info", "litle_info",
            "image1", "image2", "image3", "image4", "image5",
            "image6", "image7", "image8", "image9", "image10", "raiting"
        )
        val sortOrder = "raiting DESC"
        val cursor4 = db.query(
            "favorite",
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        while (cursor4.moveToNext()) { // Исправлено здесь
            val name = cursor4.getString(cursor4.getColumnIndexOrThrow("name"))
            val price = cursor4.getString(cursor4.getColumnIndexOrThrow("price"))
            val seil = cursor4.getString(cursor4.getColumnIndexOrThrow("seil"))
            val fullInfo = cursor4.getString(cursor4.getColumnIndexOrThrow("full_info"))
            val litleInfo = cursor4.getString(cursor4.getColumnIndexOrThrow("litle_info"))
            val imageUrls = mutableListOf<String>()
            for (i in 1..10) {
                val imageUrl = cursor4.getString(cursor4.getColumnIndexOrThrow("image$i"))
                if (!imageUrl.isNullOrBlank()) {
                    imageUrls.add(imageUrl)
                }
            }
            val rating = cursor4.getInt(cursor4.getColumnIndexOrThrow("raiting"))
            val id=1
            itemkl.add(ItemAdapter2.ItemFav6(id, name, price, seil, fullInfo, litleInfo, imageUrls, rating,adapterback.toString(),namingsize.toString(),namecolor.toString(),
                pricesize.toString(),pricecolor.toString(), seillsize.toString(),seilcolor.toString(),infocolor.toString(),ingosize.toString()))
        }
        return itemkl
        cursor4.close()
        dbHelper.close()
    }
}