package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.pojos.BookingStatus;
import com.app.pojos.Bookings;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {

	@Query("select b from Bookings b where b.customerDetails.id=:id")
	List<Bookings> findByCustomerID(@Param("id") int id);

	@Query("select b from Bookings b where b.carDetails.dealer.id=:id")
	List<Bookings> findByDealerId(@Param("id") int id);

	@Query("select b from Bookings b where b.carDetails.dealer.id=:id and b.bookingStatus=:bstatus")
	List<Bookings> findByDealerIdAndPendingStatus(@Param("id") int id, @Param("bstatus") BookingStatus bstatus);

	@Query("select b from Bookings b where b.bookingStatus=:bstatus")
	List<Bookings> findByBookingStatus(@Param("bstatus") BookingStatus bstatus);

}
