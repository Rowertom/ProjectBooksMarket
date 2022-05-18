package ru.learnUp.market.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.learnUp.market.dao.filters.BookFilter;
import ru.learnUp.market.dao.entity.Book;

import javax.persistence.criteria.*;

public class BookSpecification {

    public static Specification<Book> useFilter(BookFilter bookFilter){
        return (root, q, cb) ->{

            Predicate predicate = cb.isNotNull(root.get("bookId"));

            if(bookFilter.getBookName() != null){
                predicate = cb.and(predicate, cb.like(root.get("bookName"),
                        "%" + bookFilter.getBookName() + "%"));
            } return predicate;
        };
    }

}
