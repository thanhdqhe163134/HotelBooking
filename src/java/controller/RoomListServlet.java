package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.RoomDAO;
import model.entity.Room;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "RoomListServlet", value = "/room-list")
public class RoomListServlet extends HttpServlet {
    RoomDAO roomDAO = new RoomDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String priceFilter = request.getParameter("priceFilter");
        String statusFilter = request.getParameter("statusFilter");
        String typeFilter = request.getParameter("typeFilter");
        List<Room> roomList;
        if ((priceFilter == null || "all".equals(priceFilter)) &&
                (statusFilter == null || "all".equals(statusFilter)) &&
                (typeFilter == null || "all".equals(typeFilter))) {
            roomList = roomDAO.getAllRooms();
        } else {
            roomList = roomDAO.filterRooms(priceFilter, statusFilter, typeFilter);
        }
        request.setAttribute("roomList", roomList);
        request.getRequestDispatcher("room-list.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
