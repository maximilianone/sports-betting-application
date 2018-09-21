package com.epam.training.sportsbetting.domain.sportevent;

import com.epam.training.sportsbetting.domain.bet.Bet;

import java.time.LocalDate;
import java.util.List;

public class SportEvent {
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;
    private List<Bet> bets;

    public SportEvent(String title, LocalDate startDate, LocalDate endDate, String type, List<Bet> bets) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.bets = bets;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setBets(List<Bet> bets) {
        this.bets = bets;
    }
}
