package com.spring.java.util;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {

    private final Long id;
    private final String firstname;
    private final String lastname;
    private  String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean enabled;
    private  Date registrationDate;
    private  String token;

    public JwtUser(
          Long id,
          String firstname,
          String lastname,
          String email,
          String password, Collection<? extends GrantedAuthority> authorities,
          boolean enabled,
          Date registrationDate,
          String token
          
    ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.enabled = enabled;
        this.registrationDate = registrationDate;
        this.token=token;
    }

    
    
    

	





	/**
	 * @param id
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param authorities
	 * @param enabled
	 */
	public JwtUser(Long id, String firstname, String lastname, String email,
			Collection<? extends GrantedAuthority> authorities, boolean enabled,String token) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.authorities = authorities;
		this.enabled = enabled;
		this.password=null;
		this.registrationDate=null;
		this.token = token;
	}



	
	public String getToken() {
		return token;
	}




	  public Long getId() {
        return id;
    }

   
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

       @Override
    public boolean isAccountNonLocked() {
        return true;
    }

   
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    
    public Date getRegistrationDate() {
        return registrationDate;
    }

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}



	public void setPassword(String password) {
		this.password = password;
	}


	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}




	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
}