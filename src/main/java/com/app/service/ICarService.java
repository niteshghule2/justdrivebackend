package com.app.service;

import java.util.List;

import com.app.pojos.Car;

public interface ICarService {
	Car getCarDetails(int carId);
	
	Car addNewCar(Car transientCar);
	
	List<Car> getAllCarList(int dealerId);
	
	List<Car> getCarsByStatusAndDealer(int dId);
	
	List<Car> getAllCars();
	
	List<Car> getAllCarsByCarTypeAndCity(int carTypeId,int cityId);
	
	String changeCarHourlyRate(double rate,int cid);
	
	long getCount();
	
	long getCountByStatus();
	
	Car getCarById(int carId);
}
