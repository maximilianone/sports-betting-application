package com.epam.training.sportsbetting.domain.sportevent;

import com.epam.training.sportsbetting.domain.bet.Bet;
import com.epam.training.sportsbetting.utils.Constants;

import java.time.LocalDate;
import java.util.List;

public class TennisSportEvent extends SportEvent {
    public TennisSportEvent(String title, LocalDate startDate, LocalDate endDate, List<Bet> bets) {
        super(title, startDate, endDate, Constants.TENNIS_TYPE, bets);
    }

    @Override
    public void setType(String type) {
    }
}
