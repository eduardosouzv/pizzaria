package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import models.Drink;
import models.Order;
import models.Pizza;
import models.PizzaFlavour;
import models.StatisticOrder;
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
                long telephone = response.getLong("telephone");

                User user = new User(username, null, user_type, address_street, address_number, address_district, address_city, telephone);
                return user;
            }
        }
        return null;
    }

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

    public ArrayList<StatisticOrder> getTopUsers() throws SQLException, Exception {
        try (Connection connection = Connect.Database()) {
            PreparedStatement templateQuery = connection.prepareStatement("select users.id, users.user, count(orders.id) as orderCount from orders inner join users on users.id = orders.users_id group by users.id order by orderCount limit 10");

            ResultSet response = templateQuery.executeQuery();
            ArrayList<StatisticOrder> users = new ArrayList<>();
            while (response.next()) {

                String user = response.getString("user");
                int orderCount = Integer.parseInt(response.getString("orderCount"));
                StatisticOrder statistic = new StatisticOrder(user, orderCount);
                users.add(statistic);
            }
            return users;
        }
    }
}
