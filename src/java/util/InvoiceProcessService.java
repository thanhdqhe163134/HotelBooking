package util;

import model.dao.InvoiceDAO;
import model.dao.RoomDAO;
import model.entity.*;
import util.Converttimestamp;
import util.DBConnect;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InvoiceProcessService {
    Connection connection;


    RoomDAO roomDAO = new RoomDAO();

    public boolean createInvoice(LocalDateTime checkInTime, LocalDateTime checkOutTime, Integer roomID, String paymentMethod, String note, Customer customer) {
        boolean flag = true;
            try {
                connection = DBConnect.getConnection();
                connection.setAutoCommit(false);
                InvoiceDAO invoiceDAO = new InvoiceDAO(connection);
                Integer invoiceId = invoiceDAO.createInvoice(customer, roomID, paymentMethod, note, checkInTime, checkOutTime);
                if(invoiceId != 0) {
                    Double servicePriceTotal = 0.0;
                    Room room = roomDAO.getRoomByID(roomID);
                    Invoice invoice = invoiceDAO.getSimpleInvoiceByID(invoiceId);
                    invoiceDAO.updateInvoiceTotal(invoiceId, Converttimestamp.calcTotal(invoice.getCheckInDate(), invoice.getCheckOutDate(), room.getPrice()) + servicePriceTotal);

                } else {
                    throw new SQLException("Invoice Error");
                }
            } catch (SQLException e) {
                try {
                    flag = false;
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } finally {
                // Đóng kết nối
                try {
                    connection.setAutoCommit(true); // Đặt lại auto-commit cho connection
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return flag;
    }

    public boolean updateTotal(Integer invoiceID) {
        boolean flag = true;
        try {
            connection = DBConnect.getConnection();
            connection.setAutoCommit(false);
            InvoiceDAO invoiceDAO = new InvoiceDAO(connection);
            Integer roomID = invoiceDAO.getRoomID(invoiceID);
            Double servicePriceTotal = 0.0;
            Room room = roomDAO.getRoomByID(roomID);
            Invoice invoice = invoiceDAO.getInvoiceByID(invoiceID);
            for(Service service : invoice.getServiceList()) {
                servicePriceTotal += service.getPrice();
            }
            invoiceDAO.updateInvoiceTotal(invoiceID, Converttimestamp.calcTotal(invoice.getCheckInDate(), invoice.getCheckOutDate(), room.getPrice()) + servicePriceTotal);


        } catch (SQLException e) {
            try {
                flag = false;
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // Đóng kết nối
            try {
                connection.setAutoCommit(true); // Đặt lại auto-commit cho connection
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }



}
