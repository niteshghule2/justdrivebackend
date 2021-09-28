package com.app.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.CityRepository;
import com.app.pojos.City;

@SpringBootTest
public class _3_CityDaoTest {
	@Autowired
	CityRepository cityRepo;
	

	@Test
	public void test() {
		List<City> city=Arrays.asList(
				new City("pimpri", "MAH", "India", 410506),
				new City("Kothrud", "MAH", "India", 410506),
				new City("Katraj", "MAH", "India", 410506),
				new City("Hinjawadi", "MAH", "India", 410506),
				new City("Mulshi", "MAH", "India", 410506),
				new City("Ambegaon", "MAH", "India", 410023),
				new City("Haveli", "MAH", "India", 414578),
				new City("Khed", "MAH", "India", 410011),
				new City("Baramati", "MAH", "India", 413620),
				new City("Shirur", "MAH", "India", 410101)
				
				);
		cityRepo.saveAll(city);
	}
}
