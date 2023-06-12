package com.example.androidconstructer.Data


import android.content.ContentValues
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

class BattonCategory4(private val context: Context, private val logoValue: String) : AsyncTask<Void, Void, Boolean>() {
    private var listener: (() -> Unit)? = null
    private val TAG = "UpdateCategory4"

    override fun doInBackground(vararg params: Void?): Boolean {
        if (!isNetworkConnected()) {
            Log.e(TAG, "No Internet connection")
            return false
        }

        try {
            // Отправляем POST-запрос на сервер с параметром logo_location
            // Получаем ответ от сервера в виде JSON
            val url = URL("https://untr.ru/AndroidCategoryButton.php")
            val connection = url.openConnection() as HttpURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true
            val writer = OutputStreamWriter(connection.outputStream)
            writer.write("arb=FourCategory") // Здесь мы отправляем параметр column_name со значением "
            writer.flush()

            val reader = BufferedReader(InputStreamReader(connection.inputStream))
            val response = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                response.append(line)
            }
            reader.close()

            val jsonResponse = try {
                JSONArray(response.toString()).getJSONObject(0)
            } catch (e: JSONException) {
                Log.e(TAG, "Error parsing JSON response", e)
                return false
            }

            val logoLocation = try {
                jsonResponse.getString("FourCategory")
            } catch (e: JSONException) {
                Log.e(TAG, "Error getting logo_location from JSON response", e)
                return false
            }

            if (logoLocation.isEmpty()) {
                Log.e(TAG, "Empty logo_location value in JSON response")
                return false
            }

            // Обновляем значение столбца logo в таблице Setting в локальной базе данных
            val dbHelper = DBHelper(context)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("FourCategory", logoLocation)
            }
            val rowsAffected = db.update(DBHelper.CATEGORY_BUTTON, values, null, null)
            if (rowsAffected == 1) {
                Log.e(TAG, "Failed to update category_button_color in local database")
                return false
            }

            return true

        } catch (e: IOException) {
            Log.e(TAG, "Error updating logo", e)
            return false
        }
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}
