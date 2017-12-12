/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spring.java.security.JwtAuthenticationEntryPoint;
import com.spring.java.security.JwtAuthenticationProvider;
import com.spring.java.security.JwtAuthenticationSuccessHandler;
import com.spring.java.security.JwtAuthenticationTokenFilter;
import com.spring.java.security.LoginAuthenticationProvider;
import com.spring.java.util.CORSFilter;

/**
 * @author User
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private LoginAuthenticationProvider logingAuthProvider;

	 @Bean
	    CORSFilter corsFilter() {
		 CORSFilter filter = new CORSFilter();
	        return filter;
	    }
	@Bean
	@Override
	public AuthenticationManager authenticationManager() throws Exception {
	
		return new ProviderManager(Arrays.asList(authenticationProvider));
	}

	
	
	 
	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
		authenticationManagerBuilder.authenticationProvider(logingAuthProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManager());
		/*
		 * authenticationTokenFilter.setAuthenticationManager(
		 * authenticationManager);
		 */ authenticationTokenFilter.setAuthenticationSuccessHandler(new JwtAuthenticationSuccessHandler());
		return authenticationTokenFilter;
	}

	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf().disable()
				// All urls must be authenticated (filter for token always fires
				// (/**)
				
				.authorizeRequests().antMatchers("/login").permitAll()
				  .antMatchers( "/resources/**","/resources/public/**")
                  .permitAll()
				.antMatchers("/user").permitAll()
				.antMatchers("/users").permitAll()
				.antMatchers("/countUser").permitAll()
				.antMatchers("/api/*").permitAll()
				.antMatchers("/users/*").permitAll().anyRequest().authenticated()
				.and()
				// Call our errorHandler if authentication/authorisation fails
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				// don't create session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // .and()
		// Custom JWT based security filter
/*		httpSecurity.addFilterAt(new CORSFilter(), ChannelProcessingFilter.class);
*/				httpSecurity.addFilterBefore(corsFilter(), ChannelProcessingFilter.class);

		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		// disable page caching
		/*httpSecurity.headers().cacheControl();*/
	}

}
