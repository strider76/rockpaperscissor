package com.cicklum.paperrockscissor.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.cicklum.paperrockscissor.model.EnumGameOptionPattern;
import com.cicklum.paperrockscissor.model.GameOption;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PlayRoundDtoPost", description = "UserDto to create")
public class PlayRoundDtoPost {

    @ApiModelProperty(value = "Player1's move", required = true, example = "ROCK", allowableValues = "ROCK, PAPER, SCISSOR")
    @EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}")
    private GameOption player1Move;
    @ApiModelProperty(value = "Player2's move", required = true, example = "ROCK", allowableValues = "ROCK, PAPER, SCISSOR")
    @EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}")
    private GameOption player2Move;

}
