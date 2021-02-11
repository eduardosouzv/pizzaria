package models;

public class PizzaSize {

    public int id;
    public String description;
    public float price;

    public PizzaSize(int id, String description, float price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }
}
