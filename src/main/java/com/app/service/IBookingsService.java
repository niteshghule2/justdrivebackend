package com.app.service;

import java.util.List;

import com.app.pojos.BookingStatus;
import com.app.pojos.Bookings;

public interface IBookingsService {
	List<Bookings> findByCustomerID(int Id);

	List<Bookings> findByDealerId(int id);

	List<Bookings> findByDealerIdAndPendingStatus(int id, BookingStatus bstatus);

	List<Bookings> getAllBookings();

	Bookings findByBookingsId(int bookingId);
	
	double getTotalBookingsAmtByDealerId(int dId);

	String changeBookingStatus(int bookingId, BookingStatus bookingStatus);

	long getCount();

	long getCountByPendingB();

	long getCountByOngoingB();

	long getCountByCompletedB();

	long getCountByCanceledB();
	
	double getTotalBookingsAmt();

}
