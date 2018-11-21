package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbetting.domain.sportevent.Result;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import com.epam.training.sportsbetting.domain.wager.Wager;
import com.epam.training.sportsbetting.ui.BettingApplicationView;
import com.epam.training.sportsbetting.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SportEventServiceTest {
    private static final String PRIZE = "2,5 UAH";
    private List<SportEvent> sportEventList = TestUtils.getTestSportEventList();

    @Mock
    private BettingApplicationView view;

    @InjectMocks
    private SportEventService sportEventService;

    @Test
    void shouldShowOutcomes() {
        SportEvent sportEvent = sportEventList.get(0);
        sportEvent.getBets().get(0).setSportEvent(sportEvent);

        sportEventService.showOutcomes(sportEvent, TestUtils.getDateTime());

        verify(view, times(1)).displayFormattedMessage(any(), any(), any(),
                any(), any(), any(), any());
    }

    @Test
    void shouldReturnOutcomes() {
        SportEvent sportEvent = sportEventList.get(0);
        sportEvent.getBets().get(0).setSportEvent(sportEvent);
        List<OutcomeOdd> outcomeOddList = sportEvent.getBets().get(0).getOutcomes().get(0).getOutcomeOdds();

        assertEquals(outcomeOddList, sportEventService.showOutcomes(sportEvent, TestUtils.getDateTime()));
    }

    @Test
    void shouldGetEventsOfType() {
        assertEquals(sportEventList, sportEventService.getEventsOfType(sportEventList, Constants.TENNIS_TYPE));
        assertTrue(sportEventService.getEventsOfType(sportEventList, Constants.FOOTBALL_TYPE).isEmpty());
    }

    @Test
    void shouldGetResults() {
        sportEventList.get(0).getBets().get(0).setSportEvent(sportEventList.get(0));

        assertEquals(TestUtils.getResults(sportEventList.get(0)), sportEventService.getResults(sportEventList));
    }

    @Test
    void shouldProcessWagers() {
        SportEvent sportEvent = sportEventList.get(0);
        List<Wager> wagerList = TestUtils.getWagers(sportEvent);
        List<Result> resultList = TestUtils.getResults(sportEvent);

        sportEventService.processWagers(wagerList, resultList);

        assertTrue(wagerList.get(0).isWinning());
        assertTrue(wagerList.get(0).isProcessed());
    }

    @Test
    void shouldComputePrize() {
        SportEvent sportEvent = sportEventList.get(0);
        List<Wager> wagerList = TestUtils.getWagers(sportEvent);
        wagerList.get(0).setWinning(true);

        assertEquals(PRIZE, sportEventService.computePrize(wagerList));
    }
}