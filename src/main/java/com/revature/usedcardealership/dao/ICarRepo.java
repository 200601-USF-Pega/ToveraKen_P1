package com.revature.usedcardealership.dao;

import com.revature.usedcardealership.models.Vehicle;

import java.util.ArrayList;

public interface ICarRepo {
	Vehicle addVehicle(Vehicle vehicle);

	ArrayList<Vehicle> getAllCars();
}
