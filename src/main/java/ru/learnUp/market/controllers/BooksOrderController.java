package ru.learnUp.market.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.market.dao.entity.BooksOrder;
import ru.learnUp.market.dao.filters.BooksOrderFilter;
import ru.learnUp.market.dao.service.BooksOrderService;
import ru.learnUp.market.mapperForView.MapperForBooksOrder;
import ru.learnUp.market.view.BooksOrderView;
import ru.learnUp.market.view.ViewBooksOrderFor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books_order")
public class BooksOrderController {

    private final BooksOrderService booksOrderService;
    private final MapperForBooksOrder mapper;

    public BooksOrderController(
            BooksOrderService booksOrderService,
            MapperForBooksOrder mapper

    ) {
        this.booksOrderService = booksOrderService;
        this.mapper = mapper;

    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<BooksOrderView> getBooksOrder(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname
    ){
        return booksOrderService.getBooksOrderBy(new BooksOrderFilter(name, surname))
                .stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{orderId}")
    public BooksOrderView getBooksOrder(@PathVariable("orderId") Long orderId) {
        return mapper.mapToView(booksOrderService.getBooksOrderId(orderId));
    }

    @Secured({"ROLE_USER"})
    @PostMapping
    public BooksOrderView createBooksOrder(@RequestBody BooksOrderView body) {
        BooksOrder booksOrder = mapper.mapFromView(body);
        BooksOrder createdBooksOrder = booksOrderService.createBooksOrder(booksOrder);
        return mapper.mapToView(createdBooksOrder);
    }

    @Secured({"ROLE_USER"})
    @PutMapping("/{orderId}")
    public BooksOrderView updateBooksOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody ViewBooksOrderFor body
    ) {
        if (!Objects.equals(orderId, body.getOrderId())) {
            throw new RuntimeException("Entity has bad id");
        }

        BooksOrder booksOrder = booksOrderService.getBooksOrderId(orderId);

        if (booksOrder.getFinalPrice() != body.getFinalPrice()) {
            booksOrder.setFinalPrice(body.getFinalPrice());
        }

        BooksOrder updated = booksOrderService.update(booksOrder);

        return mapper.mapToView(updated);
    }

    @Secured({"ROLE_USER"})
    @DeleteMapping("/{orderId}")
    public Boolean deleteBooksOrder(@PathVariable("orderId") Long id) {
        return booksOrderService.delete(id);
    }
}

