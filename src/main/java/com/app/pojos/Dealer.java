package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dealers")
@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
public class Dealer extends BaseEntity {

	@Column(length = 20, nullable = false)
	private String name;
	@Column(length = 20, unique = true)
	private String email;
	@Column(length = 200, nullable = false)
	private String password;

	private long mobileNumber;
	@Column(length = 50, nullable = false)
	private String address;

	private String latitude;

	private String longitude;

//Links to other Tables
	@ManyToOne
	@JoinColumn(name = "city_fk")
	@JsonIgnoreProperties("dealerList")
	private City city;

	public Dealer(String name, String email, String password, long mobileNumber, String address, String latitude,
			String longitude) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Dealer [name=" + name + ", email=" + email + ", password=" + password + ", mobileNumber=" + mobileNumber
				+ ", address=" + address + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	public Dealer(String name, String email, String password, long mobileNumber, String address, String latitude,
			String longitude, City city) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.city = city;
	}

}
