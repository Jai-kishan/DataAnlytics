package dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class database {
    
    public static Connection connectDb(){
        
        try{
			String url = "jdbc:mysql://localhost:3306/studentdata?useSSL=false";
			String user = "root";
			String password = "";
//			Class.forName("org.sqlite.JDBC");	
//            Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager
					.getConnection(url, user, password);
            return connect;
        }catch(Exception e){e.printStackTrace();}
        return null;
    }
    
}
