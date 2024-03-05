package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.InvoiceDAO;
import model.dao.RoomDAO;
import model.dao.ServiceDAO;
import model.entity.Invoice;
import model.entity.Service;
import util.InvoiceProcessService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookingUpdateServlet", value = "/booking-update")
public class BookingUpdateServlet extends HttpServlet {
    InvoiceDAO invoiceDAO = new InvoiceDAO();

    RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invoiceID = request.getParameter("invoiceId");
        Invoice invoice = invoiceDAO.getInvoiceByID(Integer.parseInt(invoiceID));
        ServiceDAO serviceDAO = new ServiceDAO();
        request.setAttribute("services", serviceDAO.getAllServices());
        request.setAttribute("invoice", invoice);
        request.getRequestDispatcher("booking-update.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String invoiceID = request.getParameter("invoiceId");
        String status = request.getParameter("status");
        if(status != null){
            if(status.equals("Cancelled") || status.equals("Checked out")){
            invoiceDAO.updateStatus(Integer.parseInt(invoiceID), status);
            roomDAO.updateRoomStatus(invoiceDAO.getRoomID(Integer.parseInt(invoiceID)), "Available");
            request.setAttribute("message", "Booking updated successfully");
            response.sendRedirect("booking-list");
        } else if(!status.isEmpty()) {
                invoiceDAO.updateStatus(Integer.parseInt(invoiceID), status);
                response.sendRedirect("booking-list");
            }
        } else {
            ServiceDAO serviceDAO = new ServiceDAO();
            List<Service> allServices = serviceDAO.getAllServices();
            List<Service> selectedServices = new ArrayList<>();
            for (Service service : allServices) {
                String quantityStr = request.getParameter("quantity" + service.getServiceId());
                if (quantityStr != null) {
                    int quantity = Integer.parseInt(quantityStr);
                    for (int i = 0; i < quantity; i++) {
                        selectedServices.add(service);
                    }
                }
            }
            if(invoiceDAO.updateInvoiceService(Integer.parseInt(invoiceID), selectedServices)){
                InvoiceProcessService invoiceProcessService = new InvoiceProcessService();
                if(invoiceProcessService.updateTotal(Integer.parseInt(invoiceID))){
                    request.setAttribute("message", "Booking updated successfully");
                    response.sendRedirect("booking-update?invoiceId=" + invoiceID);
                }

            }else{
                request.setAttribute("message", "Booking update failed");
                request.getRequestDispatcher("booking-update.jsp").forward(request, response);
            }
        }




    }
}
