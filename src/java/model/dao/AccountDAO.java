package model.dao;

import model.entity.Account;
import util.DBConnect;
import util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AccountDAO {
    Connection connection = DBConnect.getConnection();

    PasswordUtil passwordUtil = new PasswordUtil();

    public boolean createAccount(String username, String password, String email) {
        if (connection != null) {
            try {
                String sql = "INSERT INTO Account (username, password, role) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                preparedStatement.setString(3, "user");
                preparedStatement.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean existsByUsernameOrEmail(String username, String email) {
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Account a LEFT JOIN Customer c ON a.CustomerID = c.CustomerID WHERE a.Username = ? OR c.Email = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);
                return preparedStatement.executeQuery().next();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public Account login(String username, String password) {
        if (connection != null) {
            try {
                String sql = "SELECT * FROM Account WHERE username = ? AND isDelete = 0";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");
                    if (PasswordUtil.validatePassword(password, storedPassword)) {
                        Account account = new Account();
                        account.setAccountId(resultSet.getInt("id"));
                        account.setUsername(resultSet.getString("username"));
                        account.setPassword(resultSet.getString("password"));
                        account.setRole(resultSet.getString("role"));
                        account.setCustomerId(resultSet.getInt("customerId"));
                        return account;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
