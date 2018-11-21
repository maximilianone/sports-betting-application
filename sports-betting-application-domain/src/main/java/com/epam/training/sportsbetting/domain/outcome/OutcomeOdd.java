package com.epam.training.sportsbetting.domain.outcome;

import java.time.LocalDateTime;
import java.util.Objects;

public class OutcomeOdd {
    private Outcome outcome;
    private double oddValue;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

    public OutcomeOdd(double oddValue, LocalDateTime validFrom, LocalDateTime validTo) {
        this.oddValue = oddValue;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

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
        return this.validFrom.isBefore(dateTime) && this.validTo.isAfter(dateTime)
                || this.validFrom.equals(dateTime)
                || this.validTo.equals(dateTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutcomeOdd that = (OutcomeOdd) o;
        return Double.compare(that.oddValue, oddValue) == 0 &&
                Objects.equals(outcome, that.outcome) &&
                Objects.equals(validFrom, that.validFrom) &&
                Objects.equals(validTo, that.validTo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(outcome, oddValue, validFrom, validTo);
    }
}
