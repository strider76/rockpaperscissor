package com.cicklum.paperrockscissor.aplication.pojo;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cicklum.paperrockscissor.aplication.poji.AplicationUserService;
import com.cicklum.paperrockscissor.exception.UserNotFoundException;
import com.cicklum.paperrockscissor.model.GameOption;
import com.cicklum.paperrockscissor.model.GameResult;
import com.cicklum.paperrockscissor.model.Round;
import com.cicklum.paperrockscissor.model.User;

@ExtendWith(MockitoExtension.class)
class AplicationRoundsServiceImplTest {

    @InjectMocks
    private AplicationRoundsServiceImpl applicationRoundsService;

    @Mock
    private AplicationUserService aplicationUserService;

    @Mock
    private List<Round> roundsPlayed;




    @Test
    void testPlayRoundOK() throws UserNotFoundException {
	Mockito.when(aplicationUserService.findByUserName("test"))
		.thenReturn(Optional.of(new User("test","test")));
        Assertions.assertEquals(GameResult.PLAYER1_WINS, applicationRoundsService.playRound("test", GameOption.PAPER,GameOption.ROCK).getResult());
        Assertions.assertEquals(GameResult.PLAYER1_WINS, applicationRoundsService.playRound("test", GameOption.ROCK,GameOption.SCISSOR).getResult());
        Assertions.assertEquals(GameResult.PLAYER1_WINS, applicationRoundsService.playRound("test", GameOption.SCISSOR,GameOption.PAPER).getResult());
        Assertions.assertEquals(GameResult.DRAW, applicationRoundsService.playRound("test", GameOption.ROCK,GameOption.ROCK).getResult());
    }

    @Test
    void testPlayRoundUserNorFoundKO() {
        Assertions.assertThrows(UserNotFoundException.class,
		() -> applicationRoundsService.playRound("otheruser", GameOption.PAPER, GameOption.PAPER));
    }

}