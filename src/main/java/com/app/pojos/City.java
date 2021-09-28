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
@Table(name = "city")
@NoArgsConstructor
@Setter
@Getter
public class City extends BaseEntity {
	@Column(length = 20, nullable = false, unique = true)
	private String cityName;
	@Column(length = 20, nullable = false)
	private String cityState;
	@Column(length = 20, nullable = false)
	private String cityCountry;

	private int pincode;

//Links to other Tables
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties("city")
	private List<Dealer> dealerList = new ArrayList<Dealer>();

	// helper to add dealer in dealer list
	public void addDealer(Dealer dealer) {
		dealerList.add(dealer);
		dealer.setCity(this);
	}

	@Override
	public String toString() {
		return "City [cityName=" + cityName + ", cityState=" + cityState + ", cityCountry=" + cityCountry + ", pincode="
				+ pincode + "]";
	}

	public City(String cityName, String cityState, String cityCountry, int pincode) {
		super();
		this.cityName = cityName;
		this.cityState = cityState;
		this.cityCountry = cityCountry;
		this.pincode = pincode;
	}

}
