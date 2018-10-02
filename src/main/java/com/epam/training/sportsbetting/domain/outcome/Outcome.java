package com.epam.training.sportsbetting.domain.outcome;

import java.util.List;

public class Outcome {
    private String outcome;
    private List<OutcomeOdd> outcomeOdds;

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
}
