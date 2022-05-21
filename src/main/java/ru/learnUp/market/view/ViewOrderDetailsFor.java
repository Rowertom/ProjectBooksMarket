package ru.learnUp.market.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewOrderDetailsFor {

    private Long orderDetailsId;
    private int booksCount;
}
