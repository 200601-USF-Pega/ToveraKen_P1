package com.revature.usedcardealership.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.revature.usedcardealership.service.ConnectionManager;
import com.revature.usedcardealership.menu.IMenu;
import com.revature.usedcardealership.menu.MenuFactory;
import com.revature.usedcardealership.models.Vehicle;
import com.revature.usedcardealership.service.ValidationService;


//import static com.revature.usedcardealership.service.ConnectionManager.getConnection;


public class CarRepoDB implements ICarRepo{
//	ConnectionManager ConnectionManager = new ConnectionManager();
	   static Handler fileHandler = null;
	    private static final Logger LOGGER = Logger.getLogger(CarRepoDB.class
	            .getClass().getName());

	    public static void setup() {

	        try {
	            fileHandler = new FileHandler("./logfile.log");//file
	            SimpleFormatter simple = new SimpleFormatter();
	            fileHandler.setFormatter(simple);

	            LOGGER.addHandler(fileHandler);//adding Handler for file

	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	        }

	    }

	@Override
	public Vehicle addVehicle(Vehicle vehicle) {



		 setup();
		    LOGGER.info("added vehicle");


		
			try {
				
				
				PreparedStatement vehicleStatement = ConnectionManager.getConnection().prepareStatement("INSERT INTO vehicles VALUES (?, ?, ?, ?, ?)");
				vehicleStatement.setInt(1, vehicle.getId());
				vehicleStatement.setString(2, vehicle.getMake());
				vehicleStatement.setString(3, vehicle.getModel());
				vehicleStatement.setInt(4, vehicle.getYear());
				//vehicleStatement.setBoolean(5, vehicle.isForSale()?1:0);
				vehicleStatement.setInt(5, vehicle.salePrice());
				vehicleStatement.executeUpdate();

				return vehicle;


			} catch (SQLException e) {
				System.out.println("Exception: " + e.getMessage());
				e.printStackTrace();
			}

			return null;
		}

	@Override
	public ArrayList<Vehicle> getAllCars() {

		 setup();
		    LOGGER.info("viewed inventory");

		    ArrayList<Vehicle> carList = new ArrayList<Vehicle>();

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT car_year, make, model  FROM vehicles");
			while (rs.next()) {
				Vehicle v = new Vehicle();
				v.setMake(rs.getString("make"));
				v.setModel(rs.getString("model"));
				v.setYear(rs.getInt("car_year"));

				System.out.println(v.getYear() + " " + v.getMake() + " " + v.getModel());
				if (!carList.contains(v)) {
					carList.add(v);
				}

			}

		}catch (Exception e) {
			
		}
		return carList;
	}
	
	public void buyVehicle() {

		
		 setup();
		    LOGGER.info("bought vehicle");
		
		ValidationService inputValidation = new ValidationService();
		int salesId = inputValidation.getValidInt("--Please enter sales #id to process sale--");
		int vehicleId = inputValidation.getValidInt("--Please enter vehicle #id to finish processing--");
		String cFirstName = inputValidation.getValidString("--Enter customer first name--");
		String cLastName = inputValidation.getValidString("--Enter customer last name--");
		int paidPrice = inputValidation.getValidInt("--Enter amount paid--");
		
		try {
			
			
			PreparedStatement vehicleStatement = ConnectionManager.getConnection().prepareStatement("INSERT INTO transactions (sales_id, vehicle_id, customer_fname, customer_lname, paid_price) VALUES (?, ?, ?, ?, ?)");
			vehicleStatement.setInt(1, salesId);
			vehicleStatement.setInt(2, vehicleId);
			vehicleStatement.setString(3, cFirstName);
			vehicleStatement.setString(4, cLastName);
			vehicleStatement.setInt(5, paidPrice);
			vehicleStatement.executeUpdate();


		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	
	public void deleteCar(int vehicleId) throws FileNotFoundException, IOException {

		 setup();
		    LOGGER.info("deleted vehicle");
		
//		ValidationService inputValidation = new ValidationService();
//		int vehicleId = inputValidation.getValidInt("\n--Input vehicle id# to delete vehicle-- \n");

		MenuFactory menuFactory = new MenuFactory();
		IMenu currentMenu;

		while(true) {
		try {
			
			PreparedStatement vehicleStatement = ConnectionManager.getConnection().prepareStatement("DELETE FROM vehicles WHERE vehicle_id = (?)");
			vehicleStatement.setInt(1, vehicleId);
			vehicleStatement.executeUpdate();

			System.out.println("\n Vehicle id:" + vehicleId + " deleted! \n");
//			return vehicle;

			this.getAllCars();
			currentMenu = menuFactory.changeMenu("Manager");
			currentMenu.menuStart();

		} catch (SQLException e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}

		}

//		return null;
	}
	
	public ArrayList<String> viewTransactions() {

		System.out.println("Recent transactions: ");
		ArrayList<String> transactionList = new ArrayList<String>();

		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT customer_id, sales_id, vehicle_id, customer_fname, customer_lname, paid_price, timestamp FROM transactions");
			while (rs.next()) {
				int customerId = rs.getInt("customer_id");
				int salesId = rs.getInt("sales_id");
				int vehicleId = rs.getInt("vehicle_id");
//				String firstName = rs.getString("customer_fname");
//				String lastName = rs.getString("custom_lname");
				int price = rs.getInt("paid_price");
				String timeStamp = rs.getString("timestamp");

				transactionList.add("\n" + "[Customer #ID] = " + customerId + " [Associate #ID] = " + salesId + " [VIN#] = " + vehicleId + " [Price paid] = $" + price + " [Timestamp] = " + timeStamp);
				System.out.println("\n" + "[Customer #ID] = " + customerId + " [Associate #ID] = " + salesId + " [VIN#] = " + vehicleId + " [Price paid] = $" + price + " [Timestamp] = " + timeStamp);

			}

		}catch (Exception e) {
			
		}
		return transactionList;
	}
	
}
















