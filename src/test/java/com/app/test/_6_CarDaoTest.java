package com.app.test;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.CarRepository;
import com.app.pojos.Car;
import com.app.service.ICarTypeService;
import com.app.service.IDealerService;

@SpringBootTest
public class _6_CarDaoTest {
	
	@Autowired
	ICarTypeService ctypeService;
	
	@Autowired
	IDealerService dealerService;
	
	@Autowired
	CarRepository carRepo;
	@Test
	public void test() {
		List<Car> cars=Arrays.asList(
				new Car("MH12MN1522", "Maruti Suzuki", "Baleno", "Diesel", "rcimage1", 50, true, dealerService.GetDealerById(1),
						ctypeService.getCarTypeById(1)),
				new Car("MH11BC5222", " Hondai", "Creta", "petrol", "rcimage2", 55, false, dealerService.GetDealerById(1),
						ctypeService.getCarTypeById(2)),
				new Car("MH14bh4444", " Toyata", "Fortuner", "Desel", "rcimage3", 60, true, dealerService.GetDealerById(1),
						ctypeService.getCarTypeById(3)),
				new Car("MH45AQ1111", " Mahindra", "Scorpio", "Desel", "rcimage4", 65, false, dealerService.GetDealerById(1),
						ctypeService.getCarTypeById(4)),
				new Car("MH55AQ1551", " Maruti Suzuki", "Brezza", "Desel", "rcimage5", 50, false, dealerService.GetDealerById(1),
						ctypeService.getCarTypeById(4)),
				
				
				new Car("MH23MN2243", "Maruti Suzuki", "Baleno", "Diesel", "rcimage6", 50, true, dealerService.GetDealerById(2),
						ctypeService.getCarTypeById(1)),
				new Car("MH12BE3322", " Hondai", "Creta", "petrol", "rcimage7", 55, false, dealerService.GetDealerById(2),
						ctypeService.getCarTypeById(2)),
				new Car("MH50WQ1234", " Toyata", "Fortuner", "Diesel", "rcimage8", 60, true, dealerService.GetDealerById(2),
						ctypeService.getCarTypeById(3)),
				new Car("MH45SA1212", " Mahindra", "Scorpio", "Diesel", "rcimage9", 65, false, dealerService.GetDealerById(2),
						ctypeService.getCarTypeById(4)),
				new Car("MH51DQ1551", " Maruti Suzuki", "Brezza", "Petrol", "rcimage10", 50, false, dealerService.GetDealerById(2),
						ctypeService.getCarTypeById(4)),
				
				
				new Car("MH83MN2553", "Maruti Suzuki", "Baleno", "Diesel", "rcimage11", 50, true, dealerService.GetDealerById(3),
						ctypeService.getCarTypeById(1)),
				new Car("MH22BR3422", " Hondai", "Creta", "petrol", "rcimage12", 55, false, dealerService.GetDealerById(3),
						ctypeService.getCarTypeById(2)),
				new Car("MH12EQ1222", " Toyata", "Fortuner", "Diesel", "rcimage13", 60, true, dealerService.GetDealerById(3),
						ctypeService.getCarTypeById(3)),
				new Car("MH35SD1219", " Mahindra", "Scorpio", "Diesel", "rcimage14", 65, false, dealerService.GetDealerById(3),
						ctypeService.getCarTypeById(4)),
				new Car("MH31D6999", " Maruti Suzuki", "Brezza", "Petrol", "rcimage15", 50, false, dealerService.GetDealerById(3),
						ctypeService.getCarTypeById(4)),
				
				new Car("MH11MN6666", "Maruti Suzuki", "Baleno", "Diesel", "rcimage16", 50, true, dealerService.GetDealerById(4),
						ctypeService.getCarTypeById(1)),
				new Car("MH12BR6688", " Hondai", "Creta", "petrol", "rcimage17", 55, false, dealerService.GetDealerById(4),
						ctypeService.getCarTypeById(2)),
				new Car("MH13XZ2222", " Toyata", "Fortuner", "Diesel", "rcimage18", 60, true, dealerService.GetDealerById(4),
						ctypeService.getCarTypeById(3)),
				new Car("MH11MP4545", " Mahindra", "Scorpio", "Diesel", "rcimage19", 65, false, dealerService.GetDealerById(4),
						ctypeService.getCarTypeById(4)),
				new Car("MH12JG8888", " Maruti Suzuki", "Brezza", "Petrol", "rcimage20", 50, false, dealerService.GetDealerById(4),
						ctypeService.getCarTypeById(4)),
				
				new Car("MH11MN8520", "Maruti Suzuki", "Baleno", "Diesel", "rcimage21", 50, true, dealerService.GetDealerById(5),
						ctypeService.getCarTypeById(1)),
				new Car("MH12BR9630", " Hondai", "Creta", "petrol", "rcimage22", 55, false, dealerService.GetDealerById(5),
						ctypeService.getCarTypeById(2)),
				new Car("MH13XZ7410", " Toyata", "Fortuner", "Diesel", "rcimage23", 60, true, dealerService.GetDealerById(5),
						ctypeService.getCarTypeById(3)),
				new Car("MH11MP7520", " Mahindra", "Scorpio", "Diesel", "rcimage24", 65, false, dealerService.GetDealerById(5),
						ctypeService.getCarTypeById(4)),
				new Car("MH12JG0258", " Maruti Suzuki", "Brezza", "Petrol", "rcimage25", 50, false, dealerService.GetDealerById(5),
						ctypeService.getCarTypeById(4))
					
				);
				
		carRepo.saveAll(cars);
	}
}


/*
 * this.carNo = carNo;
	this.carCompany = carCompany;
	this.carModel = carModel;
	this.fuelType = fuelType;
	this.carRCImage = carRCImage;
	this.hourlyRate = hourlyRate;
	this.carStatus = carStatus;
	this.dealer = dealer;
	this.carType = carType-*
 */