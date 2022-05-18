package ru.learnUp.market.dao.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import ru.learnUp.market.dao.entity.Customer;
import ru.learnUp.market.dao.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> getCustomers (){
        return customerRepository.findAll();
    }

    public Customer getCustomerId(Long id){
        return customerRepository.getById(id);
    }

    @Transactional
    @CacheEvict(value = "customer", key = "#customer.customerId")
    public void update(Customer customer){
        customerRepository.save(customer);
    }
}
