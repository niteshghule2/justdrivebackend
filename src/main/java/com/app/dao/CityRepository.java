package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.City;

public interface CityRepository extends JpaRepository<City,Integer> {
	City findByCityName(String cname);
}
