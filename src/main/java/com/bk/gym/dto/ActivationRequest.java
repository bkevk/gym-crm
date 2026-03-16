package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Activation/Deactivation Request")
public class ActivationRequest {
    @ApiModelProperty(required = true)
    private String username;
    @ApiModelProperty(required = true)
    private boolean isActive;
}
