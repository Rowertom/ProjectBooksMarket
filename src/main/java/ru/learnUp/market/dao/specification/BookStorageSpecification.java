package ru.learnUp.market.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.learnUp.market.dao.entity.BookStorage;
import ru.learnUp.market.dao.entity.BooksOrder;
import ru.learnUp.market.dao.filters.BookStorageFilter;
import ru.learnUp.market.dao.filters.BooksOrderFilter;

import javax.persistence.criteria.Predicate;

public class BookStorageSpecification {

    public static Specification<BookStorage> useFilterBS(BookStorageFilter bookStorageFilter){
    return (root, q, cb) ->{

        Predicate predicate = cb.isNotNull(root.get("storageId"));

        if(bookStorageFilter.getBookName() != null){
            predicate = cb.and(predicate, cb.like(root.get("bookName"),
                    "%" + bookStorageFilter.getBookName() + "%"));
        }
        return predicate;
    };
}
}