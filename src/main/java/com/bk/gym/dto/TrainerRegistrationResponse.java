package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("Trainer Registration Response")
public class TrainerRegistrationResponse {
    @ApiModelProperty
    private String username;
    @ApiModelProperty
    private String password;

    public TrainerRegistrationResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
