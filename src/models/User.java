package models;

public class User {

    public String user;
    public String password;
    public String user_type;
    public String address_street;
    public int address_number;
    public String address_district;
    public String address_city;

    public User(
            String user,
            String password,
            String user_type,
            String address_street,
            int address_number,
            String address_district,
            String address_city
    ) {
        this.user = user;
        this.password = password;
        this.user_type = user_type;
        this.address_street = address_street;
        this.address_number = address_number;
        this.address_district = address_district;
        this.address_city = address_city;
    }
}
