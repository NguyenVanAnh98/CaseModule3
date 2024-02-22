package com.cg.customer1n.dao;

import com.cg.customer1n.model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomerDAO {
    public void insertCustomer(Customer customer);

    public Customer selectCustomer(int id);

    public List<Customer> selectAllCustomers();

    public boolean deleteCustomer(int id) ;

    public boolean updateCustomer(Customer customer);

    List<Customer> searchByKw(String kw);
}
