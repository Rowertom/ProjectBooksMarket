package ru.learnUp.market.dao.entity;


import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

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

    @OneToOne(optional = false)
    @JoinColumn
    @Fetch(FetchMode.JOIN)
    private Book book;

    @Column
    private int booksCount;

    @Version
    private Long version;

}
