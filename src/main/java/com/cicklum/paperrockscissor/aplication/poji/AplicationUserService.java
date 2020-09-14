package com.cicklum.paperrockscissor.aplication.poji;

import java.util.Optional;

import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.model.User;

public interface AplicationUserService {

    User createUser(final String userName,final  String password) throws UserDuplicatedException;

    Optional<User> findByUserName(final String userName);


}
