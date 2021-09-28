package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ContactUsRepository;
import com.app.dto.DashBoardCount;
import com.app.pojos.ContactUs;

@Service
@Transactional
public class DashBoardService implements IDashBoardService {
@Autowired
private ContactUsRepository contactUsRepo;
	@Override
	public DashBoardCount getDashBoardCount(long allCustomersCount, long allDealersCount, long allCityCount, long allCarCount, long availableCarCount, long allBookingsCount, long allOngoingCount, long allPendingBCount, long allCompletedBCount,
			long allCanceledBCount, double bookingAmount) {
		DashBoardCount dCount=new DashBoardCount(allCustomersCount, allDealersCount, allCityCount, allCarCount, availableCarCount, allBookingsCount, allOngoingCount, allPendingBCount, allCompletedBCount,allCanceledBCount , bookingAmount);
		return dCount;
	}

	@Override
	public ContactUs sendContactUs(ContactUs transientContactUs) {
		return contactUsRepo.save(transientContactUs);
	}

	@Override
	public List<ContactUs> getAllContactUs() {
		return contactUsRepo.findAll();
	}
	
	
}
