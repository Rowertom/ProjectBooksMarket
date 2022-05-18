package ru.learnUp.market.view;

import lombok.Data;

@Data
public class BookView {

    private Long bookId;
    private String bookName;
    private int issueDate;
    private int leavesCount;
    private int price;
    private ViewAuthorForBook viewAuthor;
}
