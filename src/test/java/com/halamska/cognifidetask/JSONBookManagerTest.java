package com.halamska.cognifidetask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;


public class JSONBookManagerTest {




    @Test
    public void shouldSeparateBooks() {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            jsonArray.put(new JSONObject("{}"));
            jsonObject.put("kind", "book");
            jsonObject.put("totalItems", 401);
            jsonObject.put("items", jsonArray);
            Assert.assertEquals(JSONBookManager.getInstance().separateBooks(jsonObject), jsonArray) ;
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Test
    public void shouldGetIsbnByID() {
        JSONObject book = new JSONObject();
        JSONObject isbn = new JSONObject();
        JSONArray industryIdentifiers = new JSONArray();
        JSONObject volumeInfo = new JSONObject();

        try {
            isbn.put("type", "ISBN_10");
            isbn.put("identifier", "8324677615");
            industryIdentifiers.put(isbn);
            book.put("id", "UEdjAgAAQBAJ");
            volumeInfo.put("industryIdentifiers", industryIdentifiers);
            book.put("volumeInfo", volumeInfo);
            Assert.assertEquals("UEdjAgAAQBAJ", JSONBookManager.getIsbn(book));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}