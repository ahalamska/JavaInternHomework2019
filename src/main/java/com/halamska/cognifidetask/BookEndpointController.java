package com.halamska.cognifidetask;


import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(BookEndpointController.BOOK_ENDPOINT)
public class BookEndpointController {

    public static final String BOOK_ENDPOINT = "/books";
    public static final String CATEGORY_ENDPOINT = "?category=";


    @GetMapping(path = "/{isbn}")
    public String getBookByIsbn(@PathVariable String isbn){
        if(BooksManager.getInstance().getBookMap().containsKey(isbn)) {
            Book book = BooksManager.getInstance().getBookMap().get(isbn);
            return JSONBookManager.getInstance().parseToJsonTemplate(book);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book not found"
            );
        }
    }


    @GetMapping()
    @ResponseBody
    public String getBooksByCategory(@RequestParam String category){
         return JSONBookManager.getInstance().parseToJsonTemplate(BooksManager.getInstance().getBooksByCategory(category));
    }
}
