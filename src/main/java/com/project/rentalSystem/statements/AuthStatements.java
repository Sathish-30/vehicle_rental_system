package com.project.rentalSystem.statements;

public class AuthStatements {
    // The Below Query is to add new user detail to the Database;
    public static String addUserQuery(){
        return "INSERT INTO user(email_id , password , amount) VALUES(? , ? , ?)";
    }

    // The Below Query is to check the user detail is already existed in the Database
    public static String userLoginQuery(){
        return "Select password from user where email_id = ?";
    }

    // The Below Query is to check the admin detail is already existed in the Database
    public static String adminLoginQuery(){
        return "SELECT password from admin where email_id = ?";
    }
}
