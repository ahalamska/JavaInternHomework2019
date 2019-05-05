package com.halamska.cognifidetask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;


public class JSONBookManagerTest {

    private JSONBookManager subject = new JSONBookManager();

    @Test
    public void shouldSeparateBooks() throws JSONException {

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(new JSONObject("{}"));
        jsonObject.put("kind", "book");
        jsonObject.put("totalItems", 401);
        jsonObject.put("items", jsonArray);
        Assert.assertEquals(subject.separateBooks(jsonObject), jsonArray);
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