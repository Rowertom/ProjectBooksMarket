package ru.learnUp.market.dao;
//
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//
//@Repository
//public class BooksDao {
//
//    private static final String FIND_BY_ID = "select * from books_shop where book_id = :book_id";
//    private static final String SAVE = "insert into books_shop where book_name values = :book_name";
//    private final NamedParameterJdbcTemplate template;
//
//    public BooksDao(NamedParameterJdbcTemplate template) {
//        this.template = template;
//    }
//
//    public Book findById(long id) {
//        return template.query(FIND_BY_ID,
//                        new MapSqlParameterSource("id", id),
//                        (rs, rn) -> Book.builder()
//                                .author_id(rs.getLong("id"))
//                                .book_id(rs.getLong("book_id"))
//                                .book_name(rs.getString("book_name"))
//                                .build()
//                ).stream()
//                .findAny()
//                .orElseThrow(() -> new RuntimeException("book with this id - " + id + " - is not found"));
//    }
//
//    public void save(Book book) {
//        template.update(SAVE, new MapSqlParameterSource()
//                        .addValue("author_name", book.getBook_name()));
//    }
//}
