package ru.learnUp.market.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewBookForAuthor {

    private Long bookId;
    private String bookName;
    private int issueDate;
    private int leavesCount;
    private int price;

}
