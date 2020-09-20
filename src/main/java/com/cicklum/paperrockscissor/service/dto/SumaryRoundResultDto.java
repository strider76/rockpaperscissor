package com.cicklum.paperrockscissor.service.dto;

import lombok.Data;

@Data
public class SumaryRoundResultDto extends UserRoundResultDto {

    private String userName;
    private String createdAt;

}
