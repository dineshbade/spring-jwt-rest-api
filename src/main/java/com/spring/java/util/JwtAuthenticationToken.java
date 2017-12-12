/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author User
 *
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {


    private String token;

    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
