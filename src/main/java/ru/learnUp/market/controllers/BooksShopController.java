//package ru.learnUp.market.controllers;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.ModelAndView;
//import ru.learnUp.market.dao.entity.Author;
//import ru.learnUp.market.dao.entity.Book;
//import ru.learnUp.market.dao.service.AuthorService;
//import ru.learnUp.market.dao.service.BookService;
//
//import java.util.List;
//
//@Slf4j
//@Controller
//@RequestMapping("/bookshop")
//public class BooksShopController {
//
//    private final BookService bookService;
//    private final AuthorService authorService;
//
//    public BooksShopController(BookService bookService, AuthorService authorService) {
//        this.bookService = bookService;
//        this.authorService = authorService;
//    }
//
//
//    @GetMapping("/authors")
//    public ModelAndView authors(Model model){
//        List<Author> authors = authorService.getAuthors();
//        model.addAttribute("authors", authors);
//        return new ModelAndView("authors");
//    }
//
//    @GetMapping("/books")
//    public String books(Model model){
//
//        List<Book> books = bookService.getBooks();
//        model.addAttribute("books", books);
//        return "books";
//    }
//
//    @GetMapping("/home")
//    public String home(Model model){
//        return "home";
//    }
//}
//
