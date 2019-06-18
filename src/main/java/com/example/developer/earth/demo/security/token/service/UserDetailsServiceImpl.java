package com.example.developer.earth.demo.security.token.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.developer.earth.demo.model.Account;
import com.example.developer.earth.demo.model.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	public UserDetailsServiceImpl(AccountRepository repository) {
		this.repository = repository;
	}

	private AccountRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Account> account = repository.findByUsername(username);
		if (account.isPresent()) {
			UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
					account.get().getPasswordHash(), AuthorityUtils.createAuthorityList(account.get().getRole()));
			return userDetails;
		}
		throw new UsernameNotFoundException("User not found");
	}

}
