package com.example.developer.earth.demo.security.token.service;

import static java.util.Optional.ofNullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.developer.earth.demo.security.User;
import com.example.developer.earth.demo.security.token.interfaces.UserCrudService;

@Service
final class InMemoryUsers implements UserCrudService {

	Map<String, User> users = new HashMap<>();

	@Override
	public User save(final User user) {
		return users.put(user.getId(), user);
	}

	@Override
	public Optional<User> find(final String id) {
		return ofNullable(users.get(id));
	}

	@Override
	public Optional<User> findByUsername(final String username) {
		return users.values().stream().filter(u -> Objects.equals(username, u.getUsername())).findFirst();
	}

	@Override
	public User remove(User user) {
		return users.remove(user.getId());
	}

}
