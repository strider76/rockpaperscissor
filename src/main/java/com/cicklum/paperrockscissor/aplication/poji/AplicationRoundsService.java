package com.cicklum.paperrockscissor.aplication.poji;

import java.util.List;

import com.cicklum.paperrockscissor.exception.UserNotFoundException;
import com.cicklum.paperrockscissor.model.GameOption;
import com.cicklum.paperrockscissor.model.GameResult;
import com.cicklum.paperrockscissor.model.Round;

public interface AplicationRoundsService {

    Round playRound(final String userName, final GameOption player1Option, final GameOption player2Option) throws UserNotFoundException;

    List<Round> getUserGames(final String userName) throws UserNotFoundException;

    void resetUserGames(final String user) throws UserNotFoundException;

    List<Round> getAllGames();

    List<Round> getAllGamesByResuls(final GameResult resultFilter);

}
