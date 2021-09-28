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
@Table(name = "cars")
@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
public class Car extends BaseEntity {
	

	@Column(length = 20, nullable = false, unique = true)
	private String carNo;
	@Column(length = 20)
	private String carCompany;
	@Column(length = 20)
	private String carModel;
	@Column(length = 10)
	private String fuelType;

	private String carRCImage;

	private double hourlyRate;

	private boolean carStatus;

//Links to other tables
	@ManyToOne
	@JoinColumn(name = "dealer_fk")
	private Dealer dealer;

	@ManyToOne
	@JoinColumn(name = "carType_fk")
	@JsonIgnoreProperties("carList")
	private CarType carType;

	public Car(String carNo, String carCompany, String carModel, String fuelType, double hourlyRate,
			boolean carStatus) {
		super();
		this.carNo = carNo;
		this.carCompany = carCompany;
		this.carModel = carModel;
		this.fuelType = fuelType;
		this.hourlyRate = hourlyRate;
		this.carStatus = carStatus;
	}
	
	public Car(String carNo, String carCompany, String carModel, String fuelType, String carRCImage, double hourlyRate,
			boolean carStatus, Dealer dealer, CarType carType) {
		super();
		this.carNo = carNo;
		this.carCompany = carCompany;
		this.carModel = carModel;
		this.fuelType = fuelType;
		this.carRCImage = carRCImage;
		this.hourlyRate = hourlyRate;
		this.carStatus = carStatus;
		this.dealer = dealer;
		this.carType = carType;
	}
	@Override
	public String toString() {
		return "Car [carNo=" + carNo + ", carCompany=" + carCompany + ", carModel=" + carModel + ", fuelType="
				+ fuelType + ", carRCImage=" + carRCImage + ", hourlyRate=" + hourlyRate + ", status=" + carStatus
				+ "]";
	}


	

}
