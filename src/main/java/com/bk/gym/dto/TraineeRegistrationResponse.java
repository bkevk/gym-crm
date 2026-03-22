package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Trainee Registration Response")
public class TraineeRegistrationResponse {
    @ApiModelProperty
    private String username;
    @ApiModelProperty
    private String password;

    public TraineeRegistrationResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
