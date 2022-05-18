package ru.learnUp.market;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@EnableCaching
public class BooksShopApplication {

//    private static final Logger log = LoggerFactory.getLogger(BooksShopApplication.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(BooksShopApplication.class, args);

        Gson gson = new Gson();
//        AuthorService authorService = context.getBean(AuthorService.class);
//        BookService bookService = context.getBean(BookService.class);
//        BookStorageService bookStorageService = context.getBean((BookStorageService.class));

//        updateAsync(bookStorageService, bookService.getBookId(1L));
////        log.info("book storages: {}", bookStorageService.getBookStorage());
//        log.info("All about Author {}", authorService.getAuthorId(1L));
//        log.info("Remainder of books = {}", bookStorageService.addBook(bookService.getBookId(1L), 2));
//        log.info("Remainder of books = {}", bookStorageService.buyBook(bookService.getBookId(2L), 2));
    }
//        static void updateAsync(BookStorageService bookStorageService, Book book){
//
//            for (int i = 0; i < 5; i++) {
//                new Thread(() -> {
//                    try {
//                        bookStorageService.buyBook(book, 1);
//                        log.info("You bought this book!");
//                    } catch (ObjectOptimisticLockingFailureException | StaleStateException e) {
//                        log.warn("Sorry, you can't buy this book... try again later");
//                    }
//                }).start();
//            }
//        }
    }


