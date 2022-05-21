package ru.learnUp.market.dao.filters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerFilter {

    private String customerName;
    private String customerSurname;
}
