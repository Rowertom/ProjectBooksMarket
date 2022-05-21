package ru.learnUp.market.dao.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books_order")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BooksOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

   @ManyToOne
    @JoinColumn
    private Customer customer;

    @Column
    private int finalPrice;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderDetails> listOfOrderDetails;


}
