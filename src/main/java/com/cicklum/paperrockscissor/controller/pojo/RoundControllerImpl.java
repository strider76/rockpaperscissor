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

@RestController
@Validated
public class RoundControllerImpl implements RoundController {

    @Value("${spring.app.jwtSecret}")
    private String SIGNINGKEY;

    private final RoundServiceFacade roundServiceFacade;


    public RoundControllerImpl(RoundServiceFacade roundServiceFacade) {
        this.roundServiceFacade = roundServiceFacade;
    }


    @Override
    public PlayRoundResponseDto createPlayRound(@Valid PlayRoundDtoPost playRound, @RequestHeader("Authorization") String token) throws UserNotFoundException {

	return roundServiceFacade.playUserRound(getUserName(token),playRound);

    }

    @Override
    public CurrentGameDto getUserSumary(@RequestHeader("Authorization") String token) throws UserNotFoundException {

        return roundServiceFacade.sumaryUserRounds(getUserName(token));
    }

    @Override
    public void restUserRounds(@RequestHeader("Authorization") String token) throws UserNotFoundException {
	roundServiceFacade.resetUserGames(getUserName(token));
    }

    @Override
    public SumaryGeneralDto getGeneralSumary() {
        return roundServiceFacade.getAllGames();
    }

    private String getUserName(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNINGKEY)
                .parseClaimsJws(token.replace("Bearer ",""))
                .getBody()
                .getSubject();
    }
}
