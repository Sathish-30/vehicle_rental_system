package com.project.rentalSystem.model;
import com.project.rentalSystem.authentication.Auth;
import com.project.rentalSystem.controller.AuthController;

import java.util.ArrayList;

import static com.project.rentalSystem.controller.AuthController.*;

public class User implements Auth {
    private Integer user_id;
    private final String emailId;
    private final String password;
    private ArrayList<Integer> vehicleCart;
    private Vehicle rentedVehicle;
    private ArrayList<Integer> prevRented;
    private Long amount;

    public User(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }

    public void setUser_id(Integer user_id){
        this.user_id = user_id;
    }

    public String getEmailId() {
        return emailId;
    }

    public ArrayList<Integer> getVehicleCart() {
        return vehicleCart;
    }

    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }

    public ArrayList<Integer> getPrevRented() {
        return prevRented;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }


    @Override
    public boolean login(String emailId, String password) {
        return userLogin(emailId , password);
    }

    public static boolean signUp(String emailId, String password) {
        return signUpUser(emailId , password);
    }
}
