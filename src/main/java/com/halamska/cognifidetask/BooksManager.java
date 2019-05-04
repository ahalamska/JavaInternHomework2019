package com.halamska.cognifidetask;

import lombok.Data;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class BooksManager {

    private static BooksManager instance;

    private Map<String, Book> bookMap = new HashMap<>();

    public static synchronized BooksManager getInstance() {
        if (instance == null) {
            instance = new BooksManager();
        }
        return instance;
    }

    public void addBook(Book book) {
        this.bookMap.put(book.getIsbn(), book);
    }

    public void downloadBooks() {
        JSONBookManager.getInstance()
                .saveEveryBookInBookManager(JSONBookManager.getInstance()
                        .separateBooks(HttpRequestManager.downloadBooksData()));

    }



    public List<Book> getBooksByCategory(String category) {
        List<Book> books = new ArrayList<>();
        Iterator<Map.Entry<String, Book>> iterator = bookMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Book> entry = iterator.next();
            if (entry.getValue().getCategories()!= null && entry.getValue().getCategories().contains(category)) {
                books.add(entry.getValue());
            }
        }
        return books;
    }

    public List<JSONObject> getAuthorsRating() {
        Map<String,List<Double>> manyRatingsMap = getAuthorsWithRatingsList();
        Map<String, Double> singleRatingMAp = avgRating(manyRatingsMap);
        return sortAuthors(singleRatingMAp);
    }

    public List<JSONObject> sortAuthors(Map<String, Double> singleRatingMAp) {
        List<Map.Entry<String, Double>> collect = singleRatingMAp.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
        List<JSONObject> result = new ArrayList<>();
        for (Map.Entry<String, Double> author : collect){
            result.add(JSONBookManager.getInstance().parseToJsonTemplate(author));
        }
        return result;
    }

    public Map<String, Double> avgRating(Map<String, List<Double>> manyRatingsMap) {
        return manyRatingsMap.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue()
                                .stream()
                                .mapToDouble(Double::doubleValue)
                                .average().getAsDouble()));
    }






    public Map<String,List<Double>> getAuthorsWithRatingsList(){
        Map<String,List<Double>> authorMap = new HashMap<>();
        Iterator<Map.Entry<String, Book>> iterator = bookMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Book> entry = iterator.next();
            if(entry.getValue().getAverageRating() != 0){
                for (String author : entry.getValue().getAuthors()) {
                    if (authorMap.containsKey(author)) {
                        authorMap.get(author).add(entry.getValue().getAverageRating());
                    }
                    else{

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
