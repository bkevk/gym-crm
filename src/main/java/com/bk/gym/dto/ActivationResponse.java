package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Activation/Deactivation Response")
public class ActivationResponse {
    @ApiModelProperty
    private String message;

    public ActivationResponse(String message) {
        this.message = message;
    }

}
