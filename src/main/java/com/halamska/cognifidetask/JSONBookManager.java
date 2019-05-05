package com.halamska.cognifidetask;


import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JSONBookManager {

    public static JSONBookManager instance;



    public static synchronized JSONBookManager getInstance() {
        if (instance == null){
            instance = new JSONBookManager();
        }
        return instance;
    }

    public JSONArray separateBooks(JSONObject response){
        JSONArray jsonJSONArray = new JSONArray();
        try {
            jsonJSONArray = response.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonJSONArray;
    }

    public void saveEveryBookInBookManager(JSONArray books){
        for(int i = 0; i < books.length(); i++){

            try {
                Book book = new Book(books.getJSONObject(i));
                BooksManager.getInstance().addBook(book);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public static String getIsbn(JSONObject book){
        try {
            JSONArray industryIdentifiers = book.getJSONObject("volumeInfo").getJSONArray("industryIdentifiers");
            if(industryIdentifiers != null){
                for(int i = 0; i < industryIdentifiers.length(); i++){
                    if(industryIdentifiers.getJSONObject(i).get("type") != null && industryIdentifiers.getJSONObject(i)
                            .getString("type")
                            .equals("ISBN_13")) {
                        return industryIdentifiers.getJSONObject(i).getString("identifier");
                    }
                }
                return getIsbnFromId(book);
            }
            return getIsbnFromId(book);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getIsbnFromId(JSONObject book) {
        try {
            return book.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String parseToJsonTemplate(Book book) {
        Gson gson = new Gson();
        return gson.toJson(book);
    }
/*    public JSONObject parseToJsonTemplate(Map.Entry<String, Double>  entry) throws JSONException {
        Author author = new Author(entry.getKey(),entry.getValue());
        Gson gson = new Gson();
        return new JSONObject(gson.toJson(author));
    }*/

    public String parseToJsonTemplate(List<? extends Object> books) {
        Gson gson = new Gson();
        return gson.toJson(books);
    }

    public String parseToJsonTemplate(JSONArray jsonArray) {
        Gson gson = new Gson();
        return gson.toJson(jsonArray);
    }
}
