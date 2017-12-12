package com.spring.java.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.spring.java.model.Authority;
import com.spring.java.model.User;

/**
 * @author  Dinesh Bade  on Oct 23, 2017
 *
 */
public final class JwtUserFactory {

	 private JwtUserFactory() {
	    }

	    public static JwtUser create(User user) {
	        return new JwtUser(
	                user.getUserId(),
	                user.getFirstName(),
	                user.getLastName(),
	                user.getEmail(),
	                user.getPassword(),
	                mapToGrantedAuthorities(user.getAuthorities()),
	                user.getEnabled(),
	                user.getRegistrationDate(),null
	                
	        );
	    }

	    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Authority> authorities) {
	        return authorities.stream()
	                .map(authority -> new SimpleGrantedAuthority(authority.getName().name()))
	                .collect(Collectors.toList());
	    }
}
