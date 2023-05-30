package com.example.androidconstructer.Data
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.io.File

class ImageLoader(private val imageView: ImageView, private val context: Context, private val NameFile:String) {
    fun loadImageFromUrl(imageUrl: String?) {
        // Проверяем наличие сохраненного файла во внутреннем хранилище
        val file = File(context.filesDir, NameFile)
        if (file.exists()) {
            // Если файл существует, загружаем изображение из него
            val bitmap = BitmapFactory.decodeFile(file.path)
            imageView.setImageBitmap(bitmap)
        } else {
            // Если файл не существует, загружаем изображение из сети
            Picasso.get()
                .load(imageUrl)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(object : Target {
                    override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
                        // Отображение загруженного изображения в ImageView
                        imageView.setImageBitmap(bitmap)

                        // Сохранение загруженного изображения во внутреннем хранилище
                        try {
                            val outputStream = context.openFileOutput(NameFile, Context.MODE_PRIVATE)
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                            outputStream.close()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
                        // Обработка ошибки загрузки изображения
                        Log.d("загрузка килл", "загрузка килл.")
                        // You can handle the error case here, e.g., display a default error image or log an error message
                    }

                    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                        // Действия перед загрузкой изображения
                    }
                })
        }
    }
}
