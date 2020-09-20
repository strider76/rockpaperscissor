package com.cicklum.paperrockscissor.service.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "CurrentGameDto", description = "Response with all visible user's round an total number rounds")
public class CurrentGameDto {

    @ApiModelProperty(value = "User Total  rounds", example = "4")
    private Integer totalRounds;
    private List<UserRoundResultDto> rounds;

}
