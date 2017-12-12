package com.spring.java.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.spring.java.service.JwtUserDetailsServiceImpl;

/**
 * @author By Dinesh Bade 10:27:42 AM
 *
 */

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	 JwtUserDetailsServiceImpl userService;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String username = authentication.getName();
        System.out.println(authentication.getCredentials().toString());
        
        String password = passwordEncoder.encode(authentication.getCredentials().toString());
        System.out.println(password);
        UserDetails user = userService.loadUserByUsername(username);
        
        if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
            throw new BadCredentialsException("Username not found.");
        }

        if (!(passwordEncoder.matches(authentication.getCredentials().toString(), user.getPassword()))) {
            throw new BadCredentialsException("Wrong password.");
        }

        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
	    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));

	}

}
