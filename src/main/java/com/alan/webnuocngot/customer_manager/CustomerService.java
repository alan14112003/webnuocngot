package com.alan.webnuocngot.customer_manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired CustomerRepository repo;

    public List<Customer> listCustomer() {
        return (List<Customer>) repo.findAll();
    }

    public Customer save(Customer customer) {
        repo.save(customer);
        return customer;
    }
    public int countAllCustomer() {
        return (int) repo.count();
    }
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
