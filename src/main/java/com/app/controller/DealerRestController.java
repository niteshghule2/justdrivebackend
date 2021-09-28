package com.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
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
import com.app.pojos.BookingStatus;
import com.app.pojos.Car;
import com.app.pojos.Dealer;
import com.app.service.EncryptPassword;
import com.app.service.IBookingsService;
import com.app.service.ICarService;
import com.app.service.ICarTypeService;
import com.app.service.ICityService;
import com.app.service.ICustomerService;
import com.app.service.IDashBoardService;
import com.app.service.IDealerService;

@RestController
@RequestMapping("/dealer")
@CrossOrigin(origins = "http://localhost:3000")
public class DealerRestController {

	@Value("${file.upload.location}")
	private String location;

	@Autowired
	private ICustomerService custService;

	@Autowired
	private ICityService cityService;
	@Autowired
	private IDealerService dealerService;

	@Autowired
	private ICarService carService;

	@Autowired
	private ICarTypeService carTypeService;

	@Autowired
	private IBookingsService bookingService;

	@Autowired
	private IDashBoardService dService;
	
	@Autowired
	private EncryptPassword encryptPass;

	@PostMapping("/login/{emailId}/{password}")
	public ResponseEntity<?> loginDealer(@PathVariable String emailId, @PathVariable String password) {
		System.out.println("in login controller");
		Dealer dealer = dealerService.loginCustomer(emailId, encryptPass.encryptPassword(password));
		if (dealer != null)
			return new ResponseEntity<>(dealer, HttpStatus.CREATED);
		ErrorResponse error = new ErrorResponse("Login Failed : Invalid credential", LocalDateTime.now());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

//	@PostMapping("/addcar/{dealer_id}/{cartype_id}")
//	public ResponseEntity<?> addCar(@RequestParam String stringCar, @PathVariable int dealer_id,
//			@PathVariable int cartype_id, @RequestParam(required = false) MultipartFile image) {
//		System.out.println("in add car");
//		try {
//			Car car = new ObjectMapper().readValue(stringCar, Car.class);
//			if (image != null) {
//				image.transferTo(new File(location, image.getOriginalFilename()));
//				car.setCarRCImage(image.getOriginalFilename());
//			}
//			System.out.println(image.getOriginalFilename());
//			car.setCarType(carTypeService.getCarTypeById(cartype_id));
//			car.setDealer(dealerService.getDealerDetails(dealer_id));
//			return new ResponseEntity<>(carService.addNewCar(car), HttpStatus.CREATED);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		ErrorResponse err = new ErrorResponse("Failed...!!!", LocalDateTime.now());
//		return ResponseEntity.ok(err);
//	}

	@GetMapping("/carlist/{dealer_id}")
	public ResponseEntity<?> carList(@PathVariable int dealer_id) {
		return ResponseEntity.ok(carService.getAllCarList(dealer_id));
	}

	@GetMapping("/allbookingslist/{dealer_id}")
	public ResponseEntity<?> allBookingList(@PathVariable int dealer_id) {
		return ResponseEntity.ok(bookingService.findByDealerId(dealer_id));
	}

	@GetMapping("/pendingbookingslist/{dealer_id}/{bstatus}")
	public ResponseEntity<?> pendingBookingsList(@PathVariable int dealer_id, @PathVariable BookingStatus bstatus) {
		return ResponseEntity.ok(bookingService.findByDealerIdAndPendingStatus(dealer_id, bstatus));
	}

	@GetMapping("/changebstatus/{bookingId}/{bstatus}")
	public String changeBookingStatus(@PathVariable int bookingId, @PathVariable String bstatus) {

		bookingService.changeBookingStatus(bookingId, BookingStatus.valueOf(bstatus.toUpperCase()));
		return "Bookings Status Has Changed";
	}

	@GetMapping("/cardetails/{carId}/{hourlyRate}")
	public String changeCarHourlyRate(@PathVariable int carId, @PathVariable double hourlyRate) {
		// carService.getCarDetails(carId).setHourlyRate(hourlyRate);
		return carService.changeCarHourlyRate(hourlyRate, carId);
	}

	@GetMapping("/allcartype")
	public ResponseEntity<?> getAllCarType() {
		return ResponseEntity.ok(carTypeService.allCarTypeList());
	}

//	  @GetMapping("/image/{imgName}") public ResponseEntity<InputStreamResource>
//	  getImage(@PathVariable String imgName) throws IOException {
//	  
//	  System.out.println("in img download 2 " + (location + imgName)); Path path =
//	  Paths.get(location, imgName); InputStreamResource inputStreamResource = new
//	  InputStreamResource(new FileInputStream(path.toFile()));
//	  
//	  HttpHeaders headers = new HttpHeaders(); headers.add("content-type",
//	  Files.probeContentType(path)); return
//	  ResponseEntity.ok().headers(headers).body(inputStreamResource); }
	
	@PostMapping("/addcar/{dealer_id}/{cartype_id}")
	public ResponseEntity<?> addCar(@RequestBody Car transientCar, @PathVariable Integer dealer_id,
			@PathVariable Integer cartype_id) {
		System.out.println("in add car");
		transientCar.setCarType(carTypeService.getCarTypeById(cartype_id));
		transientCar.setDealer(dealerService.getDealerDetails(dealer_id));
		return new ResponseEntity<>(carService.addNewCar(transientCar), HttpStatus.CREATED);
	}

	@GetMapping("/carimage/{imgName}")
	public ResponseEntity<?> getImage(@PathVariable String imgName) throws IOException {
		System.out.println("in get Image" + location + "--" + imgName);
		Path path = Paths.get(location, imgName);
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", Files.probeContentType(path));
		// return ResponseEntity.ok().headers(headers).body(path);
		ErrorResponse err = new ErrorResponse(location + "\\" + imgName, LocalDateTime.now());
		return ResponseEntity.ok(err);
	}

	@GetMapping("/dashboard/{dId}")
	public ResponseEntity<?> getDashBoardCount(@PathVariable int dId) {
		System.out.println("in getDashBoardDealer");
		return ResponseEntity.ok(dService.getDashBoardCount(custService.getCount(), dealerService.getCount(),
				cityService.getCount(), carService.getAllCarList(dId).size(),
				carService.getCarsByStatusAndDealer(dId).size(), bookingService.findByDealerId(dId).size(),
				bookingService.findByDealerIdAndPendingStatus(dId, BookingStatus.valueOf("ONGOING")).size(),
				bookingService.findByDealerIdAndPendingStatus(dId, BookingStatus.valueOf("PENDING")).size(),
				bookingService.findByDealerIdAndPendingStatus(dId, BookingStatus.valueOf("COMPLETED")).size(),
				bookingService.findByDealerIdAndPendingStatus(dId, BookingStatus.valueOf("CANCELED")).size(),
				bookingService.getTotalBookingsAmtByDealerId(dId)));
	}

	@GetMapping("/dealerbyid/{dId}")
	public ResponseEntity<?> getDealerDetails(@PathVariable int dId) {
		return ResponseEntity.ok(dealerService.getDealerDetails(dId));
	}
}
