package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Login Request")
public class LoginRequest {
    @ApiModelProperty(required = true)
    private String username;
    @ApiModelProperty(required = true)
    private String password;
}
