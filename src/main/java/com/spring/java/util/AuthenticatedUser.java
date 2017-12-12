/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author User
 *
 */
public class AuthenticatedUser implements UserDetails {

 /*   private final Long id;*/
    private final String username;
    private final String token;
/*    private final Collection<? extends GrantedAuthority> authorities;
*/
   /* public AuthenticatedUser(Long id, String username, String token, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.authorities = authorities;
    }*/
    public AuthenticatedUser(String username, String token) {
        this.username = username;
        this.token = token;
        
            }

    /*@JsonIgnore
    public Long getId() {
        return id;
    }
*/
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    public String getToken() {
        return token;
    }
/*
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
*/
    @Override
    public String getPassword() {
        return null;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
