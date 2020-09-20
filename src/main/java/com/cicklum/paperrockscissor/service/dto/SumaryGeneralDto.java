package com.cicklum.paperrockscissor.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "SumaryGeneralDto", description = "General Sumary for screen2")
public class SumaryGeneralDto {

    @ApiModelProperty(value = "total Rounds played")
    private SumaryResultDto totalRounds;
    @ApiModelProperty(value = "total Rounds win by player1")
    private SumaryResultDto totalRoundsWinsPlayer1;
    @ApiModelProperty(value = "total Rounds win by player2")
    private SumaryResultDto totalRoundsWinsPlayer2;
    @ApiModelProperty(value = "total Rounds draw")
    private SumaryResultDto totalRoundsDraw;

}
