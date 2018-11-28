package com.epam.training.sportsbetting.domain.wager;

import com.epam.training.sportsbetting.utils.Constants;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public enum Currency {
    UAH,
    EUR,
    USD;

    private DecimalFormat format = new DecimalFormat(Constants.CURRENCY_PATTERN);

    public String format(double value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("uk", "UA"));
        symbols.setGroupingSeparator(' ');
        symbols.setCurrencySymbol(this.toString());
        format.setDecimalFormatSymbols(symbols);
        format.applyPattern("###,###.### ¤");
        return format.format(value);
    }
}
