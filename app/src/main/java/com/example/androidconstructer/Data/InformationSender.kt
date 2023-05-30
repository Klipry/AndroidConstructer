package com.example.androidconstructer.Data
import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import java.text.SimpleDateFormat
import java.util.*

class InformationSender(private val context: Context, private val information: String, private val serverUrl: String) {

    fun sendInformation() {
        // Проверяем наличие интернет-соединения
        if (!isNetworkAvailable()) {
            println("No internet connection available.")
            return
        }

        // Получаем текущее время на устройстве пользователя
        val currentTime = getCurrentTime()

        // Создаем параметры POST-запроса
        val postData = hashMapOf(
            "Information" to information,
            "Date" to currentTime
        )

        // Запускаем корутину для отправки данных на сервер
        CoroutineScope(Dispatchers.IO).launch {
            // Создаем объект HttpURLConnection и настраиваем его
            val url = URL(serverUrl)
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.doOutput = true

            // Кодируем параметры POST-запроса в виде строки
            val postDataString = StringBuilder()
            for ((key, value) in postData) {
                if (postDataString.isNotEmpty()) {
                    postDataString.append("&")
                }
                postDataString.append(URLEncoder.encode(key, "UTF-8"))
                postDataString.append("=")
                postDataString.append(URLEncoder.encode(value, "UTF-8"))
            }

            // Отправляем POST-запрос на сервер
            val outputStream = httpURLConnection.outputStream
            outputStream.write(postDataString.toString().toByteArray(Charsets.UTF_8))
            outputStream.flush()
            outputStream.close()

            // Проверяем код ответа сервера
            val responseCode = httpURLConnection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                println("Information sent successfully!")
            } else {
                println("Error sending information. Response code: $responseCode")
            }

            // Закрываем соединение
            httpURLConnection.disconnect()
        }
    }

    // Вспомогательная функция для получения текущего времени на устройстве пользователя
    private fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date())
    }

    // Вспомогательная функция для проверки наличия интернет-соединения
    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
