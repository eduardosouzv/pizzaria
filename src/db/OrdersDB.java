package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
}
