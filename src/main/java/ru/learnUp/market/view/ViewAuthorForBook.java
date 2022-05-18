package ru.learnUp.market.view;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAuthorForBook {

    private Long authorId;
    private String authorName;
    private String authorSurname;
}