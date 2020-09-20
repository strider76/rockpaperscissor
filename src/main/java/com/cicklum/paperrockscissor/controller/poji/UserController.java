package com.cicklum.paperrockscissor.controller.poji;

import javax.validation.Valid;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.service.dto.ErrorMessagesDto;
import com.cicklum.paperrockscissor.service.dto.UserDto;


@RequestMapping("/user")
@Validated
@Api(tags = "User")
@CrossOrigin(origins = "*")
public interface UserController {

    @PostMapping("/signup")
    @ApiOperation(value = "Create User", notes = "Create user in Api userList")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = UserDto.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorMessagesDto.class)
    })
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userToCreate) throws UserDuplicatedException;



}
