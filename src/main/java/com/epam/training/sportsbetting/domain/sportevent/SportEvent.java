package com.epam.training.sportsbetting.domain.sportevent;

import com.epam.training.sportsbetting.domain.bet.Bet;
import com.epam.training.sportsbetting.utils.Constants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SportEvent {
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String type;
    private List<Bet> bets;

    public SportEvent(String title, LocalDateTime startDate, LocalDateTime endDate, String type, List<Bet> bets) {
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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

    @Override
    public String toString() {
        return "SportEvent:" +
                " " + title +
                " from:" + DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN).format(startDate) +
                " to:" + DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN).format(endDate);
    }
}
