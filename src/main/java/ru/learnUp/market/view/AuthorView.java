package ru.learnUp.market.view;


import lombok.Data;


import java.util.List;

@Data
public class AuthorView {

    private Long authorId;
    private String authorName;
    private String authorSurname;
    private List<ViewBookFor> books;

}
