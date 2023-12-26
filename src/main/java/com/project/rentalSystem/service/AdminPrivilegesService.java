package com.project.rentalSystem.service;

import com.project.rentalSystem.model.Standard;
import com.project.rentalSystem.model.Vehicle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import static com.project.rentalSystem.dbConnection.DBConnection.getPreparedStatement;
import static com.project.rentalSystem.statements.InventoryStatement.*;
import static com.project.rentalSystem.vehicleInventory.Inventory.getVehicles;

public class AdminPrivilegesService {

    // The Below lines of code adds the Vehicle to the Database , Where it comes under the Admin-privileges
    public static void addVehicleToInventoryByAdmin(Vehicle vehicle){
        String query = addVehicleQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setString(1 , vehicle.getVehicleName());
                preparedStatement.setString(2 , vehicle.getNumberPlate());
                preparedStatement.setString(3 , String.valueOf(vehicle.getVehicleType()));
                preparedStatement.setBoolean(4 , vehicle.getAvailable());
                preparedStatement.setString(5 , String.valueOf(vehicle.getStandard()));
                preparedStatement.setInt(6 , vehicle.getSafetyId());
                preparedStatement.setInt(7 , vehicle.getRentalCharge());
                boolean res = preparedStatement.execute();
                System.out.println();
                System.out.print("---------------------------------------------------------------------------- \n");
                System.out.println("Vehicle added to the inventory");
                System.out.println("---------------------------------------------------------------------------- \n");
            }catch(SQLException se){
                System.out.println("Vehicle Not Added and the error thrown is " +se.getMessage());
            }
        }
    }

    // The Below lines of code retrieve the Vehicles from the Database , Where it comes under the Admin-privileges
    public static List<Vehicle> getAllVehicleByAdmin(){
        List<Vehicle> vehicles = new ArrayList<>();
        String query = getAllVehicleQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()){
                    Integer vehicle_id = rs.getInt(1);
                    String vehicle_name = rs.getString(2);
                    String number_plate = rs.getString(3);
                    Character vehicle_type = rs.getString(4).charAt(0);
                    Boolean is_available = rs.getBoolean(5);
                    Date rented_date = rs.getDate(6);
                    Date check_out_date = rs.getDate(7);
                    String standard = rs.getString(8);
                    Integer safety_id = rs.getInt(9);
                    Integer distance_driven = rs.getInt(10);
                    Integer rental_charge = rs.getInt(11);
                    Integer service_id = rs.getInt(12);
                    Vehicle vehicle = new Vehicle(vehicle_id , vehicle_name,number_plate,vehicle_type,is_available,rented_date , check_out_date, getStandard(standard) , safety_id , distance_driven , rental_charge , service_id);
                    vehicles.add(vehicle);
                }

            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        return vehicles;
    }

    // The Below line of code is to Search a vehicle by VehicleName , If (present) -> return the Vehicle else return Optional of null
    public static Optional<Vehicle> searchVehicleByName(String vehicleName){
        List<Vehicle> vehicles = getVehicles().stream().filter(e -> e.getVehicleName().equals(vehicleName)).toList();
        return Optional.ofNullable(vehicles.isEmpty() ? null : vehicles.get(0));
    }

    // The Below line of code is to Search a vehicle by numberPlate , If (present) -> return the Vehicle else return Optional of null
    public static Optional<Vehicle> searchVehicleByNumberPlate(String numberPlate){
        List<Vehicle> vehicles = getVehicles().stream().filter(e -> e.getNumberPlate().equals(numberPlate)).toList();
        return Optional.ofNullable(vehicles.isEmpty() ? null : vehicles.get(0));
    }

    // The Below line of code is to change the security deposit of the vehicle with the corresponding standards
    public static boolean changeSecurityAmount(Integer securityId , Integer amount){
        String query =  updateSecurityAmountQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setInt(1,securityId);
                preparedStatement.setInt(2 ,amount);
                preparedStatement.execute();
                return true;
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        return false;
    }


    // It returns the Standard enum , By checking its String value;
    public static Standard getStandard(String standard){
        switch (standard){
            case "FAMILY" -> {
                return Standard.FAMILY;
            }case "ECONOMY" -> {
                return Standard.ECONOMY;
            }
            case "SPORTS" -> {
                return Standard.SPORTS;
            }case "LUXURY" -> {
                return Standard.LUXURY;
            }
        }
        return null;
    }
}
