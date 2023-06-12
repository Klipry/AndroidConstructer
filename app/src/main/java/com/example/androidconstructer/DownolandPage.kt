package com.example.androidconstructer

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.androidconstructer.Data.*
import kotlinx.coroutines.*
import org.json.JSONArray
import org.w3c.dom.Text
import java.net.HttpURLConnection
import java.net.URL

class DownolandPage : AppCompatActivity() {
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_downoland_page)
        val db=DBHelper(this)
        val image=findViewById<ImageView>(R.id.imageView7)
        Glide.with(this)
            .load(R.drawable.gifmain)
            .into(image)
        val textView = findViewById<TextView>(R.id.textView2)
        val colorFrom = resources.getColor(android.R.color.white, null)
        val colorTo = resources.getColor(android.R.color.black, null)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 3000 // Длительность анимации в миллисекундах
        colorAnimation.repeatCount = ValueAnimator.INFINITE // Бесконечная анимация
        colorAnimation.repeatMode = ValueAnimator.REVERSE // Анимация будет проигрываться в обратном направлении после завершения
        colorAnimation.addUpdateListener { animator ->
            textView.setTextColor(animator.animatedValue as Int)
        }
        colorAnimation.start()
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.max = 100

        val handler = Handler()
        val runnable = object : Runnable {
            var counter = 0
            override fun run() {
                counter += 12
                progressBar.progress = counter
                if (counter < 100) {
                    handler.postDelayed(this, 1000) // запуск задачи через 1 секунду
                }
            }
        }

        handler.postDelayed(runnable, 1000) // запуск задачи через 1 секунду

        GlobalScope.launch(Dispatchers.Main) {
            // ждем 6 секунд
            delay(15000)

            // переходим на следующую активити
            val intent = Intent(this@DownolandPage, MainActivity::class.java)
            startActivity(intent)

            // закрываем текущую активити
            finish()
        }

        // Получаем данные из таблицы Setting
        val cursor: Cursor = db.getDataFromSettingTable()

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



            ///////////////////////Logo////////////////////////////////////
            val imageView=findViewById<ImageView>(R.id.imageView8)
            val imageLoader = ImageLoader(imageView, this,logoLocation)
            imageLoader.loadImageFromUrl("https://untr.ru/Image/"+logoLocation)


            //-----------------------------------------------------------/


        }


        // Закрываем курсор и базу данных

        // Закрываем курсор и базу данных
        cursor.close()
        db.close()




        val buttonCategory4 = BattonCategory4(this, "новое значение логотипа")
        buttonCategory4.execute()
        val buttonCategory5 = BattonCategory5(this, "новое значение логотипа")
        buttonCategory5.execute()

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
            synchronizeData(this@DownolandPage)
            synchronizeData1(this@DownolandPage)
            synchronizeData2(this@DownolandPage)
            synchronizeData3(this@DownolandPage)
            synchronizeData4(this@DownolandPage)
            synchronizeData5(this@DownolandPage)

        }


        //////////////////////////////////////////////APP_NAME/////////////////////
        val AppName = AppName(this, "новое значение логотипа")
        AppName.execute()



        //-----------------------------------------------------------------------------------------
        val BattonCategory = BattonCategory(this, "новое значение логотипа")
        BattonCategory.execute()
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










    }

    fun synchronizeData(context: Context) {
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
                    Log.e(ContentValues.TAG, "Error synchronizing data", e)
                }

            }
        }
    }

    fun synchronizeData1(context: Context) {
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
                    Log.e(ContentValues.TAG, "Error synchronizing data", e)
                }

            }
        }
    }
    fun synchronizeData2(context: Context) {
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
                    Log.e(ContentValues.TAG, "Error synchronizing data", e)
                }

            }
        }
    }
    fun synchronizeData3(context: Context) {
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
                    Log.e(ContentValues.TAG, "Error synchronizing data", e)
                }

            }
        }
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

    fun synchronizeData4(context:Context) {
        coroutineScope.launch {
            if (isNetworkConnected()) {
                try {
                    // Получаем данные из удаленной таблицы MySQL
                    val url = URL("https://untr.ru/AndroidItem5.php")
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
                        val rowsAffected = db.update("Category4", values, "id = $id", null)
                        if (rowsAffected == 0) {
                            db.insert("Category4", null, values)
                        }
                        Log.d("Добавленные значения", "data $values")
                        values.clear()
                    }
                } catch (e: Exception) {
                    Log.e(ContentValues.TAG, "Error synchronizing data", e)
                }

            }
        }
    }

    fun synchronizeData5(context:Context) {
        coroutineScope.launch {
            if (isNetworkConnected()) {
                try {
                    // Получаем данные из удаленной таблицы MySQL
                    val url = URL("https://untr.ru/AndroidItem6.php")
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
                        val rowsAffected = db.update("Category5", values, "id = $id", null)
                        if (rowsAffected == 0) {
                            db.insert("Category5", null, values)
                        }
                        Log.d("Добавленные значения", "data $values")
                        values.clear()
                    }
                } catch (e: Exception) {
                    Log.e(ContentValues.TAG, "Error synchronizing data", e)
                }

            }
        }
    }

}