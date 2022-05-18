package ru.learnUp.market.mapperForView;

import liquibase.pro.packaged.T;
import org.springframework.stereotype.Component;
import ru.learnUp.market.dao.entity.Author;
import ru.learnUp.market.dao.entity.Book;
import ru.learnUp.market.view.BookView;
import ru.learnUp.market.view.ViewAuthorForBook;

import java.util.ArrayList;
import java.util.List;




@Component
public class MapperForBookView {


    public BookView mapToView(Book book){
        BookView bookView = new BookView();
        bookView.setBookId(book.getBookId());
        bookView.setBookName(book.getBookName());
        bookView.setIssueDate(book.getIssueDate());
        bookView.setLeavesCount(book.getLeavesCount());
        bookView.setPrice(book.getPrice());
        bookView.setViewAuthor(new ViewAuthorForBook(
                        book.getAuthor().getAuthorId(),
                        book.getAuthor().getAuthorName(),
                        book.getAuthor().getAuthorSurname()
                )
        );
        return bookView;
    }

    public Book mapFromView(BookView bookView){
        Book book = new Book();
        List<Book> books = new ArrayList<>();
        book.setBookId(bookView.getBookId());
        book.setBookName(bookView.getBookName());
        book.setIssueDate(bookView.getIssueDate());
        book.setLeavesCount(bookView.getLeavesCount());
        book.setPrice(bookView.getPrice());
        book.setAuthor(new Author(
                bookView.getViewAuthor().getAuthorId(),
                bookView.getViewAuthor().getAuthorName(),
                bookView.getViewAuthor().getAuthorSurname(),
                 books));
        return book;
    }
}
