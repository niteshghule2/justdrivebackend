package com.app.controller;

import java.time.LocalDateTime;
import java.util.List;

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
import com.app.pojos.Bookings;
import com.app.pojos.Car;
import com.app.pojos.City;
import com.app.pojos.ContactUs;
import com.app.pojos.Customer;
import com.app.service.EncryptPassword;
import com.app.service.IBookingsService;
import com.app.service.ICarService;
import com.app.service.ICarTypeService;
import com.app.service.ICityService;
import com.app.service.ICustomerService;
import com.app.service.IDashBoardService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerRestController {

	@Autowired
	private ICustomerService custService;

	@Autowired
	private IBookingsService bookingsService;

	@Autowired
	private ICarService carService;

	@Autowired
	private ICarTypeService cartypeService;

	@Autowired
	private ICityService cityService;
	
	@Autowired
	private IDashBoardService dService;
	
	@Autowired
	private EncryptPassword encryptPass;

	public CustomerRestController() {
		System.out.println("in const of " + getClass().getName());
	}

	@GetMapping("/bookingslist/{Id}")
	public ResponseEntity<?> getAllBookingsListByCustId(@PathVariable int Id) {
		List<Bookings> bookingsList = bookingsService.findByCustomerID(Id);
		return ResponseEntity.ok(bookingsList);
	}

	@PostMapping("/login/{emailId}/{password}")
	public ResponseEntity<?> loginCustomer(@PathVariable String emailId, @PathVariable String password) {
		System.out.println("in login controller");
		Customer customer = custService.loginCustomer(emailId, encryptPass.encryptPassword(password));
		if (customer != null) {
			return new ResponseEntity<>(customer, HttpStatus.CREATED);
		}
		ErrorResponse error = new ErrorResponse("Login Failed : Invalid credential", LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> signupCustomer(@RequestBody Customer customer) {
		System.out.println("in save customer " + customer);// user : not null , except id
		 customer.setPassword(encryptPass.encryptPassword(customer.getPassword()));
		return new ResponseEntity<>(custService.saveNewCustomer(customer), HttpStatus.CREATED);

	}

	@PostMapping("/newbooking/{customer_id}/{car_id}")
	public ResponseEntity<?> newBooking(@RequestBody Bookings booking, @PathVariable int customer_id,
			@PathVariable int car_id) {
		System.out.println("in new Booking " + booking);
		booking.setCustomerDetails(custService.getCustomerDetails(customer_id));
		Car car=carService.getCarDetails(car_id);
		car.setCarStatus(true);
		booking.setCarDetails(car);
		return new ResponseEntity<>(custService.newBooking(booking), HttpStatus.ACCEPTED);
	}

	@GetMapping("/tobooking/{tid}/{cid}")
	public ResponseEntity<?> getAllCarsByCarTypeAndCity(@PathVariable int tid, @PathVariable int cid) {
		List<Car> carList = carService.getAllCarsByCarTypeAndCity(tid, cid);
		if (carList.size() > 0)
			return ResponseEntity.ok(carList);
		ErrorResponse error = new ErrorResponse("Cars Not Available for given Choices", LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	

	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable int id) {
		return ResponseEntity.ok(custService.getCustomerDetails(id));
	}

	
//	@GetMapping("/allcartype")
//	public ResponseEntity<?> getAllCarType() {
//		return ResponseEntity.ok(cartypeService.allCarTypeList());
//	}
//	
	@GetMapping("/allcartype/{cId}")
public ResponseEntity<?> getAllCarTypeWithCityId(@PathVariable int cId) {
		return ResponseEntity.ok(cartypeService.allCarTypeListByCityId(cId));
	}
	
//	public ResponseEntity<?> getAllCarTypeWithCityId(@PathVariable int cId) {
//		List<CarType> c=cartypeService.allCarTypeListByCityId(cId);
//		if(c.size()==0)
//			return new ResponseEntity<>("we are cooming to your location",HttpStatus.FOUND);
//		return ResponseEntity.ok(c);
//	}
	
	@PostMapping("/sendcontactus")
	public ResponseEntity<?> sendContactUs(@RequestBody ContactUs transientContactUs) {
		return ResponseEntity.ok(dService.sendContactUs(transientContactUs));
	}
	
	@GetMapping("/allcity")
	public ResponseEntity<?> getAllCity() {
		List<City> list = cityService.cityListWithDealer();
		return ResponseEntity.ok(list);
	}
	
	@PostMapping("/updatedl/{cId}/{dlNO}")
	public ResponseEntity<?> updateDlNumber(@PathVariable int cId,@PathVariable String dlNO ) {
			return ResponseEntity.ok(custService.updateDlNumber(cId, dlNO));
		}
	
	
	
//	@PostMapping("/resetpass")		//aceept and send otp
//	public ResponseEntity<?> sendOTP(@PathVariable String email){
//		
//	}
}
