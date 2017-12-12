/**
 * By Dinesh Oct 17, 2017
 */
package com.spring.java.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author User
 *
 */
public class JwtTokenMalformedException extends RuntimeException {


    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
