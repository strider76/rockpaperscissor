package com.cicklum.paperrockscissor.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SumaryGeneralDto {

    private SumaryResultDto totalRounds;
    private SumaryResultDto totalRoundsWinsPlayer1;
    private SumaryResultDto totalRoundsWinsPlayer2;
    private SumaryResultDto totalRoundsDraw;

}
