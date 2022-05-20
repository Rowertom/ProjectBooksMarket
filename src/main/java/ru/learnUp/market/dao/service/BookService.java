package ru.learnUp.market.dao.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.learnUp.market.dao.filters.BookFilter;
import ru.learnUp.market.dao.entity.Book;
import ru.learnUp.market.dao.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.learnUp.market.dao.specification.BookSpecification.useFilter;

@Slf4j
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

    public Boolean deleteBook(Long id) {
        bookRepository.delete(bookRepository.getById(id));
        return true;
    }

    public List<Book> getBooksBy(BookFilter bookFilter){
        Specification<Book> specification = Specification.where(useFilter(bookFilter));
        return bookRepository.findAll(specification);
    }

    public Book getBookId(Long id){
        return bookRepository.getById(id);
    }

    @Transactional
    public Book update(Book book){
        return bookRepository.save(book);
    }
}
