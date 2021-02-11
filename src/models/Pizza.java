package models;

import java.util.List;

public class Pizza {

    public Pizza(int id, PizzaSize size, List<PizzaFlavour> flavours) {
        this.id = id;
        this.size = size;
        this.flavours = flavours;
    }

    public Pizza(PizzaSize size, List<PizzaFlavour> flavours) {
        this.size = size;
        this.flavours = flavours;
    }
    
    public int id;
    public PizzaSize size;
    public List<PizzaFlavour> flavours;
}
