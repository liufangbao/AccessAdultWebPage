package com.main;

public interface Driver {
	
	
	public static Car driveCar(String s){
		
		if(s.equals("bmw"))
			return new BmwCar();
		else if(s.equals("benz"))
			return new BenzCar();
		else
			return null;
		
	}
	Car driveCar();
	static Car driveBmwCar(){
		return new BmwCar();
		
	}
	static Car driveBenzCar(){
		return new BenzCar();
		
	}
}
