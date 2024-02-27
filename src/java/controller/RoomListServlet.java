package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.RoomDAO;

import java.io.IOException;

@WebServlet(name = "RoomListServlet", value = "/room-list")
public class RoomListServlet extends HttpServlet {
    RoomDAO roomDAO = new RoomDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("roomList", roomDAO.getAllRooms());
        request.getRequestDispatcher("room-list.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
