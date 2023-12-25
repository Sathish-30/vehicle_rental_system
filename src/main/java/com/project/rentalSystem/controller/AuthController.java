package com.project.rentalSystem.controller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.project.rentalSystem.dbConnection.DBConnection.getPreparedStatement;
import static com.project.rentalSystem.statements.AuthStatements.*;
public class AuthController {
    public static boolean userLogin(String email , String password){
        String query = userLoginQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()) {
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()) {
                preparedStatement.setString(1 , email);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                return rs.getString(1).equals(password);
            } catch (SQLException se) {
                System.out.println("Unable to process the data");
            }
        }
        return false;
    }

    public static boolean adminLogin(String email , String password){
        String query = adminLoginQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()) {
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()) {
                preparedStatement.setString(1 , email);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                return rs.getString(1).equals(password);
            } catch (SQLException se) {
                System.out.println("Unable to process the data");
            }
        }
        return false;
    }

    public static boolean signUpUser(String email , String password){
        String query = addUserQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);

        if(optionalPreparedStatement.isPresent()) {
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                preparedStatement.execute();
                return true;
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
        }
        return false;
    }
}
