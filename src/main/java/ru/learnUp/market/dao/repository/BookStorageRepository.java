package ru.learnUp.market.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.learnUp.market.dao.repository.entity.Book;
import ru.learnUp.market.dao.repository.entity.BookStorage;

@Repository
public interface BookStorageRepository extends JpaRepository<BookStorage, Long> {
    @Query(value = "select * from book_storage bs where bs.book_book_id = ?1",
            nativeQuery = true)
    BookStorage getByBook(Book book);
}
