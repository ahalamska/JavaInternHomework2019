package com.halamska.cognifidetask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CognifideTaskApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class BookEndpointControllerTest {



    @Test
    public void shouldGetTitleByIsbn() {

        String isbn = "N1IiAQAAIAAJ";
        when().
                get("/books/N1IiAQAAIAAJ").
                then().
                body("title", equalTo(BooksManager.getInstance().getBookMap().get(isbn)));
    }

    @Test
    public void shouldReturnHttpError404() {

        String isbn = "N1IiAQAAIAAJ";
        when().
                get("/books/00").
                then().statusCode(404);
    }

    @Test
    public void getBooksByCategory(){
        when().
                get("/books/?category=Computers").
                then().statusCode(200);
    }

    @Test
    public void getAuthorRating() {
        when().
                get("/books/authors-rating").
                then().statusCode(200);
    }
}