package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PizzaDB {

    public ArrayList<String> getSelectedPizzaFromId(String id) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement(
                    "select * from pizzas_has_pizza_flavours inner join pizza_flavours on pizza_flavours.id = pizzas_has_pizza_flavours.pizza_flavours_id where pizzas_id = ?");
            templateQuery.setString(1, id);

            ResultSet response = templateQuery.executeQuery();
            ArrayList<String> flavours = new ArrayList<>();
            while (response.next()) {
                flavours.add(response.getString("name"));
            }
            return flavours;
        }
    }

    public ArrayList<String> getAllPizzasIdFromOrder(String id) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("select pizzas_id from orders_has_pizzas where orders_id = ?");
            templateQuery.setString(1, id);

            ResultSet response = templateQuery.executeQuery();
            ArrayList<String> pizzas = new ArrayList<>();
            while (response.next()) {
                pizzas.add(response.getString("pizzas_id"));
            }
            return pizzas;
        }
    }

    public ArrayList<String> getPizzaSizeFromId(String id) throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("select * from pizzas inner join pizza_sizes on pizzas.pizza_sizes_id = pizza_sizes.id where pizzas.id = ?");
            templateQuery.setString(1, id);

            ResultSet response = templateQuery.executeQuery();
            ArrayList<String> sizes = new ArrayList<>();
            while (response.next()) {
                sizes.add(response.getString("description"));
            }
            return sizes;
        }
    }
}
