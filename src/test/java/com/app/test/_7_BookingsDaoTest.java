package com.app.test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.BookingsRepository;
import com.app.dao.CarRepository;
import com.app.pojos.BookingStatus;
import com.app.pojos.Bookings;
import com.app.service.ICarService;
import com.app.service.ICustomerService;

@SpringBootTest
public class _7_BookingsDaoTest {
	@Autowired
	BookingsRepository bRepo;
	
	@Autowired
	ICarService carService;
	
	@Autowired
	CarRepository carRepo;
	
	@Autowired
	ICustomerService custService;
	
	
	@Test
	public void test() {
		List<Bookings> blist=Arrays.asList( 
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 50, 2500, 500, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.PENDING,custService.getCustomerById(1),  carService.getCarById(1)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 60, 3000, 1000, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.ONGOING,custService.getCustomerById(1),  carService.getCarById(1)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 70, 3500, 1200, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.COMPLETED,custService.getCustomerById(1),  carService.getCarById(1)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 80, 4000, 500, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.CANCELED,custService.getCustomerById(1),  carService.getCarById(1)),
				
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 50, 2700, 600, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.PENDING,custService.getCustomerById(2),  carService.getCarById(2)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 40, 2100, 500, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.ONGOING,custService.getCustomerById(2),  carService.getCarById(2)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 30, 1500, 500, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.COMPLETED,custService.getCustomerById(2),  carService.getCarById(2)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 70, 3500, 1000, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.CANCELED,custService.getCustomerById(2),  carService.getCarById(2)),
				
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 40, 2400, 500, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.PENDING,custService.getCustomerById(3),  carService.getCarById(3)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 50, 3000, 600, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.ONGOING,custService.getCustomerById(3),  carService.getCarById(3)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 55, 3200, 1000, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.COMPLETED,custService.getCustomerById(3),  carService.getCarById(3)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 60, 3600, 1000, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.CANCELED,custService.getCustomerById(3),  carService.getCarById(3)),
				
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 90, 6000, 1200, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.PENDING,custService.getCustomerById(4),  carService.getCarById(4)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 80, 4800, 500, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.ONGOING,custService.getCustomerById(4),  carService.getCarById(4)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 60, 3600, 1000, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.COMPLETED,custService.getCustomerById(4),  carService.getCarById(4)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 50, 3000, 600, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.CANCELED,custService.getCustomerById(4),  carService.getCarById(4)),
				
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 80, 4000, 500, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.PENDING,custService.getCustomerById(5),  carService.getCarById(5)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 90, 4500, 1500, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.ONGOING,custService.getCustomerById(5),  carService.getCarById(5)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 60, 3000, 1000, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.COMPLETED,custService.getCustomerById(5),  carService.getCarById(5)),
				new Bookings(LocalDateTime.now(), LocalDateTime.now(), 40, 2000, 500, Timestamp.valueOf("2021-09-28 02:13:38.605300"), 
						BookingStatus.CANCELED,custService.getCustomerById(5),  carService.getCarById(5))
				);
		
		bRepo.saveAll(blist);
	}

}
//Timestamp.valueOf("2018-11-12 01:02:03.123456789")
//new Bookings(LocalDateTime.now(), LocalDateTime.now(), 50, 8000, 2000, null, 
//BookingStatus.PENDING,custService.getCustomerById(2), carService.getCarById(2))
/*
 * this.pickupDateTime = pickupDateTime;
	this.returnDateTime = returnDateTime;
	this.totalHours = totalHours;
	this.totalFare = totalFare;
	this.advancePayment = advancePayment;
	this.bookingDateTime = bookingDateTime;
	this.bookingStatus = bookingStatus;
	this.customerDetails = customerDetails;
	this.carDetails = carDetails;
 */