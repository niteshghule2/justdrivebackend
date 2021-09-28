package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.UserHandlingException;
import com.app.dao.CarRepository;
import com.app.pojos.Car;

@Service
@Transactional
public class CarServiceImpl implements ICarService {

	@Autowired
	private CarRepository carRepo;

	@Override
	public Car getCarDetails(int carId) {
		return carRepo.findById(carId).orElseThrow(() -> new UserHandlingException("Car Not Found : Invalid Car ID "));
	}

	@Override
	public Car addNewCar(Car transientCar) {
		return carRepo.save(transientCar);
	}

	@Override
	public List<Car> getAllCarList(int dealerId) {
		return carRepo.findByDealerId(dealerId);
	}

	@Override
	public List<Car> getAllCars() {
		return carRepo.findAll();
	}

	@Override
	public List<Car> getAllCarsByCarTypeAndCity(int carTypeId, int cityId) {
		return carRepo.findByCarTypeAndCity(carTypeId, cityId);
	}

	@Override
	public String changeCarHourlyRate(double rate, int cid) {
		// carRepo.save(this.getCarDetails(cid).setHourlyRate(rate));
		// this.getCarDetails(cid).setHourlyRate(rate);
		Car c = carRepo.getById(cid);
		c.setHourlyRate(rate);
		carRepo.save(c);
		return "Hourly Rate of Car Id " + cid + " is updated";
	}

	@Override
	public long getCount() {
		return carRepo.count();
	}

	@Override
	public long getCountByStatus() {
		return carRepo.findByCarStatus().size();
	}

	@Override
	public List<Car> getCarsByStatusAndDealer(int dId) {

		return carRepo.getCarsByStatusByDealers(dId);
	}

	@Override
	public Car getCarById(int carId) {
		return carRepo.findById(carId).orElseThrow(() -> new UserHandlingException("Car Not Found : Invalid Car ID "));
	}
}
