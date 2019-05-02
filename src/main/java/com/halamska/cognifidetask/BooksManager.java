package com.halamska.cognifidetask;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import lombok.Data;
import org.json.JSONArray;

import java.util.*;

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
        System.out.println("Added " + this.bookMap.size());
    }

    public void downloadBooks() {
        JSONBookManager.getInstance()
                .saveEveryBookInBookManager(JSONBookManager.getInstance()
                        .separateBooks(HttpRequestMenager.downloadBooksData()));

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

}
