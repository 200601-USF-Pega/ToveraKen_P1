package com.revature.usedcardealership.models;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class VehicleTest {
    Vehicle vehicle = new Vehicle();

    @Test
    public void checkSetId(){

        vehicle.setId(123);
        assertEquals(123, vehicle.getId());

    }
    @Test
    public void checkGetId(){

        vehicle.getId();
        assertEquals(0, vehicle.getId());

    }
    @Test
    public void checkSetYear(){

        vehicle.setYear(2000);
        assertEquals(2000, vehicle.getYear());
    }
    @Test
    public void checkGetYear(){

        vehicle.getYear();
        assertEquals(0, vehicle.getYear());

    }
    @Test
    public void checkSetMake(){

        vehicle.setMake("Honda");
        assertEquals("Honda", vehicle.getMake());

    }
    @Test
    public void checkGetMake(){

        vehicle.getMake();
        assertEquals(null, vehicle.getMake());

    }
    @Test
    public void checkSetModel(){

        vehicle.setModel("Civic");
        assertEquals("Civic", vehicle.getModel());

    }
    @Test
    public void checkGetModel(){

        vehicle.getModel();
        assertEquals(null, vehicle.getModel());

    }
    @Test
    public void checkSalePrice(){

        vehicle.setForSale(12);
        assertEquals(12, vehicle.salePrice());

    }
    @Test
    public void checkSetPrice(){

        vehicle.salePrice();
        assertEquals(0, vehicle.salePrice());

    }

}