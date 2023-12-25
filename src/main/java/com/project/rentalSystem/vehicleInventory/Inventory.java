package com.project.rentalSystem.vehicleInventory;

import com.project.rentalSystem.model.User;
import com.project.rentalSystem.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println("---------------------------------------------------------------------------- \n");
        System.out.println("Vehicle added to the inventory");
        System.out.println("---------------------------------------------------------------------------- \n");
    }

    public static List<Vehicle> getVehicles(){
        return vehicles;
    }
}
