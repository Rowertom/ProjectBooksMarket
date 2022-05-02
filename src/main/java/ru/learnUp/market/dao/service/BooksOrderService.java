package ru.learnUp.market.dao.service;

import org.springframework.stereotype.Service;

import ru.learnUp.market.dao.entity.BooksOrder;
import ru.learnUp.market.dao.repository.BooksOrderRepository;

import java.util.List;

@Service
public class BooksOrderService {
    private BooksOrderRepository booksOrderRepository;

    public BooksOrderService(BooksOrderRepository booksOrderRepository) {
        this.booksOrderRepository = booksOrderRepository;
    }

    public BooksOrder createBooksOrder(BooksOrder booksOrder) {
        return booksOrderRepository.save(booksOrder);
    }

    public List<BooksOrder> getBooksOrder (){
        return booksOrderRepository.findAll();
    }

    public BooksOrder getBooksOrderId(Long id){
        return booksOrderRepository.getById(id);
    }
}
