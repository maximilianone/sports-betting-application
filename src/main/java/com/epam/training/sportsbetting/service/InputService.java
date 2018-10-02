package com.epam.training.sportsbetting.ui;

import com.epam.training.sportsbetting.domain.wager.Currency;
import com.epam.training.sportsbetting.utils.Constants;
import com.sun.javaws.exceptions.ExitException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Function;

public class InputService {
    private static Scanner scanner = new Scanner(System.in);

    private BettingApplicationView view;

    public InputService(BettingApplicationView view) {
        this.view = view;
    }

    private <R> R readInput(String question, String invalidMessage, Function<String, R> validator)
            throws ExitException {
        boolean correctInput = false;
        String input;
        R result = null;

        while (!correctInput) {
            try {
                view.displayMessage(question);
                input = scanner.nextLine();
                if (input.equals(Constants.QUIT_INPUT)) {
                    throw new ExitException(new Throwable(), ExitException.OK);
                }
                result = validator.apply(input);
                correctInput = true;
            } catch (ExitException e) {
                throw new ExitException(new Throwable(), e.getReason());
            } catch (Exception e) {
                view.displayMessage(invalidMessage);
            }
        }

        return result;
    }

    public LocalDate readDateInput(String message, String invalidMessage) throws ExitException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
        return readInput(message, invalidMessage, a -> LocalDate.parse(a, formatter));
    }

    public String readString(String message, String invalidMessage, String regex) throws ExitException {
        return readInput(message, invalidMessage, a -> matchStringToRegex(a, regex));
    }

    public int readPositiveInt(String message, String invalidMessage, int max) throws ExitException {
        return readInput(message, invalidMessage, a -> {
            int index = Integer.parseUnsignedInt(a);
            if (index > max || index < 1) {
                throw new RuntimeException();
            }
            return index;
        });
    }

    public double readPositiveDouble(String message, String invalidMessage) throws ExitException {
        return readInput(message, invalidMessage, a -> {
            double result = Double.parseDouble(a);
            if (result <= 0 && String.valueOf(result).split("\\.")[1].length() > 2) {
                throw new RuntimeException();
            }
            return result;
        });
    }

    public double readPositiveDouble(String message, String invalidMessage, double max) throws ExitException {
        return readInput(message, invalidMessage, a -> {
            double result = Double.parseDouble(a);
            if (result <= 0 || result > max && String.valueOf(result).split("\\.")[1].length() > 2) {
                throw new RuntimeException();
            }
            return result;
        });
    }

    public Currency readCurrency(String message, String invalidMessage) throws ExitException {
        return readInput(message, invalidMessage, Currency::valueOf);
    }

    private String matchStringToRegex(String input, String regex) {
        if (input.matches(regex)) {
            return input;
        } else {
            throw new RuntimeException();
        }
    }
}
