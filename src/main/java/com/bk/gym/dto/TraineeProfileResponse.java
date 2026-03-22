package com.bk.gym.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ApiModel("Trainee Profile Response")
public class TraineeProfileResponse {
    @ApiModelProperty
    private String firstName;
    @ApiModelProperty
    private String lastName;
    @ApiModelProperty
    private LocalDate dateOfBirth;
    @ApiModelProperty
    private String address;
    @ApiModelProperty
    private boolean isActive;
    @ApiModelProperty
    private List<TrainerShortInfo> trainers;


    @Getter
    @Setter
    public static class TrainerShortInfo {
        private String username;
        private String firstName;
        private String lastName;
        private String specialization;


    }
}
