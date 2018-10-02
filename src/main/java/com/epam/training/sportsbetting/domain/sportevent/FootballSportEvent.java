package com.epam.training.sportsbetting.domain.sportevent;


import com.epam.training.sportsbetting.domain.bet.Bet;
import com.epam.training.sportsbetting.utils.Constants;

import java.time.LocalDateTime;
import java.util.List;

public class FootballSportEvent extends SportEvent {
    public FootballSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate, List<Bet> bets) {
        super(title, startDate, endDate, Constants.FOOTBALL_TYPE, bets);
    }

    @Override
    public void setType(String type) {
    }


}
