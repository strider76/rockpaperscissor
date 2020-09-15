package com.cicklum.paperrockscissor.service.dto;

import lombok.Data;

import com.cicklum.paperrockscissor.model.GameOption;

@Data
public class PlayRoundDtoPost {

    private GameOption player1Move;
    private GameOption player2Move;

}
