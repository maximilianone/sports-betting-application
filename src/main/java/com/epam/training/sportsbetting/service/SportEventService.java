package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.outcome.Outcome;
import com.epam.training.sportsbetting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbetting.domain.sportevent.Result;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import com.epam.training.sportsbetting.domain.wager.Wager;
import com.epam.training.sportsbetting.ui.BettingApplicationView;
import com.epam.training.sportsbetting.utils.Constants;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class SportEventService {
    private JSONWriterReader writerReader = new JSONWriterReader();
    private BettingApplicationView view;

    public SportEventService(BettingApplicationView view) {
        this.view = view;
    }

    public List<SportEvent> getSportEventsFromJSON(String filepath) {
        List<SportEvent> sportEventList = new ArrayList<>();
        try {
            sportEventList = writerReader.read(filepath, SportEvent[].class);
            sportEventList.forEach(event -> event.getBets().forEach(bet -> {
                bet.setSportEvent(event);
                bet.getOutcomes().forEach(outcome ->
                        outcome.getOutcomeOdds().forEach(outcomeOdd ->
                                outcomeOdd.setOutcome(outcome)));
            }));
        } catch (FileNotFoundException e) {
            view.displayMessage(Constants.FILE_NOT_FOUND);
        }
        return sportEventList;
    }

    public List<OutcomeOdd> showOutcomes(SportEvent sportEvent, LocalDateTime dateTime) {
        List<OutcomeOdd> outcomes = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN);
        sportEvent.getBets().forEach(bet -> bet.getOutcomes().forEach(outcome -> {
            outcome.getOutcomeOdds().forEach(outcomeOdd -> {
                if (outcomeOdd.coversTheDate(dateTime)) {
                    outcomes.add(outcomeOdd);
                    view.displayFormattedMessage(
                            Constants.BET_STRING_REPRESENTATION,
                            outcomes.size(),
                            bet.getSportEvent().getTitle(),
                            outcome.getOutcome().equalsIgnoreCase(Constants.DRAW) ?
                                    Constants.DRAW :
                                    bet.getTypeOfBet().getText(outcome.getOutcome(), bet.getDescription()),
                            outcomeOdd.getOddValue(),
                            formatter.format(outcomeOdd.getValidFrom()),
                            formatter.format(outcomeOdd.getValidTo()));
                }
            });
        }));
        return outcomes;
    }

    public List<SportEvent> getEventsOfType(List<SportEvent> eventList, String type) {
        return eventList.stream().filter(a -> a.getType().equals(type)).collect(Collectors.toList());
    }

    public List<Result> getResults(List<SportEvent> events) {
        return events.stream().map(event -> {
            List<Outcome> outcomes = event.getBets().stream().map(bet -> bet.getOutcomes()
                    .get(ThreadLocalRandom.current().nextInt(0, bet.getOutcomes().size())))
                    .collect(Collectors.toList());
            return new Result(event.getBets().get(0).getSportEvent(), outcomes);
        }).collect(Collectors.toList());
    }

    public void processWagers(List<Wager> wagerList, List<Result> results) {
        wagerList.forEach(wager -> {
            Result result = results.stream().filter(a -> a.getSportEvent() == wager.getEvent()).findAny().get();
            wager.setWinning(result.getOutcomes().contains(wager.getOutcomeOdd().getOutcome()));
            wager.setProcessed(true);
        });
    }

    public String computePrize(List<Wager> wagerList) {
        double prize = 0;
        for (Wager wager : wagerList) {
            if (wager.isWinning()) {
                prize += wager.getAmount() * wager.getOutcomeOdd().getOddValue();
            }
        }
        return wagerList.get(0).getCurrency().format(prize);
    }
}
