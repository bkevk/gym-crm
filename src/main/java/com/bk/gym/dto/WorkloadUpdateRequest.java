package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@ApiModel("Workload Update Request")
@Getter
@Setter
public class WorkloadUpdateRequest {

    @ApiModelProperty(required = true)
    private String trainerUsername;

    @ApiModelProperty(required = true)
    private String firstName;

    @ApiModelProperty(required = true)
    private String lastName;

    @ApiModelProperty(required = true)
    private boolean isActive;

    @ApiModelProperty(required = true, example = "2024-05-01")
    private LocalDate trainingDate;

    @ApiModelProperty(required = true, example = "60")
    private int trainingDuration;

    @ApiModelProperty(required = true, allowableValues = "ADD,DELETE")
    private String actionType;

}
