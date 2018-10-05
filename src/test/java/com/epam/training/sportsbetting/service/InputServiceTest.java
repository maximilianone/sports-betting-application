package com.epam.training.sportsbetting.service;

import com.epam.training.sportsbetting.exceptions.ExitException;
import com.epam.training.sportsbetting.ui.BettingApplicationView;
import com.epam.training.sportsbetting.utils.Constants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Scanner;
import java.util.function.Function;

@RunWith(MockitoJUnitRunner.class)
public class InputServiceTest {
    @Mock
    private BettingApplicationView view;

    @InjectMocks
    private InputService inputService;

    @Mock
    private Function function;

    @Test(expected = ExitException.class)
    @SuppressWarnings("unchecked")
    public void shouldThrowExitException() throws Exception {
        inputService.readInput(new Scanner(Constants.QUIT_INPUT), "", "", function);
    }
}
