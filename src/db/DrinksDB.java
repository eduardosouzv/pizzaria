package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Drink;

public class DrinksDB {
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
                drinks.add(new Drink(response.getString("name"), Float.parseFloat(response.getString("price"))));
            }
            return drinks;
        }
    }
}
