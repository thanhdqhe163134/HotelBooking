package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.CustomerDAO;
import model.entity.Customer;

import java.io.IOException;

@WebServlet(name = "AccountProfileServlet", value = "/profile")
public class AccountProfileServlet extends HttpServlet {
    private final CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = ((model.entity.Account) request.getSession().getAttribute("loggedInUser")).getAccountId();
        Customer customer = customerDAO.findByAccountId(accountId);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("account-profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String personalInfo = request.getParameter("personalInfo");

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setPersonalInfo(personalInfo);

        customerDAO.updateCustomer(customer);

        response.sendRedirect("profile");
    }
}