package ru.learnUp.market.view;

import lombok.Data;

@Data
public class OrderDetailsView {

    private Long orderDetailsId;
    private ViewBooksOrderFor viewBooksOrder;
    private ViewBookFor viewBook;
    private int booksCount;

}

