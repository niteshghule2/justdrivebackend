package com.app.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ErrorResponse;
import com.app.pojos.Admin;
import com.app.pojos.City;
import com.app.pojos.Dealer;
import com.app.service.EncryptPassword;
import com.app.service.IAdminService;
import com.app.service.IBookingsService;
import com.app.service.ICarService;
import com.app.service.ICarTypeService;
import com.app.service.ICityService;
import com.app.service.ICustomerService;
import com.app.service.IDashBoardService;
import com.app.service.IDealerService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminRestController {
	@Autowired
	private IDealerService dealerService;

	@Autowired
	private IDashBoardService dService;

	@Autowired
	private ICityService cityService;

	@Autowired
	private ICustomerService custService;

	@Autowired
	private IBookingsService bookingService;

	@Autowired
	private ICarService carService;

	@Autowired
	private ICarTypeService carTypeService;
	
	@Autowired
	private IAdminService adminService;
	
	@Autowired
	private EncryptPassword encryptPass;
	
	@PostMapping("/login/{email}/{password}")
	public ResponseEntity<?> loginAdmin(@PathVariable String email, @PathVariable String password) {
		System.out.println("in admin login");
		Admin admin = adminService.loginAdmin(email,encryptPass.encryptPassword(password));
		if (admin != null)
			return new ResponseEntity<>(admin, HttpStatus.CREATED);
		ErrorResponse error = new ErrorResponse("Login Failed : Invalid credential", LocalDateTime.now());
		return ResponseEntity.ok(error);
	}

	@PostMapping("/addcity")
	public ResponseEntity<?> addCity(@RequestBody City transientCity) {
		return new ResponseEntity<>(cityService.addCity(transientCity), HttpStatus.CREATED);
	}

	@PostMapping("/adddealer/{cId}")
	public ResponseEntity<?> addDealer(@RequestBody Dealer transientDealer, @PathVariable int cId) {
		System.out.println("in add dealer");
		transientDealer.setPassword(encryptPass.encryptPassword(transientDealer.getPassword()));
//		Dealer dealer = custService.loginCustomer(emailId, encryptPass.encryptPassword(password));
		return new ResponseEntity<>(dealerService.addDealer(transientDealer, cId), HttpStatus.CREATED);
	}

	@GetMapping("/allcustlist")
	public ResponseEntity<?> getAllCutomerList() {
		return ResponseEntity.ok(custService.allCutomerList());
	}

	@GetMapping("/alldealerlist")
	public ResponseEntity<?> getAllDealerList() {
		return ResponseEntity.ok(dealerService.getAllDealers());
	}

	@GetMapping("/allbookinglist")
	public ResponseEntity<?> getAllBookingsList() {
		return ResponseEntity.ok(bookingService.getAllBookings());
	}

	@GetMapping("/allcars")
	public ResponseEntity<?> getAllCars() {
		return ResponseEntity.ok(carService.getAllCars());
	}

	@GetMapping("/cartypebyid/{carTypeId}")
	public ResponseEntity<?> getCarTypeById(@PathVariable int carTypeId) {
		return ResponseEntity.ok(carTypeService.getCarTypeById(carTypeId));
	}

	@GetMapping("/bookingbyid/{bookingId}")
	public ResponseEntity<?> getBookingById(@PathVariable int carTypeId) {
		return ResponseEntity.ok(bookingService.findByBookingsId(carTypeId));
	}

	@GetMapping("/allcartype")
	public ResponseEntity<?> getAllCarType() {
		return ResponseEntity.ok(carTypeService.allCarTypeList());
	}

	@GetMapping("/dashboard")
	public ResponseEntity<?> getDashBoardCount() {
		return ResponseEntity.ok(dService.getDashBoardCount(custService.getCount(), dealerService.getCount(),
				cityService.getCount(), carService.getCount(), carService.getCountByStatus(), bookingService.getCount(),
				bookingService.getCountByOngoingB(), bookingService.getCountByPendingB(),
				bookingService.getCountByCompletedB(), bookingService.getCountByCanceledB(),
				bookingService.getTotalBookingsAmt()));
	}

	@GetMapping("/getcontactus")
	public ResponseEntity<?> getAllContactUs() {
		return ResponseEntity.ok(dService.getAllContactUs());
	}
	@GetMapping("/allcity")
	public ResponseEntity<?> getAllCity() {
		return ResponseEntity.ok(cityService.allCityList());
	}

}
