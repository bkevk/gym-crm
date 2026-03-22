package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ApiModel("Trainee Registration Request")
public class TraineeRegistrationRequest {
    @ApiModelProperty(required = true)
    private String firstName;
    @ApiModelProperty(required = true)
    private String lastName;
    @ApiModelProperty
    private String dateOfBirth; // ISO string, e.g. "2000-01-01"
    @ApiModelProperty
    private String address;

}
