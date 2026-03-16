package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Password Change Request")
public class PasswordChangeRequest {
    @ApiModelProperty(required = true)
    private String username;
    @ApiModelProperty(required = true)
    private String oldPassword;
    @ApiModelProperty(required = true)
    private String newPassword;
}
