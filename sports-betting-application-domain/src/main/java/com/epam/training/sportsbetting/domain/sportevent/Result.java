package com.epam.training.sportsbetting.domain.sportevent;

import com.epam.training.sportsbetting.domain.outcome.Outcome;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(sportEvent, result.sportEvent) &&
                Objects.equals(outcomes, result.outcomes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sportEvent, outcomes);
    }
}
