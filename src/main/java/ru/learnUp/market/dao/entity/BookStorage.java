package ru.learnUp.market.dao.entity;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table
@Getter
@Setter
@ToString(exclude = {"book"})
@RequiredArgsConstructor
@AllArgsConstructor
public class BookStorage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookStorageId;

    @ManyToOne
    @JoinColumn
    private Book book;

    @Column
    private int booksCount;


}
