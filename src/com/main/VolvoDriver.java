package com.main;

public class VolvoDriver implements Driver {

	@Override
	public Car driveCar() {
		// TODO Auto-generated method stub
		return new VolvoCar();
	}

}
