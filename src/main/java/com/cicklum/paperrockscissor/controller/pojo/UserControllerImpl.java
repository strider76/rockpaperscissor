package com.cicklum.paperrockscissor.controller.pojo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cicklum.paperrockscissor.controller.poji.UserController;
import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.service.dto.UserDto;
import com.cicklum.paperrockscissor.service.facade.poji.UserServiceFacade;

/**
 * User Controller class
 *
 * @author manuel.millan
 */
@RestController
public class UserControllerImpl implements UserController {

    private final UserServiceFacade userServiceFacade;
    private final  PasswordEncoder encoder;
    public UserControllerImpl(UserServiceFacade userServiceFacade, PasswordEncoder encoder) {
        this.userServiceFacade = userServiceFacade;
        this.encoder = encoder;
    }

    /**
     * Create a new user, if username exist, we throw a UserDuplicatedException
     *
     * @param userToCreate, userDTo, Username or password can´t be null or empty
     * @return UserDto Created
     * @throws UserDuplicatedException
     */
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity createUser(UserDto userToCreate) throws UserDuplicatedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceFacade.createUser(userToCreate.getUserName(), encoder.encode(userToCreate.getPassword())));
    }
}
