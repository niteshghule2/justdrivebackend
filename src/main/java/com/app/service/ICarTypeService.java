package com.app.service;

import java.util.List;

import com.app.pojos.CarType;

public interface ICarTypeService {
	CarType findCarTypeName(String name);
	
	List<CarType> allCarTypeList();
	
	CarType getCarTypeById(int carTypeId);

	List<CarType> allCarTypeListByCityId(int cId);
	
	
}
