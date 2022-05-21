package ru.learnUp.market.mapperForView;

import org.springframework.stereotype.Component;
import ru.learnUp.market.dao.entity.Author;
import ru.learnUp.market.dao.entity.Book;
import ru.learnUp.market.dao.entity.BookStorage;
import ru.learnUp.market.view.AuthorView;
import ru.learnUp.market.view.ViewBookFor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MapperForAuthorView {

    public AuthorView mapToView(Author author){
        AuthorView authorView = new AuthorView();
        authorView.setAuthorId(author.getAuthorId());
        authorView.setAuthorName(author.getAuthorName());
        authorView.setAuthorSurname(author.getAuthorSurname());
        if (author.getBooks() != null) {
            authorView.setBooks(
                    author.getBooks().stream()
                            .map(book -> new ViewBookFor(
                                    book.getBookId(),
                                    book.getBookName(),
                                    book.getIssueDate(),
                                    book.getLeavesCount(),
                                    book.getPrice())
                                    )
                                    .collect(Collectors.toList())
                            );
        }
        return authorView;
    }

    public Author mapFromView(AuthorView authorView) {

        Author author = new Author();
        List<BookStorage> bookstorage = new ArrayList<>();
        author.setAuthorId(authorView.getAuthorId());
        author.setAuthorName(authorView.getAuthorName());
        author.setAuthorSurname(authorView.getAuthorSurname());
        if (authorView.getBooks() != null) {
            author.setBooks(
                    authorView.getBooks().stream()
                            .map(book -> new Book(
                                    book.getBookId(),
                                    book.getBookName(),
                                    book.getIssueDate(),
                                    book.getLeavesCount(),
                                    book.getPrice(),
                                    author,
                                    bookstorage
                                    )
                            )
                            .collect(Collectors.toList())
            );
        }
        return author;
    }
}
