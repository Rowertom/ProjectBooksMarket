package ru.learnUp.market.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.market.dao.filters.BookFilter;
import ru.learnUp.market.dao.entity.Book;
import ru.learnUp.market.dao.service.BookService;
import ru.learnUp.market.mapperForView.MapperForBookView;
import ru.learnUp.market.view.BookView;
import ru.learnUp.market.view.ViewBookFor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/books")
public class BookController {

    private final BookService bookService;
    private final MapperForBookView mapper;

    public BookController(BookService bookService, MapperForBookView mapper) {
        this.bookService = bookService;
        this.mapper = mapper;
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public List<BookView> getBooks(@RequestParam(value = "name", required = false) String name){
        return bookService.getBooksBy(new BookFilter(name))
                .stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{bookId}")
    public BookView getBook(@PathVariable("bookId") Long bookId) {
        return mapper.mapToView(bookService.getBookId(bookId));
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public BookView createBook(@RequestBody BookView body) {
        Book book = mapper.mapFromView(body);
        Book createdBook = bookService.createBook(book);
        return mapper.mapToView(createdBook);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/{bookId}")
    public BookView updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestBody ViewBookFor body
    ) {
        if (!Objects.equals(bookId, body.getBookId())) {
            throw new RuntimeException("Entity has bad id");
        }

        Book book = bookService.getBookId(bookId);

        if (!book.getBookName().equals(body.getBookName())) {
            book.setBookName(body.getBookName());
        }

        if (book.getPrice() != body.getPrice()) {
            book.setPrice(body.getPrice());
        }

        Book updated = bookService.update(book);

        return mapper.mapToView(updated);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{bookId}")
    public Boolean deleteBook(@PathVariable("bookId") Long id) {
        return bookService.deleteBook(id);
    }
}
