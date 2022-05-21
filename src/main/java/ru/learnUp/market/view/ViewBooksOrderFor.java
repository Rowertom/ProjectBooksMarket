package ru.learnUp.market.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewBooksOrderFor {

    private Long orderId;
    private int finalPrice;

}
