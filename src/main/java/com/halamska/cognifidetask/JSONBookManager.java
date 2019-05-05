package com.halamska.cognifidetask;


import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JSONBookManager {


    static String getIsbn(JSONObject book) throws JSONException {

            JSONArray industryIdentifiers = book.getJSONObject("volumeInfo").getJSONArray("industryIdentifiers");
            if (industryIdentifiers != null) {
                for (int i = 0; i < industryIdentifiers.length(); i++) {
                    if (industryIdentifiers.getJSONObject(i).get("type") != null && industryIdentifiers.getJSONObject(i)
                            .getString("type")
                            .equals("ISBN_13")) {
                        return industryIdentifiers.getJSONObject(i).getString("identifier");
                    }
                }
                return getIsbnFromId(book);
            }
            return getIsbnFromId(book);
    }

    private static String getIsbnFromId(JSONObject book) throws JSONException {
        return book.getString("id");
    }

    JSONArray separateBooks(JSONObject response) throws JSONException {
        JSONArray jsonJSONArray;

            jsonJSONArray = response.getJSONArray("items");

        return jsonJSONArray;
    }


    String parseToJsonTemplate(Book book) {
        Gson gson = new Gson();
        return gson.toJson(book);
    }

    JSONObject parseToJsonTemplate(Map.Entry<String, Double> entry) throws JSONException {
        Author author = new Author(entry.getKey(), entry.getValue());
        Gson gson = new Gson();
        return new JSONObject(gson.toJson(author));
    }

    String parseToJsonTemplate(List<?> books) {
        Gson gson = new Gson();
        return gson.toJson(books);
    }
}
