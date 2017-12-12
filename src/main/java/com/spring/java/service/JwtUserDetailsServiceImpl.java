package com.spring.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.java.dao.UserRepository;
import com.spring.java.model.User;
import com.spring.java.util.JwtUserFactory;

/**
 * @author  Dinesh Bade  on Oct 23, 2017
 *
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found "));
        } else {
            return JwtUserFactory.create(user);
        }
    }
}