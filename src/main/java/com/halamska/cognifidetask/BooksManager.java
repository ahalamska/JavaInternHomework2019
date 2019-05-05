package com.halamska.cognifidetask;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Service
@RequiredArgsConstructor

public class BooksManager {


    private final RestTemplate restTemplate = new RestTemplate();
    private final JSONBookManager jsonBookManager;
    private Map<String, Book> bookMap = new HashMap<>();

    void addBook(Book book) {
        this.bookMap.put(book.getIsbn(), book);
    }


    private void saveEveryBookInBookManager(JSONArray books) throws JSONException {
        for (int i = 0; i < books.length(); i++) {
            Book book = new Book(books.getJSONObject(i));
            addBook(book);

        }

    }

    public void downloadBooks() throws JSONException {

        JSONObject response = new JSONObject(restTemplate.getForObject("https://www.googleapis" + ".com/books/v1" +
                "/volumes?q=java&maxResults=40", String.class));
        saveEveryBookInBookManager(jsonBookManager.separateBooks(response));

    }


    List<Book> getBooksByCategory(String category) {
        List<Book> books = new ArrayList<>();
        for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
            if (entry.getValue().getCategories() != null && entry.getValue().getCategories().contains(category)) {
                books.add(entry.getValue());
            }
        }
        return books;
    }

    List<Author> getAuthorsRating() {
        Map<String, List<Double>> manyRatingsMap = getAuthorsWithRatingsList();
        Map<String, Double> singleRatingMAp = avgRating(manyRatingsMap);
        return sortAuthors(singleRatingMAp);
    }

    private List<Author> sortAuthors(Map<String, Double> singleRatingMAp) {
        List<Map.Entry<String, Double>> collect = singleRatingMAp.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        List<Author> result = new ArrayList<>();
        for (Map.Entry<String, Double> author : collect) {
            result.add(new Author(author.getKey(), author.getValue()));
        }
        return result;
    }

    Map<String, Double> avgRating(Map<String, List<Double>> manyRatingsMap) {
        return manyRatingsMap.entrySet()
                .stream()
                .collect(Collectors.toConcurrentMap(Map.Entry::getKey, entry -> entry.getValue()
                        .stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .getAsDouble()));
    }


    Map<String, List<Double>> getAuthorsWithRatingsList() {
        Map<String, List<Double>> authorMap = new HashMap<>();
        for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
            if (entry.getValue().getAverageRating() != 0 && entry.getValue().getAuthors() != null) {
                for (String author : entry.getValue().getAuthors()) {
                    if (authorMap.containsKey(author)) {
                        authorMap.get(author).add(entry.getValue().getAverageRating());
                    } else {

                        List<Double> array = new ArrayList<>();
                        array.add(entry.getValue().getAverageRating());
                        authorMap.put(author, array);
                    }
                }
            }
        }
        return authorMap;
    }


}