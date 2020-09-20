package com.cicklum.paperrockscissor.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cicklum.paperrockscissor.model.Round;
import com.cicklum.paperrockscissor.service.dto.CurrentGameDto;
import com.cicklum.paperrockscissor.service.dto.PlayRoundResponseDto;
import com.cicklum.paperrockscissor.service.dto.SumaryGeneralDto;
import com.cicklum.paperrockscissor.service.dto.SumaryResultDto;
import com.cicklum.paperrockscissor.service.dto.SumaryRoundResultDto;
import com.cicklum.paperrockscissor.service.dto.UserRoundResultDto;

@Mapper
public abstract class RoundMapper {

    @Mapping(target = "player2Move", source = "player2")
    @Mapping(target = "player1Move", source = "player1")
    public abstract PlayRoundResponseDto roundToPlayRoundResponseDto(Round round);

    @Mapping(target = "resultRound", source = "result")
    @Mapping(target = "player2Move", source = "player2")
    @Mapping(target = "player1Move", source = "player1")
    public abstract UserRoundResultDto roundToUserRoundResultDto(Round round);

    @Mapping(target = "userName", source = "user.userName")
    @Mapping(target = "resultRound", source = "result")
    @Mapping(target = "player2Move", source = "player2")
    @Mapping(target = "player1Move", source = "player1")
    public abstract SumaryRoundResultDto roundToSumaryRoundResltDto (Round round);


    public SumaryGeneralDto roundsToSumaryGeneralDto(List<Round> all, List<Round> player1Wins, List<Round> player2Wins, List<Round> draw) {
     	return SumaryGeneralDto.builder()
		.totalRounds(this.roundToSumaryResultDto(all))
		.totalRoundsWinsPlayer1(this.roundToSumaryResultDto(player1Wins))
		.totalRoundsWinsPlayer2(this.roundToSumaryResultDto(player2Wins))
		.totalRoundsDraw(this.roundToSumaryResultDto(draw))
		.build();
    }

    public CurrentGameDto listRoundToCurrentGameDto(List<Round> userRounds) {
	return CurrentGameDto.builder()
		.totalRounds(userRounds.size())
		.rounds(userRounds.stream()
			.sorted()
			.map(this::roundToUserRoundResultDto)
			.collect(Collectors.toList()))
		.build();
    }

    public SumaryResultDto roundToSumaryResultDto(List<Round> allGamesByResuls) {
	return SumaryResultDto.builder()
		.totalRounds(allGamesByResuls.size())
		.totalResultsRounds(allGamesByResuls.stream()
			.sorted()
			.map(this::roundToSumaryRoundResltDto)
			.collect(Collectors.toList()))
		.build();
    }
}
