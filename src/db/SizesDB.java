package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.PizzaSize;

public class SizesDB {

    public List<PizzaSize> getSizes() throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("SELECT * FROM pizza_sizes;");

            List<PizzaSize> sizes = new ArrayList<>();
            ResultSet response = templateQuery.executeQuery();
            while (response.next()) {
                sizes.add(new PizzaSize(response.getInt("id"), response.getString("description"), response.getFloat("price"), response.getInt("max_flavours")));
            }

            return sizes;
        }
    }
}
