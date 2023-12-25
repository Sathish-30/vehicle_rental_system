package com.project.rentalSystem.statements;

public class UserStatement {
    public static String getVehicleFilterByNameQuery(){
        return "SELECT * FROM VEHICLE WHERE vehicle_name = ? LIMIT 1";
    }

    public static String getVehicleFilterByIdQuery(){
        return "SELECT * FROM VEHICLE WHERE vehicle_id = ? LIMIT 1";
    }

    public static String RentVehicle(){
        return "Update user Set rented_vehicle = ? , rented_date = GETDATE() WHERE user_id = ?";
    }

    public static String getVehicleUsingVehicleId(){
        return "SELECT Vehicle.* FROM vehicle_cart Cart inner join vehicle Vehicle on Cart.vehicle_id = Vehicle.vehicle_id WHERE Vehicle.vehicle_id = ?";
    }

    public static String getUserIdQuery(){
        return "SELECT user_id FROM User WHERE email_id = ? LIMIT 1";
    }

    public static String checkVehicleAvailable(){
        return "SELECT is_available from Vehicle Where vehicle_id = ?";
    }

    public static String addVehicleToCartQuery(){
        return "INSERT INTO vehicle_cart(user_id , vehicle_id) VALUES(? , ?)";
    }

    public static String getAllVehicleFromCart(){
        return "SELECT Vehicle.* FROM vehicle_cart Cart inner join vehicle Vehicle on Cart.vehicle_id = Vehicle.vehicle_id WHERE Cart.user_id = ?";
    }

    public static String deleteVehicleFromCartQuery() {
        return "Delete from vehicle_cart where user_id = ? AND vehicle_id = ?";
    }

    public static String toChangeTheAvailabilityOfTheVehicle(){
        return "Update Vehicle set is_available = 0 WHERE vehicle_id = ?";
    }

    public static String getSafetyDepositQuery(){
        return "SELECT D.safety_deposit_amount FROM Vehicle V inner join security_deposit D on V.safety_id = D.security_deposit_id where vehicle_id = ?";
    }

    public static String updatePriceAmountOfUser(){
        return "Update user SET amount = amount - ? WHERE user_id = ?";
    }
}
