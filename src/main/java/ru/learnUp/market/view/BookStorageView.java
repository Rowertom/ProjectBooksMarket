package ru.learnUp.market.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStorageView {

    private Long bookStorageId;
    private ViewBookFor viewBook;
    private int booksCount;
}
