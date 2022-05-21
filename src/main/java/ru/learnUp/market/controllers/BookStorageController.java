package ru.learnUp.market.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.market.dao.entity.BookStorage;
import ru.learnUp.market.dao.filters.BookStorageFilter;
import ru.learnUp.market.dao.service.BookStorageService;
import ru.learnUp.market.mapperForView.MapperForBookStorage;
import ru.learnUp.market.view.*;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/book_storage")
public class BookStorageController {

    private final BookStorageService bookStorageService;
    private final MapperForBookStorage mapper;

    public BookStorageController(BookStorageService bookStorageService, MapperForBookStorage mapper) {
        this.bookStorageService = bookStorageService;
        this.mapper = mapper;
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<BookStorageView> getBookStorage(
            @RequestParam(value = "name", required = false) String name
    ) {
        return bookStorageService.getBooksStorageBy(new BookStorageFilter(name))
                .stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/{storageId}")
    public BookStorageView getBookStorage(@PathVariable("storageId") Long storageId) {
        return mapper.mapToView(bookStorageService.getBookStorageId(storageId));
    }

    @Secured({"ROlE_ADMIN"})
    @PostMapping
    public BookStorageView createBookStorage(@RequestBody BookStorageView body) {
        if (body.getBookStorageId() != null) {
            throw new EntityExistsException(
                    String.format("BookStorage with id = %s already exist", body.getBookStorageId())
            );
        }
        BookStorage bookStorage = mapper.mapFromView(body);
        BookStorage createdBookStorage = bookStorageService.createBookStorage(bookStorage);
        return mapper.mapToView(createdBookStorage);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/{storageId}")
    public BookStorageView updateBookStorage(
            @PathVariable("storageId") Long storageId,
            @RequestBody ViewBookStorageFor body
    ) {
        if (!Objects.equals(storageId, body.getBookStorageId())) {
            throw new RuntimeException("Entity has bad id");
        }

        BookStorage bookStorage = bookStorageService.getBookStorageId(storageId);

        if (bookStorage.getBooksCount() != body.getBooksCount()) {
            bookStorage.setBooksCount(body.getBooksCount());
        }
        BookStorage updated = bookStorageService.update(bookStorage);
        return mapper.mapToView(updated);

    }


    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/{orderId}")
    public Boolean deleteBooksOrder(@PathVariable("orderId") Long id) {
        return bookStorageService.delete(id);
    }
}

