package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Login Response")
public class LoginResponse {
    @ApiModelProperty
    private String message;

    public LoginResponse(String message) {
        this.message = message;
    }
}
