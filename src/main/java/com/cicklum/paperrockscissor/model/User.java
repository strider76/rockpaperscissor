package com.cicklum.paperrockscissor.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class User -  Register User , password should be save in Bcrypt
 *
 * @author manuel.millan
 *
 */

@Getter
@Setter
@ToString
public class User {

    private  String userName;
    private  String password;
    private Date createdAt;

    public User(String userName, String password) {
	this.userName = userName;
	this.password = password;
	createdAt = new Date();
    }

}
