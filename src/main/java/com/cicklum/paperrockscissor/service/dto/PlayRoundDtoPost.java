package com.cicklum.paperrockscissor.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.cicklum.paperrockscissor.model.EnumGameOptionPattern;
import com.cicklum.paperrockscissor.model.GameOption;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayRoundDtoPost {

    @EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}")
    private GameOption player1Move;
    @EnumGameOptionPattern(regexp = "ROCK|PAPER|SCISSOR", message = "{error.round.play.invalid}")
    private GameOption player2Move;

}
