package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.InvoiceDAO;

import java.io.IOException;

@WebServlet(name = "BookingAdminListServlet", value = "/booking-list")
public class BookingAdminListServlet extends HttpServlet {
    InvoiceDAO invoiceDAO = new InvoiceDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerID = ((model.entity.Customer) request.getSession().getAttribute("customer")).getCustomerId();
        String sort = request.getParameter("Sort");
        if (sort == null || sort.isEmpty()) {
            request.setAttribute("invoiceList", invoiceDAO.getAll());
            request.getRequestDispatcher("booking-list-admin.jsp").forward(request, response);
        }
        else {
            request.setAttribute("invoiceList", invoiceDAO.getAllSort(sort));
            request.getRequestDispatcher("booking-list-admin.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
