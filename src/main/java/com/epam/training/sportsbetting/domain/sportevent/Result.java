package com.epam.training.sportsbetting.domain.sportevent;

import com.epam.training.sportsbetting.domain.outcome.Outcome;

import java.util.List;

public class Result {
    private SportEvent sportEvent;
    private List<Outcome> outcomes;

    public Result(SportEvent sportEvent, List<Outcome> outcomes) {
        this.sportEvent = sportEvent;
        this.outcomes = outcomes;
    }

    public SportEvent getSportEvent() {
        return sportEvent;
    }

    public void setSportEvent(SportEvent sportEvent) {
        this.sportEvent = sportEvent;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(List<Outcome> outcomes) {
        this.outcomes = outcomes;
    }
}
