package ru.learnUp.market.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.learnUp.market.dao.repository.entity.Author;
import ru.learnUp.market.dao.repository.entity.Book;
import ru.learnUp.market.dao.service.AuthorService;
import ru.learnUp.market.dao.service.BookService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/bookshop")
public class BooksShopController {

    private final ApplicationContext context;

    public BooksShopController(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/authors")
    public ModelAndView authors(Model model){
        AuthorService authorService = context.getBean(AuthorService.class);
        List<Author> authors = authorService.getAuthors();
        model.addAttribute("authors", authors);
        return new ModelAndView("authors");
    }

    @GetMapping("/books")
    public String books(Model model){

        BookService bookService = context.getBean(BookService.class);
        List<Book> books = bookService.getBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/home")
    public String home(Model model){
        return "home";
    }
}

