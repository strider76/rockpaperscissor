package com.cicklum.paperrockscissor.controller.poji;

import javax.validation.Valid;

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
import com.cicklum.paperrockscissor.service.dto.PlayRoundDtoPost;
import com.cicklum.paperrockscissor.service.dto.PlayRoundResponseDto;
import com.cicklum.paperrockscissor.service.dto.SumaryGeneralDto;

@RequestMapping("/round")
@CrossOrigin(origins = "*")
public interface RoundController {

    @PostMapping("/play")
    @ResponseStatus(HttpStatus.CREATED)
    PlayRoundResponseDto createPlayRound(@Valid @RequestBody PlayRoundDtoPost playRound, @RequestHeader("Authorization") String token) throws UserNotFoundException;

    @GetMapping("/sumary")
    @ResponseStatus(HttpStatus.OK)
    CurrentGameDto getUserSumary(@RequestHeader("Authorization") String token) throws UserNotFoundException;

    @PutMapping("/reset")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void resetUserRounds(@RequestHeader("Authorization") String token) throws UserNotFoundException;

    @GetMapping("/sumary/general")
    @ResponseStatus(HttpStatus.OK)
    SumaryGeneralDto getGeneralSumary();

}
