package models;

import java.util.List;

public class Order {

    public Order(List<Pizza> pizzas, List<Drink> drinks, User user, int id, String status) {
        this.pizzas = pizzas;
        this.drinks = drinks;
        this.user = user;
        this.id = id;
        this.status = status;
    }
    
    public Order(List<Pizza> pizzas, List<Drink> drinks, User user, String status) {
        this.pizzas = pizzas;
        this.drinks = drinks;
        this.user = user;
        this.status = status;
    }
    
    public List<Pizza> pizzas;
    public List<Drink> drinks;
    public User user;
    public int id;
    public String status;
}

