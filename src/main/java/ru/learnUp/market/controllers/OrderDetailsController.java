package ru.learnUp.market.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.market.dao.entity.OrderDetails;
import ru.learnUp.market.dao.service.OrderDetailsService;
import ru.learnUp.market.mapperForView.MapperForOrderDetails;
import ru.learnUp.market.view.OrderDetailsView;

@RestController
@RequestMapping("/api/order_details")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;
    private final MapperForOrderDetails mapper;

    public OrderDetailsController(
            OrderDetailsService orderDetailsService,
            MapperForOrderDetails mapper
    ) {
        this.orderDetailsService = orderDetailsService;
        this.mapper = mapper;
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{orderDetailsId}")
    public OrderDetailsView getBooksOrderId(@PathVariable("orderDetailsId") Long orderDetailsId) {
        return mapper.mapToView(orderDetailsService.getOrderDetailsId(orderDetailsId));
    }

    @Secured({"ROLE_USER"})
    @PostMapping
    public OrderDetailsView createOrderDetails(@RequestBody OrderDetailsView body) {
        OrderDetails orderDetails = mapper.mapFromView(body);
        OrderDetails createdOrderDetails = orderDetailsService.createOrderDetails(orderDetails);
        return mapper.mapToView(createdOrderDetails);
    }

    @Secured({"ROLE_USER"})
    @DeleteMapping("/{orderDetailsId}")
    public Boolean deleteBooksOrder(@PathVariable("orderDetailsId") Long id) {
        return orderDetailsService.delete(id);
    }
}

