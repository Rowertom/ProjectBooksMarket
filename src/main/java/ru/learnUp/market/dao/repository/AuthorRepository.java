package ru.learnUp.market.dao.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnUp.market.dao.entity.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query(value = "from Author a join fetch a.books where a.authorId = :id")
    Author findId(Long id);
List<Author> findAll(Specification<Author> specification);

}
