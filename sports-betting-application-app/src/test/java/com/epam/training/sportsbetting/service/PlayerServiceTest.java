package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.outcome.OutcomeOdd;
import com.epam.training.sportsbetting.domain.sportevent.SportEvent;
import com.epam.training.sportsbetting.domain.user.Player;
import com.epam.training.sportsbetting.exceptions.ExitException;
import com.epam.training.sportsbetting.ui.BettingApplicationView;
import com.epam.training.sportsbetting.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;

import static com.epam.training.sportsbetting.utils.Constants.NO_OUTCOME_ODDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    private static final double BET = 20d;
    private static final double LARGE_BET = 100d;
    private static final double NEW_BALANCE = 80d;
    private static final int TEST_INT = 1;
    private static final double TEST_DOUBLE = 1d;

    private Player player = TestUtils.getPlayer();
    private List<SportEvent> sportEventList = TestUtils.getTestSportEventList();
    private List<OutcomeOdd> outcomeOdds = sportEventList.get(0).getBets().get(0).getOutcomes().get(0).getOutcomeOdds();

    private final Answer answer = new Answer() {
        private int count = 0;

        public Object answer(InvocationOnMock invocation) throws Exception {
            if (count++ == 1)
                throw new ExitException(ExitException.OK);

            return Constants.TENNIS_TYPE;
        }
    };

    @Mock
    private BettingApplicationView view;

    @Mock
    private SportEventService sportEventService;

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private InputService inputService;

    @Test
    void shouldProcessPlayerBalance() throws Exception {
        Player player = TestUtils.getPlayer();

        playerService.processPlayerBalance(player, BET);

        Assertions.assertEquals(NEW_BALANCE, player.getBalance());
    }

    @Test
    void shouldThrowExitException() throws Exception {
        Player player = TestUtils.getPlayer();

        assertThrows(ExitException.class, () -> playerService.processPlayerBalance(player, LARGE_BET));
    }

    @Test
    void shouldDisplayMessages() throws Exception {
        stabMocks();

        playerService.inputWager(sportEventList, player);

        verify(view, Mockito.times(1)).displayList(any());
        verify(view, Mockito.times(1)).displayFormattedMessage(anyString(), anyString());
    }

    @Test
    void shouldDisplayOutcomes() throws Exception {
        stabMocks();

        playerService.inputWager(sportEventList, player);

        verify(sportEventService, Mockito.times(1)).showOutcomes(any(), any());
    }

    @Test
    void shouldReturnWagerList() throws Exception {
        stabMocks();

        assertEquals(TestUtils.getWagers(sportEventList.get(0)), playerService.inputWager(sportEventList, player));
    }

    @Test
    void shouldDisplayNoEventsMessage() throws Exception {
        when(inputService.readPositiveInt(anyString(), anyString(), anyInt())).thenReturn(TEST_INT);
        when(sportEventService.getEventsOfType(any(), anyString())).thenReturn(sportEventList);
        when(inputService.readString(anyString(), anyString(), anyString())).thenAnswer(answer);

        assertThrows(ExitException.class, () -> playerService.inputWager(sportEventList, player));
        verify(view, Mockito.times(1)).displayMessage(NO_OUTCOME_ODDS);
    }

    private void stabMocks() throws Exception {
        when(inputService.readPositiveInt(anyString(), anyString(), anyInt())).thenReturn(TEST_INT);
        when(inputService.readPositiveDouble(anyString(), anyString(), anyDouble())).thenReturn(TEST_DOUBLE);
        when(sportEventService.getEventsOfType(any(), anyString())).thenReturn(sportEventList);
        when(sportEventService.showOutcomes(any(), any())).thenReturn(outcomeOdds);
        when(inputService.readString(anyString(), anyString(), anyString())).thenAnswer(answer);
    }
}