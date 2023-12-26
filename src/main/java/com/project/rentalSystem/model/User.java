package com.project.rentalSystem.model;

import com.project.rentalSystem.authentication.Auth;
import com.project.rentalSystem.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.project.rentalSystem.service.AuthService.*;

public class User implements Auth {
    private Integer user_id;
    private final String emailId;
    private final String password;
    private List<Vehicle> vehicleCart;
    private Vehicle rentedVehicle;
    private List<Vehicle> prevRented;
    private Long amount;

    public User(String emailId, String password) {
        this.emailId = emailId;
        this.password = password;
        vehicleCart = new ArrayList<>();
        prevRented = new ArrayList<>();
    }

    public User(String emailId, String password , long amount){
        this.emailId = emailId;
        this.password = password;
        this.amount = amount;
        vehicleCart = new ArrayList<>();
        prevRented = new ArrayList<>();
    }

    public void setUser_id(Integer user_id){
        this.user_id = user_id;
    }

    public String getEmailId() {
        return emailId;
    }

    public List<Vehicle> getVehicleCart() {
        return vehicleCart;
    }

    public void addVehicleToCart(Vehicle vehicle){
        vehicleCart.add(vehicle);
    }

    public Vehicle getRentedVehicle() {
        return rentedVehicle;
    }

    public List<Vehicle> getPrevRented() {
        return prevRented;
    }

    public Long getAmount() {
        return amount;
    }

    public void addAmount(Long amount) {
        this.amount = amount;
        UserService.setAmountForUser(this.emailId , amount);
    }

    public void setAmount(long amount){
        this.amount = amount;
    }

    @Override
    public boolean login(String emailId, String password) {
        return userLogin(emailId , password);
    }

    public static boolean signUp(String emailId, String password , long amount) {
        return signUpUser(emailId , password , amount);
    }

    // The below line of code is to Check Whether the vehicle is present with particular vehicle Id
    public boolean checkVehicleWithId(int vehicleId) {
        List<Vehicle> vehicles = UserService.getAllVehicleFromCart(this.emailId);
        List<Vehicle> tempVehicles = vehicles.stream().filter(e -> e.getVehicleId() == vehicleId).toList();
        return !tempVehicles.isEmpty();
    }

    // The Below line of code is to retrieve All the vehicles.
    public static void getListOfVehicle(){
        UserService.listOfVehicles();
    }


    // The Below line of code is to add Vehicle to The cart by selecting vehicle By name
    public void addVehicleToCartBySelectingVehicleByName(String vehicleName){
        Optional<Vehicle> vehicle = UserService.getVehicleFilteredByName(vehicleName);
        if(vehicle.isPresent()) {
            UserService.addVehicleToCart(this.emailId, vehicle.get());
            this.vehicleCart.add(vehicle.get());
        }
    }

    // The Below line of code is to add Vehicle to The cart by selecting vehicle By vehicleId
    public void addVehicleToCartBySelectingVehicleById(Integer vehicleId){
        Optional<Vehicle> vehicle = UserService.getVehicleFilteredById(vehicleId);
        if(vehicle.isPresent()) {
            UserService.addVehicleToCart(this.emailId, vehicle.get());
            this.vehicleCart.add(vehicle.get());
        }
    }

    // The Below line of code is to retrieve All the data from the vehicle cart
    public void getListOfVehiclesInCart(){
         this.vehicleCart = UserService.getAllVehicleFromCart(this.emailId);
         System.out.println();
         System.out.print("---------------------------------------------------------------------------- \n");
         if(!vehicleCart.isEmpty()) {
             vehicleCart.forEach(System.out::println);
         }else{
             System.out.println("Vehicles Cart is Empty");
         }
        System.out.print("---------------------------------------------------------------------------- \n");
    }

    // The Below line of code is to delete the Vehicle from the cart using vehicle Id
    public void deleteVehicleFromCart(int vehId) {
        UserService.deleteVehicleRecord(vehId , this.emailId);
    }

    // The Below line of code is to rent the vehicle from the Vehicle Cart
    public void rentVehicle(Integer vehicleId) {
        // Retrieving the Vehicle with corresponding vehicleId
        Optional<Vehicle> optionalVehicle = UserService.RentVehicle(vehicleId);
        // If Vehicle is present , then going to further Steps
        if(optionalVehicle.isPresent()) {
            // Retrieving the security deposit of the vehicle and checking with the amount in wallet
            Long securityDeposit = UserService.returnSecurityDeposit(vehicleId);
            // If user with more amount in wallet than the security deposit can proceed further
            // Get a user amount from Database
            Long amount = UserService.getUserWalletAmount(this.emailId);
//            System.out.println("The vehicle deposit amount is "+securityDeposit+" and the user wallet amount is "+amount);
            this.setAmount(amount);
            this.rentedVehicle = optionalVehicle.get();
            if (UserService.vehicleIsAvailable(rentedVehicle.getVehicleId()) && UserService.rentTheVehicle(this.rentedVehicle, this.emailId)) {
                if (this.getAmount() >= securityDeposit) {
                    // Adding the vehicleId to the rented_vehicle attribute of the User
                    // Updating the amount of the User wallet now!
                    System.out.println();
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("User rented the Vehicle " + rentedVehicle.getVehicleName());
                    System.out.println("---------------------------------------------------------------------------- \n");
                    UserService.updateUserWalletAmount(this.emailId, securityDeposit);
                    // If user with more amount in wallet than the security deposit can proceed further
                } else {
                    System.out.println();
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("Invalid amount for user");
                    System.out.println("---------------------------------------------------------------------------- \n");
                }
            } else {
                System.out.println();
                System.out.print("---------------------------------------------------------------------------- \n");
                System.out.println("Vehicle Not Available");
                System.out.println("---------------------------------------------------------------------------- \n");
            }
        }
    }
}
