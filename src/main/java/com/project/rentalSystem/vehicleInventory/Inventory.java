package com.project.rentalSystem.vehicleInventory;

import com.project.rentalSystem.model.User;
import com.project.rentalSystem.model.Vehicle;
import com.project.rentalSystem.service.AdminPrivilegesService;

import java.util.ArrayList;
import java.util.List;

import static com.project.rentalSystem.service.AdminPrivilegesService.addVehicleToInventoryByAdmin;
import static com.project.rentalSystem.service.AdminPrivilegesService.getAllVehicleByAdmin;

public class Inventory {
    private static List<Vehicle> vehicles;

    private Inventory() {
        vehicles = new ArrayList<>();
    }

    public static void openInventory(){
        Inventory inventory = new Inventory();
    }

    // The Below Two methods retrieve data from the AdminPrivileges Class
    public static void addVehicleToInventory(Vehicle vehicle){
        vehicles.add(vehicle);
        // Retrieve vehicles from the DB
        AdminPrivilegesService.addVehicleToInventoryByAdmin(vehicle);
    }

    public static List<Vehicle> getVehicles(){
        vehicles =  getAllVehicleByAdmin();
        return vehicles;
    }
}
