package ru.learnUp.market.dao.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ru.learnUp.market.dao.entity.Customer;
import ru.learnUp.market.dao.filters.CustomerFilter;
import ru.learnUp.market.dao.repository.CustomerRepository;
import ru.learnUp.market.dao.specification.CustomerSpecification;

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
    public Customer update(Customer customer){
        customerRepository.save(customer);
        return customer;
    }

    public List<Customer> getCustomersBy(CustomerFilter customerFilter){
        Specification<Customer> specification = Specification.where(
                CustomerSpecification.useFilterC(customerFilter)
        );
        return customerRepository.findAll(specification);
    }

    public Boolean delete(Long id) {
        customerRepository.delete(customerRepository.getById(id));
        return true;
    }

}
