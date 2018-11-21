package com.epam.training.sportsbetting.utils;

public interface Constants {
    String FOOTBALL_TYPE = "football";
    String TENNIS_TYPE = "tennis";

    String SPORT_EVENTS_FILEPATH = "/sport_events.json";

    String FILE_NOT_FOUND = "File with sport events not found";
    String INVALID_INPUT = "Invalid input";
    String INVALID_INPUT_MONEY = "Invalid input or more then you have";
    String NO_OUTCOME_ODDS = "Sorry, no valid outcome odds fot this event";

    String QUIT_INPUT = "q";

    String DATE_TIME_PATTERN = "dd MMM yyyy HH:mm";
    String DATE_PATTERN = "dd MMM yyyy";
    String STRING_PATTERN = "[a-z A-Z0-9]+";
    String ACCOUNT_NUMBER_PATTERN = "^[0-9]+(\\-[0-9]+)*$";
    String EVENT_TYPE_PATTERN = "^(football|tennis)$";
    String CURRENCY_PATTERN = "###,###.## ¤";

    String REQUEST_BIRTH_DATE = "When were you born? eg.:02 Oct 1993";
    String REQUEST_NAME = "Hi, what is your name?";
    String REQUEST_ACCOUNT_NUMBER = "What is your account number? (e.g. 5464545-5165165)";
    String REQUEST_BALANCE_AMMOUNT = "How much money do you have (more than 0)?";
    String REQUEST_CURRENCY = "What is your currency? (UAH, EUR or USD)";
    String REQUEST_OUTCOME = "Please choose an outcome to bet on! (choose a number or enter q for quit)";
    String REQUEST_EVENT = "Please choose an event to bet on! (choose a number or enter q for quit)";
    String REQUEST_MONEY_AMMOUNT = "How much do you want to bet on it? (q for quit)";
    String REQUEST_EVENT_TYPE = "On what type of sport event you want to bet? (football, tennis)";

    String WELCOME_MESSAGE = "Welcome, %s!%n";
    String BALANCE_MESSAGE = "Your balance is %s%n";
    String RESULTS_MESSAGE = "Results:";
    String PRIZE_MESSAGE = "You have won %s%n";
    String GOODBYE_MESSAGE = "See you next time!";

    String LIST_ELEMENT = "%d. %s%n";

    String BET_STRING_REPRESENTATION = "%d: Bet on the %s sport event, %s. The odd on this is %.2f valid from %s to %s%n";

    String DRAW = "draw";
    String BETTING_FOR_GOALS_STRING = "the number of scored goals will be %s";
    String WINNER_STRING = "the winner will be %s";
    String PLAYERS_SCORE_STRING = "%s will be scored by %s";
}
