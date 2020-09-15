package com.cicklum.paperrockscissor.model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *  Round class - Save a player's round , result , creation & update dates, or showed in the player's summary
 *
 * @author manuel.millan
 *
 */

@Getter
@Setter
@Builder
public class Round implements Comparable<Round> {

    private GameOption player1;
    private GameOption player2;
    private GameResult result;
    private Boolean isShowed;
    private User user;
    private Date createdAt;
    private Date modifyAt;

    @Override
    public int compareTo(Round o) {
        return - getCreatedAt().compareTo(o.createdAt);
    }

}
