package ru.learnUp.market.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.learnUp.market.dao.entity.Customer;
import ru.learnUp.market.dao.entity.OrderDetails;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksOrderView {

    private Long orderId;
    private CustomerView customerView;
    private int finalPrice;
//    private List<ViewOrderDetailsFor> listOfOrderDetails;


}
