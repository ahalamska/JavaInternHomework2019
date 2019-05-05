package com.halamska.cognifidetask;

import lombok.RequiredArgsConstructor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BooksManagerTest {


    private Book book1 = new Book();
    private Book book2 = new Book();



    private BooksManager booksManager = new BooksManager(new JSONBookManager());

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
        booksManager.addBook(book1);
        booksManager.addBook(book2);
    }


    @Test
    public void shouldGetBooksByCategory() {
        Book book = new Book();
        String category = "Computers";
        List<String> categories = new ArrayList<>();
        categories.add(category);
        book.setIsbn("1");
        book.setCategories(categories);
        booksManager.addBook(book);
        List<Book> books = new ArrayList<>();
        books.add(book);
        Assert.assertEquals(books, booksManager.getBooksByCategory(category));

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
        Assert.assertEquals("3.0", booksManager.avgRating(manyRatingsMap).get("author1").toString());
    }

    @Test
    public void shouldGetAuthorsWithRatingsList() {
        Map<String,List<Double>> authorMap = new HashMap<>();
        List<Double> ratingList = new ArrayList<>();
        ratingList.add(3.0);
        ratingList.add(2.0);
        authorMap.put("author1", ratingList);
        Assert.assertEquals(authorMap, booksManager.getAuthorsWithRatingsList());
    }
}