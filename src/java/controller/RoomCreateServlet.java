package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.RoomDAO;
import util.ConvertIMG;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
@WebServlet(name = "RoomCreateServlet", value = "/create-room")
public class RoomCreateServlet extends HttpServlet {
    RoomDAO roomDAO = new RoomDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("room-create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String roomType = request.getParameter("roomType");
        String roomPrice = request.getParameter("roomPrice");
        String roomStatus = request.getParameter("roomStatus");
        String roomDescription = request.getParameter("roomDescription");
        Part filePart = request.getPart("roomImage");
        String relativePath = ConvertIMG.saveImage(filePart, request, "images");

        if(roomDAO.createRoom(roomType, roomPrice, roomStatus, roomDescription, relativePath, username)){
            request.setAttribute("message", "Room created successfully");
            response.sendRedirect("room-list");
        }else{
            request.setAttribute("message", "Room creation failed");
            request.getRequestDispatcher("room-create.jsp").forward(request, response);
        }
    }
}
