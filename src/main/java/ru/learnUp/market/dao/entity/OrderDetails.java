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
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailsId;

    @ManyToOne
    @JoinColumn
    private BooksOrder order;

    @OneToOne
    @JoinColumn
    private BookStorage bookStorage;

    @Column
    private int booksCount;


}
