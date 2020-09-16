package com.cicklum.paperrockscissor.service.facade.poji;

import com.cicklum.paperrockscissor.exception.UserNotFoundException;
import com.cicklum.paperrockscissor.service.dto.CurrentGameDto;
import com.cicklum.paperrockscissor.service.dto.PlayRoundDtoPost;
import com.cicklum.paperrockscissor.service.dto.PlayRoundResponseDto;
import com.cicklum.paperrockscissor.service.dto.SumaryGeneralDto;

public interface RoundServiceFacade {

    PlayRoundResponseDto playUserRound(final String userName, final PlayRoundDtoPost roundMoves) throws UserNotFoundException;

    CurrentGameDto sumaryUserRounds(final String userName) throws UserNotFoundException;

    void resetUserGames(final String userName) throws UserNotFoundException;

    SumaryGeneralDto getAllGames();


}
