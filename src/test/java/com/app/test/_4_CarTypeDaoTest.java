package com.app.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.CarTypeRepository;
import com.app.pojos.CarType;

@SpringBootTest
public class _4_CarTypeDaoTest {
	@Autowired
	CarTypeRepository carRepo;
	
	@Test
	public void test() {
		List<CarType> type=Arrays.asList(
				new CarType("SUV", "Brezza,Creta", "5"),
				new CarType("SEADEN", "Desire,Baleno", "6"),
				new CarType("7SEATER", "Fortuner,Scorpio", "7"),
				new CarType("HATCHBACK", "Fortuner,Scorpio", "4")
				);
		carRepo.saveAll(type);
	}

}
