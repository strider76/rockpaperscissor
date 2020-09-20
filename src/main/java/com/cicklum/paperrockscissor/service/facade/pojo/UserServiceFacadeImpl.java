package com.cicklum.paperrockscissor.service.facade.pojo;

import org.springframework.stereotype.Service;

import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.service.aplication.poji.AplicationUserService;
import com.cicklum.paperrockscissor.service.dto.UserDto;
import com.cicklum.paperrockscissor.service.facade.poji.UserServiceFacade;
import com.cicklum.paperrockscissor.service.mapper.UserMapper;

/**
 * User ServiceFacade
 *
 * @author manuel.millan
 */
@Service
public class UserServiceFacadeImpl implements UserServiceFacade {

    private final UserMapper userMapper;
    private final AplicationUserService aplicationUserService;

    public UserServiceFacadeImpl(UserMapper userMapper, AplicationUserService aplicationUserService) {
        this.userMapper = userMapper;
	this.aplicationUserService = aplicationUserService;
    }

    /**
     * function to create user in userList's bean
     * @param userName userName to create
     * @param password password encoded
     * @return UserDto create
     * @throws UserDuplicatedException  username not in userList's Bean
     */
    @Override
    public UserDto createUser(String userName, String password) throws UserDuplicatedException {
	return userMapper.modelToDto(this.aplicationUserService.createUser(userName, password));
    }

}
