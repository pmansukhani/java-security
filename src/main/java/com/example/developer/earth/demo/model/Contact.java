package com.example.developer.earth.demo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_contact")
public class Contact {
	
	@Id
    @GeneratedValue
    private Long id;

	@NotBlank(message = "Please provide a name")
	@Size(min=2, message="Name should have atleast 2 characters")
	@Column(unique = true)
    private String name;
    
	@NotNull(message = "Please provide at least one phone number")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Phone> phones;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Email> emails;
    
    private String address;
    
    private Country country;
    
    @Version
    @Column(name = "VERSION")
    private long version;

}
