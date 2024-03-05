package model.dao;

import model.entity.Customer;
import model.entity.Invoice;
import model.entity.Service;
import util.Converttimestamp;
import util.DBConnect;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {
    Connection connection;
    ResultSet rs = null;
    PreparedStatement preparedStatement = null;

    public InvoiceDAO(Connection connection) {
        this.connection = connection;
    }

    public InvoiceDAO() {
        this.connection = DBConnect.getConnection();
    }

    public Integer createInvoice(Customer customer, Integer roomID, String paymentMethod, String note, LocalDateTime checkInTime, LocalDateTime checkOutTime) throws SQLException {
        Integer invoiceId = 0;
        if (connection != null) {
            try {
                String sql = "INSERT INTO Invoice (CustomerID, RoomID, CheckInDate, CheckOutDate, PaymentMethod, Note, CreatedDate, CreatedBy, UpdatedDate, UpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, customer.getCustomerId());
                preparedStatement.setInt(2, roomID);
                preparedStatement.setTimestamp(3, Converttimestamp.convertLocalDateTimeToDate(checkInTime));
                preparedStatement.setTimestamp(4, Converttimestamp.convertLocalDateTimeToDate(checkOutTime));
                preparedStatement.setString(5, paymentMethod);
                preparedStatement.setString(6, "Unpaid");
                preparedStatement.setTimestamp(7, Converttimestamp.convertLocalDateTimeToDate(LocalDateTime.now()));
                preparedStatement.setString(8, customer.getName());
                preparedStatement.setTimestamp(9, Converttimestamp.convertLocalDateTimeToDate(LocalDateTime.now()));
                preparedStatement.setString(10, customer.getName());
                preparedStatement.executeUpdate();
                connection.commit();

                String getInvoiceIdSQL = "SELECT TOP 1 * FROM Invoice ORDER BY InvoiceID DESC";
                preparedStatement = connection.prepareStatement(getInvoiceIdSQL);
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    invoiceId = rs.getInt(1);
                }
                connection.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return invoiceId;
    }


    public boolean updateInvoiceTotal(Integer invoiceID, Double total) {
        if (connection != null) {
            try {
                String sql = "UPDATE Invoice SET Total = ? WHERE InvoiceID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1, total);
                preparedStatement.setInt(2, invoiceID);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean duplicateCheck(LocalDateTime checkInTime, LocalDateTime checkOutTime, Integer roomID) throws SQLException {
        if (connection != null) {
            String duplicateCheckSql = "SELECT COUNT(*) FROM Invoice WHERE RoomID = ? AND ((CheckInDate <= ? AND CheckOutDate >= ?) OR (CheckInDate >= ? AND CheckOutDate <= ?) OR (CheckInDate <= ? AND CheckOutDate >= ?))";
            PreparedStatement duplicateCheckStatement = connection.prepareStatement(duplicateCheckSql);
            duplicateCheckStatement.setInt(1, roomID);
            duplicateCheckStatement.setTimestamp(2, Converttimestamp.convertLocalDateTimeToDate(checkInTime));
            duplicateCheckStatement.setTimestamp(3, Converttimestamp.convertLocalDateTimeToDate(checkInTime));
            duplicateCheckStatement.setTimestamp(4, Converttimestamp.convertLocalDateTimeToDate(checkInTime));
            duplicateCheckStatement.setTimestamp(5, Converttimestamp.convertLocalDateTimeToDate(checkOutTime));
            duplicateCheckStatement.setTimestamp(6, Converttimestamp.convertLocalDateTimeToDate(checkOutTime));
            duplicateCheckStatement.setTimestamp(7, Converttimestamp.convertLocalDateTimeToDate(checkOutTime));
            ResultSet resultSet = duplicateCheckStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count > 0) return true;
        }
        return false;
    }

    public Invoice getSimpleInvoiceByID(Integer invoiceID) {
        Invoice invoice = new Invoice();
        try {
            String sql = "SELECT * FROM Invoice WHERE InvoiceID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            Date currentDate = new Date(System.currentTimeMillis());

            preparedStatement.setInt(1, invoiceID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                invoice.setInvoiceId(resultSet.getInt("InvoiceID"));
                invoice.setRoomId(resultSet.getInt("RoomID"));
                invoice.setTotal(resultSet.getDouble("Total"));
                invoice.setCheckInDate(resultSet.getTimestamp("CheckInDate"));
                invoice.setCheckOutDate(resultSet.getTimestamp("CheckOutDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }

    public List<Invoice> getInvoiceByCustomerID(Integer customerID) {
        List<Invoice> invoiceList = new ArrayList<>();
        try {
            String sql = "SELECT* FROM Invoice WHERE CustomerID = ? ORDER BY CreatedDate DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getInt("InvoiceID"));
                invoice.setRoomId(resultSet.getInt("RoomID"));
                invoice.setTotal(resultSet.getDouble("Total"));
                invoice.setCheckInDate(resultSet.getTimestamp("CheckInDate"));
                invoice.setCheckOutDate(resultSet.getTimestamp("CheckOutDate"));
                invoice.setPaymentMethod(resultSet.getString("PaymentMethod"));
                invoice.setNote(resultSet.getString("Note"));
                invoice.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
                invoiceList.add(invoice);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    public List<Invoice> getInvoiceByCustomerIDSort(int customerID, String sort) {
        List<Invoice> invoiceList = new ArrayList<>();
        try {
            String sql = "SELECT* FROM Invoice WHERE CustomerID = ? ORDER BY " + sort + " DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customerID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getInt("InvoiceID"));
                invoice.setRoomId(resultSet.getInt("RoomID"));
                invoice.setTotal(resultSet.getDouble("Total"));
                invoice.setCheckInDate(resultSet.getTimestamp("CheckInDate"));
                invoice.setCheckOutDate(resultSet.getTimestamp("CheckOutDate"));
                invoice.setPaymentMethod(resultSet.getString("PaymentMethod"));
                invoice.setNote(resultSet.getString("Note"));
                invoice.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
                invoiceList.add(invoice);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    public List<Invoice> getAll() {
        List<Invoice> invoiceList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Invoice ORDER BY CreatedDate DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getInt("InvoiceID"));
                invoice.setRoomId(resultSet.getInt("RoomID"));
                invoice.setTotal(resultSet.getDouble("Total"));
                invoice.setCheckInDate(resultSet.getTimestamp("CheckInDate"));
                invoice.setCheckOutDate(resultSet.getTimestamp("CheckOutDate"));
                invoice.setPaymentMethod(resultSet.getString("PaymentMethod"));
                invoice.setNote(resultSet.getString("Note"));
                invoice.setCustomerId(resultSet.getInt("CustomerID"));
                invoice.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
                invoiceList.add(invoice);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    public List<Invoice> getAllSort(String sort) {
        List<Invoice> invoiceList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Invoice  ORDER BY " + sort + " DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Invoice invoice = new Invoice();
                invoice.setInvoiceId(resultSet.getInt("InvoiceID"));
                invoice.setRoomId(resultSet.getInt("RoomID"));
                invoice.setTotal(resultSet.getDouble("Total"));
                invoice.setCheckInDate(resultSet.getTimestamp("CheckInDate"));
                invoice.setCheckOutDate(resultSet.getTimestamp("CheckOutDate"));
                invoice.setPaymentMethod(resultSet.getString("PaymentMethod"));
                invoice.setNote(resultSet.getString("Note"));
                invoice.setCustomerId(resultSet.getInt("CustomerID"));
                invoice.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
                invoiceList.add(invoice);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceList;
    }

    public boolean updateStatus(int parseInt, String status) {
        if (connection != null) {
            try {
                String sql = "UPDATE Invoice SET Note = ? WHERE InvoiceID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, status);
                preparedStatement.setInt(2, parseInt);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Integer getRoomID(int parseInt) {
        if (connection != null) {
            try {
                String sql = "SELECT RoomID FROM Invoice WHERE InvoiceID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, parseInt);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Invoice getInvoiceByID(int parseInt) {
        Invoice invoice = new Invoice();
        try {
            String sql = "SELECT Invoice.*, Service.* FROM Invoice " +
                    "LEFT JOIN InvoiceService ON Invoice.InvoiceID = InvoiceService.InvoiceID " +
                    "LEFT JOIN Service ON InvoiceService.ServiceID = Service.ServiceID " +
                    "WHERE Invoice.InvoiceID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, parseInt);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Service> services = new ArrayList<>();
            while (resultSet.next()) {
                if (invoice.getInvoiceId() == 0) {
                    invoice.setInvoiceId(resultSet.getInt("InvoiceID"));
                    invoice.setRoomId(resultSet.getInt("RoomID"));
                    invoice.setTotal(resultSet.getDouble("Total"));
                    invoice.setCheckInDate(resultSet.getTimestamp("CheckInDate"));
                    invoice.setCheckOutDate(resultSet.getTimestamp("CheckOutDate"));
                    invoice.setPaymentMethod(resultSet.getString("PaymentMethod"));
                    invoice.setNote(resultSet.getString("Note"));
                    invoice.setCustomerId(resultSet.getInt("CustomerID"));
                    invoice.setCreatedDate(resultSet.getTimestamp("CreatedDate"));
                }
                Service service = new Service();
                service.setServiceId(resultSet.getInt("ServiceID"));
                service.setName(resultSet.getString("Name"));
                service.setPrice(resultSet.getDouble("Price"));
                services.add(service);
            }
            invoice.setServiceList(services);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoice;
    }


    public boolean updateInvoiceService(int parseInt, List<Service> selectedServices) {
if (connection != null) {
            try {
                String sql = "DELETE FROM InvoiceService WHERE InvoiceID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, parseInt);
                preparedStatement.executeUpdate();
                for (Service service : selectedServices) {
                    String insertSQL = "INSERT INTO InvoiceService (InvoiceID, ServiceID) VALUES (?, ?)";
                    PreparedStatement insertStatement = connection.prepareStatement(insertSQL);
                    insertStatement.setInt(1, parseInt);
                    insertStatement.setInt(2, service.getServiceId());
                    insertStatement.executeUpdate();
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}


