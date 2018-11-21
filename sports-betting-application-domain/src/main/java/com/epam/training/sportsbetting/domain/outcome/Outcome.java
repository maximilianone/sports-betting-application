package com.epam.training.sportsbetting.domain.outcome;

import java.util.List;
import java.util.Objects;

public class Outcome {
    private String outcome;
    private List<OutcomeOdd> outcomeOdds;

    public Outcome(String outcome, List<OutcomeOdd> outcomeOdds) {
        this.outcome = outcome;
        this.outcomeOdds = outcomeOdds;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public List<OutcomeOdd> getOutcomeOdds() {
        return outcomeOdds;
    }

    public void setOutcomeOdds(List<OutcomeOdd> outcomeOdds) {
        this.outcomeOdds = outcomeOdds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Outcome outcome1 = (Outcome) o;
        return Objects.equals(outcome, outcome1.outcome) &&
                Objects.equals(outcomeOdds, outcome1.outcomeOdds);
    }

    @Override
    public int hashCode() {

        return Objects.hash(outcome, outcomeOdds);
    }
}
