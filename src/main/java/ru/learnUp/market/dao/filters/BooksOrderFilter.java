package ru.learnUp.market.dao.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BooksOrderFilter {

    private String customerName;
    private String customerSurname;
}
