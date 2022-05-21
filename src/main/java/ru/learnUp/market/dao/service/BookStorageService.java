package ru.learnUp.market.dao.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ru.learnUp.market.dao.entity.Book;
import ru.learnUp.market.dao.entity.BookStorage;
import ru.learnUp.market.dao.filters.BookStorageFilter;
import ru.learnUp.market.dao.repository.BookStorageRepository;
import ru.learnUp.market.dao.specification.BookStorageSpecification;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookStorageService {
    private final BookStorageRepository bookStorageRepository;

    public BookStorageService(BookStorageRepository bookStorageRepository) {
        this.bookStorageRepository = bookStorageRepository;
    }

    public BookStorage getStorageByBook(Book book) {
        return bookStorageRepository.getByBook(book);
    }
    public BookStorage createBookStorage(BookStorage bookStorage) {
        return bookStorageRepository.save(bookStorage);
    }

    public List<BookStorage> getBookStorage (){
        return bookStorageRepository.findAll();
    }

    public BookStorage getBookStorageId(Long id){
        return bookStorageRepository.getById(id);
    }

    @Transactional
//    @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public BookStorage update(BookStorage bookStorage) {
        bookStorageRepository.save(bookStorage);
        return bookStorage;
    }

    @Transactional
    public int buyBook(Book book, int booksCount) {
        BookStorage bookStorage = getStorageByBook(book);
        bookStorage.setBooksCount(bookStorage.getBooksCount() - booksCount);
        update(bookStorage);
        return bookStorage.getBooksCount();
    }

    @Transactional
    public int addBook(Book book, int booksCount) {
        BookStorage bookStorage = getStorageByBook(book);
        bookStorage.setBooksCount(bookStorage.getBooksCount() + booksCount);
        update(bookStorage);
        return bookStorage.getBooksCount();
    }

    public Boolean delete(Long id) {
        bookStorageRepository.delete(bookStorageRepository.getById(id));
        return true;
    }
    public List<BookStorage> getBooksStorageBy(BookStorageFilter bookStorageFilter){
        Specification<BookStorage> specification = Specification.where(
                BookStorageSpecification.useFilterBS(bookStorageFilter)
        );
        return bookStorageRepository.findAll(specification);
    }
}
