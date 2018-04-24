package com.khh.demo.stomp.entity;

import java.io.Serializable;
import java.security.Principal;

/**
 * Created by admin on 2018/4/24.
 */
public class User implements Principal {

    private final String name;

    public User(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}
