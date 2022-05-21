package ru.learnUp.market.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.learnUp.market.dao.entity.Customer;
import ru.learnUp.market.dao.filters.CustomerFilter;
import ru.learnUp.market.dao.service.CustomerService;
import ru.learnUp.market.mapperForView.MapperForCustomerView;
import ru.learnUp.market.view.CustomerView;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final MapperForCustomerView mapper;

    public CustomerController(
            CustomerService customerService,
            MapperForCustomerView mapper
    ) {
        this.customerService = customerService;
        this.mapper = mapper;
    }


    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<CustomerView> getCustomers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname
    ) {
        return customerService.getCustomersBy(new CustomerFilter(name, surname))
                .stream()
                .map(mapper::mapToView)
                .collect(Collectors.toList());
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/{customerId}")
    public CustomerView getCustomer(@PathVariable("customerId") Long customerId) {
        return mapper.mapToView(customerService.getCustomerId(customerId));
    }

    @Secured({"ROLE_USER"})
    @PostMapping
    public CustomerView createCustomer(@RequestBody CustomerView body){
        if (body.getCustomerId() != null) {
            throw new EntityExistsException(
                    String.format("Customer with id = %s already exist", body.getCustomerId())
            );
        }
        Customer customer= mapper.mapFromView(body);
        Customer createdCustomer = customerService.createCustomer(customer);
        return mapper.mapToView(createdCustomer);
    }

    @Secured({"ROLE_USER"})
    @PutMapping("/{customerId}")
    public CustomerView updateCustomer(
            @PathVariable("customerId") Long customerId,
            @RequestBody CustomerView body
    ) {
        if (body.getCustomerId() == null) {
            throw new EntityNotFoundException("Try to found null entity");
        }
        if (!Objects.equals(customerId, body.getCustomerId())) {
            throw new RuntimeException("Entity has bad id");
        }

        Customer customer = customerService.getCustomerId(customerId);

        if(!customer.getCustomerName().equals(body.getCustomerName())){
            customer.setCustomerName(body.getCustomerName());
        }

        if (!customer.getCustomerSurname().equals(body.getCustomerSurname())) {
            customer.setCustomerSurname(body.getCustomerSurname());
        }
        Customer updated = customerService.update(customer);
        return mapper.mapToView(updated);
    }

    @Secured({"ROLE_USER"})
    @DeleteMapping("/{customerId}")
    public Boolean deleteCustomer(@PathVariable("customerId") Long id){
        return customerService.delete(id);
    }
}

