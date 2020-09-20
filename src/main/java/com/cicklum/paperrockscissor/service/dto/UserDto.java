package com.cicklum.paperrockscissor.service.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "UserDto", description = "UserDto to create")
public class UserDto {

    @ApiModelProperty(value = "Username to create/user", required = true, example = "user1")
    @NotBlank(message = "{error.user.name.blank}")
    private String userName;
    @ApiModelProperty(value = "Password user to create/user", required = true, example = "password1")
    @NotBlank(message = "{error.user.password.blank}")
    private String password;

}
