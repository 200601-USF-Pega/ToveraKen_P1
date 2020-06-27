package com.revature.usedcardealership.menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.usedcardealership.inventory.Inventory;
import com.revature.usedcardealership.models.Vehicle;
import com.revature.usedcardealership.service.ConnectionManager;
import com.revature.usedcardealership.service.ValidationService;

import static com.revature.usedcardealership.service.ConnectionManager.getConnection;

public class SelectionMenu implements IMenu{

//	com.revature.usedcardealership.service.ConnectionManager ConnectionManager = new ConnectionManager();

	@Override
	public void menuStart() throws FileNotFoundException, IOException {
		ValidationService inputValidation = new ValidationService();
		Inventory carInventory = new Inventory();
		List<Integer> yearList = new ArrayList<Integer>();
		int carYear;
		List<String> makeList = new ArrayList<String>();
		String carMake;
		List<String> modelList = new ArrayList<String>();
		String carModel;

		MenuFactory menuFactory = new MenuFactory();
		IMenu currentMenu;

		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();



		try {
			Statement st = ConnectionManager.getConnection().createStatement();
			ResultSet rs = st.executeQuery("SELECT car_year, make, model  FROM vehicles");

			while (rs.next()) {

//				String model = rs.getString("model");
//				String make = rs.getString("make");
//				int year = rs.getInt("car_year");


				Vehicle v = new Vehicle();
				v.setMake(rs.getString("make"));
				v.setModel(rs.getString("model"));
				v.setYear(rs.getInt("car_year"));
				if (!vehicles.contains(v)) {
					vehicles.add(v);
				}


//				yearList.add(year);
//				makeList.add(make);
//				modelList.add(model);
			}
			
		}catch (Exception e) {
			
		}

		while(true) {

			Vehicle selectV = new Vehicle();

		carYear = inputValidation.getValidInt("\n--Please input year of vehicle selected-- \n");
		carMake = inputValidation.getValidString("\n--Please input make of vehicle selected-- \n");
		carModel = inputValidation.getValidString("\n--Please input model of vehicle selected-- \n");

		selectV.setYear(carYear);
		selectV.setMake(carMake);
		selectV.setModel(carModel);

		//	yearList.contains(carYear) && makeList.contains(carMake) && modelList.contains(carModel)

		if(vehicles.contains(selectV)) {
			System.out.println("You have selected: " + carYear + " " + carMake + " " + carModel + "\n");
			TestDriveBuyMenu.buyCar = carYear + " " + carMake + " " + carModel + "\n";



			
			currentMenu = menuFactory.changeMenu("Test or Buy");
			currentMenu.menuStart();
		} else {
			System.out.println("--Invalid input please try again--");
		}
		}

		}
		
	}