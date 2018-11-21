package com.epam.training.sportsbetting;

import com.epam.training.sportsbetting.domain.sportevent.Result;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import com.epam.training.sportsbetting.domain.user.Player;
import com.epam.training.sportsbetting.domain.wager.Wager;
import com.epam.training.sportsbetting.exceptions.ExitException;
import com.epam.training.sportsbetting.service.PlayerService;
import com.epam.training.sportsbetting.service.SportEventService;
import com.epam.training.sportsbetting.ui.BettingApplicationView;
import com.epam.training.sportsbetting.utils.Constants;

import java.util.List;

public class App {
    private BettingApplicationView view;
    private SportEventService sportEventService;
    private PlayerService playerService;

    private App(BettingApplicationView view, SportEventService sportEventService, PlayerService playerService) {
        this.view = view;
        this.sportEventService = sportEventService;
        this.playerService = playerService;
    }

    public static void main(String[] args) {
        BettingApplicationView view = new BettingApplicationView();
        SportEventService sportEventService = new SportEventService(view);
        PlayerService playerService = new PlayerService(view, sportEventService);

        App app = new App(view, sportEventService, playerService);
        app.run();
    }

    private void run() {
        List<SportEvent> list = sportEventService.getSportEventsFromJSON(Constants.SPORT_EVENTS_FILEPATH);
        try {
            Player player = playerService.inputPlayer();
            view.displayFormattedMessage(Constants.WELCOME_MESSAGE, player.getName());
            view.displayFormattedMessage(Constants.BALANCE_MESSAGE, player.getCurrency().format(player.getBalance()));

            List<Wager> wagerList = playerService.inputWager(list, player);

            List<Result> results = sportEventService.getResults(list);

            sportEventService.processWagers(wagerList, results);
            String prize = sportEventService.computePrize(wagerList);
            view.displayMessage(Constants.RESULTS_MESSAGE);
            view.displayFormattedMessage(Constants.PRIZE_MESSAGE, prize);
        } catch (ExitException e) {
            view.displayMessage(Constants.GOODBYE_MESSAGE);
        }
    }


}
