package com.cicklum.paperrockscissor.service.mapper;

import org.mapstruct.Mapper;

import com.cicklum.paperrockscissor.model.User;
import com.cicklum.paperrockscissor.service.dto.UserDto;

@Mapper
public interface UserMapper {

    UserDto modelToDto(User user);

}
