package ru.learnUp.market.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.learnUp.market.dao.filters.AuthorFilter;
import ru.learnUp.market.dao.entity.Author;

import javax.persistence.criteria.Predicate;

public class AuthorSpecification {

    public static Specification<Author> useFilter(AuthorFilter authorFilter){
        return (root, q, cb) ->{

            Predicate predicate = cb.isNotNull(root.get("authorId"));

            if(authorFilter.getAuthorName() != null){
                predicate = cb.and(predicate, cb.like(root.get("authorName"),
                        "%" + authorFilter.getAuthorName() + "%"));
            }
            if(authorFilter.getAuthorSurname() != null){
                predicate = cb.and(predicate, cb.like(root.get("authorSurname"),
                        "%" + authorFilter.getAuthorSurname() + "%"));
            }
            return predicate;
        };
    }
}
