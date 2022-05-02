package ru.learnUp.market.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString(exclude = "author")
@RequiredArgsConstructor
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @ManyToOne
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    private Author author;

    @Column
    private String bookName;

    @Column
    private int issueDate;

    @Column
    private int leavesCount;

    @Column
    private int price;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<BookStorage> bookStorage;

}
