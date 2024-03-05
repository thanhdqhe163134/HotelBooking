package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.InvoiceDAO;

import java.io.IOException;

@WebServlet(name = "BookingListServlet", value = "/booked-list")
public class BookingListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        int customerID = ((model.entity.Customer) request.getSession().getAttribute("customer")).getCustomerId();
        String sort = request.getParameter("Sort");
        if (sort == null || sort.isEmpty()) {
            request.setAttribute("invoiceList", invoiceDAO.getInvoiceByCustomerID(customerID));
            request.getRequestDispatcher("booked-list.jsp").forward(request, response);
        }
        else {
            request.setAttribute("invoiceList", invoiceDAO.getInvoiceByCustomerIDSort(customerID, sort));
            request.getRequestDispatcher("booked-list.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
