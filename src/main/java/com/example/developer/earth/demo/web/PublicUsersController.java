package com.example.developer.earth.demo.web;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.developer.earth.demo.model.Account;
import com.example.developer.earth.demo.model.AccountRepository;
import com.example.developer.earth.demo.security.User;
import com.example.developer.earth.demo.security.token.interfaces.UserAuthenticationService;
import com.example.developer.earth.demo.security.token.interfaces.UserCrudService;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/public/users")
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor(access = PACKAGE)
final class PublicUsersController {
	@NonNull
	UserAuthenticationService authentication;
	@NonNull
	UserCrudService users;

	@Autowired
	AccountRepository accountRepository;

	@PostMapping("/register")
	String register(@RequestParam("username") final String username, @RequestParam("password") final String password) {
		Optional<Account> account = accountRepository.findByUsername(username);
		if (account.isEmpty()) {
			accountRepository.save(new Account(username, password, "USER"));
			users.save(User.builder().id(username).username(username).password(password).build());
			return login(username, password);
		}

		return "User already exists";
	}

	@PostMapping("/login")
	String login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
		return authentication.login(username, password)
				.orElseThrow(() -> new RuntimeException("invalid login and/or password"));
	}
}
