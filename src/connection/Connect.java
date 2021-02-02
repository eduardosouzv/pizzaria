package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    
    public static Connection Database(){        
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "root";
        
        try{
            Class.forName(driver);
            return DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }   
}
