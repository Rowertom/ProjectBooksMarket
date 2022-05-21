package ru.learnUp.market.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookView {

    private Long bookId;
    private String bookName;
    private int issueDate;
    private int leavesCount;
    private int price;
    private ViewAuthorFor viewAuthor;
    private List<ViewBookStorageFor> bookStorage;


}
