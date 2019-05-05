package com.halamska.cognifidetask.bootstrap;

import com.halamska.cognifidetask.BooksManager;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BooksManager booksManager;


    @Autowired
    public DevBootstrap(BooksManager booksManager) {
        this.booksManager = booksManager;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        initData();

    }

    private void initData() {
        try {
            booksManager.downloadBooks();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
