package models;

public class PizzaSize {

    public int id;
    public String description;
    public float price;
    public int max_flavours;

    public PizzaSize(int id, String description, float price, int max_flavours) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.max_flavours = max_flavours;
    }
}
