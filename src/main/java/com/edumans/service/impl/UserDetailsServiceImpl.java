package com.edumans.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edumans.model.SystemUser;
import com.edumans.repository.SystemUserRepository;
/**
 * handle system users business logic
 * @author mohamed.elmasry
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	SystemUserRepository systemUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<SystemUser> optionalSystemUser = systemUserRepository.findById(username);

		if (optionalSystemUser.isPresent() && optionalSystemUser.get().getUserName().equals(username)) {
			return new User(optionalSystemUser.get().getUserName(), optionalSystemUser.get().getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

	}
}