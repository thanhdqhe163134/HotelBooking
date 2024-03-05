package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.RoomDAO;

import java.io.IOException;

@WebServlet(name = "RoomDeleteServlet", value = "/delete-room")
public class RoomDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomID = request.getParameter("roomID");
        request.setAttribute("roomID", roomID);
        doPost(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomID = (String) request.getAttribute("roomID");
        int id = Integer.parseInt(roomID);
        RoomDAO roomDAO = new RoomDAO();
        if(roomDAO.deleteRoom(id)) {
            request.setAttribute("message", "Room deleted successfully");
            response.sendRedirect("room-list");
        } else {
            request.setAttribute("message", "Room deletion failed");
            request.getRequestDispatcher("room-list").forward(request, response);
        }
    }
}
