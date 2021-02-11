package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.Drink;
import models.Order;
import models.Pizza;
import models.PizzaFlavour;

public class OrderDB {

    public void addOrder(Order order) throws SQLException, Exception {
        Connection connection = Connect.Database();
        try {
            connection.setAutoCommit(false);
            PreparedStatement templateQuery = connection.prepareStatement("INSERT INTO orders(users_id, status) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            templateQuery.setInt(1, order.user.id);
            templateQuery.setString(2, order.status);

            int response = templateQuery.executeUpdate();
            try (ResultSet generatedKeys = templateQuery.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.id = generatedKeys.getInt(1);
                }
            }

            for (Pizza pizza : order.pizzas) {
                templateQuery = connection.prepareStatement("INSERT INTO pizzas(pizza_sizes_id) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                templateQuery.setInt(1, pizza.size.id);
                templateQuery.executeUpdate();

                try (ResultSet generatedKeys = templateQuery.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        pizza.id = generatedKeys.getInt(1);
                    }
                }
                templateQuery = connection.prepareStatement("INSERT INTO orders_has_pizzas(orders_id, pizzas_id) VALUES (?, ?)");
                templateQuery.setInt(1, order.id);
                templateQuery.setInt(2, pizza.id);
                templateQuery.executeUpdate();

                for (PizzaFlavour flavour : pizza.flavours) {
                    templateQuery = connection.prepareStatement("INSERT INTO pizzas_has_pizza_flavours(pizzas_id, pizza_flavours_id) VALUES (?, ?)");
                    templateQuery.setInt(1, pizza.id);
                    templateQuery.setInt(2, flavour.id);
                    templateQuery.executeUpdate();
                }
            }

            for (Drink drink : order.drinks) {
                templateQuery = connection.prepareStatement("INSERT INTO orders_has_drinks(orders_id, drinks_id) VALUES (?, ?)");
                templateQuery.setInt(1, order.id);
                templateQuery.setInt(2, drink.id);
                templateQuery.executeUpdate();
            }

            if (response <= 0) {
                throw new Exception("operation failed");
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }

    }
}
