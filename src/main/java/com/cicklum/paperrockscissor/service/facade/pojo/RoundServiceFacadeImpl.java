package com.cicklum.paperrockscissor.service.facade.pojo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cicklum.paperrockscissor.exception.UserNotFoundException;
import com.cicklum.paperrockscissor.model.GameResult;
import com.cicklum.paperrockscissor.model.Round;
import com.cicklum.paperrockscissor.service.aplication.poji.AplicationRoundsService;
import com.cicklum.paperrockscissor.service.dto.CurrentGameDto;
import com.cicklum.paperrockscissor.service.dto.PlayRoundDtoPost;
import com.cicklum.paperrockscissor.service.dto.PlayRoundResponseDto;
import com.cicklum.paperrockscissor.service.dto.SumaryGeneralDto;
import com.cicklum.paperrockscissor.service.facade.poji.RoundServiceFacade;
import com.cicklum.paperrockscissor.service.mapper.RoundMapper;

/**
 * RoundServiceFacada - We used this class to call AplicationRoundService and return the Results in DTO
 * across RoundMapper( MapStruct Mapper)
 *
 * @author manuel.millan
 */
@Service
public class RoundServiceFacadeImpl implements RoundServiceFacade {

    private final AplicationRoundsService aplicationRoundsService;
    private final RoundMapper roundMapper;

    public RoundServiceFacadeImpl(AplicationRoundsService aplicationRoundsService, RoundMapper roundMapper) {
        this.aplicationRoundsService = aplicationRoundsService;
        this.roundMapper = roundMapper;
    }

    /**
     * function to play a round
     * @param userName username logged
     * @param roundMoves moves player1 and player2
     * @return PlayRoundResponseDTO with final Result
     * @throws UserNotFoundException userName not in userList´s Bean
     */
    @Override
    public PlayRoundResponseDto playUserRound(final String userName, final PlayRoundDtoPost roundMoves) throws UserNotFoundException {
	return this.roundMapper.roundToPlayRoundResponseDto(
	        this.aplicationRoundsService.playRound(userName, roundMoves.getPlayer1Move(), roundMoves.getPlayer2Move())
        );
    }

    /**
     * Get User summary
     * @param userName username logged
     * @return CurrentGameDto Object with the user sumary
     * @throws UserNotFoundException username not in userList's Bean
     */
    @Override
    public CurrentGameDto sumaryUserRounds(String userName) throws UserNotFoundException {

        return this.roundMapper.listRoundToCurrentGameDto(this.aplicationRoundsService.getUserGames(userName));

    }

    /**
     * Change All Round to invisible
     * @param userName username logged
     * @throws UserNotFoundException username not in userList's Bean
     */
    @Override
    public void resetUserGames(final String userName) throws UserNotFoundException {
        this.aplicationRoundsService.resetUserGames(userName);
    }

    /**
     * Get a general sumary
     * @return SumaryGeneralDTO
     */
    @Override
    public SumaryGeneralDto getAllGames() {
        List<Round> all = this.aplicationRoundsService.getAllGamesByResuls(GameResult.ALL);
        List<Round> player1Wins = this.aplicationRoundsService.getAllGamesByResuls(GameResult.PLAYER1_WINS);
        List<Round> player2Wins = this.aplicationRoundsService.getAllGamesByResuls(GameResult.PLAYER2_WINS);
        List<Round> draw = this.aplicationRoundsService.getAllGamesByResuls(GameResult.DRAW);

        return this.roundMapper.roundsToSumaryGeneralDto(all,player1Wins,player2Wins,draw);
    }

}
