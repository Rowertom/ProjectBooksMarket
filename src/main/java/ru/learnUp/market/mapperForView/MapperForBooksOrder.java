package ru.learnUp.market.mapperForView;

import org.springframework.stereotype.Component;
import ru.learnUp.market.dao.entity.BooksOrder;
import ru.learnUp.market.dao.entity.Customer;
import ru.learnUp.market.dao.entity.OrderDetails;
import ru.learnUp.market.view.BooksOrderView;
import ru.learnUp.market.view.CustomerView;

import java.util.ArrayList;
import java.util.List;


@Component
public class MapperForBooksOrder {

    public BooksOrderView mapToView(BooksOrder booksOrder){
        int price = 0;
        BooksOrderView booksOrderView = new BooksOrderView();
        booksOrderView.setOrderId(booksOrder.getOrderId());
        booksOrderView.setCustomerView(new CustomerView(
                booksOrder.getCustomer().getCustomerId(),
                booksOrder.getCustomer().getCustomerName(),
                booksOrder.getCustomer().getCustomerSurname(),
                booksOrder.getCustomer().getBirthDate()
                )
        );
        if (booksOrder.getListOfOrderDetails() != null) {
            for (OrderDetails orderDetails : booksOrder.getListOfOrderDetails()) {
                price += orderDetails.getBook().getPrice();
            }
            booksOrderView.setFinalPrice(price);
        } else {
            booksOrderView.setFinalPrice(booksOrder.getFinalPrice());
        }
        return booksOrderView;
    }

    public BooksOrder mapFromView(BooksOrderView booksOrderView){
        BooksOrder booksOrder = new BooksOrder();
        List<BooksOrder> booksOrderList = new ArrayList<>();
        booksOrder.setOrderId(booksOrder.getOrderId());
        booksOrder.setCustomer(new Customer(
                booksOrderView.getCustomerView().getCustomerId(),
                booksOrderView.getCustomerView().getCustomerName(),
                booksOrderView.getCustomerView().getCustomerSurname(),
                booksOrderView.getCustomerView().getBirthDate(),
                booksOrderList
                )
        );
        booksOrder.setFinalPrice(booksOrder.getFinalPrice());
        return booksOrder;
    }

}
