package com.app.pojos;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@NoArgsConstructor
@Setter
@Getter
public class Bookings extends BaseEntity {
	private LocalDateTime pickupDateTime;
	// private LocalTime pickupTime;
	private LocalDateTime returnDateTime;
	// private LocalDateTime returnTime;
	private int totalHours;
	// private double dailyRate;
	private double totalFare;
	// private double gst;
	private double advancePayment;
//	@Column(length = 20)
//	private String paymentMode;
	private Timestamp bookingDateTime;
	private BookingStatus bookingStatus;

//Links to other Tables

	@ManyToOne
	@JoinColumn(name = "customer_fk")
	@JsonIgnoreProperties("bookingHistory")
	private Customer customerDetails;

	@ManyToOne
	@JoinColumn(name = "car_fk")
	private Car carDetails;

	public Bookings(LocalDateTime pickupDateTime, LocalDateTime returnDateTime, double totalFare, int totalHours,
			double advancePayment, Timestamp bookingDateTime, BookingStatus bookingStatus) {
		super();
		this.pickupDateTime = pickupDateTime;
		this.returnDateTime = returnDateTime;
		this.totalFare = totalFare;
		this.totalHours = totalHours;
		this.advancePayment = advancePayment;
		this.bookingDateTime = bookingDateTime;
		this.bookingStatus = bookingStatus;
	}

	public Bookings(LocalDateTime pickupDateTime, LocalDateTime returnDateTime, int totalHours, double totalFare,
			double advancePayment, Timestamp bookingDateTime, BookingStatus bookingStatus, Customer customerDetails,
			Car carDetails) {
		super();
		this.pickupDateTime = pickupDateTime;
		this.returnDateTime = returnDateTime;
		this.totalHours = totalHours;
		this.totalFare = totalFare;
		this.advancePayment = advancePayment;
		this.bookingDateTime = bookingDateTime;
		this.bookingStatus = bookingStatus;
		this.customerDetails = customerDetails;
		this.carDetails = carDetails;
	}

}
