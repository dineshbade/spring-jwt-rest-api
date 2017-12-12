/**
 * By Dinesh Oct 18, 2017
 */
package com.spring.java.controller;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.java.security.JwtTokenUtil;
import com.spring.java.security.LoginAuthenticationProvider;
import com.spring.java.service.JwtUserDetailsServiceImpl;
import com.spring.java.service.UserService;
import com.spring.java.util.JwtAuthenticationRequest;
import com.spring.java.util.JwtAuthenticationResponse;
import com.spring.java.util.JwtUser;
import com.spring.java.util.RestResponse;

/**
 * @author User
 *
 */
@RestController
@RequestMapping("")
public class LoginController {

	@Autowired
	private LoginAuthenticationProvider authenticationManager;

	@Autowired
	UserService userService;
	@Autowired
	JwtUserDetailsServiceImpl jwtUserDetailService;
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> jsonLogin(@RequestBody JwtAuthenticationRequest credentials) {
		try {
			// Perform the security
			final Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			// Reload password post-security so we can generate token
			final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(credentials.getUsername());
			final String token = jwtTokenUtil.generateToken(userDetails);
			userDetails.setPassword(null);

			userDetails.setToken(token);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new RestResponse(HttpStatus.OK.value(), userDetails, "success"));
		} catch (BadCredentialsException e) {

			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new RestResponse(HttpStatus.BAD_REQUEST.value(), null, e.getMessage()));
		} catch (UsernameNotFoundException u) {

			u.printStackTrace();

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new RestResponse(HttpStatus.NOT_FOUND.value(), null, u.getMessage()));
		}

		catch (Exception ex) {

			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new RestResponse(HttpStatus.NOT_FOUND.value(), null, ex.getMessage()));
		}

	}
}
