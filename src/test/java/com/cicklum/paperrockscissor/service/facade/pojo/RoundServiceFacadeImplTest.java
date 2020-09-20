package com.cicklum.paperrockscissor.service.facade.pojo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cicklum.paperrockscissor.exception.UserNotFoundException;
import com.cicklum.paperrockscissor.model.GameOption;
import com.cicklum.paperrockscissor.model.GameResult;
import com.cicklum.paperrockscissor.model.Round;
import com.cicklum.paperrockscissor.service.aplication.poji.AplicationRoundsService;
import com.cicklum.paperrockscissor.service.dto.PlayRoundDtoPost;
import com.cicklum.paperrockscissor.service.dto.PlayRoundResponseDto;
import com.cicklum.paperrockscissor.service.mapper.RoundMapper;

@SpringBootTest
class RoundServiceFacadeImplTest {

    @InjectMocks
    private RoundServiceFacadeImpl roundServiceFacade;

    @Mock
    private AplicationRoundsService aplicationRoundsService;

    @Mock
    private RoundMapper roundMapper;

    @Test
    void playUserRound() throws UserNotFoundException {
        PlayRoundDtoPost playRoundDtoPost= new PlayRoundDtoPost(GameOption.PAPER, GameOption.ROCK);

        Round roundResult = Round.builder()
                .player1(GameOption.PAPER)
                .player2(GameOption.ROCK)
                .result(GameResult.PLAYER1_WINS)
                .build();

        PlayRoundResponseDto playRoundResponseDto = PlayRoundResponseDto.builder()
                .player1Move(GameOption.PAPER)
                .player2Move(GameOption.ROCK)
                .result(GameResult.PLAYER1_WINS)
                .build();

        Mockito.when(aplicationRoundsService.playRound("test",GameOption.PAPER,GameOption.ROCK)).thenReturn(roundResult);
        Mockito.when(roundMapper.roundToPlayRoundResponseDto(roundResult)).thenReturn(playRoundResponseDto);
        Assertions.assertEquals(playRoundResponseDto, this.roundServiceFacade.playUserRound("test", playRoundDtoPost));
    }


}