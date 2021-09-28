package com.app.service;

import java.util.List;

import com.app.pojos.Dealer;

public interface IDealerService {
	Dealer loginCustomer(String email,String password);
	
	Dealer getDealerDetails(int dealerId);

	Dealer addDealer(Dealer transientDealer,int cityName);
	
	List<Dealer> getAllDealers();
	
	long getCount();
	
	Dealer GetDealerById(int  id);
}
