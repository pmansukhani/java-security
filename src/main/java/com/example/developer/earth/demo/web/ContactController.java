package com.example.developer.earth.demo.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.example.developer.earth.demo.model.Contact;
import com.example.developer.earth.demo.model.ContactRepository;

@RestController
@RequestMapping("/api")
public class ContactController {
	
	private final Logger log = LoggerFactory.getLogger(ContactController.class);
	
	private ContactRepository repository;
	
	public ContactController(ContactRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/contacts")
	public Collection<Contact> getAllContacts() {
		log.debug("request received.");
		return repository.findAll();
	}
	
	@GetMapping("/contact/{id}")
	public ResponseEntity<Contact> getContact(@PathVariable Long id) {
		log.debug("request received.");
		Optional<Contact> contact = repository.findById(id);
		if (contact.isPresent()) {
			return ResponseEntity.ok(contact.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/contact")
	public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact contact) throws URISyntaxException {
		log.debug("request received.");
		log.info("Request to create contact: {}", contact.getName());
		Contact result = repository.save(contact);
		return ResponseEntity.created(new URI("/api/contact/" + result.getId())).body(result);
	}
	
	@PutMapping("/contact")
	@ResponseStatus
	public ResponseEntity<Contact> updateContact(@Valid @RequestBody Contact contact) {
		Contact result = repository.save(contact);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/contact/{id}")
	public void deleteContact(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(
	  ConstraintViolationException ex, WebRequest request) {
	    List<String> errors = new ArrayList<String>();
	    
	    ex.getConstraintViolations().forEach((violation) -> {
	    	errors.add(violation.getRootBeanClass().getName() + " " + 
	  	          violation.getPropertyPath() + ": " + violation.getMessage());
	    });	   
	 
	    ApiError apiError = 
	      new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
}
