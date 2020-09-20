package com.cicklum.paperrockscissor.controller.poji;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.service.dto.UserDto;


@RequestMapping("/user")
@Validated
@Api(tags = "User")
@CrossOrigin(origins = "*")
public interface UserController {

    @PostMapping("/signup")
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userToCreate) throws UserDuplicatedException;
    @ApiOperation(value = "Create User")
    void createUser(@Valid @RequestBody UserDto userToCreate) throws UserDuplicatedException;


}
