package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Password Change Response")
public class PasswordChangeResponse {
    @ApiModelProperty
    private String message;

    public PasswordChangeResponse(String message) {
        this.message = message;
    }
}
