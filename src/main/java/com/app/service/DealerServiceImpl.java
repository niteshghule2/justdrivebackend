package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.UserHandlingException;
import com.app.dao.CityRepository;
import com.app.dao.DealerRepository;
import com.app.pojos.City;
import com.app.pojos.Dealer;
@Service
@Transactional
public class DealerServiceImpl implements IDealerService {
	@Autowired
	private DealerRepository dealerRepo;
	
	@Autowired
	private CityRepository cityRepo;

	@Override
	public Dealer loginCustomer(String email, String password) {
		return dealerRepo.findByEmailAndPassword(email, password);
	}

	@Override
	public Dealer getDealerDetails(int dealerId) {
		return dealerRepo.findById(dealerId).orElseThrow(()->new UserHandlingException("Dealer Not Found : Invalid Dealer ID"));
	}

	@Override
	public Dealer addDealer(Dealer transientDealer,int cityId) {
		City city=cityRepo.findById(cityId).orElseThrow(()->new UserHandlingException("City Not Found : Invalid City ID"));
		transientDealer.setCity(city);
		return dealerRepo.save(transientDealer);
	}

	@Override
	public List<Dealer> getAllDealers() {
		return dealerRepo.findAll();
	}

	@Override
	public long getCount() {
		// TODO Auto-generated method stub
		return dealerRepo.count();
		
	}
	
	@Override
	public Dealer GetDealerById(int carTypeId) {
		return dealerRepo.findById(carTypeId).orElseThrow(() -> new UserHandlingException("Dealer Not Found : Invalid Dealer ID"));
	}
}
