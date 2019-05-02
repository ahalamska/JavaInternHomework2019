package com.halamska.cognifidetask;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/book")
public class BookEndpointController {

    @GetMapping(path = "/{isbn}")
    public String getBookByIsbn(@PathVariable String isbn){
        if(BooksManager.getInstance().getBookMap().containsKey(isbn)) {
            Book book = BooksManager.getInstance().getBookMap().get(isbn);
            return BooksManager.getInstance().parseToJsonTemplate(book);
        }
        else{
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Book not found"
            );
        }
    }
}
