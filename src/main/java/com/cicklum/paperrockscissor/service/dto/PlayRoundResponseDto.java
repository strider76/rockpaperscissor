package com.cicklum.paperrockscissor.service.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import com.cicklum.paperrockscissor.model.EnumGameOptionPattern;
import com.cicklum.paperrockscissor.model.GameOption;
import com.cicklum.paperrockscissor.model.GameResult;

@Getter
@EqualsAndHashCode
public class PlayRoundResponseDto extends PlayRoundDtoPost{

    private GameResult result;

    @Builder
    public PlayRoundResponseDto(@EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}") GameOption player1Move,
            @EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}") GameOption player2Move,
            GameResult result) {
        super(player1Move, player2Move);
        this.result = result;
    }
}
