
package com.halamska.cognifidetask;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(BookEndpointController.BOOK_ENDPOINT)
@RequiredArgsConstructor
public class BookEndpointController {

    public static final String BOOK_ENDPOINT = "/books";
    public static final String AUTHORS_ENDPOINT = "/authors-rating";
    private final JSONBookManager jsonBookManager;

    @GetMapping(path = "/{isbn}")
    @ResponseBody
    public String getBookByIsbn(@PathVariable String isbn) {
        if (BooksManager.getInstance().getBookMap().containsKey(isbn)) {
            Book book = BooksManager.getInstance().getBookMap().get(isbn);
            return jsonBookManager.parseToJsonTemplate(book);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @GetMapping()
    @ResponseBody
    public String getBooksByCategory(@RequestParam String category) {
        return jsonBookManager.parseToJsonTemplate(BooksManager.getInstance().getBooksByCategory(category));
    }

    @GetMapping(AUTHORS_ENDPOINT)
    @ResponseBody
    public String getAuthorRating() {
        return jsonBookManager.parseToJsonTemplate(BooksManager.getInstance().getAuthorsRating());
    }

}
