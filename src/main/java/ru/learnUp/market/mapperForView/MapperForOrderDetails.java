package ru.learnUp.market.mapperForView;

import org.springframework.stereotype.Component;
import ru.learnUp.market.dao.entity.*;
import ru.learnUp.market.view.OrderDetailsView;
import ru.learnUp.market.view.ViewBookFor;
import ru.learnUp.market.view.ViewBooksOrderFor;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapperForOrderDetails {

   public OrderDetailsView mapToView(OrderDetails orderDetails) {
       OrderDetailsView orderDetailsView = new OrderDetailsView();
       orderDetailsView.setOrderDetailsId(orderDetails.getOrderDetailsId());
        orderDetailsView.setViewBooksOrder(new ViewBooksOrderFor(
                orderDetails.getOrder().getOrderId(),
                orderDetails.getOrder().getFinalPrice()
        ));
        orderDetailsView.setViewBook(new ViewBookFor(
                orderDetails.getBook().getBookId(),
                orderDetails.getBook().getBookName(),
                orderDetails.getBook().getIssueDate(),
                orderDetails.getBook().getLeavesCount(),
                orderDetails.getBook().getPrice()
        ));
        orderDetailsView.setBooksCount(orderDetails.getBooksCount());
        return orderDetailsView;
   }

   public OrderDetails mapFromView(OrderDetailsView orderDetailsView){
       OrderDetails orderDetails = new OrderDetails();
       Customer customer = new Customer();
       Author author = new Author();
       List<BookStorage> storage = new ArrayList<>();
       List<OrderDetails> orderDetailsList = new ArrayList<>();
       orderDetails.setOrderDetailsId(orderDetailsView.getOrderDetailsId());
       orderDetails.setOrder(new BooksOrder(
               orderDetailsView.getViewBooksOrder().getOrderId(),
               customer,
               orderDetailsView.getViewBooksOrder().getFinalPrice(),
               orderDetailsList
               ));
       orderDetails.setBook(new Book(
               orderDetailsView.getViewBook().getBookId(),
               orderDetailsView.getViewBook().getBookName(),
               orderDetailsView.getViewBook().getIssueDate(),
               orderDetailsView.getViewBook().getLeavesCount(),
               orderDetailsView.getViewBook().getPrice(),
               author,
               storage
       ));
       orderDetails.setBooksCount(orderDetailsView.getBooksCount());
       return orderDetails;
   }
}
