package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "admin")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Admin extends BaseEntity {
	@Column(length = 20,nullable = false)
	private String name;
	@Column(length = 20,unique = true)
	private String email;
	@Column(length = 200,nullable = false)
	@JsonIgnore
	private String password;
}
