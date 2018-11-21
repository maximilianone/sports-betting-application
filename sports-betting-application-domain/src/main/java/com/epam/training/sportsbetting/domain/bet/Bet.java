package com.epam.training.sportsbetting.domain.bet;

import com.epam.training.sportsbetting.domain.outcome.Outcome;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import com.epam.training.sportsbetting.utils.Constants;

import java.util.List;
import java.util.Objects;

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

    public Bet(String description, List<Outcome> outcomes, TypeOfBet typeOfBet) {
        this.description = description;
        this.outcomes = outcomes;
        this.typeOfBet = typeOfBet;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bet bet = (Bet) o;
        return Objects.equals(sportEvent, bet.sportEvent) &&
                Objects.equals(description, bet.description) &&
                Objects.equals(outcomes, bet.outcomes) &&
                typeOfBet == bet.typeOfBet;
    }

    @Override
    public int hashCode() {

        return Objects.hash(sportEvent, description, outcomes, typeOfBet);
    }
}
