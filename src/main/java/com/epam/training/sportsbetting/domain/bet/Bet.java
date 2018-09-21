package com.epam.training.sportsbetting.domain.bet;

import com.epam.training.sportsbetting.domain.outcome.Outcome;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;

import java.util.List;

public class Bet {
    private SportEvent sportEvent;
    private String description;
    private List<Outcome> outcomes;
    private TypeOfBet typeOfBet;

    private enum TypeOfBet{
        BETTING_FOR_GOALS,
        WINNER,
        PLAYERS_SCORE
    }

}
