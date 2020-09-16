package com.cicklum.paperrockscissor.service.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "{error.user.name.blank}")
    private String userName;
    @NotBlank(message = "{error.user.password.blank}")
    private String password;

}
