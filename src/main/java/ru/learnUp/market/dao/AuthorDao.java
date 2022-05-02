//package ru.learnUp.market.dao;
//
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.time.ZoneOffset;
//import java.sql.Date;
//
//@Repository
//public class AuthorDao {

//    private static final String FIND_BY_ID = "select * from author where author_id = :author_id";
//    private static final String SAVE = "insert into author (author_name, author_surname, birth_date) " +
//            "values (:author_name, :author_surname, :birth_date)";
//    private final NamedParameterJdbcTemplate template;
//
//    public AuthorDao(NamedParameterJdbcTemplate template) {
//        this.template = template;
//    }
//
//    public Author findById(long id) {
//        return template.query(FIND_BY_ID,
//                        new MapSqlParameterSource("author_id", id),
//                        (rs, rn) -> Author.builder()
//                                .author_id(rs.getLong("author_id"))
//                                .author_name(rs.getString("author_name"))
//                                .author_surname(rs.getString("author_surname"))
//                                .birth_date(rs.getDate("birth_date").toLocalDate())
//                                .build()
//                ).stream()
//                .findAny()
//                .orElseThrow(() -> new RuntimeException("author with this id - " + id + " - is not found"));
//    }
//
//    public void save(Author author) {
//        template.update(SAVE,
//                new MapSqlParameterSource()
//                        .addValue("author_name", author.getAuthor_name())
//                        .addValue("author_surname", author.getAuthor_surname())
//                        .addValue("birth_date", new Date(author.getBirth_date()
//                                        .atStartOfDay()
//                                        .toInstant(ZoneOffset.UTC)
//                                        .toEpochMilli()
//                                )
//                        )
//        );
//    }
//}
