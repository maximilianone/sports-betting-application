package com.epam.training.sportsbetting.domain.outcome;

import java.time.LocalDate;
import java.util.Objects;

public class OutcomeOdd {
    private Outcome outcome;
    private double oddValue;
    private LocalDate validFrom;
    private LocalDate validTo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutcomeOdd that = (OutcomeOdd) o;
        return Objects.equals(outcome, that.outcome) &&
                Objects.equals(validFrom, that.validFrom) &&
                Objects.equals(validTo, that.validTo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(outcome, validFrom, validTo);
    }

    private boolean overlaps(OutcomeOdd testedOdd) {
        boolean overlap = true;
        if (this.validFrom.isAfter(testedOdd.validTo) || this.validTo.isBefore(testedOdd.validFrom)) {
            overlap = false;
        }
        return overlap;
    }
}
