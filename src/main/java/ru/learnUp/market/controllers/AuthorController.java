package ru.learnUp.market.controllers;


import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.market.dao.entity.Author;
import ru.learnUp.market.dao.filters.AuthorFilter;
import ru.learnUp.market.dao.service.AuthorService;
import ru.learnUp.market.mapperForView.MapperForAuthorView;
import ru.learnUp.market.view.AuthorView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;
    private final MapperForAuthorView mapper;

    public AuthorController(AuthorService authorService, MapperForAuthorView mapper) {
        this.authorService = authorService;
        this.mapper = mapper;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping
    public List<AuthorView> getAuthors(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname
    ) {
        return authorService.getAuthorsBy(new AuthorFilter(name, surname))
                .stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{authorId}")
    public AuthorView getAuthor(@PathVariable("authorId") Long authorId) {
        return mapper.mapToView(authorService.getAuthorById(authorId));
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public AuthorView createAuthor(@RequestBody AuthorView body){
        if (body.getAuthorId() != null) {
            throw new EntityExistsException(
                    String.format("Author with id = %s already exist", body.getAuthorId())
            );
        }
        Author author = mapper.mapFromView(body);
        Author createdAuthor = authorService.createAuthor(author);
        return mapper.mapToView(createdAuthor);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/{authorId}")
    public AuthorView updateAuthor(
            @PathVariable("authorId") Long authorId,
            @RequestBody AuthorView body
    ) {
        if (body.getAuthorId() == null) {
        throw new EntityNotFoundException("Try to found null entity");
    }
        if (!Objects.equals(authorId, body.getAuthorId())) {
        throw new RuntimeException("Entity has bad id");
    }

    Author author = authorService.getAuthorById(authorId);

        if(!author.getAuthorName().equals(body.getAuthorName())){
            author.setAuthorName(body.getAuthorName());
        }

        if (!author.getAuthorSurname().equals(body.getAuthorSurname())) {
        author.setAuthorSurname(body.getAuthorSurname());
    }
    Author updated = authorService.updateAuthor(author);
        return mapper.mapToView(updated);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{authorId}")
    public Boolean deleteAuthor(@PathVariable("authorId") Long id){
        return authorService.delete(id);
    }
}

