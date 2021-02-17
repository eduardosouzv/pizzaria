package db;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.User;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDB {

    public User addUser(User user) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("INSERT INTO users(user,password,user_type,address_street,address_number,address_district,address_city,telephone) VALUES (?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            templateQuery.setString(1, user.user);
            templateQuery.setString(2, user.password);
            templateQuery.setString(3, user.user_type);
            templateQuery.setString(4, user.address_street);
            templateQuery.setInt(5, user.address_number);
            templateQuery.setString(6, user.address_district);
            templateQuery.setString(7, user.address_city);
            templateQuery.setLong(8, user.telephone);

            try {
                int response = templateQuery.executeUpdate();

                if (response <= 0) {
                    throw new Exception("operation failed");
                }
            } catch (SQLException e) {
                throw new MySQLIntegrityConstraintViolationException();
            }

            try (ResultSet generatedKeys = templateQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.id = generatedKeys.getInt(1);
                    return user;
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    public User authenticate(String user, String pass) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("SELECT * FROM users WHERE user = ? AND password = ?");
            templateQuery.setString(1, user);
            templateQuery.setString(2, pass);

            ResultSet response = templateQuery.executeQuery();

            if (response.next()) {
                return new User(
                        Integer.parseInt(response.getString("id")),
                        response.getString("user"),
                        response.getString("password"),
                        response.getString("user_type"),
                        response.getString("address_street"),
                        Integer.parseInt(response.getString("address_number")),
                        response.getString("address_district"),
                        response.getString("address_city"),
                        response.getLong("telephone")
                );
            } else {
                throw new Exception("operation failed");
            }

        }
    }
}
