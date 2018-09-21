package com.epam.training.sportsbetting.domain.sportevent;


import java.time.LocalDate;
import java.util.List;

import com.epam.training.sportsbetting.domain.bet.Bet;
import com.epam.training.sportsbetting.utils.Constants;

public class FootballSportEvent extends SportEvent {
    public FootballSportEvent(String title, LocalDate startDate, LocalDate endDate, List<Bet> bets) {
        super(title, startDate, endDate, Constants.FOOTBALL_TYPE, bets);
    }

    @Override
    public void setType(String type) {
    }
}
