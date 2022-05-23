package ru.learnUp.market.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Getter
@Setter
@ToString
public class OrderReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @OneToOne
    private BooksOrder order;

    @Column
    private Calendar calendar;

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "{\nOrder №: " + order.getOrderId() + "\n"
                + "Date: " + dateFormat.format(calendar.getTime()) + "\n"
                + "Order final price: " + order.getFinalPrice() + "\n}\n";
    }
}
