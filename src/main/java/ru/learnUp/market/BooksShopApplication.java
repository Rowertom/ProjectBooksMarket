package ru.learnUp.market;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnUp.market.dao.repository.AuthorRepository;
import ru.learnUp.market.dao.service.AuthorService;
import ru.learnUp.market.dao.service.BookService;
import ru.learnUp.market.dao.service.BookStorageService;
import ru.learnUp.market.dao.service.CustomerService;

@SpringBootApplication
@EnableCaching
public class BooksShopApplication {

    private static final Logger log = LoggerFactory.getLogger(BooksShopApplication.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BooksShopApplication.class, args);

        AuthorService authorService = context.getBean(AuthorService.class);
        BookService bookService = context.getBean(BookService.class);
        BookStorageService bookStorageService = context.getBean((BookStorageService.class));


        log.info("book storages: {}", bookStorageService.getBookStorage());
        log.info("All about Author {}", authorService.getAuthorId(1L));
        log.info("Remainder of books = {}", bookStorageService.addBook(bookService.getBookId(1L), 2));
        log.info("Remainder of books = {}", bookStorageService.buyBook(bookService.getBookId(2L), 2));
    }
}

