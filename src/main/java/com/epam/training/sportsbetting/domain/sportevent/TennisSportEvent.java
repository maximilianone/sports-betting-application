package com.epam.training.sportsbetting.domain.sportevent;

import com.epam.training.sportsbetting.domain.bet.Bet;
import com.epam.training.sportsbetting.utils.Constants;

import java.time.LocalDateTime;
import java.util.List;

public class TennisSportEvent extends SportEvent {
    public TennisSportEvent(String title, LocalDateTime startDate, LocalDateTime endDate, List<Bet> bets) {
        super(title, startDate, endDate, Constants.TENNIS_TYPE, bets);
    }

    @Override
    public void setType(String type) {
    }
}
