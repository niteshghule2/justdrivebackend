package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Total Cars Count
//Total Bookings Count
//Pending Bookings Count
//Completed Bookings Count
//All Customer Count
//All Dealers Count
//All City Count

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DashBoardCount {
	private long allCustomersCount;
	private long allDealersCount;
	private long allCityCount;
	private long allCarCount;
	private long availableCarCount;
	private long allBookingsCount;
	private long allOngoingCount;
	private long allPendingBCount;
	private long allCompletedBCount;
	private long allCanceledBCount;

	private double bookingAmount;

}
