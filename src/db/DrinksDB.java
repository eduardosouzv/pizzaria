package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Drink;

public class DrinksDB {

    public void addDrink(Drink drink) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("INSERT INTO drinks(name, price) VALUES (?, ?)");
            templateQuery.setString(1, drink.name);
            templateQuery.setFloat(2, drink.price);

            int response = templateQuery.executeUpdate();

            if (response <= 0) {
                throw new Exception("operation failed");
            }
        }
    }

    public List<Drink> getDrink() throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("SELECT * FROM drinks;");

            List<Drink> drinks = new ArrayList<>();
            ResultSet response = templateQuery.executeQuery();
            while (response.next()) {
                drinks.add(new Drink(response.getInt("id"), response.getString("name"), response.getFloat("price")));
            }
            return drinks;
        }
    }

    public ArrayList<Drink> getDrinksFromOrder(String id) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement(
                    "select * from orders_has_drinks "
                    + "inner join drinks on drinks.id = orders_has_drinks.drinks_id "
                    + "where orders_has_drinks.orders_id = ?");
            templateQuery.setString(1, id);

            ResultSet response = templateQuery.executeQuery();
            ArrayList<Drink> drinks = new ArrayList<>();
            while (response.next()) {
                drinks.add(new Drink(response.getInt("id"), response.getString("name"), Float.parseFloat(response.getString("price"))));
            }
            return drinks;
        }
    }
}
