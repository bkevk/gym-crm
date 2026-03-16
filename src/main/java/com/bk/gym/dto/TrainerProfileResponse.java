package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Trainer Profile Response")
public class TrainerProfileResponse {
    @ApiModelProperty
    private String firstName;
    @ApiModelProperty
    private String lastName;
    @ApiModelProperty
    private String specialization;
    @ApiModelProperty
    private boolean isActive;
    @ApiModelProperty
    private List<TraineeShortInfo> trainees;

    @Getter
    @Setter
    public static class TraineeShortInfo {
        private String username;
        private String firstName;
        private String lastName;

    }
}
