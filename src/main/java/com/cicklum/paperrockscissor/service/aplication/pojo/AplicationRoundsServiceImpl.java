package com.cicklum.paperrockscissor.service.aplication.pojo;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cicklum.paperrockscissor.service.aplication.poji.AplicationRoundsService;
import com.cicklum.paperrockscissor.service.aplication.poji.AplicationUserService;
import com.cicklum.paperrockscissor.exception.UserNotFoundException;
import com.cicklum.paperrockscissor.model.GameOption;
import com.cicklum.paperrockscissor.model.GameResult;
import com.cicklum.paperrockscissor.model.Round;
import com.cicklum.paperrockscissor.model.User;

@Service
public class AplicationRoundsServiceImpl implements AplicationRoundsService {



    private final List<Round> roundsPlayed;
    private final AplicationUserService aplicationUserService;
    private static final String USERNAME_NOT_FOUND = "User Name not found :";

    public AplicationRoundsServiceImpl(List<Round> roundsPlayed, AplicationUserService aplicationUserService) {
        this.roundsPlayed = roundsPlayed;
        this.aplicationUserService = aplicationUserService;
    }

    @Override
    public Round playRound(String userName, GameOption player1Option, GameOption player2Option) throws UserNotFoundException {

	Optional<User> currentUser = getUser(userName);
	if (!currentUser.isPresent()) {
	    throw new UserNotFoundException(USERNAME_NOT_FOUND + userName);
	}

        User userRound = currentUser.get();
	Round roundToCreate = createRound(player1Option,player2Option,userRound);
        roundsPlayed.add(roundToCreate);
	return roundToCreate;
    }

    @Override
    public List<Round> getUserGames(String userName) throws UserNotFoundException {

        Optional<User> currentUser = getUser(userName);
	if (!currentUser.isPresent()) {
	    throw new UserNotFoundException(USERNAME_NOT_FOUND + userName);
	}

	return roundsPlayed.stream()
		.filter(e-> e.getUser().equals(currentUser.get()))
		.filter(Round::getIsShowed)
		.collect(Collectors.toList());

    }

    @Override
    public void resetUserGames(String userName) throws UserNotFoundException {

        Optional<User> currentUser = getUser(userName);
        if (!currentUser.isPresent()) {
            throw new UserNotFoundException(USERNAME_NOT_FOUND + userName);
	}
	roundsPlayed.stream()
		.filter(e-> e.getUser().equals(currentUser.get()))
		.filter(Round::getIsShowed)
		.forEach(currentRound -> {
		    			currentRound.setIsShowed(false);
		    			currentRound.setModifyAt(new Date());
					 }
		 );

    }


    @Override
    public List<Round> getAllGamesByResuls(final GameResult resultFilter) {

        if (GameResult.ALL.equals(resultFilter))
            return roundsPlayed;

        return roundsPlayed.stream()
		.filter(e->e.getResult().equals(resultFilter))
		.collect(Collectors.toList());

    }

    // PRIVATE METHODS

    private Optional<User> getUser(String userName)  {
	return aplicationUserService.findByUserName(userName);
    }

    private Round createRound(final GameOption player1, final GameOption player2, final User user) {

	return Round.builder()
		.player1(player1)
		.player2(player2)
		.user(user)
		.isShowed(true)
		.createdAt(new Date())
		.result(resultRound(player1, player2))
		.build();

    }

    private GameResult resultRound(GameOption player1, GameOption player2) {

        GameResult result = null;

        if (player1.equals(player2)) {
            result = GameResult.DRAW;
	} else if (player1.equals(GameOption.ROCK)) {
	    result = player2.equals(GameOption.SCISSOR) ? GameResult.PLAYER1_WINS : GameResult.PLAYER2_WINS;
	} else if (player1.equals(GameOption.PAPER)) {
	    result = player2.equals(GameOption.ROCK)? GameResult.PLAYER1_WINS : GameResult.PLAYER2_WINS;
	} else if (player1.equals(GameOption.SCISSOR)) {
	    result = player2.equals(GameOption.PAPER)? GameResult.PLAYER1_WINS : GameResult.PLAYER2_WINS;
	}

	return result;
    }
}
