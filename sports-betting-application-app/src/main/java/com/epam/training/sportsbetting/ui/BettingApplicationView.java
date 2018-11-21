package com.epam.training.sportsbetting.ui;


import com.epam.training.sportsbetting.utils.Constants;

import java.util.List;

public class BettingApplicationView {
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayFormattedMessage(String message, Object... args) {
        System.out.printf(message, args);
    }

    public <T> void displayList(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            displayFormattedMessage(Constants.LIST_ELEMENT, i + 1, list.get(i).toString());
        }
    }
}
