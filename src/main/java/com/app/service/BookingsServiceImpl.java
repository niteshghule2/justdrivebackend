package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.UserHandlingException;
import com.app.dao.BookingsRepository;
import com.app.pojos.BookingStatus;
import com.app.pojos.Bookings;

@Service
@Transactional
public class BookingsServiceImpl implements IBookingsService {
	@Autowired
	private BookingsRepository bookingRepo;

	@Override
	public List<Bookings> findByCustomerID(int Id) {

		return bookingRepo.findByCustomerID(Id);
	}

	@Override
	public List<Bookings> findByDealerId(int id) {
		return bookingRepo.findByDealerId(id);
	}

	@Override
	public List<Bookings> findByDealerIdAndPendingStatus(int id, BookingStatus bstatus) {
		return bookingRepo.findByDealerIdAndPendingStatus(id, bstatus);
	}

	@Override
	public List<Bookings> getAllBookings() {
		return bookingRepo.findAll();
	}

	@Override
	public Bookings findByBookingsId(int bookingId) {
		return bookingRepo.findById(bookingId)
				.orElseThrow(() -> new UserHandlingException("Booking Not Found : Invalid Booking ID"));
	}

	@Override
	public String changeBookingStatus(int bookingId, BookingStatus bookingStatus) {
		Bookings b = bookingRepo.findById(bookingId)
				.orElseThrow(() -> new UserHandlingException("Booking Not Found : Invalid Booking ID"));
		b.setBookingStatus(bookingStatus);
		if (bookingStatus.equals(BookingStatus.valueOf("COMPLETED"))
				|| bookingStatus.equals(BookingStatus.valueOf("CANCELED")))
			b.getCarDetails().setCarStatus(false);
		if (bookingStatus.equals(BookingStatus.valueOf("ONGOING"))
				|| bookingStatus.equals(BookingStatus.valueOf("PENDING")))
			b.getCarDetails().setCarStatus(true);
		return "Status Changed";
	}

	@Override
	public long getCount() {
		return bookingRepo.count();
	}

	@Override
	public long getCountByCanceledB() {
		return bookingRepo.findByBookingStatus(BookingStatus.valueOf("CANCELED")).size();
	}

	@Override
	public long getCountByCompletedB() {
		return bookingRepo.findByBookingStatus(BookingStatus.valueOf("COMPLETED")).size();
	}

	@Override
	public long getCountByOngoingB() {
		return bookingRepo.findByBookingStatus(BookingStatus.valueOf("ONGOING")).size();
	}

	@Override
	public long getCountByPendingB() {
		return bookingRepo.findByBookingStatus(BookingStatus.valueOf("PENDING")).size();
	}

	@Override
	public double getTotalBookingsAmt() {
		List<Bookings> bList = bookingRepo.findAll();
		double amt = 0;

		for (Bookings b : bList) {
			amt += b.getTotalFare();
		}
		return amt;
	}

	@Override
	public double getTotalBookingsAmtByDealerId(int dId) {
		List<Bookings> bList =bookingRepo.findByDealerId(dId);
		double amt = 0;

		for (Bookings b : bList) {
			amt += b.getTotalFare();
		}
		return amt;
	}
}
