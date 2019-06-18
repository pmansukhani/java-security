package com.example.developer.earth.demo.security.token.interfaces;

import java.util.Optional;

import com.example.developer.earth.demo.security.User;

/**
 * User security operations like login and logout, and CRUD operations on
 * {@link User}.
 * 
 * @author jerome
 *
 */
public interface UserCrudService {

	User save(User user);

	Optional<User> find(String id);

	Optional<User> findByUsername(String username);

	User remove(User user);
}