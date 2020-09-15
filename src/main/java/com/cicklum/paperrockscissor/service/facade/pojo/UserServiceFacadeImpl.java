package com.cicklum.paperrockscissor.service.facade.pojo;

import org.springframework.stereotype.Service;

import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.service.aplication.poji.AplicationUserService;
import com.cicklum.paperrockscissor.service.dto.UserDto;
import com.cicklum.paperrockscissor.service.facade.poji.UserServiceFacade;
import com.cicklum.paperrockscissor.service.mapper.UserMapper;

@Service
public class UserServiceFacadeImpl implements UserServiceFacade {

    private final UserMapper userMapper;
    private final AplicationUserService aplicationUserService;

    public UserServiceFacadeImpl(UserMapper userMapper, AplicationUserService aplicationUserService) {
        this.userMapper = userMapper;
	this.aplicationUserService = aplicationUserService;
    }

    @Override
    public UserDto createUser(String userName, String password) throws UserDuplicatedException {
	return userMapper.modelToDto(this.aplicationUserService.createUser(userName, password));
    }

}
