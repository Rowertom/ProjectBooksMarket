package ru.learnUp.market.dao.service;

import org.springframework.stereotype.Service;

import ru.learnUp.market.dao.entity.Customer;
import ru.learnUp.market.dao.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers (){
        return customerRepository.findAll();
    }

    public Customer getCustomerId(Long id){
        return customerRepository.getById(id);
    }
}
