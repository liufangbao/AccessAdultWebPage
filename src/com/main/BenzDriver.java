package com.main;

public class BenzDriver implements Driver {

	@Override
	public Car driveCar() {
		// TODO Auto-generated method stub
		return new BenzCar();
	}

}
