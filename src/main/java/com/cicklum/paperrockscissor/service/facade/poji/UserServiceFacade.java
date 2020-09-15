package com.cicklum.paperrockscissor.service.facade.poji;

import com.cicklum.paperrockscissor.exception.UserDuplicatedException;
import com.cicklum.paperrockscissor.service.dto.UserDto;

public interface UserServiceFacade {

    UserDto createUser(String userName, String password) throws UserDuplicatedException;

}
