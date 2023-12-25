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

    public boolean checkVehicleWithId(int vehicleId) {
        List<Vehicle> vehicles = UserService.getAllVehicleFromCart(this.emailId);
        List<Vehicle> tempVehicles = vehicles.stream().filter(e -> e.getVehicleId() == vehicleId).toList();
        return !tempVehicles.isEmpty();
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

    public void setAmount(Long amount) {
        this.amount = amount;
    }


    @Override
    public boolean login(String emailId, String password) {
        return userLogin(emailId , password);
    }

    public static boolean signUp(String emailId, String password , long amount) {
        return signUpUser(emailId , password , amount);
    }

    public static void getListOfVehicle(){
        UserService.listOfVehicles();
    }

    public void addVehicleToCartBySelectingVehicleByName(String vehicleName){
        Optional<Vehicle> vehicle = UserService.getVehicleFilteredByName(vehicleName);
        if(vehicle.isPresent()) UserService.addVehicleToCart(this.emailId, vehicle.get());
    }
    public void addVehicleToCartBySelectingVehicleById(Integer vehicleId){
        Optional<Vehicle> vehicle = UserService.getVehicleFilteredById(vehicleId);
        if(vehicle.isPresent()) UserService.addVehicleToCart(this.emailId, vehicle.get());
    }

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

    public void deleteVehicleFromCart(int vehId) {
        UserService.deleteVehicleRecord(vehId , this.emailId);
    }

    public void rentVehicle(Integer vehicleId) {
        Optional<Vehicle> optionalVehicle = UserService.RentVehicle(vehicleId);
        if(optionalVehicle.isPresent()) {
            this.rentedVehicle = optionalVehicle.get();
            Long securityDeposit = UserService.returnSecurityDeposit(rentedVehicle);
            if(this.amount > securityDeposit){
                UserService.rentTheVehicle(rentedVehicle, this.emailId);
                UserService.updateAmount(this.emailId , securityDeposit);
            }

        }
    }
}
