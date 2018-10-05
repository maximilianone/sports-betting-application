package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.domain.wager.Currency;
import com.epam.training.sportsbetting.exceptions.ExitException;
import com.epam.training.sportsbetting.ui.BettingApplicationView;
import com.epam.training.sportsbetting.utils.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class InputServiceTypesTest {
    private static final LocalDate date = LocalDate.of(1993, Month.JANUARY, 1);
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

    @Before
    public void init() throws Exception {
        doAnswer(ANSWER).when(inputService).readInput(any(), any(), any(), any());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnParsedDate() throws Exception {
        inputService.readDateInput("", "");

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(date, functionCaptor.getValue().apply(DATE_STRING));
    }

    @Test(expected = DateTimeParseException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionWhenInvalidDate() throws Exception {
        inputService.readDateInput("", "");

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        functionCaptor.getValue().apply(TEST_STRING);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnString() throws Exception {
        inputService.readString("", "", Constants.STRING_PATTERN);

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(TEST_STRING, functionCaptor.getValue().apply(TEST_STRING));
    }

    @Test(expected = RuntimeException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionWhenInvalidString() throws Exception {
        inputService.readString("", "", Constants.STRING_PATTERN);

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        functionCaptor.getValue().apply(INVALID_STRING);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnParsedPositiveInt() throws Exception {
        inputService.readPositiveInt("", "", MAX);

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(VALID_INT, functionCaptor.getValue().apply(VALID_INT_STRING));
    }

    @Test(expected = NumberFormatException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionWhenInvalidInt() throws Exception {
        inputService.readPositiveInt("", "", MAX);

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        functionCaptor.getValue().apply(TEST_STRING);
    }

    @Test(expected = RuntimeException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionWhenGreaterThenMaxInt() throws Exception {
        inputService.readPositiveInt("", "", MAX);

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        functionCaptor.getValue().apply(INVALID_INT_STRING);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnParsedPositiveDouble() throws Exception {
        inputService.readPositiveDouble("", "");

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(VALID_DOUBLE, functionCaptor.getValue().apply(VALID_DOUBLE_STRING));
    }

    @Test(expected = RuntimeException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionWhenInvalidDouble() throws Exception {
        inputService.readPositiveDouble("", "");

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        functionCaptor.getValue().apply(TEST_STRING);
    }

    @Test(expected = RuntimeException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionWhenGreaterThenMax() throws Exception {
        inputService.readPositiveDouble("", "", MAX_DOUBLE);

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        functionCaptor.getValue().apply(INVALID_DOUBLE_STRING);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldReturnParsedCurrency() throws Exception {
        inputService.readCurrency("", "");

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        assertEquals(Currency.UAH, functionCaptor.getValue().apply(VALID_CURRENCY_STRING));
    }

    @Test(expected = IllegalArgumentException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExceptionWhenInvalidCurrency() throws Exception {
        inputService.readCurrency("", "");

        verify(inputService).readInput(any(), any(), any(), functionCaptor.capture());
        functionCaptor.getValue().apply(INVALID_CURRENCY_STRING);
    }
}