package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.bet.Bet;
import com.epam.training.sportsbetting.domain.outcome.Outcome;
import com.epam.training.sportsbetting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbetting.domain.sportevent.Result;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import com.epam.training.sportsbetting.domain.user.Player;
import com.epam.training.sportsbetting.domain.wager.Currency;
import com.epam.training.sportsbetting.domain.wager.Wager;
import com.epam.training.sportsbetting.utils.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {
    private static final String SPORT_EVENTS_TITLE = "Title";
    private static final String SPORT_EVENTS_DATE = "10 Oct 2018 19:00";
    private static final String SPORT_EVENTS_TYPE = "tennis";
    private static final String BET_DESCRIPTION = "-";
    private static final String OUTCOME = "Outcome";

    private static final double OUTCOME_ODD = 2.5d;
    private static final double AMOUNT = 1d;
    private static final double PLAYER_BALANCE = 100d;


    public static List<SportEvent> getTestSportEventList() {
        List<OutcomeOdd> outcomeOddList = new ArrayList<>();
        OutcomeOdd outcomeOdd = new OutcomeOdd(OUTCOME_ODD, getDateTime(), getDateTime());
        outcomeOddList.add(outcomeOdd);

        List<Outcome> outcomeList = new ArrayList<>();
        Outcome outcome = new Outcome(OUTCOME, outcomeOddList);
        outcomeList.add(outcome);

        List<Bet> bets = new ArrayList<>();
        Bet bet = new Bet(BET_DESCRIPTION, outcomeList, Bet.TypeOfBet.WINNER);
        bets.add(bet);

        List<SportEvent> sportEventList = new ArrayList<>();
        SportEvent sportEvent = new SportEvent(SPORT_EVENTS_TITLE, getDateTime(), getDateTime(),
                SPORT_EVENTS_TYPE, bets);
        sportEventList.add(sportEvent);

        return sportEventList;
    }

    public static LocalDateTime getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);
        return LocalDateTime.parse(SPORT_EVENTS_DATE, formatter);
    }

    public static List<Wager> getWagers(SportEvent sportEvent) {
        OutcomeOdd outcomeOdd = sportEvent.getBets().get(0).getOutcomes().get(0).getOutcomeOdds().get(0);
        List<Wager> wagers = new ArrayList<>();
        Wager wager = new Wager(new Player(), sportEvent, outcomeOdd, AMOUNT, Currency.UAH, getDateTime());
        wager.getOutcomeOdd().setOutcome(sportEvent.getBets().get(0).getOutcomes().get(0));
        wagers.add(wager);
        return wagers;
    }

    public static List<Result> getResults(SportEvent sportEvent) {
        List<Outcome> outcomeList = new ArrayList<>();
        outcomeList.add(sportEvent.getBets().get(0).getOutcomes().get(0));

        List<Result> resultList = new ArrayList<>();
        resultList.add(new Result(sportEvent, outcomeList));
        return resultList;
    }

    public static Player getPlayer() {
        Player player = new Player();
        player.setBalance(PLAYER_BALANCE);
        player.setCurrency(Currency.UAH);
        return player;
    }
}
