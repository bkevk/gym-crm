package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@ApiModel("Trainee Profile Update Request")
public class TraineeProfileUpdateRequest {
    @ApiModelProperty(required = true)
    private String username;
    @ApiModelProperty(required = true)
    private String firstName;
    @ApiModelProperty(required = true)
    private String lastName;
    @ApiModelProperty
    private LocalDate dateOfBirth;
    @ApiModelProperty
    private String address;
    @ApiModelProperty(required = true)
    private boolean isActive;
}
