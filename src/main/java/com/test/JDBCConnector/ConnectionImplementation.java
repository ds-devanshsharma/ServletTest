package com.test.JDBCConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionImplementation implements ConnectionNeeds{
    static Connection connection;
    private ConnectionImplementation(){}
    public static Connection getConnectionForDB(){
        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(url,user,password);
            }
        }catch (ClassNotFoundException | SQLException e ){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
