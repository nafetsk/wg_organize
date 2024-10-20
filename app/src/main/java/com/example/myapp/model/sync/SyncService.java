package com.example.myapp.model.sync;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.myapp.model.database.Aufgaben;
import com.example.myapp.model.database.Mitbewohni;
import com.google.gson.Gson;

public class SyncService {

    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void makePostRequest(String apiUrl, String data, Callback callback) {
        executorService.execute(() -> {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                conn.getOutputStream().write(data.getBytes(StandardCharsets.UTF_8));

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    callback.onSuccess(response.toString());
                } else {
                    callback.onError(new Exception("Fehler: " + responseCode));
                }
            } catch (Exception e) {
                callback.onError(e);
            }
        });
    }

    public static void createMitbewohni(Mitbewohni mitbewohni, Callback callback) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(mitbewohni);

        makePostRequest("http://10.0.2.2:8000/create_mitbewohni/", jsonString, callback);
    }

    public static void createAufgabe(Aufgaben aufgabe, Callback callback) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(aufgabe);

        makePostRequest("http://10.0.2.2:8000/create_aufgabe/", jsonString, callback);
    }



    public interface Callback {
        void onSuccess(String result);

        void onError(Exception e);
    }
}
