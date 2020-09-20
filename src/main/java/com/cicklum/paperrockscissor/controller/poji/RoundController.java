package com.cicklum.paperrockscissor.controller.poji;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cicklum.paperrockscissor.exception.UserNotFoundException;
import com.cicklum.paperrockscissor.service.dto.CurrentGameDto;
import com.cicklum.paperrockscissor.service.dto.ErrorMessagesDto;
import com.cicklum.paperrockscissor.service.dto.PlayRoundDtoPost;
import com.cicklum.paperrockscissor.service.dto.PlayRoundResponseDto;
import com.cicklum.paperrockscissor.service.dto.SumaryGeneralDto;

@RequestMapping("/round")
@Api(tags = "Round")
@CrossOrigin(origins = "*")
public interface RoundController {

    @PostMapping("/play")
    @ApiOperation(value = "User Play Round", notes = "create a Json with the moves of player1 and player2, the response object give you the moves and result, you must enter the Authorization token obtained in /login")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = PlayRoundResponseDto.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorMessagesDto.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    PlayRoundResponseDto createPlayRound(@Valid @RequestBody PlayRoundDtoPost playRound, @RequestHeader("Authorization") String token) throws UserNotFoundException;

    @GetMapping("/sumary")
    @ApiOperation(value = "Get User Sumary", notes = "get User sumary used in the play rounds view, must be authenticated with authorization token in login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = CurrentGameDto.class),
            @ApiResponse(code = 400, message = "Bad Request", response = ErrorMessagesDto.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessagesDto.class)
    })
    @ResponseStatus(HttpStatus.OK)
    CurrentGameDto getUserSumary(@RequestHeader("Authorization") String token) throws UserNotFoundException;

    @PutMapping("/reset")
    @ApiOperation(value = "Reset user rounds", notes = "all user rounds updated hide, must be authenticated with authorization token in login")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = CurrentGameDto.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessagesDto.class)
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    void resetUserRounds(@RequestHeader("Authorization") String token) throws UserNotFoundException;

    @GetMapping("/sumary/general")
    @ApiOperation(value = "General Sumary", notes = "Get a general sumary with all rounds played, rounds win by player1, rounds win by player2, or draw rounds")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = SumaryGeneralDto.class),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorMessagesDto.class)
    })
    SumaryGeneralDto getGeneralSumary();

}
