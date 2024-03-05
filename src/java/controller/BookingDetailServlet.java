package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.InvoiceDAO;
import model.dao.ServiceDAO;
import model.entity.Service;

import java.io.IOException;

@WebServlet(name = "BookingDetailServlet", value = "/booking-detail")
public class BookingDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invoiceID = request.getParameter("invoiceId");
        InvoiceDAO invoiceDAO = new InvoiceDAO();
        request.setAttribute("invoice", invoiceDAO.getInvoiceByID(Integer.parseInt(invoiceID)));
        request.getRequestDispatcher("booking-detail.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
