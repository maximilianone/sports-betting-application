package com.epam.training.sportsbetting.domain.bet;

import com.epam.training.sportsbetting.domain.outcome.Outcome;
import com.epam.training.sportsbetting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import com.epam.training.sportsbetting.utils.Constants;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bet {
    private SportEvent sportEvent;
    private String description;
    private List<Outcome> outcomes;
    private TypeOfBet typeOfBet;

    public enum TypeOfBet {
        BETTING_FOR_GOALS(Constants.BETTING_FOR_GOALS_STRING),
        WINNER(Constants.WINNER_STRING),
        PLAYERS_SCORE(Constants.PLAYERS_SCORE_STRING);

        private String text;

        TypeOfBet(String text) {
            this.text = text;
        }

        public String getText(Object... args) {
            return String.format(text, args);
        }
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }

    public TypeOfBet getTypeOfBet() {
        return typeOfBet;
    }

    public void setTypeOfBet(TypeOfBet typeOfBet) {
        this.typeOfBet = typeOfBet;
    }
}
