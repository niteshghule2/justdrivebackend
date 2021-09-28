package com.app.service;

import java.util.List;

import com.app.dto.DashBoardCount;
import com.app.pojos.ContactUs;

public interface IDashBoardService {

	DashBoardCount getDashBoardCount(long allCustomersCount, long allDealersCount, long allCityCount, long allCarCount,
			long availableCarCount, long allBookingsCount, long allOngoingCount, long allPendingBCount,
			long allCompletedBCount, long allCanceledBCount, double bookingAmount);
	
	ContactUs sendContactUs(ContactUs transientContactUs);
	
	List<ContactUs> getAllContactUs();
}
