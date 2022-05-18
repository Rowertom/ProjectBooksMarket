package ru.learnUp.market.dao.service;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import ru.learnUp.market.dao.entity.Book;
import ru.learnUp.market.dao.entity.BookStorage;
import ru.learnUp.market.dao.repository.BookStorageRepository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookStorageService {
    private final BookStorageRepository bookStorageRepository;

    public BookStorageService(BookStorageRepository bookStorageRepository) {
        this.bookStorageRepository = bookStorageRepository;
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

    @Lock(value = LockModeType.READ)
    public BookStorage getStorageByBook(Book book) {
        return bookStorageRepository.getByBook(book);
    }

    @Transactional
//    @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public void update(BookStorage bookStorage) {
        bookStorageRepository.save(bookStorage);
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
}
