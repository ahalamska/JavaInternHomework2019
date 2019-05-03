package com.halamska.cognifidetask;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Book {

    private String isbn;

    private String title;

    private String subtitle;

    private String publisher;

    private String publishedDate;

    private String description;

    private int pageCount;

    private String thumbnailUrl;

    private String language;

    private  String previewLink;

    private  double averageRating;

    private List<String> authors;

    private List<String> categories;

    public Book(JSONObject jsonBook) {

        this.isbn  =  JSONBookManager.getIsbn(jsonBook);

        try {
            JSONObject volumeInfo = jsonBook.getJSONObject("volumeInfo");

            this.title = volumeInfo.getString("title");

            if(volumeInfo.has("subtitle")) {
                this.subtitle = volumeInfo.getString("subtitle");
            }

            this.publisher = volumeInfo.getString("publisher");

            this.publishedDate = volumeInfo.getString("publishedDate");

            this.description = volumeInfo.getString("description");

            this.pageCount = volumeInfo.getInt("pageCount");

            this.thumbnailUrl = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");

            this.language = volumeInfo.getString("language");

            this.previewLink = volumeInfo.getString("previewLink");

            if(volumeInfo.has("averageRating")) {
                this.averageRating = volumeInfo.getDouble("averageRating");
            }


            ObjectMapper mapper = new ObjectMapper();

            this.authors = mapper.readValue(volumeInfo.getString("authors"), List.class);

            this.categories = mapper.readValue(volumeInfo.getString("categories"), List.class);



        } catch (JSONException e) {

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public JSONObject parseJSON(){

    }
*/

}
