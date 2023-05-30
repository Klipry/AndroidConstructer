package com.example.androidconstructer

import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.example.androidconstructer.Data.DBHelper
import com.example.androidconstructer.Data.ImageLoader
import com.example.androidconstructer.Data.InformationSender


class ItemDetailsActivity : AppCompatActivity() {
    var name:String?=null
    var price:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        val itemId = intent.getIntExtra("ITEM_ID", 1)
        val image_url = intent.getStringExtra("image_url")
         price = intent.getStringExtra("price")
        val info = intent.getStringExtra("full_info")
        val sell = intent.getStringExtra("sell")
        val litle_info=intent.getStringExtra("litle_info")

        val rait=intent.getIntExtra("raiting",1)

        var i=0

        val itemsList = image_url?.split(",")?.map { it.trim().replace("[", "").replace("]", "") } ?: emptyList()
        val image1=itemsList[0]
        val image2=itemsList[1]
        val image3=itemsList[2]
        val image4=itemsList[3]
        val image5=itemsList[4]
        val image6=itemsList[5]
        val image7=itemsList[6]
        val image8=itemsList[7]
        val image9=itemsList[8]
        val image10=itemsList[9]


         name = intent.getStringExtra("name")
        val textname = findViewById<TextView>(R.id.textName)
        val textsell = findViewById<TextView>(R.id.textViewsell)
        val textinfo = findViewById<TextView>(R.id.textViewInfo)
        textsell.text = sell
        textinfo.text = info
        textname.text = name
        val itemClear = mutableListOf<String>()
        val image = findViewById<ImageView>(R.id.imageView)
        val imagemain = ImageLoader(image, this, itemsList[0])
        val LayotMain=findViewById<ConstraintLayout>(R.id.layoutMainDetails)
        val Downline=findViewById<LinearLayout>(R.id.DownLine)
        val scrollView=findViewById<ScrollView>(R.id.scrollText)
        val imageOnLike=findViewById<ImageView>(R.id.imageView9)
        val imageOfLike=findViewById<ImageView>(R.id.imageView10)

        val cg=textinfo.lineHeight
        textinfo.maxLines=cg

        imageOfLike.setOnClickListener {
            val db5=DBHelper(this).writableDatabase
            val values = ContentValues().apply {
                put("name", name)
                put("price", price)
                put("seil", sell)
                put("full_info", info)
                put("litle_info", litle_info)
                put("image1", image1)
                put("image2", image2)
                put("image3", image3)
                put("image4", image4)
                put("image5", image5)
                put("image6", image6)
                put("image7", image7)
                put("image8", image8)
                put("image9", image9)
                put("image10", image10)
                put("raiting", rait)
            }


            db5.insert("favorite", null, values)
            db5.close()
            imageOfLike.visibility=View.GONE
            imageOnLike.visibility=View.VISIBLE

        }
        imageOnLike.setOnClickListener {
            imageOfLike.visibility=View.VISIBLE
            imageOnLike.visibility=View.GONE
            val db909=DBHelper(this).writableDatabase
            val selection = "name=? AND full_info=?"
            val selectionArgs = arrayOf(name, info)
            val deletedRows = db909.delete("favorite", selection, selectionArgs)
            db909.close()

        }



        val db = DBHelper(this).readableDatabase

        val query= "SELECT bacground_on_item, text_name_color_on_item, text_seil_color_on_item, text_info_color_on_item, text_seil_colorBack_on_item, text_name_colorBack_on_item, text_info_colorBack_on_item, text_name_size_on_item, text_info_size_on_item, text_seil_size_on_item, background_down_on_ite FROM Setting"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val bacground_on_item = cursor.getString(cursor.getColumnIndex("bacground_on_item"))
            val text_name_color_on_item = cursor.getString(cursor.getColumnIndex("text_name_color_on_item"))
            val text_seil_color_on_item = cursor.getString(cursor.getColumnIndex("text_seil_color_on_item"))
            val text_info_color_on_item = cursor.getString(cursor.getColumnIndex("text_info_color_on_item"))
            val text_seil_colorBack_on_item = cursor.getString(cursor.getColumnIndex("text_seil_colorBack_on_item"))
            val text_name_colorBack_on_item = cursor.getString(cursor.getColumnIndex("text_name_colorBack_on_item"))
            val text_info_colorBack_on_item = cursor.getString(cursor.getColumnIndex("text_info_colorBack_on_item"))
            val text_name_size_on_item = cursor.getString(cursor.getColumnIndex("text_name_size_on_item"))
            val text_info_size_on_item = cursor.getString(cursor.getColumnIndex("text_info_size_on_item"))
            val text_seil_size_on_item = cursor.getString(cursor.getColumnIndex("text_seil_size_on_item"))
            val background_down_on_ite = cursor.getString(cursor.getColumnIndex("background_down_on_ite"))
            // делаем что-то с полученными значениями
            textname.setTextColor(Color.parseColor(text_name_color_on_item))
            LayotMain.setBackgroundColor(Color.parseColor(bacground_on_item))
            textsell.setTextColor(Color.parseColor(text_seil_color_on_item))
            textinfo.setTextColor(Color.parseColor(text_info_color_on_item))
            textsell.setBackgroundColor(Color.parseColor(text_seil_colorBack_on_item))
            textname.setBackgroundColor(Color.parseColor(text_name_colorBack_on_item))
            textinfo.setBackgroundColor(Color.parseColor(text_info_colorBack_on_item))
            val size1 = (text_name_size_on_item.toFloat() * (resources.displayMetrics.density) + 0.5f).toInt() // переводим значение в пиксели, округляя до ближайшего целого числа
            textname.textSize=size1.toFloat()
            val size2 = (text_info_size_on_item.toFloat() * (resources.displayMetrics.density) + 0.5f).toInt() // переводим значение в пиксели, округляя до ближайшего целого числа
            textinfo.textSize=size2.toFloat()
            val size3 = (text_seil_size_on_item.toFloat() * (resources.displayMetrics.density) + 0.5f).toInt() // переводим значение в пиксели, округляя до ближайшего целого числа
            textsell.textSize=size3.toFloat()
            Downline.setBackgroundColor(Color.parseColor(background_down_on_ite))

