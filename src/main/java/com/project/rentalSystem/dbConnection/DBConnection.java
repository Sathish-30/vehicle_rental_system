package com.project.rentalSystem.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class DBConnection {
    private static Connection con;
    private DBConnection(){
        String url = "jdbc:mysql://localhost:3306/rn";
        String name = "root";
        String password = "helloworld";
        try {
            con = DriverManager.getConnection(url , name , password);
            System.out.println("Database connected");
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }

    public static Optional<PreparedStatement> getPreparedStatement(String sql){
        try{
            return Optional.ofNullable(con.prepareStatement(sql));
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.ofNullable(null);
    }
    public static void connection(){
        DBConnection db = new DBConnection();
    }
    public static void closeConnection(){
        try {
            con.close();
        }catch(SQLException se){
            System.out.println(se.getMessage());
        }
    }
}
