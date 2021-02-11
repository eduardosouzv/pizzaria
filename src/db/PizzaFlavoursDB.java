package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.PizzaFlavour;

public class PizzaFlavoursDB {

    public void addFlavour(PizzaFlavour flavour) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("INSERT INTO pizza_flavours(name) VALUES (?)");
            templateQuery.setString(1, flavour.name);

            int response = templateQuery.executeUpdate();

            if (response <= 0) {
                throw new Exception("operation failed");
            }
        }
    }

    public List<PizzaFlavour> getFlavours() throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("SELECT * FROM pizza_flavours;");

            List<PizzaFlavour> flavours = new ArrayList<>();
            ResultSet response = templateQuery.executeQuery();
            while (response.next()) {
                flavours.add(new PizzaFlavour(response.getInt("id"), response.getString("name")));
            }

            return flavours;
        }
    }
}
