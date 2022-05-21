package ru.learnUp.market.mapperForView;

import org.springframework.stereotype.Component;
import ru.learnUp.market.dao.entity.Customer;
import ru.learnUp.market.view.CustomerView;

@Component
public class MapperForCustomerView {

    public CustomerView mapToView(Customer customer){
        CustomerView customerView = new CustomerView();
        customerView.setCustomerId(customer.getCustomerId());
        customerView.setCustomerName(customer.getCustomerName());
        customerView.setCustomerSurname(customer.getCustomerSurname());
        customerView.setBirthDate(customer.getBirthDate());
        return customerView;
    }

    public Customer mapFromView(CustomerView customerView){
        Customer customer = new Customer();
        customer.setCustomerId(customerView.getCustomerId());
        customer.setCustomerName(customerView.getCustomerName());
        customer.setCustomerSurname((customerView.getCustomerSurname()));
        customer.setBirthDate(customerView.getBirthDate());
        return customer;
    }
}
