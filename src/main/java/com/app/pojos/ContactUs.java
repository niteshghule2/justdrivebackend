package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contact_us")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class ContactUs extends BaseEntity{
private String name;
private String email;
private String subject;
private String msg;
//public ContactUs(String name, String email, String subject, String msg) {
//	super();
//	this.name = name;
//	this.email = email;
//	this.subject = subject;
//	this.msg = msg;
//}
}
