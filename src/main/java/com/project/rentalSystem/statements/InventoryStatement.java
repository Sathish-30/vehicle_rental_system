package com.project.rentalSystem.statements;

public class InventoryStatement {

    // The Below Query is used to add the Vehicle to the Database
    public static String addVehicleQuery(){
        return "INSERT INTO Vehicle(vehicle_name, number_plate, vehicle_type, is_available, standard, safety_id, rental_charge) VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    // The Below Query is to update the security deposit of the security deposit of each vehicle Standard
    public static String updateSecurityAmountQuery(){
        return "UPDATE security_deposit set security_deposit.safety_deposit_amount = ? where security_deposit.security_deposit_id = ?";
    }

    // The Below Query is to retrieve All the Vehicle from Database;
    public static String getAllVehicleQuery(){
        return "SELECT * FROM Vehicle";
    }

}
