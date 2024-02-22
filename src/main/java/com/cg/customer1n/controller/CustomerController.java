package com.cg.customer1n.controller;

import com.cg.customer1n.dao.CustomerDAO;
import com.cg.customer1n.dao.ICustomerDAO;
import com.cg.customer1n.dao.IProvinceDAO;
import com.cg.customer1n.dao.ProvinceDAO;
import com.cg.customer1n.model.Customer;
import com.cg.customer1n.model.Province;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/customer")
    public class CustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ICustomerDAO customerDAO;
    private IProvinceDAO provinceDAO;

    public void init() {

        customerDAO = new CustomerDAO();
        provinceDAO = new ProvinceDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                insertCustomer(request, response);
                break;
            case "edit":
                updateCustomer(request, response);
                break;
        }

    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String email = request.getParameter("address");
            String address = request.getParameter("email");
            String phone = request.getParameter("phone");
            int province_id = Integer.parseInt(request.getParameter("province"));
            Province province = provinceDAO.findProvinceById(province_id);


            Customer customer = new Customer(id,name,address,email,phone, province);
            customerDAO.updateCustomer(customer);

            response.sendRedirect("/customer");

    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        int provinceId = Integer.parseInt(request.getParameter("province"));

        Province province = new Province(provinceId, "Hue");

        Customer customer = new Customer(-1, name, address, email, phone, province);
        customerDAO.insertCustomer(customer);

        response.sendRedirect("/customer");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                listCustomer(request, response);
                break;
        }

    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String kw = request.getParameter("kw");
        if (kw == null) {
            kw = "";
        }

        List<Customer> customerList = new ArrayList<>();
        if (kw.equals("")) {
            customerList = customerDAO.selectAllCustomers();
        }else{
            customerList = customerDAO.searchByKw(kw);
        }
        request.setAttribute("listCustomers", customerList);
        request.getRequestDispatcher("/WEB-INF/customers.jsp").forward(request, response);

    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerDAO.deleteCustomer(id);
        response.sendRedirect("/customer");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCustomer = Integer.parseInt(request.getParameter("id"));

        Customer customer = customerDAO.selectCustomer(idCustomer);
        List<Province> provinces = provinceDAO.selectProvinces();

        request.setAttribute("customer", customer);
        request.setAttribute("provinces", provinces);
        request.getRequestDispatcher("/WEB-INF/edit.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Province> provinces = provinceDAO.selectProvinces();
        request.setAttribute("provinces", provinces);
        request.getRequestDispatcher("/WEB-INF/create.jsp").forward(request, response);
    }

}
