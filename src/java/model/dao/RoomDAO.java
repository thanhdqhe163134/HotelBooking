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
    public boolean createRoom(String roomType, String roomPrice, String roomStatus, String roomDescription) {
        if (connection != null) {
            try {
                String sql = "INSERT INTO Room (roomType, roomPrice, roomStatus, roomDescription) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, roomType);
                preparedStatement.setString(2, roomPrice);
                preparedStatement.setString(3, roomStatus);
                preparedStatement.setString(4, roomDescription);
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
        if(connection != null){
            try {
                String sql = "SELECT * FROM Room where isDelete = 0";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);
                while (resultSet.next()) {
                    Room room = new Room();
                    room.setRoomId(resultSet.getInt("roomID"));
                    room.setRoomType(resultSet.getString("roomType"));
                    room.setRoomType(resultSet.getString("roomPrice"));
                    room.setStatus(resultSet.getString("roomStatus"));
                    room.setDescription(resultSet.getString("roomDescription"));
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
                    room.setRoomType(resultSet.getString("roomPrice"));
                    room.setStatus(resultSet.getString("roomStatus"));
                    room.setDescription(resultSet.getString("roomDescription"));
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

    public boolean updateRoom(int roomID, String roomType, String roomPrice, String roomStatus, String roomDescription) {
        if (connection != null) {
            try {
                String sql = "UPDATE Room SET roomType = ?, roomPrice = ?, roomStatus = ?, roomDescription = ?, updatedDate = ?, updatedBy = ? WHERE roomID = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, roomType);
                preparedStatement.setString(2, roomPrice);
                preparedStatement.setString(3, roomStatus);
                preparedStatement.setString(4, roomDescription);
                preparedStatement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
                preparedStatement.setString(6, "admin");
                preparedStatement.setInt(7, roomID);
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
