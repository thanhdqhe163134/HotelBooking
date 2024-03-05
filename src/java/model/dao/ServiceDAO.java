package model.dao;

import model.entity.Service;
import util.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ServiceDAO {
    Connection connection = DBConnect.getConnection();

    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Service";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Service service = new Service();
                    service.setServiceId(resultSet.getInt("ServiceID"));
                    service.setName(resultSet.getString("name"));
                    service.setPrice(resultSet.getDouble("Price"));
                    services.add(service);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return services;
    }

}
