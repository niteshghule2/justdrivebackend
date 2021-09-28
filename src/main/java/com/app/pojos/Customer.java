package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customers")
@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
public class Customer extends BaseEntity {

	@Column(length = 20, nullable = false)
	private String firstName;
	@Column(length = 20, nullable = false)
	private String lastName;
	@Column(length = 50, unique = true)
	private String email;
	@Column(length = 200, nullable = false)
	private String password;
	@Column (unique =true)
	private long mobile;

	@Column(length = 30)
	private String drivingLicenseNo;

	private String DrivingLicenceImage;

	// Links to other tables
	@OneToMany(mappedBy = "customerDetails", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnoreProperties("customerDetails")
	private List<Bookings> bookingHistory=new ArrayList<Bookings>();
	
	//helper method to add booking in bookingHistory
	public void addBookingHistory(Bookings booking) {
		bookingHistory.add(booking);
		booking.setCustomerDetails(this);
	}

	public Customer(String firstName, String lastName, String email, String password, long mobile,
			String drivingLicenseNo, String drivingLicenceImage) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobile = mobile;
		this.drivingLicenseNo = drivingLicenseNo;
		DrivingLicenceImage = drivingLicenceImage;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + ", mobile=" + mobile + ", drivingLicenseNo=" + drivingLicenseNo + ", DrivingLicenceImage="
				+ DrivingLicenceImage + "]";
	}

}
