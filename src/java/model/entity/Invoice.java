package model.entity;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Invoice {
    private int invoiceId;
    private int customerId;
    private int roomId;
    private Timestamp checkInDate;
    private Timestamp checkOutDate;
    private double total;
    private String paymentMethod;
    private String note;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

    List<Service> serviceList;

    public Invoice() {
    }

    public Invoice(int invoiceId, int customerId, int roomId, Timestamp checkInDate, Timestamp checkOutDate, double total, String paymentMethod, String note, Date createdDate, String createdBy, Date updatedDate, String updatedBy) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.note = note;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
    }

    public Invoice(int invoiceId, int customerId, int roomId, Timestamp checkInDate, Timestamp checkOutDate, double total, String paymentMethod, String note) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.note = note;
    }

    public Invoice(int invoiceId, int customerId, int roomId, Timestamp checkInDate, Timestamp checkOutDate, double total, String paymentMethod, String note, Date createdDate, String createdBy, Date updatedDate, String updatedBy, List<Service> serviceList) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.note = note;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.serviceList = serviceList;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Timestamp getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Timestamp checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Timestamp getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Timestamp checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
