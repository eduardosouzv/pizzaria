package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    
    public static Connection Database(){        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/pizzaria?useSSL=false";
        String user = "root";
        String password = "153490";
        
        try{
            Class.forName(driver);
            return DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }   
}
