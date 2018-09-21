package com.epam.training.sportsbetting.domain.wager;

import com.epam.training.sportsbetting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbetting.domain.user.Player;

import java.time.LocalDate;

public class Wager {
    private Player player;
    private OutcomeOdd outcomeOdd;
    private double amount;
    private Currency currency;
    private LocalDate timestamp;
    private boolean isProcessed;
    private boolean isWinnig;
}
