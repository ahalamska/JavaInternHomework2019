package com.halamska.cognifidetask;

import lombok.Data;

import java.util.*;

@Data
public class BooksManager {

    private static BooksManager instance;

    private Map<String, Book> bookMap = new HashMap<String, Book>() ;


    public static synchronized BooksManager getInstance() {
        if (instance == null){
            instance = new BooksManager();
        }
        return instance;
    }

    public void addBook(Book book){
        this.bookMap.put(book.getIsbn(), book);
        System.out.println("Added " + this.bookMap.size());
    }

    public void downloadBooks(){
        JSONBookManager.getInstance().saveEveryBookInBookManager(
                JSONBookManager.getInstance().separateBooks(
                        HttpRequestMenager.downloadBooksData()));

    }


}
