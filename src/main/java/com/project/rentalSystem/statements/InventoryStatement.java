package com.project.rentalSystem.statements;

public class InventoryStatement {
    public static String addVehicleQuery(){
        return "INSERT INTO Vehicle(vehicle_name, number_plate, vehicle_type, is_available, standard, safety_id, rental_charge) VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    public static String updateSecurityAmountQuery(){
        return "UPDATE security_deposit set security_deposit.safety_deposit_amount = ? where security_deposit.security_deposit_id = ?";
    }

    public static String getAllVehicleQuery(){
        return "SELECT * FROM Vehicle";
    }

}
