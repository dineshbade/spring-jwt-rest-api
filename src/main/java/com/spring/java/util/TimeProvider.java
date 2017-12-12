package com.spring.java.util;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author  Dinesh Bade  on Oct 23, 2017
 *
 */
@Component
public class TimeProvider implements Serializable {

    private static final long serialVersionUID = -3301695478208950415L;

    public Date now() {
        return new Date();
    }
}