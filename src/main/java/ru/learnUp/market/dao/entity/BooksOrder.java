package ru.learnUp.market.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books_order")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BooksOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @OneToOne
    @JoinColumn
    private Customer customer;

    @Column
    private int finalPrice;

    @OneToMany
    private List<OrderDetails> listOfOrderDetails;

}
