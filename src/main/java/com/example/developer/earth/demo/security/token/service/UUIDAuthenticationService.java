package com.example.developer.earth.demo.security.token.service;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.developer.earth.demo.model.Account;
import com.example.developer.earth.demo.model.AccountRepository;
import com.example.developer.earth.demo.security.User;
import com.example.developer.earth.demo.security.token.interfaces.UserAuthenticationService;
import com.example.developer.earth.demo.security.token.interfaces.UserCrudService;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

//@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
final class UUIDAuthenticationService implements UserAuthenticationService {

	@NonNull
	UserCrudService users;

	@Autowired
	AccountRepository accountRepository;

	@Override
	public Optional<String> login(final String username, final String password) {
		Optional<Account> account = accountRepository.findByUsername(username);
		if (account.isPresent() && StringUtils.equals(account.get().getPasswordHash(), password)) {
			final String uuid = UUID.randomUUID().toString();
			final User user = User.builder().id(uuid).username(username).password(password).build();

			users.save(user);
			return Optional.of(uuid);
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> findByToken(final String token) {
		return users.find(token);
	}

	@Override
	public void logout(final User user) {
		users.remove(user);
	}
}