package com.example.androidconstructer.Data;
// TRaceRP.java

// TRaceRP.java

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TraceRP {

    private static final String TAG = "TRaceRP";

    // URL для отправки POST-запроса
    private static final String URL = "https://example.com/api/update-logo";

    // Имя столбца в таблице
    private static final String COLUMN_NAME = "logo_location";

    // ID записи, которую нужно обновить
    private static final int ROW_ID = 1;

    // Класс для выполнения асинхронной задачи
    private static class UpdateLogoTask extends AsyncTask<String, Void, String> {

        private final Context context;

        public UpdateLogoTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                // Создаем JSON-объект с данными
                JSONObject postData = new JSONObject();
                postData.put(COLUMN_NAME, params[0]);

                // Создаем соединение
                URL url = new URL(URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Отправляем данные в виде JSON-строки
                String postDataString = postData.toString();
                connection.getOutputStream().write(postDataString.getBytes("UTF-8"));

                // Получаем ответ
                int responseCode = connection.getResponseCode();
                StringBuilder response = new StringBuilder();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                }
                return response.toString();
            } catch (Exception e) {
                Log.e(TAG, "Error sending POST request: " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    // Получаем данные из JSON-объекта
                    JSONObject jsonResult = new JSONObject(result);
                    String logoLocation = jsonResult.getString(COLUMN_NAME);

                    // Обновляем данные в таблице SQLite
                    SQLiteDatabase db = DBHelper.getInstance(context).getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put(COLUMN_NAME, logoLocation);
                    db.update("Setting", values, "id=?", new String[]{String.valueOf(ROW_ID)});
                } catch (JSONException e) {
                    Log.e(TAG, "Error parsing JSON response: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "POST request failed");
            }
        }
    }

    public static void updateLogoLocation(Context context, String logoLocation) {
        // Выполняем асинхронную задачу для отправки POST-запроса и обновления данных в таблице SQLite
        UpdateLogoTask task = new UpdateLogoTask(context);
        task.execute(logoLocation);
    }
}
