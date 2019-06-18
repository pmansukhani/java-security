package com.example.developer.earth.demo;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.developer.earth.demo.model.Account;
import com.example.developer.earth.demo.model.AccountRepository;
import com.example.developer.earth.demo.model.Contact;
import com.example.developer.earth.demo.model.ContactRepository;
import com.example.developer.earth.demo.model.Country;
import com.example.developer.earth.demo.model.Phone;

@Component
public class Initializer implements CommandLineRunner {

	private final ContactRepository contactRepository;

	private final AccountRepository accountRepository;

	public Initializer(ContactRepository contactRepository, AccountRepository accountRepository) {
		this.contactRepository = contactRepository;
		this.accountRepository = accountRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		Phone phone = new Phone();
		phone.setNumber("123 456 7890");
		Contact contact = Contact.builder().name("Arpit Joshi").address("SW Lawton St").country(Country.USA)
				.phones(Set.of(phone)).build();
		contactRepository.save(contact);

		Account user1 = new Account("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		Account user2 = new Account("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		accountRepository.save(user1);
		accountRepository.save(user2);

		accountRepository.findAll().forEach(System.out::println);
		contactRepository.findAll().forEach(System.out::println);
	}

}
