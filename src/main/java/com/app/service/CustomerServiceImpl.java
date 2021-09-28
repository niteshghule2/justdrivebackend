package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.UserHandlingException;
import com.app.dao.BookingsRepository;
import com.app.dao.CustomerRepository;
import com.app.pojos.Bookings;
import com.app.pojos.Customer;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {
	// dependency
	@Autowired
	private CustomerRepository custRepo;

	@Autowired
	private BookingsRepository bookingsRepo;

	@Override
	public Customer saveNewCustomer(Customer transientCustomer) {
		return custRepo.save(transientCustomer);
	}

	@Override
	public Customer loginCustomer(String email, String password) {
		// Customer customer = custRepo.findByCustomerEmailAndPassword(email, password);
		Customer cust1 = custRepo.findByEmailAndPassword(email, password);

		return cust1;

	}

	@Override
	public List<Customer> getAllCustomers() {

		return custRepo.findAll();
	}

	@Override
	public Bookings newBooking(Bookings transientBooking) {

		return bookingsRepo.save(transientBooking);
	}

	@Override
	public Customer getCustomerDetails(int custId) {

		return custRepo.findById(custId)
				.orElseThrow(() -> new UserHandlingException("Customer Not Found : Invalid Customer ID"));
	}

	@Override
	public List<Customer> allCutomerList() {
		return custRepo.findAll();

	}

	@Override
	public long getCount() {
		return custRepo.count();
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		return custRepo.findByEmail(email);
	}

	@Override
	public Customer resetPassword(Customer transientCust) {
		return custRepo.save(transientCust);
	}
	
	@Override
	public Customer getCustomerById(int custId) {
		return custRepo.findById(custId)
				.orElseThrow(() -> new UserHandlingException("Customer Not Found : Invalid Customer ID"));
	}
	
	@Override
	public String updateDlNumber(int custId, String drivingLicenseNo) {
		Customer c = custRepo.findById(custId).
					orElseThrow(() -> new UserHandlingException("Customer Not Found : Invalid Customer ID"));
		c.setDrivingLicenseNo(drivingLicenseNo);
		return "DL_Number changed";
	}
	
	
}
