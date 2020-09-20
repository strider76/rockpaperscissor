package com.cicklum.paperrockscissor.controller.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.service.dto.UserDto;
import com.cicklum.paperrockscissor.service.facade.poji.UserServiceFacade;

@ExtendWith(MockitoExtension.class)
class UserControllerImplTest {

    @InjectMocks
    private UserControllerImpl userController;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserServiceFacade userServiceFacade;

    @Test
    void createUser() throws UserDuplicatedException {
        UserDto userDto = new UserDto();
        userDto.setUserName("test");
        userDto.setPassword("test");

        userServiceFacade.createUser(userDto.getUserName(), passwordEncoder.encode(userDto.getPassword()));
        ResponseEntity<UserDto> response = userController.createUser(userDto);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}