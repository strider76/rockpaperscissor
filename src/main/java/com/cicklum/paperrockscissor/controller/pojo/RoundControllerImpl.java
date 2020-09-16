package com.cicklum.paperrockscissor.controller.pojo;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
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

    private final RoundServiceFacade roundServiceFacade;

    public RoundControllerImpl(RoundServiceFacade roundServiceFacade) {
        this.roundServiceFacade = roundServiceFacade;
    }


    @Override
    public PlayRoundResponseDto createPlayRound(@Valid PlayRoundDtoPost playRound) throws UserNotFoundException {

	return roundServiceFacade.playUserRound("manolo",playRound);

    }

    @Override
    public CurrentGameDto getUserSumary() throws UserNotFoundException {

        return roundServiceFacade.sumaryUserRounds("manolo");
    }

    @Override
    public void restUserRounds() throws UserNotFoundException {
	roundServiceFacade.resetUserGames("manolo");
    }

    @Override
    public SumaryGeneralDto getGeneralSumary() {
        return roundServiceFacade.getAllGames();
    }

}
