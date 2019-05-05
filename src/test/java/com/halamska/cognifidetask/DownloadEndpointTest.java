package com.halamska.cognifidetask;

import org.junit.Test;

import static io.restassured.RestAssured.when;

public class DownloadEndpointTest {

    @Test
    public void shouldReturnHTTPResponseStatusCode200() {

        when().
                get("https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40").
                then().
                statusCode(200);

    }

}