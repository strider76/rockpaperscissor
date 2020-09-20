package com.cicklum.paperrockscissor.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import com.cicklum.paperrockscissor.model.EnumGameOptionPattern;
import com.cicklum.paperrockscissor.model.GameOption;
import com.cicklum.paperrockscissor.model.GameResult;

@Getter
@EqualsAndHashCode
@ApiModel(value = "PlayRoundResponseDto", description = "Response with result of the round")
public class PlayRoundResponseDto extends PlayRoundDtoPost{

    @ApiModelProperty(value = "Round's results ", required = true, example = "PLAYER1_WIN", allowableValues = "PLAYER1_WIN, PLAYER2_WIN, DRAW")
    private GameResult result;

    @Builder
    public PlayRoundResponseDto(@EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}") GameOption player1Move,
            @EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}") GameOption player2Move,
            GameResult result) {
        super(player1Move, player2Move);
        this.result = result;
    }
}
