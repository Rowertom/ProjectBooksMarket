package ru.learnUp.market.dao.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString(exclude = {"author"})
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column
    private String bookName;

    @Column
    private int issueDate;

    @Column
    private int leavesCount;

    @Column
    private int price;

    @ManyToOne
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    private Author author;


//    @OneToOne(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Fetch(FetchMode.JOIN)
//    private List<BookStorage> bookStorage;
//
}
