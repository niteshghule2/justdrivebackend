package com.app.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpiringOTP {
	private String email;
private int opt;
private LocalTime createdTime;
public ExpiringOTP(String email, int opt) {
	super();
	this.email = email;
	this.opt = opt;
	this.createdTime=LocalTime.now();
}

}
