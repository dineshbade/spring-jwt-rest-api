/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.spring.java.exception.JwtTokenMalformedException;
import com.spring.java.util.AuthenticatedUser;
import com.spring.java.util.JwtAuthenticationToken;


/**
 * @author User
 *
 */@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider/* implements AuthenticationProvider*/  {
 
  
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
  
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
   
    System.out.println("Here i am");
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    	
    	
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String token = jwtAuthenticationToken.getToken();
       
      String  newUsername = jwtTokenUtil.getUsernameFromToken(token);

        if (newUsername == null) {
            throw new JwtTokenMalformedException("JWT token is not valid");
        }

     /*   List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRole());
*/
        /*return new AuthenticatedUser( newUsername, token, authorityList);*/
        return new AuthenticatedUser( newUsername, token);
    }
   
}
