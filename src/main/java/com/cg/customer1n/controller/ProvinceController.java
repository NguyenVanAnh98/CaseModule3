package com.cg.customer1n.controller;

import com.cg.customer1n.dao.IProvinceDAO;
import com.cg.customer1n.dao.ProvinceDAO;
import com.cg.customer1n.model.Customer;
import com.cg.customer1n.model.Province;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProvinceController", urlPatterns = "/province")
public class ProvinceController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IProvinceDAO provinceDAO;

    public void init() {
        provinceDAO = new ProvinceDAO();
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
                deleteProvince(request, response);
                break;
            default:
                listProvinces(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                insertProvince(request, response);
                break;
            case "edit":
                updateProvince(request, response);
                break;
        }
    }

    private void listProvinces(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        List<Province> provinceList = provinceDAO.selectProvinces();

        request.setAttribute("listProvinces", provinceList);
        request.getRequestDispatcher("/WEB-INF/provinces.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int provinceId = Integer.parseInt(request.getParameter("id"));
        Province province = provinceDAO.findProvinceById(provinceId);
        request.setAttribute("province", province);
        request.getRequestDispatcher("/WEB-INF/edit_province.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/create_province.jsp").forward(request, response);
    }

    private void insertProvince(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        Province province = new Province(name);
        provinceDAO.insertProvince(province);
        response.sendRedirect("/province");
    }

    private void updateProvince(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Province province = new Province(id, name);
        provinceDAO.updateProvince(province);
        response.sendRedirect("/province");
    }

    private void deleteProvince(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        provinceDAO.deleteProvince(id);
        response.sendRedirect("/province");
    }
}