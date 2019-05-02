package com.halamska.cognifidetask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CognifideTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(CognifideTaskApplication.class, args);
        BooksManager.getInstance().downloadBooks();
        System.out.println(BooksManager.getInstance().getBookMap());
    }

}
