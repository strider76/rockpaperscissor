package com.cicklum.paperrockscissor.service.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrentGameDto {

    private Integer totalRounds;
    private List<UserRoundResultDto> rounds;

}
