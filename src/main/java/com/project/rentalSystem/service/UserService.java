package com.project.rentalSystem.service;

import com.project.rentalSystem.model.User;
import com.project.rentalSystem.model.Vehicle;
import com.project.rentalSystem.statements.UserStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static com.project.rentalSystem.dbConnection.DBConnection.getPreparedStatement;
import static com.project.rentalSystem.service.AdminPrivilegesService.getStandard;
import static com.project.rentalSystem.statements.UserStatement.*;
import static com.project.rentalSystem.vehicleInventory.Inventory.getVehicles;


public class UserService {
    public static void listOfVehicles(){
        // Retrieving list of vehicles from the inventory
        List<Vehicle> vehicles = getVehicles();
        System.out.println();
        System.out.print("---------------------------------------------------------------------------- \n");
        if(vehicles.isEmpty()){
            System.out.println("Inventory is Empty");
        }
        else{
            vehicles.forEach(System.out::println);
        }
        System.out.println("---------------------------------------------------------------------------- \n");
    }

    public static Optional<Vehicle> getVehicleFilteredByName(String vehicleName) {
        String query = getVehicleFilterByNameQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setString(1 , vehicleName);
                ResultSet rs = preparedStatement.executeQuery();
                return getSingleVehicleFromDB(rs);
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        return Optional.empty();
    }

    public static Optional<Vehicle> getVehicleFilteredById(Integer vehicleId) {
        String query = getVehicleFilterByIdQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setInt(1 , vehicleId);
                ResultSet rs = preparedStatement.executeQuery();
                return getSingleVehicleFromDB(rs);
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        return Optional.empty();
    }

    private static Optional<Vehicle> getSingleVehicleFromDB(ResultSet rs) throws SQLException {
        Vehicle vehicle = null;
        while(rs.next()) {
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
            vehicle = new Vehicle(vehicle_id, vehicle_name, number_plate, vehicle_type, is_available, rented_date, check_out_date, getStandard(standard), safety_id, distance_driven, rental_charge, service_id);
        }
        return Optional.ofNullable(vehicle);
    }

    public static void addVehicleToCart(String emailId, Vehicle vehicle ) {
        int userId = getUserId(emailId);
        String queryToAddVehicleToCart = addVehicleToCartQuery();
        Optional<PreparedStatement>optionalPreparedStatement = getPreparedStatement(queryToAddVehicleToCart);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setInt(1 , userId);
                preparedStatement.setInt(2 , vehicle.getVehicleId());
                preparedStatement.execute();
                System.out.println();
                System.out.print("---------------------------------------------------------------------------- \n");
                System.out.println("Added vehicle to the cart");
                System.out.println("---------------------------------------------------------------------------- \n");
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
    }

    private static int getUserId(String emailId){
        String queryToRetrieveId = getUserIdQuery();
        int userId = 0;
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(queryToRetrieveId);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setString(1 , emailId);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                userId = rs.getInt(1);
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        return userId;
    }

    public static List<Vehicle> getAllVehicleFromCart(String emailId) {
        int userId = getUserId(emailId);
        List<Vehicle> vehicles = new ArrayList<>();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(UserStatement.getAllVehicleFromCart());
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setInt(1 , userId);
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
                return vehicles;
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        return new ArrayList<>();
    }

    public static void deleteVehicleRecord(Integer vehicleId , String emailId) {
        int userId = getUserId(emailId);
        String query = UserStatement.deleteVehicleFromCartQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setInt(1,userId);
                preparedStatement.setInt(2 , vehicleId);
                preparedStatement.execute();
                System.out.println();
                System.out.print("---------------------------------------------------------------------------- \n");
                System.out.println("Vehicle with Id "+vehicleId+" has be deleted in the user with the user Id "+ userId);
                System.out.println("---------------------------------------------------------------------------- \n");
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
    }

    public static Optional<Vehicle> RentVehicle(Integer vehicleId){
        String query = UserStatement.getVehicleUsingVehicleId();
        Vehicle vehicle = null;
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setInt(1 , vehicleId);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
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
                    vehicle = new Vehicle(vehicle_id , vehicle_name,number_plate,vehicle_type,is_available,rented_date , check_out_date, getStandard(standard) , safety_id , distance_driven , rental_charge , service_id);
                }
                return Optional.ofNullable(vehicle);
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        return Optional.empty();
    }

    private static boolean vehicleIsAvailable(Integer vehicleId){
        String query = UserStatement.checkVehicleAvailable();
        boolean res = false;
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setInt(1, vehicleId);
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                res = rs.getBoolean(1);
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        return res;
    }

    public static void rentTheVehicle(Vehicle vehicle, String emailId) {
        // Checking whether the vehicle is Available or not
        if(vehicleIsAvailable(vehicle.getVehicleId())) {
            // Retrieving the User Id , Whom we need to add the rented Car;
            int userId = getUserId(emailId);
            String query = UserStatement.RentVehicle();
            Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(query);
            // Update the Vehicle Id
            if (optionalPreparedStatement.isPresent()) {
                try (PreparedStatement preparedStatement = optionalPreparedStatement.get()) {
                    preparedStatement.setInt(1, vehicle.getVehicleId());
                    preparedStatement.setInt(2, userId);
                    preparedStatement.execute();
                    System.out.println();
                    System.out.print("---------------------------------------------------------------------------- \n");
                    System.out.println("User rented the Vehicle "+vehicle.getVehicleName());
                    System.out.println("---------------------------------------------------------------------------- \n");
                } catch (SQLException se) {
                    System.out.println(se.getMessage());
                }
                updateVehicleAvailability(vehicle.getVehicleId());

            }
        }
    }

    private static void updateVehicleAvailability(Integer vehicleId){
        String queryToChangeAvailabilityOfVehicle = UserStatement.toChangeTheAvailabilityOfTheVehicle();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(queryToChangeAvailabilityOfVehicle);
        // Update the Vehicle Id Availability
        if (optionalPreparedStatement.isPresent()) {
            try (PreparedStatement preparedStatement = optionalPreparedStatement.get()) {
                preparedStatement.setInt(1 , vehicleId);
                preparedStatement.execute();
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
    }

    public static Long returnSecurityDeposit(Vehicle rentedVehicle) {
        String getSecurityDepositOfEachVehicleQuery = UserStatement.getSafetyDepositQuery();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(getSecurityDepositOfEachVehicleQuery);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setInt(1 , rentedVehicle.getVehicleId());
                ResultSet rs = preparedStatement.executeQuery();
                rs.next();
                return rs.getLong(1);
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
        return 0L;
    }

    public static void updateAmount(String emailId , Long securityDeposit) {
        int userId = getUserId(emailId);
        String updateAmountOfUserQuery = updatePriceAmountOfUser();
        Optional<PreparedStatement> optionalPreparedStatement = getPreparedStatement(updateAmountOfUserQuery);
        if(optionalPreparedStatement.isPresent()){
            try(PreparedStatement preparedStatement = optionalPreparedStatement.get()){
                preparedStatement.setLong(1 , securityDeposit);
                preparedStatement.setInt(2 , userId);
                preparedStatement.execute();
            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
        }
    }
}
