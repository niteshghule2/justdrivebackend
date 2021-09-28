package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.CarType;

public interface CarTypeRepository extends JpaRepository<CarType, Integer> {
	// CarType findByCarTypeName(String name);
	CarType findByCarTypeName(String name);
}
