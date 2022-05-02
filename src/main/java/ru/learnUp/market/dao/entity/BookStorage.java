package ru.learnUp.market.dao.entity;


import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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
