package ru.learnUp.market.dao.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ru.learnUp.market.dao.entity.BooksOrder;
import ru.learnUp.market.dao.filters.BooksOrderFilter;
import ru.learnUp.market.dao.repository.BooksOrderRepository;
import ru.learnUp.market.dao.specification.BooksOrderSpecification;

import java.util.List;

@Service
public class BooksOrderService {
    private final BooksOrderRepository booksOrderRepository;

    public BooksOrderService(BooksOrderRepository booksOrderRepository) {
        this.booksOrderRepository = booksOrderRepository;
    }

    public BooksOrder createBooksOrder(BooksOrder booksOrder) {
        return booksOrderRepository.save(booksOrder);
    }

    public List<BooksOrder> getBooksOrder(BooksOrderFilter booksOrderFilter) {
        return booksOrderRepository.findAll();
    }

    public BooksOrder getBooksOrderId(Long id) {
        return booksOrderRepository.getById(id);
    }

    public BooksOrder update(BooksOrder booksOrder){
        return booksOrderRepository.save(booksOrder);
    }

    public Boolean delete(Long id) {
        booksOrderRepository.delete(booksOrderRepository.getById(id));
        return true;
    }

    public List<BooksOrder> getBooksOrderBy(BooksOrderFilter booksOrderFilter){
        Specification<BooksOrder> specification = Specification.where(
                BooksOrderSpecification.useFilterBO(booksOrderFilter)
        );
        return booksOrderRepository.findAll(specification);
    }
}

