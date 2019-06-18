package com.example.developer.earth.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

@Data
@Entity
@Table(name="user_email")
public class Email {
	
	@Id
    @GeneratedValue
    private Long id;
	
	@javax.validation.constraints.Email
	private String email;
	
	@Version
	@Column(name = "VERSION")
	private long version;

}
