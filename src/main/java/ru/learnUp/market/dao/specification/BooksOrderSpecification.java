package ru.learnUp.market.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.learnUp.market.dao.entity.BooksOrder;
import ru.learnUp.market.dao.filters.BooksOrderFilter;

import javax.persistence.criteria.Predicate;

public class BooksOrderSpecification {

    public static Specification<BooksOrder> useFilterBO(BooksOrderFilter booksOrderFilter){
        return (root, q, cb) ->{

            Predicate predicate = cb.isNotNull(root.get("orderId"));

            if(booksOrderFilter.getCustomerName() != null){
                predicate = cb.and(predicate, cb.like(root.get("customerName"),
                        "%" + booksOrderFilter.getCustomerName() + "%"));
            }
            if(booksOrderFilter.getCustomerName() != null){
                predicate = cb.and(predicate, cb.like(root.get("customerSurname"),
                        "%" + booksOrderFilter.getCustomerSurname() + "%"));
            }
            return predicate;
        };
    }
}
