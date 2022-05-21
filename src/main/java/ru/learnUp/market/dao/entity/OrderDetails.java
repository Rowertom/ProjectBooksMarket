package ru.learnUp.market.dao.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailsId;

    @ManyToOne
    @JoinColumn
    private BooksOrder order;

    @OneToOne
    @JoinColumn
    private Book book;

    @Column
    private int booksCount;



}
