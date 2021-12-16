package com.edumans.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edumans.dto.AuthRequestDto;
import com.edumans.dto.AuthResponesDto;
import com.edumans.service.impl.UserDetailsServiceImpl;
import com.edumans.utils.JwtUtil;

import io.jsonwebtoken.JwtException;
/**
 * this controller for routing Authentication API to create JSON Web Token
 * 
 * @author mohamed.elmasry
 *
 */
@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequestDto authDto) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
		} catch (BadCredentialsException e) {
			throw new JwtException("Incorrect username or password");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authDto.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);

		return new ResponseEntity<AuthResponesDto>(new AuthResponesDto(HttpStatus.OK, "Token created", jwt),
				HttpStatus.OK);
	}

}
