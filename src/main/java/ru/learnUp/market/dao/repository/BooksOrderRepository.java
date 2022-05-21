package ru.learnUp.market.dao.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.learnUp.market.dao.entity.Author;
import ru.learnUp.market.dao.entity.BooksOrder;

import java.util.List;

@Repository
public interface BooksOrderRepository extends JpaRepository<BooksOrder, Long> {

    List<BooksOrder> findAll(Specification<BooksOrder> specification);
}
