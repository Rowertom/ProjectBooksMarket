package ru.learnUp.market.dao.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import ru.learnUp.market.dao.repository.entity.Author;
import ru.learnUp.market.dao.repository.AuthorRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
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

    public Author getAuthorId(Long id){
        return authorRepository.findId(id);
    }

    @Transactional
    @CacheEvict(value = "author", key = "#author.authorId")
    public void update (Author author){
        authorRepository.save(author);
    }

}

