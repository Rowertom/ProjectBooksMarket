package ru.learnUp.market.dao.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.learnUp.market.dao.entity.Author;
import ru.learnUp.market.dao.filters.AuthorFilter;
import ru.learnUp.market.dao.repository.AuthorRepository;

import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static ru.learnUp.market.dao.specification.AuthorSpecification.useFilter;

@Service
@Slf4j
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository author, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> getAuthors (){
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsBy(AuthorFilter authorFilter){
        Specification<Author> specification = Specification.where(useFilter(authorFilter));
                return authorRepository.findAll(specification);
    }
    @Transactional
    public Author getAuthorById(Long id){
        return authorRepository.findId(id);
    }

    @Transactional
    public Author updateAuthor(Author author) {
        try {
            return authorRepository.save(author);
        } catch (OptimisticLockException e) {
            log.warn("Optimistic lock exception for post {}", author.getAuthorId());
            throw e;
        }
    }

    public Boolean delete(Long id) {
        authorRepository.delete(authorRepository.getById(id));
        return true;
    }
}

