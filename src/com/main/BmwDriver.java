package com.main;

public class BmwDriver implements Driver {

	@Override
	public Car driveCar() {
		// TODO Auto-generated method stub
		return new BmwCar();
	}

}
