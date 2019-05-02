package com.halamska.cognifidetask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class HttpRequestMenager {



    public static JSONObject downloadBooksData(){

        HttpURLConnection connection = null;
        JSONObject response = new JSONObject();
        try {
            URL books = new URL("https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40");
            connection = (HttpURLConnection) books.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder builder = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                builder.append(inputLine);
                builder.append('\r');
            }
            in.close();
            response = new JSONObject(builder.toString()) ;
        }
        catch (IOException | JSONException e) {
            e.printStackTrace();
        }


        return response;
    }




}


