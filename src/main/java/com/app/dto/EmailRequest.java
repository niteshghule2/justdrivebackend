package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class EmailRequest {

	private String to;//email
	private String subject;//OTP
	private String message;//Password

	public EmailRequest() {
		System.out.println("in EmailRequest");
	}

}
