package ru.learnUp.market.dao.service;

import org.springframework.stereotype.Service;
import ru.learnUp.market.dao.entity.Author;
import ru.learnUp.market.dao.entity.Book;
import ru.learnUp.market.dao.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks (){
        return bookRepository.findAll();
    }

    public Book getBookId(Long id){
        return bookRepository.getById(id);
    }
}
