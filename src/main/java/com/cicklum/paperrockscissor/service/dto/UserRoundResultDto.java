package com.cicklum.paperrockscissor.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserRoundResultDto", description = "Response with Players's move and result")
public class UserRoundResultDto {

    @ApiModelProperty(value = "Player1's move", required = true, example = "ROCK", allowableValues = "ROCK, PAPER, SCISSOR")
    private String player1Move;
    @ApiModelProperty(value = "Player2's move", required = true, example = "PAPER", allowableValues = "ROCK, PAPER, SCISSOR")
    private String player2Move;
    @ApiModelProperty(value = "Round's result", required = true, example = "PLAYER2_WIN", allowableValues = "PLAYER1_WIN, PLAYER2_WIN, DRAW")
    private String resultRound;

}
