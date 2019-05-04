package com.halamska.cognifidetask;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooksManagerTest {


    Book book1 = new Book();


    Book book2 = new Book();

    @Before
    public void setUp(){
        List<String> authors = new ArrayList<>();
        authors.add("author1");
        book1.setIsbn("1");
        book1.setAuthors(authors);
        book1.setAverageRating(3.0);
        book2.setIsbn("2");
        book2.setAuthors(authors);
        book2.setAverageRating(2.0);
        BooksManager.getInstance().addBook(book1);
        BooksManager.getInstance().addBook(book2);
    }


    @Test
    public void shouldGetBooksByCategory() {
        Book book = new Book();
        String category = "Computers";
        List<String> categories = new ArrayList<>();
        categories.add(category);
        book.setIsbn("1");
        book.setCategories(categories);
        BooksManager.getInstance().addBook(book);
        List<Book> books = new ArrayList<>();
        books.add(book);
        Assert.assertEquals(books, BooksManager.getInstance().getBooksByCategory(category));

    }

    @Test
    public void shouldCountAvgRating() {
        List<Double> ratingList = new ArrayList<>();
        Map<String, List<Double>> manyRatingsMap = new HashMap<>();
        ratingList.add(2.0);
        ratingList.add(3.0);
        ratingList.add(5.0);
        ratingList.add(3.0);
        ratingList.add(2.0);

        manyRatingsMap.put("author1", ratingList);
        Assert.assertEquals("3.0", BooksManager.getInstance().avgRating(manyRatingsMap).get("author1").toString());
    }

    @Test
    public void shouldGetAuthorsWithRatingsList() {
        Map<String,List<Double>> authorMap = new HashMap<>();
        List<Double> ratingList = new ArrayList<>();
        ratingList.add(3.0);
        ratingList.add(2.0);
        authorMap.put("author1", ratingList);
        Assert.assertEquals(authorMap, BooksManager.getInstance().getAuthorsWithRatingsList());
    }
}