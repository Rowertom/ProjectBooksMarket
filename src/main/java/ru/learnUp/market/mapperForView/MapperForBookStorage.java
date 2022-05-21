package ru.learnUp.market.mapperForView;

import org.springframework.stereotype.Component;
import ru.learnUp.market.dao.entity.Author;
import ru.learnUp.market.dao.entity.Book;
import ru.learnUp.market.dao.entity.BookStorage;
import ru.learnUp.market.view.BookStorageView;
import ru.learnUp.market.view.ViewBookFor;

import java.util.ArrayList;
import java.util.List;


@Component
public class MapperForBookStorage {

    public BookStorageView mapToView(BookStorage bookStorage) {
        BookStorageView storageView = new BookStorageView();
        storageView.setBookStorageId(bookStorage.getBookStorageId());
        storageView.setViewBook(new ViewBookFor(
                bookStorage.getBook().getBookId(),
                bookStorage.getBook().getBookName(),
                bookStorage.getBook().getIssueDate(),
                bookStorage.getBook().getLeavesCount(),
                bookStorage.getBook().getPrice()
                )
        );
        storageView.setBooksCount(bookStorage.getBooksCount());
        return storageView;
    }

    public BookStorage mapFromView(BookStorageView storageView) {
        BookStorage bookStorage = new BookStorage();
        Author author = new Author();
        List<BookStorage> bookStorages = new ArrayList<>();
        bookStorage.setBookStorageId(storageView.getBookStorageId());
        bookStorage.setBook(new Book(
                storageView.getViewBook().getBookId(),
                storageView.getViewBook().getBookName(),
                storageView.getViewBook().getIssueDate(),
                storageView.getViewBook().getLeavesCount(),
                storageView.getViewBook().getPrice(),
                author,
                bookStorages
                )
        );
        bookStorage.setBooksCount(storageView.getBooksCount());
        return bookStorage;
    }


}
