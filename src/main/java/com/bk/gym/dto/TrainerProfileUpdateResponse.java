package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Trainer Profile Update Response")
public class TrainerProfileUpdateResponse {
    @ApiModelProperty
    private String username;
    @ApiModelProperty
    private String firstName;
    @ApiModelProperty
    private String lastName;
    @ApiModelProperty
    private String specialization;
    @ApiModelProperty
    private boolean isActive;
    @ApiModelProperty
    private List<TrainerProfileResponse.TraineeShortInfo> trainees;

}
