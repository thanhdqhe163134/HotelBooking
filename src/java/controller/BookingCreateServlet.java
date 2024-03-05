package controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.*;
import model.entity.Customer;
import util.InvoiceProcessService;

@WebServlet(name = "BookingCreateServlet", value = "/create-booking")
public class BookingCreateServlet extends HttpServlet {



    InvoiceDAO invoiceDAO = new InvoiceDAO();

    CustomerDAO customerDAO = new CustomerDAO();
    InvoiceProcessService invoiceProcessService = new InvoiceProcessService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String checkInDateStr = request.getParameter("check_in_date");
        String checkOutDateStr = request.getParameter("check_out_date");
        String roomID = request.getParameter("roomID");
        if(request.getSession().getAttribute("customer") != null) {
            request.setAttribute("roomID", roomID);

            if (checkInDateStr != null && checkOutDateStr != null) {
                if(!checkInDateStr.isEmpty() && !checkOutDateStr.isEmpty()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    LocalDateTime checkInDate = LocalDateTime.parse(checkInDateStr, formatter);
                    LocalDateTime checkOutDate = LocalDateTime.parse(checkOutDateStr, formatter);

                    request.setAttribute("checkInDate", checkInDate);
                    request.setAttribute("checkOutDate", checkOutDate);
                }
            }

            request.getRequestDispatcher("booking.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("checkInDate", checkInDateStr);
            session.setAttribute("checkOutDate", checkOutDateStr);
            session.setAttribute("roomID", roomID);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String checkInDateStr = request.getParameter("check_in_time");
        String checkOutDateStr = request.getParameter("check_out_time");
        if (checkInDateStr != null && checkOutDateStr != null) {
            if (!checkInDateStr.isEmpty() && !checkOutDateStr.isEmpty()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                LocalDateTime checkInTime = LocalDateTime.parse(checkInDateStr, formatter);
                LocalDateTime checkOutTime = LocalDateTime.parse(checkOutDateStr, formatter);

                Integer roomID = Integer.parseInt(request.getParameter("roomID"));
                String paymentMethod = request.getParameter("payment");

                String note = request.getParameter("note");

                HttpSession session = request.getSession();
                Customer customer = (Customer) session.getAttribute("customer");
                if(invoiceProcessService.createInvoice(checkInTime, checkOutTime, roomID, paymentMethod, note, customer)) {
                    RoomDAO roomDAO = new RoomDAO();
                    roomDAO.updateRoomStatus(roomID, "Unavailable");
                    session.removeAttribute("checkInDate");
                    session.removeAttribute("checkOutDate");
                    session.removeAttribute("roomID");
                    response.sendRedirect(request.getContextPath() + "/room-list?message=success");


                } else {
                    session.removeAttribute("checkInDate");
                    session.removeAttribute("checkOutDate");
                    session.removeAttribute("roomID");
                    response.sendRedirect(request.getContextPath() + "/room-list?message=error");
                }
            }
        }
    }

    public void destroy() {
    }
}