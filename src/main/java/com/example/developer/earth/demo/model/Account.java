package com.example.developer.earth.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	@NotBlank(message = "Please provide a name")
	private String username;

	@NotNull(message = "Please provide at least one phone number")
	private String passwordHash;

	@Column(name = "role", nullable = false)
	private String role;

	public Account(String username, String passwordHash, String role) {
		this.username = username;
		this.passwordHash = passwordHash;
		this.role = role;
	}

}
