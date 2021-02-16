package models;

public class StatisticOrder {
    
    public StatisticOrder(String user, int orderCount){
        this.user = user;
        this.orderCount = orderCount;
    }
    
    public String user;
    public int orderCount;
}