            Log.d("Проверка дб" , "$size3" )






        }
        cursor.close()
        val cursor10 = db.rawQuery("SELECT * FROM favorite WHERE name=? AND full_info=?", arrayOf(name, info))
        if (cursor10.moveToFirst()) {
            // запись с указанным name и info существует
            imageOfLike.visibility = View.GONE
            imageOnLike.visibility = View.VISIBLE
        } else {
            // такой записи не найдено
            imageOfLike.visibility = View.VISIBLE
            imageOnLike.visibility = View.GONE
        }
        cursor10.close()

        db.close()
        for (item in itemsList) {
            if (item != "null") {
                itemClear.add(item)

            }
        }
        var kos: ImageView?
        var op = 0
        while (op != itemClear.size) {
                op++
                kos = findViewById(resources.getIdentifier("take$op", "id", packageName))

                kos?.visibility = View.VISIBLE

        }





        imagemain.loadImageFromUrl("https://untr.ru/Image/"+itemClear[0])
        var col=1
        var cor=0
        var j = 0
        var imageblack: ImageView?
        var imagewhite: ImageView?


        //Кнопка вперед
        val imageSwap=findViewById<ImageView>(R.id.imageView5)
        imageSwap.setOnClickListener{
            if (i < itemClear.size-1){ // изменение условия в if
                col+=1
                imageblack = findViewById(resources.getIdentifier("take$col", "id", packageName))
                imageblack?.setImageResource(R.drawable.greyshape)

                j=col-1
                imagewhite = findViewById(resources.getIdentifier("take$j", "id", packageName))
                imagewhite?.setImageResource(R.drawable.whiteshape)

                i = i + 1
                var imagemain1 = ImageLoader(image, this, itemsList[i])

                imagemain1.loadImageFromUrl("https://untr.ru/Image/"+itemClear[i])
            }
        }

//Кнопка назад
        val imageBack=findViewById<ImageView>(R.id.imageView6)
        imageBack.setOnClickListener{
            if (i > 0) { // изменение условия в if
                i = i - 1
                col-=1
                imageblack = findViewById(resources.getIdentifier("take$col", "id", packageName))
                imageblack?.setImageResource(R.drawable.greyshape)
                j=col+1
                imagewhite = findViewById(resources.getIdentifier("take$j", "id", packageName))
                imagewhite?.setImageResource(R.drawable.whiteshape)

                var imagemain2 = ImageLoader(image, this, itemsList[i])

                imagemain2.loadImageFromUrl("https://untr.ru/Image/"+itemClear[i])
            }
        }

        val imageHome = findViewById<ImageView>(R.id.imageView2)
        imageHome.setOnClickListener{
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

            //Кнопка звонок
        val imageCall=findViewById<ImageView>(R.id.imageView4)
        imageCall.setOnClickListener {
            val f="Название товара $name , Цена $price"
            val informationSender = InformationSender(this, f,"https://untr.ru/AndroidTakeInfo.php")
            informationSender.sendInformation()

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
                val phoneNumber = cursor2.getString(cursor2.getColumnIndex("phone_number"))

                val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                startActivity(dialIntent)
            }

            cursor2.close()
            db3.close()

        }
        val imageGoFavorites=findViewById<ImageView>(R.id.imageView3)
        imageGoFavorites.setOnClickListener {
            val intent=Intent(this,favourites::class.java)
            startActivity(intent)
        }



    }
}