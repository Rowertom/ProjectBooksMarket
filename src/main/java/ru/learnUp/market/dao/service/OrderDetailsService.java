package ru.learnUp.market.dao.service;

import org.springframework.stereotype.Service;
import ru.learnUp.market.dao.entity.OrderDetails;
import ru.learnUp.market.dao.repository.OrderDetailsRepository;

import java.util.List;

@Service
public class OrderDetailsService {
    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public OrderDetails createOrderDetails(OrderDetails orderDetails) {
        return orderDetailsRepository.save(orderDetails);
    }

    public List<OrderDetails> getOrderDetails (){
        return orderDetailsRepository.findAll();
    }

    public OrderDetails getOrderDetailsId(Long id){
        return orderDetailsRepository.getById(id);
    }
}
