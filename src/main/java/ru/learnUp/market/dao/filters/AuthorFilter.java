package ru.learnUp.market.dao.filters;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorFilter {

    private String authorName;
    private String authorSurname;
}
