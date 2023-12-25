package com.project.rentalSystem.model;

import com.project.rentalSystem.authentication.Auth;
import com.project.rentalSystem.vehicleInventory.Inventory;

import java.util.List;
import java.util.Scanner;

import static com.project.rentalSystem.controller.AuthController.adminLogin;
import static com.project.rentalSystem.vehicleInventory.Inventory.addVehicleToInventory;
import static com.project.rentalSystem.vehicleInventory.Inventory.getVehicles;

public class Admin implements Auth {
    private Integer admin_id;
    private String emailId;
    private String password;

    public Admin(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
    }
    public Admin(){

    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean login(String emailId, String password) {
        return adminLogin(emailId , password);
    }

    public static void addVehicle(String vehicleName ,String numberPlate ,String vehicleType ,Boolean isAvailable ,Standard standard ,Integer safetyId ,Integer rentalCharge ,Integer serviceId){
        Vehicle vehicle = new Vehicle(vehicleName , numberPlate , vehicleType , isAvailable , standard , safetyId , rentalCharge , serviceId);
        Inventory.addVehicleToInventory(vehicle);
    }
}

