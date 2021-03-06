package com.epam.training.sportsbetting.domain.wager;

import com.epam.training.sportsbetting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import com.epam.training.sportsbetting.domain.user.Player;

import java.time.LocalDateTime;
import java.util.Objects;

public class Wager {
    private Player player;
    private SportEvent event;
    private OutcomeOdd outcomeOdd;
    private double amount;
    private Currency currency;
    private LocalDateTime timestamp;
    private boolean isProcessed;
    private boolean isWinning;

    public Wager(Player player, SportEvent event, OutcomeOdd outcomeOdd, double amount, Currency currency,
                 LocalDateTime timestamp) {
        this.player = player;
        this.event = event;
        this.outcomeOdd = outcomeOdd;
        this.amount = amount;
        this.currency = currency;
        this.timestamp = timestamp;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public OutcomeOdd getOutcomeOdd() {
        return outcomeOdd;
    }

    public void setOutcomeOdd(OutcomeOdd outcomeOdd) {
        this.outcomeOdd = outcomeOdd;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public boolean isWinning() {
        return isWinning;
    }

    public void setWinning(boolean winning) {
        isWinning = winning;
    }

    public SportEvent getEvent() {
        return event;
    }

    public void setEvent(SportEvent event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wager wager = (Wager) o;
        return Double.compare(wager.amount, amount) == 0 &&
                Objects.equals(event, wager.event) &&
                Objects.equals(outcomeOdd, wager.outcomeOdd) &&
                currency == wager.currency;
    }

    @Override
    public int hashCode() {

        return Objects.hash(event, outcomeOdd, amount, currency);
    }
}
