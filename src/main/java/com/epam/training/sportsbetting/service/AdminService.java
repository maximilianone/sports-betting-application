package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.outcome.Outcome;
import com.epam.training.sportsbetting.domain.outcome.OutcomeOdd;

import java.time.LocalDate;
import java.util.List;

public class AdminService {
    public boolean modifyOutcomeOdd(Outcome outcome, double newOddValue, LocalDate validFrom, LocalDate validTo) {
        return false;
    }

    public boolean modifyOutcomeOdd(OutcomeOdd outcomeOdd, double newOddValue) {
        return false;
    }

    public boolean changeOdds(Outcome outcome, List<Outcome> outcomeList) {
        return false;
    }

    public boolean addNevOutcomeOdd(Outcome outcome, OutcomeOdd outcomeOdd) {
        return false;
    }

}
