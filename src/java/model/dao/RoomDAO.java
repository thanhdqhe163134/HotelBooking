package model.dao;

import model.entity.Room;
import util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    Connection connection = DBConnect.getConnection();

    public boolean createRoom(String roomType, String roomPrice, String roomStatus, String roomDescription, String relativePath, String createdBy) {
        if (connection != null) {
            try {
                String sql = "INSERT INTO Room(roomType, Price, Status, Description, IMG, createdDate, createdBy) VALUES(?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, roomType);
                preparedStatement.setString(2, roomPrice);
                preparedStatement.setString(3, "Available");
                preparedStatement.setString(4, roomDescription);
                preparedStatement.setString(5, relativePath);
                preparedStatement.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
                preparedStatement.setString(7, createdBy);
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Room> getAllRooms() {
        List<Room> roomList = new ArrayList<>();
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Room where isDelete = 0";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Room room = new Room();
                    room.setRoomId(resultSet.getInt("roomID"));
                    room.setRoomType(resultSet.getString("roomType"));
                    room.setPrice(resultSet.getDouble("Price"));
                    room.setStatus(resultSet.getString("Status"));
                    room.setDescription(resultSet.getString("Description"));
                    room.setIMG(resultSet.getString("IMG"));
                    roomList.add(room);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roomList;
    }

    public Room getRoomByID(int roomID) {
        Room room = null;
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Room WHERE isDelete = 0 AND roomID = " + roomID;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    room = new Room();
                    room.setRoomId(resultSet.getInt("roomID"));
                    room.setRoomType(resultSet.getString("roomType"));
                    room.setPrice(resultSet.getDouble("Price"));
                    room.setStatus(resultSet.getString("Status"));
                    room.setIMG(resultSet.getString("IMG"));
                    room.setDescription(resultSet.getString("Description"));
                    room.setCreatedDate(resultSet.getDate("createdDate"));
                    room.setCreatedBy(resultSet.getString("createdBy"));
                    room.setUpdatedDate(resultSet.getDate("updatedDate"));
                    room.setUpdatedBy(resultSet.getString("updatedBy"));
                    room.setDelete(resultSet.getBoolean("isDelete"));
                    room.setDeletedDate(resultSet.getDate("deletedDate"));
                    room.setDeletedBy(resultSet.getString("deletedBy"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return room;
    }



    public List<Room> filterRooms(String priceFilter, String statusFilter, String typeFilter) {
        List<Room> roomList = new ArrayList<>();
        if (connection != null) {
            try {
                // Start building the SQL query
                StringBuilder sql = new StringBuilder("SELECT * FROM Room WHERE isDelete = 0");

                if (!"all".equals(statusFilter)) {
                    sql.append(" AND Status = '").append(statusFilter).append("'");
                }
                if (!"all".equals(typeFilter)) {
                    sql.append(" AND roomType = '").append(typeFilter).append("'");
                }

                // Add filters to the SQL query
                if (!"all".equals(priceFilter)) {
                    if ("low".equals(priceFilter)) {
                        sql.append(" ORDER BY Price ASC");
                    } else if ("high".equals(priceFilter)) {
                        sql.append(" ORDER BY Price DESC");
                    }
                }

                // Execute the SQL query
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql.toString());

                // Process the result set
                while (resultSet.next()) {
                    Room room = new Room();
                    room.setRoomId(resultSet.getInt("roomID"));
                    room.setRoomType(resultSet.getString("roomType"));
                    room.setPrice(resultSet.getDouble("Price"));
                    room.setStatus(resultSet.getString("Status"));
                    room.setDescription(resultSet.getString("Description"));
                    room.setIMG(resultSet.getString("IMG"));
                    roomList.add(room);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roomList;
    }

    public void updateRoomStatus(Integer roomID, String unavailable) {
        if (connection != null) {
            try {
                String sql = "UPDATE Room SET Status = ? WHERE roomID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, unavailable);
                preparedStatement.setInt(2, roomID);
                preparedStatement.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean updateRoom(int roomID, String roomType, String roomPrice, String roomStatus, String roomDescription, String relativePath, String username) {
        if (connection != null) {
            try {
                String sql = "UPDATE Room SET roomType = ?, Price = ?, Status = ?, Description = ?, IMG = ?, updatedDate = ?, updatedBy = ? WHERE roomID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, roomType);
                preparedStatement.setString(2, roomPrice);
                preparedStatement.setString(3, roomStatus);
                preparedStatement.setString(4, roomDescription);
                preparedStatement.setString(5, relativePath);
                preparedStatement.setTimestamp(6, new java.sql.Timestamp(System.currentTimeMillis()));
                preparedStatement.setString(7, username);
                preparedStatement.setInt(8, roomID);
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean deleteRoom(int id) {
        if (connection != null) {
            try {
                String sql = "UPDATE Room SET isDelete = 1, deletedDate = ?, deletedBy = ? WHERE roomID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
                preparedStatement.setString(2, "admin");
                preparedStatement.setInt(3, id);
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
