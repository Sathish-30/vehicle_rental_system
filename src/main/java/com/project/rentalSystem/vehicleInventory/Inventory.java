package com.project.rentalSystem.vehicleInventory;

import com.project.rentalSystem.model.User;
import com.project.rentalSystem.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

import static com.project.rentalSystem.service.AdminPrivilegesService.addVehicleToInventoryByAdmin;
import static com.project.rentalSystem.service.AdminPrivilegesService.getAllVehicleByAdmin;

public class Inventory {
    private static List<Vehicle> vehicles;
    private static List<User> users;

    private Inventory() {
        vehicles = new ArrayList<>();
        users = new ArrayList<>();
    }

    public static void openInventory(){
        Inventory inventory = new Inventory();
    }

    public static void addVehicleToInventory(Vehicle vehicle){
        vehicles.add(vehicle);
        addVehicleToInventoryByAdmin(vehicle);
    }

    public static List<Vehicle> getVehicles(){
        vehicles =  getAllVehicleByAdmin();
        return vehicles;
    }
}
