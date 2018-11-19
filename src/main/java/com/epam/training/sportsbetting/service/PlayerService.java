package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import com.epam.training.sportsbetting.domain.user.Player;
import com.epam.training.sportsbetting.domain.wager.Wager;
import com.epam.training.sportsbetting.exceptions.ExitException;
import com.epam.training.sportsbetting.ui.BettingApplicationView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.epam.training.sportsbetting.utils.Constants.ACCOUNT_NUMBER_PATTERN;
import static com.epam.training.sportsbetting.utils.Constants.BALANCE_MESSAGE;
import static com.epam.training.sportsbetting.utils.Constants.EVENT_TYPE_PATTERN;
import static com.epam.training.sportsbetting.utils.Constants.INVALID_INPUT;
import static com.epam.training.sportsbetting.utils.Constants.INVALID_INPUT_MONEY;
import static com.epam.training.sportsbetting.utils.Constants.NO_OUTCOME_ODDS;
import static com.epam.training.sportsbetting.utils.Constants.REQUEST_ACCOUNT_NUMBER;
import static com.epam.training.sportsbetting.utils.Constants.REQUEST_BALANCE_AMMOUNT;
import static com.epam.training.sportsbetting.utils.Constants.REQUEST_BIRTH_DATE;
import static com.epam.training.sportsbetting.utils.Constants.REQUEST_CURRENCY;
import static com.epam.training.sportsbetting.utils.Constants.REQUEST_EVENT;
import static com.epam.training.sportsbetting.utils.Constants.REQUEST_EVENT_TYPE;
import static com.epam.training.sportsbetting.utils.Constants.REQUEST_MONEY_AMMOUNT;
import static com.epam.training.sportsbetting.utils.Constants.REQUEST_NAME;
import static com.epam.training.sportsbetting.utils.Constants.REQUEST_OUTCOME;
import static com.epam.training.sportsbetting.utils.Constants.STRING_PATTERN;

public class PlayerService {
    private BettingApplicationView view;
    private InputService inputService;
    private SportEventService sportEventService;

    public PlayerService(BettingApplicationView view, SportEventService sportEventService) {
        this.view = view;
        this.sportEventService = sportEventService;
        this.inputService = new InputService(view);
    }

    public List<Wager> inputWager(List<SportEvent> eventList, Player player) {
        List<Wager> wagerList = new ArrayList<>();

        try {
            while (true) {
                String eventType = inputService.readString(REQUEST_EVENT_TYPE, INVALID_INPUT, EVENT_TYPE_PATTERN);
                List<SportEvent> eventsOfType = sportEventService.getEventsOfType(eventList, eventType);
                view.displayList(eventsOfType);

                LocalDateTime now = LocalDateTime.now();
                int eventNumber = inputService.readPositiveInt(REQUEST_EVENT, INVALID_INPUT, eventsOfType.size()) - 1;
                List<OutcomeOdd> outcomeOdds = sportEventService.showOutcomes(eventsOfType.get(eventNumber), now);

                if (!outcomeOdds.isEmpty()) {
                    int betNumber = inputService.readPositiveInt(REQUEST_OUTCOME, INVALID_INPUT,
                            outcomeOdds.size()) - 1;
                    double moneyAmount = inputService.readPositiveDouble(REQUEST_MONEY_AMMOUNT, INVALID_INPUT_MONEY,
                            player.getBalance());
                    wagerList.add(new Wager(player, eventsOfType.get(eventNumber), outcomeOdds.get(betNumber),
                            moneyAmount, player.getCurrency(), now));

                    processPlayerBalance(player, moneyAmount);
                    view.displayFormattedMessage(BALANCE_MESSAGE, player.getCurrency().format(player.getBalance()));
                } else {
                    view.displayMessage(NO_OUTCOME_ODDS);
                }
            }
        } catch (ExitException ignored) {
        }
        return wagerList;
    }

    public Player inputPlayer() throws ExitException {
        Player player = new Player();

        player.setName(inputService.readString(REQUEST_NAME, INVALID_INPUT, STRING_PATTERN));
        player.setAccountNumber(inputService.readString(REQUEST_ACCOUNT_NUMBER, INVALID_INPUT, ACCOUNT_NUMBER_PATTERN));
        player.setBalance(inputService.readPositiveDouble(REQUEST_BALANCE_AMMOUNT, INVALID_INPUT));
        player.setCurrency(inputService.readCurrency(REQUEST_CURRENCY, INVALID_INPUT));
        player.setDateOfBirth(inputService.readDateInput(REQUEST_BIRTH_DATE, INVALID_INPUT));

        return player;
    }

    void processPlayerBalance(Player player, double moneyAmount) throws ExitException {
        double newBalance = player.getBalance() - moneyAmount;
        if (newBalance <= 1) throw new ExitException(ExitException.OK);
        player.setBalance(newBalance);
    }
}
