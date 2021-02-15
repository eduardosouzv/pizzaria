package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.User;

public class OrdersDB {

    public ArrayList<Integer> getOrders() throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("select * from orders where status = ?");
            templateQuery.setString(1, "ANDAMENTO");

            ResultSet response = templateQuery.executeQuery();
            ArrayList<Integer> ids = new ArrayList<>();
            while (response.next()) {
                ids.add(Integer.parseInt(response.getString("id")));
            }
            return ids;
        }
    }

    public String getNameFromOrder(String id) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("SELECT * FROM orders INNER JOIN users ON orders.users_id = users.id WHERE orders.id = ?");
            templateQuery.setString(1, id);

            ResultSet response = templateQuery.executeQuery();
            if (response.next()) {
                return response.getString("user");
            }
        }
        return null;
    }

    public void setOrderStatus(String id) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("update orders set status = 'ENTREGUE' where id = ?");
            templateQuery.setString(1, id);

            int response = templateQuery.executeUpdate();
            if (response <= 0) {
                throw new Exception("operation failed");
            }
        }
    }

    public ArrayList<Integer> getDeliveredOrders() throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("select * from orders where status = ?");
            templateQuery.setString(1, "ENTREGUE");

            ResultSet response = templateQuery.executeQuery();
            ArrayList<Integer> ids = new ArrayList<>();
            while (response.next()) {
                ids.add(Integer.parseInt(response.getString("id")));
            }
            return ids;
        }
    }

    public User getUserFromID(String id) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("select * from users inner join orders on users.id = orders.users_id where orders.id = ?");
            templateQuery.setString(1, id);

            ResultSet response = templateQuery.executeQuery();
            while (response.next()) {
                String username = response.getString("user");
                String user_type = response.getString("user_type");
                String address_street = response.getString("address_street");
                int address_number = response.getInt("address_number");
                String address_district = response.getString("address_district");
                String address_city = response.getString("address_city");

                User user = new User(username, null, user_type, address_street, address_number, address_district, address_city);
                return user;
            }
        }
        return null;
    }
}
