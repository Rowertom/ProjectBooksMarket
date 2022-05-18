package ru.learnUp.market.view;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class AuthorView {

    private Long authorId;
    private String authorName;
    private String authorSurname;
    private List<ViewBookForAuthor> books;

}
