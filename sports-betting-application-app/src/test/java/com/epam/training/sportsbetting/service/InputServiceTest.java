package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.wager.Currency;
import com.epam.training.sportsbetting.exceptions.ExitException;
import com.epam.training.sportsbetting.ui.BettingApplicationView;
import com.epam.training.sportsbetting.utils.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class InputServiceTest {
    private static final LocalDate DATE = LocalDate.of(1993, Month.JANUARY, 1);
    private static final String DATE_STRING = "01 Jan 1993";
    private static final String TEST_STRING = "test string";
    private static final String INVALID_STRING = ")(*#$^&@";
    private static final int MAX = 10;
    private static final int VALID_INT = 7;
    private static final String VALID_INT_STRING = "7";
    private static final String INVALID_INT_STRING = "11";
    private static final double MAX_DOUBLE = 10.0d;
    private static final double VALID_DOUBLE = 7.0d;
    private static final String VALID_DOUBLE_STRING = "7";
    private static final String INVALID_DOUBLE_STRING = "11";
    private static final String VALID_CURRENCY_STRING = "UAH";
    private static final String INVALID_CURRENCY_STRING = "RUB";

    private static final Answer ANSWER = invocationOnMock -> {
        Object[] args = invocationOnMock.getArguments();
        args[0] = new Scanner(TEST_STRING);
        return null;
    };

    @Mock
    private BettingApplicationView view;

    @Spy
    @InjectMocks
    private InputService inputService;

    @Captor
    private ArgumentCaptor<Function> functionCaptor;

    @Mock
    private Function function;

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowExitException() throws Exception {
        assertThrows(ExitException.class, () ->
                inputService.readInput(new Scanner(Constants.QUIT_INPUT), "", "", function));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnParsedDate() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        inputService.readDateInput("", "");

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(DATE, functionCaptor.getValue().apply(DATE_STRING));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowExceptionWhenInvalidDate() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        assertThrows(DateTimeParseException.class, () -> {
            inputService.readDateInput("", "");

            verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
            functionCaptor.getValue().apply(TEST_STRING);
        });


    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnString() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        inputService.readString("", "", Constants.STRING_PATTERN);

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(TEST_STRING, functionCaptor.getValue().apply(TEST_STRING));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowExceptionWhenInvalidString() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        assertThrows(IllegalArgumentException.class, () -> {
            inputService.readString("", "", Constants.STRING_PATTERN);

            verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
            functionCaptor.getValue().apply(INVALID_STRING);
        });


    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnParsedPositiveInt() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        inputService.readPositiveInt("", "", MAX);

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(VALID_INT, functionCaptor.getValue().apply(VALID_INT_STRING));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowExceptionWhenInvalidInt() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        assertThrows(NumberFormatException.class, () -> {
            inputService.readPositiveInt("", "", MAX);

            verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
            functionCaptor.getValue().apply(TEST_STRING);
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowExceptionWhenGreaterThenMaxInt() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        assertThrows(IllegalArgumentException.class, () -> {
            inputService.readPositiveInt("", "", MAX);

            verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
            functionCaptor.getValue().apply(INVALID_INT_STRING);
        });


    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnParsedPositiveDouble() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        inputService.readPositiveDouble("", "");

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(VALID_DOUBLE, functionCaptor.getValue().apply(VALID_DOUBLE_STRING));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowExceptionWhenInvalidDouble() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        assertThrows(IllegalArgumentException.class, () -> {
            inputService.readPositiveDouble("", "");

            verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
            functionCaptor.getValue().apply(TEST_STRING);
        });


    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowExceptionWhenGreaterThenMax() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        assertThrows(IllegalArgumentException.class, () -> {
            inputService.readPositiveDouble("", "", MAX_DOUBLE);

            verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
            functionCaptor.getValue().apply(INVALID_DOUBLE_STRING);
        });
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldReturnParsedCurrency() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        inputService.readCurrency("", "");

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(Currency.UAH, functionCaptor.getValue().apply(VALID_CURRENCY_STRING));
    }

    @Test
    @SuppressWarnings("unchecked")
    void shouldThrowExceptionWhenInvalidCurrency() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());

        assertThrows(IllegalArgumentException.class, () -> {
            inputService.readCurrency("", "");

            verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
            functionCaptor.getValue().apply(INVALID_CURRENCY_STRING);
        });


    }
}