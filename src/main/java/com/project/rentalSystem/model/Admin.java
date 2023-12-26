package com.project.rentalSystem.model;

import com.project.rentalSystem.authentication.Auth;
import com.project.rentalSystem.service.AdminPrivilegesService;
import com.project.rentalSystem.vehicleInventory.Inventory;

import java.util.List;
import java.util.Optional;
import static com.project.rentalSystem.service.AuthService.adminLogin;
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

    // Add Vehicle to The DB
    public static void addVehicle(String vehicleName ,String numberPlate ,Character vehicleType ,Boolean isAvailable ,Standard standard ,Integer safetyId ,Integer rentalCharge){
        Vehicle vehicle = new Vehicle(vehicleName , numberPlate , vehicleType , isAvailable , standard , safetyId , rentalCharge );
//        System.out.println(vehicle);
        Inventory.addVehicleToInventory(vehicle);
    }

    // Get All Vehicle from The DB;
    public static void getAllVehicle(){
        List<Vehicle> vehicles = getVehicles();
        System.out.println();
        System.out.print("---------------------------------------------------------------------------- \n");
        if(vehicles.isEmpty()){
            System.out.println("No vehicle present in the inventory");
        }else {
            vehicles.forEach(System.out::println);
        }
        System.out.println("---------------------------------------------------------------------------- \n");
    }

    // Search Vehicle By Name
    public static void searchVehicleByName(String vehicleName){
        Optional<Vehicle> vehicle = AdminPrivilegesService.searchVehicleByName(vehicleName);
        System.out.println();
        System.out.print("---------------------------------------------------------------------------- \n");
        System.out.println(vehicle.map(value -> ("Vehicle Found : " + value)).orElseGet(() -> ("There is no vehicle in inventory with name " + vehicleName)));
        System.out.println("---------------------------------------------------------------------------- \n");
    }

    // Search vehicle by Number plate
    public static void searchVehicleByNumberPlate(String numberPlate){
        Optional<Vehicle> vehicle = AdminPrivilegesService.searchVehicleByNumberPlate(numberPlate);
        System.out.println();
        System.out.print("---------------------------------------------------------------------------- \n");
        System.out.println(vehicle.map(value -> ("Vehicle Found : " + value)).orElseGet(() -> ("There is no vehicle in inventory with number plate " + numberPlate)));
        System.out.println("---------------------------------------------------------------------------- \n");
    }

    // Change the security deposit amount of the Vehicle
    public static void changeSecurityDeposit(Integer securityId , Integer updatedAmount){
        System.out.println();
        System.out.print("---------------------------------------------------------------------------- \n");
        System.out.println(AdminPrivilegesService.changeSecurityAmount(securityId , updatedAmount) ? "Security Deposit is updated" : "Couldn't Able to Update the Security Deposit");
        System.out.println("---------------------------------------------------------------------------- \n");
    }

}

