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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car_type")
@NoArgsConstructor
//@AllArgsConstructor
@Setter
@Getter
public class CarType extends BaseEntity {

	

	@Column(length = 20, nullable = false,unique = true)
	private String carTypeName;

	@Column(length = 30)
	private String carIncludes;
	@Column(length = 10)
	private String personCapacity;

//Links to other Tables
	@OneToMany(mappedBy = "carType",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JsonIgnoreProperties("carType")
	private List<Car> carList=new ArrayList<Car>();
	
	public CarType(String carTypeName, String carIncludes, String personCapacity) {
		super();
		this.carTypeName = carTypeName;
		this.carIncludes = carIncludes;
		this.personCapacity = personCapacity;
	}

	public CarType(String carTypeName, String carIncludes, String personCapacity, List<Car> carList) {
		super();
		this.carTypeName = carTypeName;
		this.carIncludes = carIncludes;
		this.personCapacity = personCapacity;
		this.carList = carList;
	}

	//helper method to add car in carList
	public void addCar(Car car) {
		carList.add(car);
		car.setCarType(this);
	}




}
