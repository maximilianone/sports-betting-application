package com.epam.training.sportsbetting.domain.outcome;

import java.time.LocalDateTime;

public class OutcomeOdd {
    private Outcome outcome;
    private double oddValue;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public double getOddValue() {
        return oddValue;
    }

    public void setOddValue(double oddValue) {
        this.oddValue = oddValue;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    public boolean coversTheDate(LocalDateTime dateTime) {
        return this.validFrom.isBefore(dateTime) && this.validTo.isAfter(dateTime);
    }
}
