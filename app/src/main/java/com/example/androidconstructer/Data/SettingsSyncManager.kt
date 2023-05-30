import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class SettingsSyncManager(private val database: SQLiteDatabase) {
    private val client = OkHttpClient()

    fun syncSettings() {
        val url = "https:http://klipryid.beget.tech/Android.php"
        val request = Request.Builder()
            .url(url)
            .post(FormBody.Builder().build())
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Обработка ошибок при запросе данных с сервера
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) {
                        return
                    }

                    val responseBody = response.peekBody(Long.MAX_VALUE)
                    val json = JSONObject(responseBody.string())
                    val remoteSettings = mapRemoteSettings(json)

                    database.beginTransaction()
                    try {
                        val localSettings = loadLocalSettings()

                        if (localSettings != remoteSettings) {
                            saveRemoteSettings(remoteSettings)
                        }

                        database.setTransactionSuccessful()
                    } finally {
                        database.endTransaction()
                    }
                }
            }
        })
    }

    private fun loadLocalSettings(): Map<String, String> {
        val cursor = database.rawQuery("SELECT * FROM Setting", null)
        cursor.use {
            if (it.moveToFirst()) {
                val settings = mutableMapOf<String, String>()
                do {
                    val key = it.getString(it.getColumnIndexOrThrow("id"))
                    val value = it.getString(it.getColumnIndexOrThrow("value"))
                    settings[key] = value
                } while (it.moveToNext())
                return settings
            }
        }
        return emptyMap()
    }

    private fun saveRemoteSettings(settings: Map<String, String>) {
        database.delete("Setting", null, null)

        settings.forEach { (key, value) ->
            val values = ContentValues().apply {
                put("key", key)
                put("value", value)
            }
            database.insert("Setting", null, values)
        }
    }

    private fun mapRemoteSettings(json: JSONObject): Map<String, String> {
        return mapOf(
            "main_activity_background_color" to json.getString("main_activity_background_color"),
            "category_button_color" to json.getString("category_button_color"),
            "product_list_item_background_color" to json.getString("product_list_item_background_color"),
            "app_name" to json.getString("app_name"),
            "user_agreement" to json.getString("user_agreement"),
            "phone_number" to json.getString("phone_number"),
            "product_card_background_color" to json.getString("product_card_background_color"),
            "product_card_text_color" to json.getString("product_card_text_color"),
            "app_name_text_color" to json.getString("app_name_text_color"),
            "logo_location" to json.getString("logo_location")
        )
    }
}

