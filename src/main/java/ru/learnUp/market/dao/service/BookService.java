package ru.learnUp.market.dao.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.learnUp.market.dao.repository.entity.Book;
import ru.learnUp.market.dao.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks (){
        return bookRepository.findAll();
    }

    public Book getBookId(Long id){
        return bookRepository.getById(id);
    }

    @Transactional
    @CacheEvict(value = "book", key = "#book.bookId")
    public void update(Book book){
        bookRepository.save(book);
    }
}
