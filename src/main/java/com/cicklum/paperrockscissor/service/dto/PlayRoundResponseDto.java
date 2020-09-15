package com.cicklum.paperrockscissor.service.dto;

import lombok.Data;

import com.cicklum.paperrockscissor.model.GameResult;

@Data
public class PlayRoundResponseDto extends PlayRoundDtoPost{

    private GameResult result;

}
