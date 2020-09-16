package com.cicklum.paperrockscissor.service.dto;

import lombok.Data;

import com.cicklum.paperrockscissor.model.EnumGameOptionPattern;
import com.cicklum.paperrockscissor.model.GameOption;

@Data
public class PlayRoundDtoPost {

    @EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}")
    private GameOption player1Move;
    @EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}")
    private GameOption player2Move;

}
