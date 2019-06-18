package com.example.developer.earth.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

	Contact findByName(String name);
}
