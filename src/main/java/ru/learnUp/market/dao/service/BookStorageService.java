package ru.learnUp.market.dao.service;

import org.springframework.stereotype.Service;

import ru.learnUp.market.dao.entity.BookStorage;
import ru.learnUp.market.dao.repository.BookStorageRepository;

import java.util.List;

@Service
public class BookStorageService {
    private BookStorageRepository bookStorageRepository;

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
}
