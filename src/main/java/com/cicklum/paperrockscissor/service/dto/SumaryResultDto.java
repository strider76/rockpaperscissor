package com.cicklum.paperrockscissor.service.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SumaryResultDto {

    private Integer totalRounds;
    private List<SumaryRoundResultDto> totalResultsRounds;

}
