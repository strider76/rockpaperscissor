package com.cicklum.paperrockscissor.controller.pojo;

import javax.validation.Valid;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cicklum.paperrockscissor.controller.poji.RoundController;
import com.cicklum.paperrockscissor.exception.UserNotFoundException;
import com.cicklum.paperrockscissor.service.dto.CurrentGameDto;
import com.cicklum.paperrockscissor.service.dto.PlayRoundDtoPost;
import com.cicklum.paperrockscissor.service.dto.PlayRoundResponseDto;
import com.cicklum.paperrockscissor.service.dto.SumaryGeneralDto;
import com.cicklum.paperrockscissor.service.facade.poji.RoundServiceFacade;

/**
 * Round Controller - function over Rounds, all endpoints must be autheticathed previously with /login GET
 * @author manuel.millan
 */
@RestController
@Validated
public class RoundControllerImpl implements RoundController {

    @Value("${spring.app.jwtSecret}")
    private String signingkey;

    private final RoundServiceFacade roundServiceFacade;


    public RoundControllerImpl(RoundServiceFacade roundServiceFacade) {
        this.roundServiceFacade = roundServiceFacade;
    }

    /**
     * Play a Round by Authenticated user ( passing JWT obtained in /login GET)
     * @param playRound Moves of Player1 and Player2 (values must be "[ROCK|PAPER|SCISSOR]")
     * @param token user logged
     * @return PlayRoundResponseDTO with Player1Player2's moves and final result [PLAYER1_WINS|PLAYER2_WINs|DRAW]
     * @throws UserNotFoundException if user Not in userList
     */
    @Override
    public PlayRoundResponseDto createPlayRound(@Valid PlayRoundDtoPost playRound, @RequestHeader("Authorization") String token) throws UserNotFoundException {

	return roundServiceFacade.playUserRound(getUserName(token),playRound);

    }

    /**
     * Get User visible summary ,
     * @param token user logged
     * @return CurrentGameDto - Total Rounds and round's list order desc by date
     * @throws UserNotFoundException user dont exist in userlist bean
     */
    @Override
    public CurrentGameDto getUserSumary(@RequestHeader("Authorization") String token) throws UserNotFoundException {

        return roundServiceFacade.sumaryUserRounds(getUserName(token));
    }

    /**
     * Update all round to invisible, so theses rounds doesnt apper in getUserSumary
     * @param token user logged
     * @throws UserNotFoundException UserNotFoundException user dont exist in userlist bean
     */
    @Override
    public void resetUserRounds(@RequestHeader("Authorization") String token) throws UserNotFoundException {
	roundServiceFacade.resetUserGames(getUserName(token));
    }

    /**
     * Get General summary used in second screen,
     * @return Sumary of all games , Player1 Wins, Player2 WINs and draws
     * (total played and show total Round)
     */
    @Override
    public SumaryGeneralDto getGeneralSumary() {
        return roundServiceFacade.getAllGames();
    }

    /**
     * private method to obtain username from JWT
     * @param token JWT from RequestHeader Authorization
     * @return userName
     */
    private String getUserName(String token) {
        return Jwts.parser()
                .setSigningKey(signingkey)
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody()
                .getSubject();
    }
}
