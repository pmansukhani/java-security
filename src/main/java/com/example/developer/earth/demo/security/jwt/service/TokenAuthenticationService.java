package com.example.developer.earth.demo.security.jwt.service;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.developer.earth.demo.model.Account;
import com.example.developer.earth.demo.model.AccountRepository;
import com.example.developer.earth.demo.security.User;
import com.example.developer.earth.demo.security.jwt.interfaces.TokenService;
import com.example.developer.earth.demo.security.token.interfaces.UserAuthenticationService;
import com.example.developer.earth.demo.security.token.interfaces.UserCrudService;
import com.google.common.collect.ImmutableMap;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
final class TokenAuthenticationService implements UserAuthenticationService {
	@NonNull
	TokenService tokens;
	@NonNull
	UserCrudService users;
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Optional<String> login(final String username, final String password) {
		Optional<Account> account = accountRepository.findByUsername(username);
		if (account.isPresent() && StringUtils.equals(account.get().getPasswordHash(), password)) {
			return Optional.of(tokens.expiring(ImmutableMap.of("username", username)));
		}
		return Optional.empty();
	}

	@Override
	public Optional<User> findByToken(final String token) {
		return Optional.of(tokens.verify(token)).map(map -> map.get("username")).flatMap(users::findByUsername);
	}

	@Override
	public void logout(final User user) {
		// Nothing to doy
	}
}
