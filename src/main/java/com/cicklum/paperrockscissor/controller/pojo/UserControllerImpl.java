package com.cicklum.paperrockscissor.controller.pojo;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cicklum.paperrockscissor.controller.poji.UserController;
import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.service.dto.UserDto;
import com.cicklum.paperrockscissor.service.facade.poji.UserServiceFacade;

@RestController
public class UserControllerImpl implements UserController {

    private final UserServiceFacade userServiceFacade;
    private final  PasswordEncoder encoder;
    public UserControllerImpl(UserServiceFacade userServiceFacade, PasswordEncoder encoder) {
        this.userServiceFacade = userServiceFacade;
        this.encoder = encoder;
    }


    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createUser(UserDto userToCreate) throws UserDuplicatedException {
        userServiceFacade.createUser(userToCreate.getUserName(), encoder.encode(userToCreate.getPassword()));
    }


}
