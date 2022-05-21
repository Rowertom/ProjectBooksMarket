package ru.learnUp.market.dao.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.learnUp.market.dao.entity.Customer;
import ru.learnUp.market.dao.filters.CustomerFilter;

import javax.persistence.criteria.Predicate;

public class CustomerSpecification {

    public static Specification<Customer> useFilterC(CustomerFilter customerFilter){
        return (root, q, cb) ->{

            Predicate predicate = cb.isNotNull(root.get("customerId"));

            if(customerFilter.getCustomerName() != null){
                predicate = cb.and(predicate, cb.like(root.get("customerName"),
                        "%" + customerFilter.getCustomerName() + "%"));
            }
            if(customerFilter.getCustomerSurname() != null){
                predicate = cb.and(predicate, cb.like(root.get("customerSurname"),
                        "%" + customerFilter.getCustomerSurname() + "%"));
            }
            return predicate;
        };
    }
}
