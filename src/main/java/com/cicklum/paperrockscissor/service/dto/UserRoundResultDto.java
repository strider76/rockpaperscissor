package com.cicklum.paperrockscissor.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoundResultDto {

    private String player1Move;
    private String player2Move;
    private String resultRound;

}
