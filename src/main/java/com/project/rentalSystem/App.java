package com.project.rentalSystem;

import com.project.rentalSystem.model.Admin;
import com.project.rentalSystem.model.Standard;
import com.project.rentalSystem.model.User;
import java.util.Scanner;
import static com.project.rentalSystem.dbConnection.DBConnection.connection;
import static com.project.rentalSystem.dbConnection.DBConnection.closeConnection;
import static com.project.rentalSystem.model.Admin.addVehicle;
import static com.project.rentalSystem.model.User.signUp;
import static com.project.rentalSystem.vehicleInventory.Inventory.openInventory;

public class App
{
    public static void main( String[] args ) {
        connection();
        openInventory();
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("1. Add User");
            System.out.println("2. Login User");
            System.out.println("3. Login Admin");
            System.out.println("4. Exit");
            System.out.print("Enter the operation to be performed : ");
            int n = in.nextInt();
            if (n == 4) {
                break;
            }
            System.out.print("Enter mail Id : ");
            String email = in.next();
            System.out.print("Enter password : ");
            String password = in.next();
            if (n == 1) {
                System.out.print("Enter the amount to add into wallet : ");
                long amount = in.nextLong();
                if(signUp(email , password, amount)){
                    System.out.println();
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("User Signed Up");
                    System.out.println("---------------------------------------------------------------------------- \n");
                    User user = new User(email , password , amount);
                    userOperations(user);
                }
            } else if (n == 2) {
                User user = new User(email , password);
                System.out.println();
                boolean res = user.login(email, password);
                System.out.print("---------------------------------------------------------------------------- \n");
                System.out.println( res ? "User signed In" : "Invalid credentials");
                System.out.println("---------------------------------------------------------------------------- \n");
                if(res) {
                    userOperations(user);
                }
            } else if (n == 3) {
                Admin admin = new Admin(email , password);
                System.out.println();
                boolean res = admin.login(email, password);
                System.out.print("---------------------------------------------------------------------------- \n");
                System.out.println(res ? "Admin signed In" : "Invalid credentials");
                System.out.println("---------------------------------------------------------------------------- \n");
                if(res) {
                    while (true) {
                        System.out.println("1. Add Vehicle");
                        System.out.println("2. Search Vehicle by name");
                        System.out.println("3. Search Vehicle by number plate");
                        System.out.println("4. List all vehicle");
                        System.out.println("5. Change safety deposit amount");
                        System.out.println("6. Exit");
                        System.out.print("Enter the operation number to access the admin privileges : ");
                        int adminOperation = in.nextInt();
                        if (adminOperation == 6) {
                            break;
                        } else if (adminOperation == 1) {
                            System.out.println();
                            System.out.print("---------------------------------------------------------------------------- \n");
                            System.out.println("Add details of the car : ");
                            System.out.println("---------------------------------------------------------------------------- \n");
                            System.out.print("Enter the vehicle name : ");
                            String vehicleName = in.next();
                            in.nextLine();
                            System.out.print("Enter the number plate number : ");
                            String numberPlate = in.nextLine();
                            System.out.print("Enter the Vehicle Type (Car / Bike) : ");
                            char vehicleType = in.next().charAt(0);
                            System.out.print("Enter the availability : ");
                            Boolean isAvailable = in.nextBoolean();
                            System.out.println("Vehicle standard's category : \n1. Family\n2. Luxury\n3. Economy\n4. Sports");
                            System.out.print("Enter the standard of the vehicle : ");
                            int category = in.nextInt();
                            Standard standard = Standard.values()[category];
                            int safetyId = getSafetyId(standard, vehicleType);
                            System.out.print("Enter the rental charge : ");
                            int rentalCharge = in.nextInt();
                            addVehicle(vehicleName, numberPlate, vehicleType, isAvailable, standard, safetyId, rentalCharge);
                        }else if(adminOperation == 2){
                            System.out.print("Enter the name of the vehicle to be searched : ");
                            String name = in.next();
                            Admin.searchVehicleByName(name);
                        }else if(adminOperation == 3){
                            System.out.print("Enter the number plate of the vehicle to be searched : ");
                            in.nextLine();
                            String numberPlate = in.nextLine();
                            Admin.searchVehicleByNumberPlate(numberPlate);
                        }else if (adminOperation == 4) {
                            Admin.getAllVehicle();
                        }else if(adminOperation == 5){
                            System.out.print("Enter the Vehicle Type (Car / Bike) : ");
                            char vehicleType = in.next().charAt(0);
                            System.out.println("Vehicle standard's category : \n1. Family\n2. Luxury\n3. Economy\n4. Sports");
                            System.out.print("Enter the standard of the vehicle : ");
                            int category = in.nextInt();
                            Standard standard = Standard.values()[category];
                            int safetyId = getSafetyId(standard, vehicleType);
                            System.out.print("Enter the amount for the security deposit : ");
                            int updatedAmount = in.nextInt();
                            Admin.changeSecurityDeposit(safetyId , updatedAmount);
                        }
                    }
                }
            }
        }
        in.close();
        closeConnection();
    }

    private static void userOperations(User user){
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("1. View All Vehicles");
            System.out.println("2. Select Vehicle by name and Add to cart");
            System.out.println("3. Select Vehicle by Id and Add to cart");
            System.out.println("4. View vehicles in the Cart");
            System.out.println("5. Delete vehicle using Id from the Cart");
            System.out.println("6. Rent a Vehicle , with minimum security deposit");
            System.out.println("7. Add amount to the user wallet");
            System.out.println("8. Exit");
            System.out.print("Enter the operation number to perform specific Task : ");
            int userOperation = in.nextInt();
            if(userOperation == 8){
                break;
            }else if(userOperation == 1){
                User.getListOfVehicle();
            }else if(userOperation == 2){
                System.out.print("Enter the name of the vehicle to be Selected : ");
                String vehicleName = in.next();
                user.addVehicleToCartBySelectingVehicleByName(vehicleName);
            }else if(userOperation == 3){
                System.out.print("Enter the Id of the vehicle to be Selected : ");
                int vehicleId = in.nextInt();
                user.addVehicleToCartBySelectingVehicleById(vehicleId);
            }else if(userOperation == 4){
                user.getListOfVehiclesInCart();
            }else if(userOperation == 5){
                System.out.print("Enter the Vehicle Id to delete : ");
                int vehId = in.nextInt();
                user.deleteVehicleFromCart(vehId);
            }else if(userOperation == 6){
                User.getListOfVehicle();
                System.out.print("Enter the Id of the vehicle to Rent it : ");
                int vehicleId = in.nextInt();
                if(user.checkVehicleWithId(vehicleId)){
                    user.rentVehicle(vehicleId);
//                    user.deleteVehicleFromCart(vehicleId);
                }
            }else if(userOperation == 7){
                System.out.print("Enter the amount to be added for the user in their wallet : ");
                Long updatedAmount = in.nextLong();
                user.addAmount(updatedAmount);
            }
        }
        in.close();
    }

    private static int getSafetyId(Standard standard, char vehicleType) {
        int safetyId = 0;
        switch (standard){
            case FAMILY -> {
                if (vehicleType == 'B') {
                    safetyId = 1;
                } else {
                    safetyId = 5;
                }
            }
            case ECONOMY -> {
                if (vehicleType == 'B') {
                    safetyId = 2;
                } else {
                    safetyId = 6;
                }
            }
            case SPORTS -> {
                if (vehicleType == 'B') {
                    safetyId = 3;
                } else {
                    safetyId = 7;
                }
            }
            case LUXURY -> {
                if (vehicleType == 'B') {
                    safetyId = 4;
                } else {
                    safetyId = 8;
                }
            }

        }
        return safetyId;
    }
}
