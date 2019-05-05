package com.halamska.cognifidetask;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(BookEndpointController.BOOK_ENDPOINT)
@RequiredArgsConstructor
public class BookEndpointController {

    static final String BOOK_ENDPOINT = "/books";
    private static final String AUTHORS_ENDPOINT = "/authors-rating";
    private final JSONBookManager jsonBookManager;
    private final BooksManager booksManager;


    @GetMapping(path = "/{isbn}")
    @ResponseBody
    public String getBookByIsbn(@PathVariable String isbn) {
        if (booksManager.getBookMap().containsKey(isbn)) {
            Book book = booksManager.getBookMap().get(isbn);
            return jsonBookManager.parseToJsonTemplate(book);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @GetMapping()
    @ResponseBody
    public String getBooksByCategory(@RequestParam String category) {
        return jsonBookManager.parseToJsonTemplate(booksManager.getBooksByCategory(category));
    }

    @GetMapping(AUTHORS_ENDPOINT)
    @ResponseBody
    public String getAuthorRating() {
        return jsonBookManager.parseToJsonTemplate(booksManager.getAuthorsRating());
    }

}
