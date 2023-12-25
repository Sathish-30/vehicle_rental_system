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
                if(signUp(email , password)){
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("User Signed Up");
                    System.out.println("---------------------------------------------------------------------------- \n");
                }
                User user = new User(email , password);
            } else if (n == 2) {
                User user = new User(email , password);
                System.out.print("---------------------------------------------------------------------------- \n");
                System.out.println(user.login(email, password) ? "User signed In" : "Invalid credentials");
                System.out.println("---------------------------------------------------------------------------- \n");
            } else if (n == 3) {
                Admin admin = new Admin(email , password);
                System.out.print("---------------------------------------------------------------------------- \n");
                System.out.println(admin.login(email, password) ? "Admin signed In" : "Invalid credentials");
                System.out.println("---------------------------------------------------------------------------- \n");
                while(true){
                    System.out.println("1. Add Vehicle");
                    System.out.println("2. Search Vehicle");
                    System.out.println("3. List all vehicle");
                    System.out.println("4. Change safety deposit amount");
                    System.out.println("5. Exit");
                    System.out.print("Enter the operation number to access the admin privileges : ");
                    int adminOperation = in.nextInt();
                    if(adminOperation == 5){
                        break;
                    }else if(adminOperation == 1){
                        System.out.print("---------------------------------------------------------------------------- \n");
                        System.out.println("Add details of the car : ");
                        System.out.println("---------------------------------------------------------------------------- \n");
                        System.out.print("Enter the vehicle name : ");
                        String vehicleName = in.next();
                        System.out.print("Enter the number plate number : ");
                        String numberPlate = in.nextLine();
                        in.nextLine();
                        System.out.print("Enter the Vehicle Type (Car / Bike) : ");
                        String vehicleType = in.next();
                        System.out.print("Enter the availability : ");
                        Boolean isAvailable = in.nextBoolean();
                        System.out.println("Vehicle standard's category : \n1. Family\n2. Luxury\n3. Economy\n4. Sports");
                        System.out.println("Enter the standard of the vehicle");
                        int category = in.nextInt();
                        Standard standard = Standard.values()[category];
                        System.out.print("Enter the safetyId : ");
                        int safetyId = in.nextInt();
                        System.out.print("Enter the rental charge : ");
                        int rentalCharge = in.nextInt();
                        System.out.println("Enter the serviceId : ");
                        int serviceId = in.nextInt();
                        addVehicle(vehicleName , numberPlate , vehicleType , isAvailable , standard , safetyId , rentalCharge , serviceId);
                    }
                }

            }
        }
        in.close();
        closeConnection();
    }
}
