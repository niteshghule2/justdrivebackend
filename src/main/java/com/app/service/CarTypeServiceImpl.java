package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.UserHandlingException;
import com.app.dao.CarTypeRepository;
import com.app.pojos.Car;
import com.app.pojos.CarType;

@Service
@Transactional
public class CarTypeServiceImpl implements ICarTypeService {
	@Autowired
	private CarTypeRepository cartypeRepo;

	@Override
	public CarType findCarTypeName(String name) {
		return cartypeRepo.findByCarTypeName(name);
	}

	@Override
	public List<CarType> allCarTypeList() {
		return cartypeRepo.findAll();
	}

	@Override
	public CarType getCarTypeById(int carTypeId) {
		return cartypeRepo.findById(carTypeId)
				.orElseThrow(() -> new UserHandlingException("CarType Not Found : Invalid CarType ID"));
	}

	@Override
	public List<CarType> allCarTypeListByCityId(int cId) {
		List<CarType> carTypes = cartypeRepo.findAll();
		List<CarType> cType = new ArrayList<CarType>();
		for (CarType ct : carTypes) {
			List<Car> cars = ct.getCarList();
			if (cars.size() != 0) {
				for (Car c : cars) {
					if (!c.isCarStatus()) {
						if (c.getDealer().getCity().getId() == cId) {
							cType.add(ct);
							break;
						}
					}
				}
			}
		}
		return cType;
	}

}
