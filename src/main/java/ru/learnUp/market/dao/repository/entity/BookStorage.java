package ru.learnUp.market.dao.repository.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
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
