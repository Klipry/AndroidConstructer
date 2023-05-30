package com.example.androidconstructer

import android.content.ClipData
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.CursorUtil.getColumnIndexOrThrow
import com.example.androidconstructer.Data.*
import kotlinx.coroutines.*
import org.json.JSONArray
import java.lang.reflect.Array.getInt
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {
    var namingsize:String?=null
    var seillsize:String?=null
    var pricesize:String?=null
    var namecolor:String?=null
    var infocolor:String?=null
    var ingosize:String?=null
    var seilcolor:String?=null
    var adapterback:String?=null
    var pricecolor:String?=null
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1=findViewById<Button>(R.id.Category1)
        val scrollbutton1=findViewById<Button>(R.id.scrollbut1)
        val scrollbutton2=findViewById<Button>(R.id.scrollbut2)
        val scrollbutton3=findViewById<Button>(R.id.scrollbut3)
        val scrollbutton4=findViewById<Button>(R.id.scrollbut4)

        val button2=findViewById<Button>(R.id.Category2)
        val button3=findViewById<Button>(R.id.Category3)
        val dbHelper214 = DBHelper(this)
        val cursor214: Cursor = dbHelper214.getDataFromButtonCategoryTable()

        // Переходим к первой записи в результате запроса

        // Переходим к первой записи в результате запроса
        if (cursor214.moveToFirst()) {
            val oneCategoryIndex = cursor214.getColumnIndex(DBHelper.OneCategory)
            val twoCategoryIndex = cursor214.getColumnIndex(DBHelper.TwoCategory)
            val threeCategoryIndex = cursor214.getColumnIndex(DBHelper.ThreeCategory)

            button1.text = cursor214.getString(oneCategoryIndex)
            button2.text = cursor214.getString(twoCategoryIndex)
            button3.text = cursor214.getString(threeCategoryIndex)
            scrollbutton1.text=cursor214.getString(oneCategoryIndex)
            scrollbutton2.text=cursor214.getString(twoCategoryIndex)
            scrollbutton3.text=cursor214.getString(threeCategoryIndex)


            Log.d("Проверка кнопки", "$button1")
        }



        cursor214.close()
        dbHelper214.close()




            //////сохраняем данные в переменные////////////////////////////////////////
        var dbHelper = DBHelper(this)


        // Получаем данные из таблицы Setting

        // Получаем данные из таблицы Setting
        val cursor: Cursor = dbHelper.getDataFromSettingTable()

        // Переходим к первой записи в результате запроса

        // Переходим к первой записи в результате запроса
        if (cursor.moveToFirst()) {
            // Получаем значения столбцов и сохраняем их в соответствующие переменные
           val logoLocation = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_LOGO_LOCATION)?:1)
            val mainActivityBackgroundColor =
                cursor.getString(cursor.getColumnIndex(DBHelper.KEY_MAIN_ACTIVITY_BACKGROUND_COLOR)?:1)
            val  categoryButtonColor =
                cursor.getString(cursor.getColumnIndex(DBHelper.KEY_CATEGORY_BUTTON_COLOR)?:1)
            val productListItemBackgroundColor =
                cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PRODUCT_LIST_ITEM_BACKGROUND_COLOR)?:1)
            val appName = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_APP_NAME)?:1)
            val userAgreement = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_USER_AGREEMENT)?:1)
            val  phoneNumber = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PHONE_NUMBER)?:1)
            val  productCardBackgroundColor =
                cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PRODUCT_CARD_BACKGROUND_COLOR)?:1)
            val productCardTextColor =
                cursor.getString(cursor.getColumnIndex(DBHelper.KEY_PRODUCT_CARD_TEXT_COLOR)?:1)
            val  appNameTextColor =
                cursor.getString(cursor.getColumnIndex(DBHelper.KEY_APP_NAME_TEXT_COLOR)?:1)
            val background_down_on_ite =
                cursor.getString(cursor.getColumnIndex("background_down_on_ite")?:1)
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
            val lto =findViewById<LinearLayout>(R.id.DownLine)
            lto.setBackgroundColor(Color.parseColor(background_down_on_ite))




            val layout:ConstraintLayout=findViewById(R.id.Mainconstr)
            layout.setBackgroundColor(Color.parseColor(mainActivityBackgroundColor))
            //Category color

            button1.setBackgroundColor(Color.parseColor(categoryButtonColor))
            button2.setBackgroundColor(Color.parseColor(categoryButtonColor))
            button3.setBackgroundColor(Color.parseColor(categoryButtonColor))
            scrollbutton1.setBackgroundColor(Color.parseColor(categoryButtonColor))
            scrollbutton2.setBackgroundColor(Color.parseColor(categoryButtonColor))
            scrollbutton3.setBackgroundColor(Color.parseColor(categoryButtonColor))
            scrollbutton4.setBackgroundColor(Color.parseColor(categoryButtonColor))


            //App name
            val Nameapp=findViewById<TextView>(R.id.Nameapp)
            Nameapp.text=appName



            ///////////////////////Logo////////////////////////////////////
            val imageView=findViewById<ImageView>(R.id.LogoUp)
            val imageLoader = ImageLoader(imageView, this,logoLocation)
            imageLoader.loadImageFromUrl("https://untr.ru/Image/"+logoLocation)


            //-----------------------------------------------------------/


        }


        // Закрываем курсор и базу данных

        // Закрываем курсор и базу данных
        cursor.close()
        dbHelper.close()









        val buttonCategory2 = BattonCategory2(this, "новое значение логотипа")
        buttonCategory2.execute()
        val buttonCategory3 = BattonCategory3(this, "новое значение логотипа")
        buttonCategory3.execute()
        //-------------------------
        // ----------------------------------------------------------------
        val phoneCall = PhoneNumber(this, "новое значение логотипа")
        phoneCall.execute()

        val Favorite_upd = Favorite_upd(this, "новое значение логотипа")
        Favorite_upd.execute()


        val task = UpdateLogoTask(this, "новое значение логотипа")
        task.execute()

        val adapter_back_color = adapter_background_color(this, "новое значение логотипа")
        adapter_back_color.execute()

        val adapter_price_color = adapter_price_color(this, "новое значение логотипа")
        adapter_price_color.execute()

        val adapter_seil_color = adapter_seil_color(this, "новое значение логотипа")
        adapter_seil_color.execute()


        val adapter_info_color = adapter_info_color(this, "новое значение логотипа")
        adapter_info_color.execute()

        val adapter_name_color = adapter_name_color(this, "новое значение логотипа")
        adapter_name_color.execute()

        val adapter_info_size = adapter_info_size(this, "новое значение логотипа")
        adapter_info_size.execute()

        val adapter_price_size = adapter_price_size(this, "новое значение логотипа")
        adapter_price_size.execute()

        val adapter_seil_size = adapter_seil_size(this, "новое значение логотипа")
        adapter_seil_size.execute()

        val adapter_name_size = adapter_name_size(this, "новое значение логотипа")
        adapter_name_size.execute()







        //////////////////////////////////////////////BacgroundColor/////////////////////
        val taskBackGroundColor = MainBackGroundColor(this, "новое значение логотипа")
        taskBackGroundColor.execute()


        //-----------------------------------------------------------------------------------------



        //////////////////////////////////////////////ButtonCollor/////////////////////
        val CategoryButtonColor = CategoryButtonColor(this, "новое значение логотипа")
        CategoryButtonColor.execute()



        //-----------------------------------------------------------------------------------------


        GlobalScope.launch(Dispatchers.IO) {
            synchronizeData(this@MainActivity)
            synchronizeData1(this@MainActivity)
            synchronizeData2(this@MainActivity)
            synchronizeData3(this@MainActivity)
        }


        //////////////////////////////////////////////APP_NAME/////////////////////
        val AppName = AppName(this, "новое значение логотипа")
        AppName.execute()



        //-----------------------------------------------------------------------------------------
        val BattonCategory = BattonCategory(this, "новое значение логотипа")
        BattonCategory.execute()

