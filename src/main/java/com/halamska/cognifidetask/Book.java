package com.halamska.cognifidetask;


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
class Book {

    private String isbn;

    private String title;

    private String subtitle;

    private String publisher;

    private String publishedDate;

    private String description;

    private int pageCount;

    private String thumbnailUrl;

    private String language;

    private String previewLink;

    private double averageRating;

    private List<String> authors;

    private List<String> categories;


    Book(JSONObject jsonBook) {

        try {
            this.isbn = JSONBookManager.getIsbn(jsonBook);

            JSONObject volumeInfo = jsonBook.getJSONObject("volumeInfo");

            this.title = volumeInfo.getString("title");

            if (volumeInfo.has("subtitle")) {
                this.subtitle = volumeInfo.getString("subtitle");
            }

            if (volumeInfo.has("publisher")) {
                this.publisher = volumeInfo.getString("publisher");
            }

            if (volumeInfo.has("publishedDate")) {
                this.publishedDate = volumeInfo.getString("publishedDate");
            }

            if (volumeInfo.has("description")) {
                this.description = volumeInfo.getString("description");
            }

            if (volumeInfo.has("pageCount")) {
                this.pageCount = volumeInfo.getInt("pageCount");
            }

            if (volumeInfo.has("imageLinks")) {
                this.thumbnailUrl = volumeInfo.getJSONObject("imageLinks").getString("thumbnail");
            }

            if (volumeInfo.has("language")) {
                this.language = volumeInfo.getString("language");
            }

            if (volumeInfo.has("previewLink")) {
                this.previewLink = volumeInfo.getString("previewLink");
            }

            if (volumeInfo.has("averageRating")) {
                this.averageRating = volumeInfo.getDouble("averageRating");
            }


            ObjectMapper mapper = new ObjectMapper();

            if (volumeInfo.has("authors")) {
                this.authors = mapper.readValue(volumeInfo.getString("authors"), List.class);
            }

            if (volumeInfo.has("categories")) {
                this.categories = mapper.readValue(volumeInfo.getString("categories"), List.class);
            }


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}
