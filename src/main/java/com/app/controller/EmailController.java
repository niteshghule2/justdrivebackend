package com.app.controller;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.EmailRequest;
import com.app.dto.ErrorResponse;
import com.app.dto.ExpiringOTP;
import com.app.pojos.Customer;
import com.app.service.EmailService;
import com.app.service.EncryptPassword;
import com.app.service.ICustomerService;

@RestController
@RequestMapping("/email")
@CrossOrigin
public class EmailController {
	// private static final long EXPIRED_TIME_IN_SEC = 180L;
	@Autowired
	private ICustomerService custService;
	private HashMap<String, ExpiringOTP> otpMap = new HashMap<String, ExpiringOTP>();

	class CrunchifyRemoveOTP extends TimerTask {
		public void run() {
			HashMap<String, ExpiringOTP> tempMap = new HashMap<String, ExpiringOTP>();
			for (ExpiringOTP e : otpMap.values()) {
				if (Duration.between(e.getCreatedTime(), LocalTime.now()).toMillis() < 120000L) {
					tempMap.put(e.getEmail(), e);
				}
			}
			otpMap = tempMap;
		}
	}

	public EmailController() {
		Timer crunchifyTimer = new Timer();
		crunchifyTimer.schedule(new CrunchifyRemoveOTP(), 120000, 60 * 1000L);
		System.out.println("in EmailController");
	}

	@Autowired
	private EncryptPassword encryptPass;
	@Autowired
	private EmailService emailService;

//	@GetMapping("/hi")
//	public ResponseEntity<?> welcome() {
//		return ResponseEntity.ok("hello");
//	}

	@PostMapping("/sendmail")
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request) {
		System.out.println("in send email");
		// System.out.println(request);
//		Timer crunchifyTimer = new Timer();
//		crunchifyTimer.schedule(new CrunchifyRemoveOTP(), 120000, 60 * 1000L);
		Customer cust = custService.getCustomerByEmail(request.getTo());
		if (cust == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email : SingUp First");
		Random random = new Random();
		int otp = random.nextInt(999999);
		// EmailRequest request = new EmailRequest();
		otpMap.put(request.getTo(), new ExpiringOTP(request.getTo(), otp));
		System.out.println("=====>" + otpMap);
		request.setMessage(
				"Hello " + request.getTo() + " Welcome To JustDrive.Your OTP is " + otp + " Valid for only 2 minutes");
		request.setSubject("JustDrive OTP");

		boolean result = this.emailService.SendEmail(request.getSubject(), request.getMessage(), request.getTo());
		if (result) {
			return ResponseEntity.ok("Send Successfuly");
		} else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not send");
	}
//
//	@GetMapping("/otpvarify")
//	public ResponseEntity<?> otpVarify(@RequestBody EmailRequest e) {
//		// System.out.println("**" + otpMap);
//		ExpiringOTP ee = otpMap.get(e.getTo());
//		//System.out.println(ee);
//		if (ee != null) {
//			// System.out.println("=======>not
//			// null"+Integer.parseInt(e.getSubject())+"*"+ee.getOpt());
//			if (Integer.parseInt(e.getSubject()) == ee.getOpt()) {
//				// System.out.println("=======>not null");
//				otpMap.remove(e.getTo);
//				return ResponseEntity.ok("OTP Varified Successful");
//			}
//		}
//		return ResponseEntity.ok("OTP Varification Failed");
//	}

	@PostMapping("/otpvarify") // email & pass & otp
	public ResponseEntity<?> otpVarify(@RequestBody EmailRequest request) {
		ExpiringOTP ee = otpMap.get(request.getTo());
		if (ee != null) {
			if (Integer.parseInt(request.getSubject()) == ee.getOpt()) {
				Customer cust = custService.getCustomerByEmail(request.getTo());
				cust.setPassword(encryptPass.encryptPassword(request.getMessage()));
				custService.resetPassword(cust);
				otpMap.remove(request.getTo());
				return ResponseEntity.ok(cust);
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ErrorResponse("Wrong OTP Varification Failed...!!!", LocalDateTime.now()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse("OTP Expired...!!!", LocalDateTime.now()));
	}

	// for signup
	@PostMapping("/signupmail")
	public ResponseEntity<?> sendSingupEmail(@RequestBody EmailRequest request) {
		System.out.println("in send email");
		// System.out.println(request);
//		Timer crunchifyTimer = new Timer();
//		crunchifyTimer.schedule(new CrunchifyRemoveOTP(), 120000, 60 * 1000L);
		Customer cust = custService.getCustomerByEmail(request.getTo());
		if (cust != null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Already Present.Use Different Email");
		Random random = new Random();
		int otp = random.nextInt(999999);
		// EmailRequest request = new EmailRequest();
		otpMap.put(request.getTo(), new ExpiringOTP(request.getTo(), otp));
		System.out.println("=====>" + otpMap);
		request.setMessage(
				"Hello " + request.getTo() + " Welcome To JustDrive.Your OTP is " + otp + " Valid for only 2 minutes");
		request.setSubject("JustDrive OTP");
		boolean result = this.emailService.SendEmail(request.getSubject(), request.getMessage(), request.getTo());
		if (result) {
			return ResponseEntity.ok("Send Successfuly");
		} else {
			return new ResponseEntity<>(new ErrorResponse("Email not found!", LocalDateTime.now()),
					HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/signupwithotp/{otp}") // email and otp
	public ResponseEntity<?> signupWithOTP(@RequestBody Customer cust, @PathVariable int otp) {
		ExpiringOTP ee = otpMap.get(cust.getEmail());
		if (ee != null) {
			if (otp == ee.getOpt()) {
				cust.setPassword(encryptPass.encryptPassword(cust.getPassword()));
				custService.saveNewCustomer(cust);
				otpMap.remove(cust.getEmail());
				return ResponseEntity.ok(cust);
			} else
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new ErrorResponse("Wrong OTP....Failed...!!!", LocalDateTime.now()));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse("OTP Expired...!!!", LocalDateTime.now()));
	}
}
