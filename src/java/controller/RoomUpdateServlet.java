package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.dao.RoomDAO;
import model.entity.Room;
import util.ConvertIMG;

import java.io.IOException;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
@WebServlet(name = "RoomUpdateServlet", value = "/update-room")
public class RoomUpdateServlet extends HttpServlet {

    RoomDAO roomDAO = new RoomDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomID = request.getParameter("roomID");
        request.setAttribute("room", roomDAO.getRoomByID(Integer.parseInt(roomID)));
        request.getRequestDispatcher("room-update.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomIDStr = request.getParameter("roomID");
        String roomType = request.getParameter("roomType");
        String roomPrice = request.getParameter("roomPrice");
        String roomStatus = request.getParameter("status");
        String roomDescription = request.getParameter("roomDescription");
        String username = request.getParameter("username");
        Part filePart = request.getPart("roomImage");
        int roomID = Integer.parseInt(roomIDStr);
        String relativePath = null;
        if (filePart.getSize() > 0) { // Kiểm tra xem người dùng có tải lên hình ảnh mới không
            relativePath = ConvertIMG.saveImage(filePart, request, "images"); // Nếu có, lưu hình ảnh mới
        } else {
            Room currentRoom = roomDAO.getRoomByID(roomID);
            relativePath = currentRoom.getIMG(); // Nếu không, giữ nguyên hình ảnh hiện tại
        }
        if(roomDAO.updateRoom(roomID, roomType, roomPrice, roomStatus, roomDescription, relativePath, username)){
            request.setAttribute("message", "Room updated successfully");
            response.sendRedirect("room-list");
        }else{
            request.setAttribute("message", "Room update failed");
            request.getRequestDispatcher("room-update.jsp").forward(request, response);
        }
    }
}
