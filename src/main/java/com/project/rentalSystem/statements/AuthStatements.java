package com.project.rentalSystem.statements;

public class AuthStatements {
    public static String addUserQuery(){
        return "INSERT INTO user(email_id , password) VALUES(? , ?)";
    }

    public static String userLoginQuery(){
        return "Select password from user where email_id = ?";
    }

    public static String adminLoginQuery(){
        return "SELECT password from admin where email_id = ?";
    }
}
