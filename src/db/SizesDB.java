package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.PizzaSize;

public class SizesDB {

    public void addSize(PizzaSize size) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("INSERT INTO pizza_sizes(description, price) VALUES (?, ?)");
            templateQuery.setString(1, size.description);
            templateQuery.setFloat(2, size.price);

            int response = templateQuery.executeUpdate();

            if (response <= 0) {
                throw new Exception("operation failed");
            }
        }
    }

    public List<PizzaSize> getSizes() throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("SELECT * FROM pizza_sizes;");

            List<PizzaSize> sizes = new ArrayList<>();
            ResultSet response = templateQuery.executeQuery();
            while (response.next()) {
                sizes.add(new PizzaSize(response.getInt("id"), response.getString("description"), response.getFloat("price")));
            }

            return sizes;
        }
    }
}
