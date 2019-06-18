package com.example.developer.earth.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name="user_phone")
public class Phone {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@NotBlank(message = "Phone number is required")
	private String number;
	
	@Version
	@Column(name = "VERSION")
	private long version;

}