//////////////////////////////////////////////////////////////////////////////////////////////////
        val bacground_on_item= bacground_on_item(this, "новое значение логотипа")
        bacground_on_item.execute()
        val text_name_color_on_item= text_name_color_on_item(this, "новое значение логотипа")
        text_name_color_on_item.execute()
        val text_seil_color_on_item= text_seil_color_on_item(this, "новое значение логотипа")
        text_seil_color_on_item.execute()
        val text_info_color_on_item= text_info_color_on_item(this, "новое значение логотипа")
        text_info_color_on_item.execute()
        val text_seil_colorBack_on_item= text_seil_colorBack_on_item(this, "новое значение логотипа")
        text_seil_colorBack_on_item.execute()
        val text_name_colorBack_on_item= text_name_colorBack_on_item(this, "новое значение логотипа")
        text_name_colorBack_on_item.execute()
        val text_info_colorBack_on_item= text_info_colorBack_on_item(this, "новое значение логотипа")
        text_info_colorBack_on_item.execute()
        val text_name_size_on_item = text_name_size_on_item(this, "новое значение логотипа")
        text_name_size_on_item.execute()
        val text_info_size_on_item= text_info_size_on_item(this, "новое значение логотипа")
        text_info_size_on_item.execute()
        val text_seil_size_on_item= text_seil_size_on_item(this, "новое значение логотипа")
        text_seil_size_on_item.execute()
        val background_down_on_item= background_down_on_item(this, "новое значение логотипа")
        background_down_on_item.execute()
        //////////////////////////////////////////////////////////////////////////////////////


        readFromDatabase()
        /////////////////Удалить...
        val recyclerView = findViewById<RecyclerView>(R.id.recycle)
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        val items = getItemsFromDatabase()
        val adapter = ItemAdapter(this, items)
        recyclerView.adapter = adapter
        button1.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            val items = getItemsFromDatabase1()
            val adapter = ItemAdapter(this, items)
            recyclerView.adapter = adapter

        }
        button2.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            val items = getItemsFromDatabase2()
            val adapter = ItemAdapter(this, items)
            recyclerView.adapter = adapter

        }
        button3.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            val items = getItemsFromDatabase3()
            val adapter = ItemAdapter(this, items)
            recyclerView.adapter = adapter

        }
        val imageScroll=findViewById<ImageView>(R.id.ImageScroll)
        var ik=0
        imageScroll.setOnClickListener {
            Log.d("ikkkkkkkkkkkkk","$ik")
            if (ik==0){
                ik++
                scrollbutton1.visibility=View.VISIBLE
                scrollbutton2.visibility=View.VISIBLE
                scrollbutton3.visibility=View.VISIBLE
                scrollbutton4.visibility=View.VISIBLE
                button3.visibility=View.GONE


            }else if (ik==1){
                ik--
                scrollbutton1.visibility=View.GONE
                scrollbutton2.visibility=View.GONE
                scrollbutton3.visibility=View.GONE
                scrollbutton4.visibility=View.GONE
                button3.visibility=View.VISIBLE

            }
        }
        scrollbutton1.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            val items = getItemsFromDatabase1()
            val adapter = ItemAdapter(this, items)
            recyclerView.adapter = adapter
            scrollbutton1.visibility=View.GONE
            scrollbutton2.visibility=View.GONE
            scrollbutton3.visibility=View.GONE
            scrollbutton4.visibility=View.GONE
            button3.visibility=View.VISIBLE

            ik--

        }
        scrollbutton2.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            val items = getItemsFromDatabase2()
            val adapter = ItemAdapter(this, items)
            recyclerView.adapter = adapter
            recyclerView.adapter = adapter
            scrollbutton1.visibility=View.GONE
            scrollbutton2.visibility=View.GONE
            scrollbutton3.visibility=View.GONE
            scrollbutton4.visibility=View.GONE
            button3.visibility=View.VISIBLE

            ik--

        }
        scrollbutton3.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            val items = getItemsFromDatabase3()
            val adapter = ItemAdapter(this, items)
            recyclerView.adapter = adapter
            recyclerView.adapter = adapter
            scrollbutton1.visibility=View.GONE
            scrollbutton2.visibility=View.GONE
            scrollbutton3.visibility=View.GONE
            scrollbutton4.visibility=View.GONE
            button3.visibility=View.VISIBLE

            ik--

        }
        scrollbutton4.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
            val items = getItemsFromDatabase()
            val adapter = ItemAdapter(this, items)
            recyclerView.adapter = adapter
            recyclerView.adapter = adapter
            scrollbutton1.visibility=View.GONE
            scrollbutton2.visibility=View.GONE
            scrollbutton3.visibility=View.GONE
            scrollbutton4.visibility=View.GONE
            button3.visibility=View.VISIBLE
            ik--

        }

        val imageView=findViewById<ImageView>(R.id.ButtonLikes)
        imageView.setOnClickListener {
            val intent = Intent(this,favourites::class.java)
            startActivity(intent)
        }
        val imageCall=findViewById<ImageView>(R.id.ButtonCall)
        imageCall.setOnClickListener {
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
                val phoneNumber = cursor2.getString(cursor2.getColumnIndex("phone_number")?:1)
                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialIntent)
            }

            cursor2.close()
            db3.close()

        }
    }

    private fun getItemsFromDatabase(): List<ItemAdapter.Item> {
        val items = mutableListOf<ItemAdapter.Item>()
        val dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            "id", "name", "price", "seil", "full_info", "litle_info",
            "image1", "image2", "image3", "image4", "image5",
            "image6", "image7", "image8", "image9", "image10", "raiting"
        )
        val sortOrder = "raiting DESC"
        val cursor4 = db.query(
            "Item",
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        while (cursor4.moveToNext()) { // Исправлено здесь
            val id = cursor4.getInt(cursor4.getColumnIndexOrThrow("id"))
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
            items.add(ItemAdapter.Item(id, name, price, seil, fullInfo, litleInfo, imageUrls, rating,adapterback.toString(),namingsize.toString(),namecolor.toString(),
            pricesize.toString(),pricecolor.toString(), seillsize.toString(),seilcolor.toString(),infocolor.toString(),ingosize.toString()))

        }
        return items
        cursor4.close()
        dbHelper.close()


    }



    private fun readFromDatabase() {
        val dbHelper = DBHelper(this)


        val db = dbHelper.readableDatabase

        // Выбрать все строки из таблицы Setting и получить значение столбца logo_location
        val cursor = db.query(
            DBHelper.TABLE_SETTING,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val logoIndex = cursor.getColumnIndexOrThrow(DBHelper.KEY_APP_NAME)

        while (cursor.moveToNext()) {
            val logoLocation = cursor.getString(logoIndex)
            Log.d("Обновись", "Logo location: $logoLocation")
        }

        cursor.close()
        dbHelper.close()
    }
    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    //////////////////////////Загрузка данных товаров.
    fun synchronizeData(context:Context) {
        coroutineScope.launch {
            if (isNetworkConnected()) {
                try {
                    // Получаем данные из удаленной таблицы MySQL
                    val url = URL("https://untr.ru/AndroidItem.php")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    val inputStream = connection.inputStream
                    val json = inputStream.bufferedReader().use { it.readText() }
                    val jsonArray = JSONArray(json)

                    // Обновляем локальную таблицу SQLite
                    val dbHelper = DBHelper(context)
                    val db = dbHelper.writableDatabase
                    val values = ContentValues()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        values.put("id", jsonObject.optInt("id"))
                        values.put("name", jsonObject.optString("name",""))
                        values.put("price", jsonObject.optString("price",""))
                        values.put("seil", jsonObject.optString("seil",""))
                        values.put("full_info", jsonObject.optString("full_info",""))
                        values.put("litle_info", jsonObject.optString("litle_info",""))
                        values.put("image1", jsonObject.optString("image1",""))
                        values.put("image2", jsonObject.optString("image2",""))
                        values.put("image3", jsonObject.optString("image3",""))
                        values.put("image4", jsonObject.optString("image4",""))
                        values.put("image5", jsonObject.optString("image5",""))
                        values.put("image6", jsonObject.optString("image6",""))
                        values.put("image7", jsonObject.optString("image7",""))
                        values.put("image8", jsonObject.optString("image8",""))
                        values.put("image9", jsonObject.optString("image9",""))
                        values.put("image10", jsonObject.optString("image10",""))
                        values.put("raiting", jsonObject.optInt("raiting"))
                        val id = jsonObject.getInt("id")
                        val rowsAffected = db.update("Item", values, "id = $id", null)
                        if (rowsAffected == 0) {
                            db.insert("Item", null, values)
                        }
                        Log.d("Добавленные значения", "data $values")
                        values.clear()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error synchronizing data", e)
                }

            }
        }
    }

    fun synchronizeData1(context:Context) {
        coroutineScope.launch {
            if (isNetworkConnected()) {
                try {
                    // Получаем данные из удаленной таблицы MySQL
                    val url = URL("https://untr.ru/AndroidItem1.php")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    val inputStream = connection.inputStream
                    val json = inputStream.bufferedReader().use { it.readText() }
                    val jsonArray = JSONArray(json)

                    // Обновляем локальную таблицу SQLite
                    val dbHelper = DBHelper(context)
                    val db = dbHelper.writableDatabase
                    val values = ContentValues()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        values.put("id", jsonObject.optInt("id"))
                        values.put("name", jsonObject.optString("name",""))
                        values.put("price", jsonObject.optString("price",""))
                        values.put("seil", jsonObject.optString("seil",""))
                        values.put("full_info", jsonObject.optString("full_info",""))
                        values.put("litle_info", jsonObject.optString("litle_info",""))
                        values.put("image1", jsonObject.optString("image1",""))
                        values.put("image2", jsonObject.optString("image2",""))
                        values.put("image3", jsonObject.optString("image3",""))
                        values.put("image4", jsonObject.optString("image4",""))
                        values.put("image5", jsonObject.optString("image5",""))
                        values.put("image6", jsonObject.optString("image6",""))
                        values.put("image7", jsonObject.optString("image7",""))
                        values.put("image8", jsonObject.optString("image8",""))
                        values.put("image9", jsonObject.optString("image9",""))
                        values.put("image10", jsonObject.optString("image10",""))
                        values.put("raiting", jsonObject.optInt("raiting"))
                        val id = jsonObject.getInt("id")
                        val rowsAffected = db.update("Category1", values, "id = $id", null)
                        if (rowsAffected == 0) {
                            db.insert("Category1", null, values)
                        }
                        Log.d("Добавленные значения", "data $values")
                        values.clear()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error synchronizing data", e)
                }

            }
        }
    }
    fun synchronizeData2(context:Context) {
        coroutineScope.launch {
            if (isNetworkConnected()) {
                try {
                    // Получаем данные из удаленной таблицы MySQL
                    val url = URL("https://untr.ru/AndroidItem2.php")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    val inputStream = connection.inputStream
                    val json = inputStream.bufferedReader().use { it.readText() }
                    val jsonArray = JSONArray(json)

                    // Обновляем локальную таблицу SQLite
                    val dbHelper = DBHelper(context)
                    val db = dbHelper.writableDatabase
                    val values = ContentValues()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        values.put("id", jsonObject.optInt("id"))
                        values.put("name", jsonObject.optString("name",""))
                        values.put("price", jsonObject.optString("price",""))
                        values.put("seil", jsonObject.optString("seil",""))
                        values.put("full_info", jsonObject.optString("full_info",""))
                        values.put("litle_info", jsonObject.optString("litle_info",""))
                        values.put("image1", jsonObject.optString("image1",""))
                        values.put("image2", jsonObject.optString("image2",""))
                        values.put("image3", jsonObject.optString("image3",""))
                        values.put("image4", jsonObject.optString("image4",""))
                        values.put("image5", jsonObject.optString("image5",""))
                        values.put("image6", jsonObject.optString("image6",""))
                        values.put("image7", jsonObject.optString("image7",""))
                        values.put("image8", jsonObject.optString("image8",""))
                        values.put("image9", jsonObject.optString("image9",""))
                        values.put("image10", jsonObject.optString("image10",""))
                        values.put("raiting", jsonObject.optInt("raiting"))
                        val id = jsonObject.getInt("id")
                        val rowsAffected = db.update("Category2", values, "id = $id", null)
                        if (rowsAffected == 0) {
                            db.insert("Category2", null, values)
                        }
                        Log.d("Добавленные значения", "data $values")
                        values.clear()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error synchronizing data", e)
                }

            }
        }
    }
    fun synchronizeData3(context:Context) {
        coroutineScope.launch {
            if (isNetworkConnected()) {
                try {
                    // Получаем данные из удаленной таблицы MySQL
                    val url = URL("https://untr.ru/AndroidItem3.php")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    val inputStream = connection.inputStream
                    val json = inputStream.bufferedReader().use { it.readText() }
                    val jsonArray = JSONArray(json)

                    // Обновляем локальную таблицу SQLite
                    val dbHelper = DBHelper(context)
                    val db = dbHelper.writableDatabase
                    val values = ContentValues()
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        values.put("id", jsonObject.optInt("id"))
                        values.put("name", jsonObject.optString("name",""))
                        values.put("price", jsonObject.optString("price",""))
                        values.put("seil", jsonObject.optString("seil",""))
                        values.put("full_info", jsonObject.optString("full_info",""))
                        values.put("litle_info", jsonObject.optString("litle_info",""))
                        values.put("image1", jsonObject.optString("image1",""))
                        values.put("image2", jsonObject.optString("image2",""))
                        values.put("image3", jsonObject.optString("image3",""))
                        values.put("image4", jsonObject.optString("image4",""))
                        values.put("image5", jsonObject.optString("image5",""))
                        values.put("image6", jsonObject.optString("image6",""))
                        values.put("image7", jsonObject.optString("image7",""))
                        values.put("image8", jsonObject.optString("image8",""))
                        values.put("image9", jsonObject.optString("image9",""))
                        values.put("image10", jsonObject.optString("image10",""))
                        values.put("raiting", jsonObject.optInt("raiting"))
                        val id = jsonObject.getInt("id")
                        val rowsAffected = db.update("Category3", values, "id = $id", null)
                        if (rowsAffected == 0) {
                            db.insert("Category3", null, values)
                        }
                        Log.d("Добавленные значения", "data $values")
                        values.clear()
                    }
                } catch (e: Exception) {
                    Log.e(TAG, "Error synchronizing data", e)
                }

            }
        }
    }
    private fun getItemsFromDatabase1(): List<ItemAdapter.Item> {
        val items = mutableListOf<ItemAdapter.Item>()
        val dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            "id", "name", "price", "seil", "full_info", "litle_info",
            "image1", "image2", "image3", "image4", "image5",
            "image6", "image7", "image8", "image9", "image10", "raiting"
        )
        val sortOrder = "raiting DESC"
        val cursor4 = db.query(
            "Category1",
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        while (cursor4.moveToNext()) { // Исправлено здесь
            val id = cursor4.getInt(cursor4.getColumnIndexOrThrow("id"))
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
            items.add(ItemAdapter.Item(id, name, price, seil, fullInfo, litleInfo, imageUrls, rating,adapterback.toString(),namingsize.toString(),namecolor.toString(),
                pricesize.toString(),pricecolor.toString(), seillsize.toString(),seilcolor.toString(),infocolor.toString(),ingosize.toString()))

        }
        return items
        cursor4.close()
        dbHelper.close()


    }
    private fun getItemsFromDatabase2(): List<ItemAdapter.Item> {
        val items = mutableListOf<ItemAdapter.Item>()
        val dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            "id", "name", "price", "seil", "full_info", "litle_info",
            "image1", "image2", "image3", "image4", "image5",
            "image6", "image7", "image8", "image9", "image10", "raiting"
        )
        val sortOrder = "raiting DESC"
        val cursor4 = db.query(
            "Category2",
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        while (cursor4.moveToNext()) { // Исправлено здесь
            val id = cursor4.getInt(cursor4.getColumnIndexOrThrow("id"))
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
            items.add(ItemAdapter.Item(id, name, price, seil, fullInfo, litleInfo, imageUrls, rating,adapterback.toString(),namingsize.toString(),namecolor.toString(),
                pricesize.toString(),pricecolor.toString(), seillsize.toString(),seilcolor.toString(),infocolor.toString(),ingosize.toString()))

        }
        return items
        cursor4.close()
        dbHelper.close()


    }
    private fun getItemsFromDatabase3(): List<ItemAdapter.Item> {
        val items = mutableListOf<ItemAdapter.Item>()
        val dbHelper = DBHelper(this)
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            "id", "name", "price", "seil", "full_info", "litle_info",
            "image1", "image2", "image3", "image4", "image5",
            "image6", "image7", "image8", "image9", "image10", "raiting"
        )
        val sortOrder = "raiting DESC"
        val cursor4 = db.query(
            "Category3",
            projection,
            null,
            null,
            null,
            null,
            sortOrder
        )

        while (cursor4.moveToNext()) { // Исправлено здесь
            val id = cursor4.getInt(cursor4.getColumnIndexOrThrow("id"))
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
            items.add(ItemAdapter.Item(id, name, price, seil, fullInfo, litleInfo, imageUrls, rating,adapterback.toString(),namingsize.toString(),namecolor.toString(),
                pricesize.toString(),pricecolor.toString(), seillsize.toString(),seilcolor.toString(),infocolor.toString(),ingosize.toString()))

        }
        return items
        cursor4.close()
        dbHelper.close()


    }
    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

}
