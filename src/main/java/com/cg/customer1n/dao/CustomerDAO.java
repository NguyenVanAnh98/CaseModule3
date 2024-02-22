package com.cg.customer1n.dao;

import com.cg.customer1n.model.Customer;
import com.cg.customer1n.model.Province;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO{
    private static final String SEARCHBYKW = "SELECT * FROM customer c join province p  on c.province_id = p.id where c.name like (?) or p.name like (?) ";
    private String jdbcURL = "jdbc:mysql://localhost:3306/customer?allowPublicKeyRetrieval=true&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Raisingthebar123!!/";

    private String SELECT_CUSTOMERS = "SELECT c.*, p.name as province_name \n" +
            "FROM customer c join province p on c.province_id = p.id;";
    private String INSERT_CUSTOMER = "INSERT INTO `customer` (`name`, `address`, `email`, `phone`, `province_id`) \n" +
            "VALUES (?, ?, ?, ?, ?);";
    private String DELETE_CUSTOMER_SQL ="delete from customer where id = ?;";
    private String UPDATE_CUSTOMER_SQL = "update customer set name = ?,address= ?, email =?, phone =?, province_id =? where id = ?;";
    private String SELECT_CUSTOMER_BY_ID = "select c.*, p.name as province_name from customer c join province p on c.province_id = p.id  where c.id =?";

    @Override
    public void insertCustomer(Customer customer) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getAddress());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPhone());
            preparedStatement.setInt(5, customer.getProvince().getId());

            System.out.println("insertCustomer: " + preparedStatement);
            preparedStatement.execute();

        } catch (SQLException e) {
            printSQLException(e);
        }

    }

    @Override
    public Customer selectCustomer(int id) {
        Customer customer = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String adress = rs.getString("address");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                int province_id = rs.getInt("province_id");
                String province_name = rs.getString("province_name");
                customer = new Customer(id,name,adress,email,phone,new Province(province_id,province_name));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> selectAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int province_id = rs.getInt("province_id");
                String province_name = rs.getString("province_name");

                Customer customer = new Customer(id, name, address, email, phone,
                        new Province(province_id, province_name));
                customers.add(customer);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return customers;
    }

    @Override
    public boolean deleteCustomer(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return rowDeleted;
    }

    @Override
    public boolean updateCustomer(Customer customer)  {
        boolean rowUpdated = false;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getEmail());
            statement.setString(4, customer.getPhone());
            statement.setInt(5, customer.getProvince().getId());
            statement.setInt(6, customer.getId());
            rowUpdated = statement.executeUpdate() > 0;

            System.out.println("updateCustomer: " +  statement);
        } catch (SQLException sqlException) {
            printSQLException(sqlException);
        }
        return rowUpdated;
    }

    @Override
    public List<Customer> searchByKw(String kw) {
        List<Customer> customers = new ArrayList<>();
        try {
            Connection connection = getConnection();

            String searchQuery = "SELECT c.*, p.name AS province_name " +
                    "FROM customer c JOIN province p ON c.province_id = p.id " +
                    "WHERE c.name LIKE ? OR p.name LIKE ?";

            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            String searchParam = "%" + kw + "%";
            preparedStatement.setString(1, searchParam);
            preparedStatement.setString(2, searchParam);


            System.out.println("searchByKw: " + preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                int province_id = rs.getInt("province_id");
                String province_name = rs.getString("province_name");

                Customer customer = new Customer(id, name, address, email, phone,
                        new Province(province_id, province_name));
                customers.add(customer);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return customers;
    }


    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
